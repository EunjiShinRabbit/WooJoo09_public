package com.WooJoo09.controller;

import com.WooJoo09.dto.MemberDTO;
import com.WooJoo09.entity.Member;
import com.WooJoo09.service.EmailService;
import com.WooJoo09.service.MemberService;
import com.WooJoo09.service.sens_sms;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/developerkirby")
public class MemberController {

    // Service 로직 연결
    private final MemberService memberService;
    private final EmailService emailService;
    private final JwtController jwtController;

    public ResponseEntity<List<Member>> findMember() {
        return ResponseEntity.ok().body(memberService.findMember());
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(HttpServletResponse response, @RequestBody Map<String, String> loginData) throws Exception {
        String id = loginData.get("loginId");
        String pwd = loginData.get("loginPwd");
        if((boolean)memberService.loginService(id, pwd).get("login")){
            log.info("로그인 성공 해서 토큰을 발급");
            String token = (String)memberService.loginService(id, pwd).get("token");
            Cookie cookie = new Cookie("token", token); // 생성된 토큰을 cookie에 세팅
            cookie.setMaxAge(60 * 60); // 유효기간 60분으로 설정
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie); // 응답에 쿠키 추가
            return ResponseEntity.ok(true);
        }else return ResponseEntity.ok(false);
    }

    //회원가입
    @PostMapping("/memberinsert")
    public ResponseEntity<Boolean> registerMember(@RequestBody Map<String, String> regData) throws ParseException {
        String id = regData.get("regId");
        String pwd = regData.get("regPwd");
        String nickname = regData.get("regNick");
        String realName = regData.get("regName");
        String email = regData.get("regEmail");
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = date.parse(regData.get("birthDate"));
        String phone = regData.get("regPhone");
        String receiveAd = regData.get("isAdOk");
        String isActive = regData.get("isActive");
        boolean result = memberService.regMember(id, pwd, nickname, realName, email, birthDate, phone, receiveAd, isActive);
        if (result) {
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }

    //가입 후 소개 이메일 전송
    @PostMapping("/sendcelmail")
    @ResponseBody
    public ResponseEntity<Boolean> mailConfirm(@RequestBody Map<String, String> regEmailSend) throws Exception {
        String email = regEmailSend.get("regEmail");
        return ResponseEntity.ok(emailService.sendSimpleRegMessage(email));
    }

    //아이디 중복 체크
    @PostMapping("/iddup")
    public ResponseEntity<Boolean> memberIdDup(@RequestBody Map<String, String> idDupData) {
        String id = idDupData.get("regId");
        return ResponseEntity.ok(memberService.regIdDupCk(id));
//        boolean result = memberService.regIdDupCk(id);
//        if(result) {
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(false, HttpStatus.OK);
//        }
    }

    //닉네임 중복 체크
    @PostMapping("/nickdup")
    public ResponseEntity<Boolean> memberNickDup(@RequestBody Map<String, String> nickDupData) {
        String nickname = nickDupData.get("regNick");
        return ResponseEntity.ok(memberService.regNickDupCk(nickname));
    }

    //아이디 찾기
    @PostMapping("/findid")
    public ResponseEntity<Boolean> memberFindId(@RequestBody Map<String, String> findIdData) {
        String realName = findIdData.get("findIdName");
        String email = findIdData.get("findIdEmail");
        return ResponseEntity.ok(memberService.findId(realName, email));
//        boolean result = memberService.findId(realName, email);
//        if (result) {
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(false, HttpStatus.OK);
//        }
    }

    //이메일로 회원정보 받아오기
    @PostMapping("/findidmember")
    public ResponseEntity<List<MemberDTO>> findIdMember(@RequestBody Map<String, String> findIdDataEmail) {
        String email = findIdDataEmail.get("findIdEmail");
        List<MemberDTO> list = memberService.getMemberList(email);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //비밀번호 찾기
    @PostMapping("/findpwd")
    public ResponseEntity<Boolean> memberFindPwd(@RequestBody Map<String, String> findPwdData) {
        String id = findPwdData.get("findPwdId");
        String email = findPwdData.get("findPwdEmail");
        return ResponseEntity.ok(memberService.findPwd(id, email));
    }

    //이메일 인증번호 전송
    @PostMapping("/findpwdverify")
    @ResponseBody
    public String findPwdVerify(@RequestBody Map<String, String> findPwdEmail) throws Exception {
        String email = findPwdEmail.get("findPwdEmail");
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);
        return code;
    }

    //비밀번호 재설정
    @PostMapping("/resetpwd")
    @ResponseBody
    public ResponseEntity<Boolean> resetPwd(@RequestBody Map<String, String> resetPwdData) {
//        Long memberNum = Long.parseLong(resetPwdData.get("memberNum"));
        String id = resetPwdData.get("findPwdId");
        String newPwd = resetPwdData.get("resetPwd");
        return ResponseEntity.ok(memberService.memberIdService(id, newPwd));
    }

    //현재 비밀번호 맞는지 체크
    @PostMapping("/currentPwd")
    @ResponseBody
    public ResponseEntity<Boolean> currentPwd(@RequestBody Map<String, String> currentPwdData) {
        Long memberNum = Long.parseLong(currentPwdData.get("memberNum"));
        String pwd = currentPwdData.get("inputPwd1");
        return ResponseEntity.ok(memberService.currentPwdService(memberNum, pwd));
    }

    //휴대폰번호 인증
    @PostMapping("/phoneverify")
    public ResponseEntity<Map<String, String>> memberPhoneVerify(@RequestBody Map<String, String> phoneVerData) {
        String getPhone = phoneVerData.get("regPhone");
        boolean isPhoneVer = memberService.getPhoneVer(getPhone);

        System.out.println("문자 보낼 수 있음?(중복아님?)" + isPhoneVer);

        Map<String, String> map = new HashMap<>();

        if (isPhoneVer) {
            int verifyCode = sens_sms.makeVerifyCode();
            map.put("result", "OK");
            map.put("code", String.valueOf(verifyCode));
            System.out.println("인증번호 " + verifyCode);

            String match = "[^0-9]";
            getPhone = getPhone.replaceAll(match, "");
            boolean isSend = sens_sms.sendSms(getPhone, verifyCode);

            if (isSend) {
                map.put("result", "OK");
                map.put("code", String.valueOf(verifyCode));
                System.out.println("인증번호 " + verifyCode);
            } else {
                map.put("result", "NOK");
            }
        } else {
            map.put("result", "DUP");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Map<?,?>> logout(HttpServletResponse response, @RequestBody Map<String, String> Data) {
        Map<String, String> map = new HashMap<>();
        Cookie cookie = new Cookie("token", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
        cookie.setMaxAge(0); // 유효시간을 0으로 설정
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie); // 응답 헤더에 추가해서 없어지도록 함
        map.put("logout", "OK");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //회원번호로 회원정보 가져오기
    @PostMapping("/memberNick")
    public ResponseEntity<MemberDTO> memberInfoNum(@RequestBody Map<String, String> memberInfoData) {
        Long memberNum = Long.parseLong(memberInfoData.get("memberNum"));
        MemberDTO numList = memberService.memberNumList(memberNum);
        return new ResponseEntity<>(numList, HttpStatus.OK);
    }
	
	//닉네임 변경하기
    @PostMapping("/infoNewNick")
    @ResponseBody
    public ResponseEntity<Boolean> infoNewNick(@RequestBody Map<String, String> infoNewNickData) {
        Long memberNum = Long.parseLong(infoNewNickData.get("memberNum"));
        String nickname = infoNewNickData.get("infoNewNickInput");
        return ResponseEntity.ok(memberService.newNick(memberNum, nickname));
    }

    //회원정보수정 - 비밀번호 변경
    @PostMapping("/infoResetPwd")
    @ResponseBody
    public ResponseEntity<Boolean> infoNewPwd(@RequestBody Map<String, String> infoNewPwdData) {
        Long memberNum = Long.parseLong(infoNewPwdData.get("memberNum"));
        String pwd = infoNewPwdData.get("inputPwd2");
        return ResponseEntity.ok(memberService.infoNewPwdService(memberNum, pwd));
    }

    //회원정보수정 - 이메일 변경
    @PostMapping("/infoResetEmail")
    @ResponseBody
    public ResponseEntity<Boolean> infoNewEmail(@RequestBody Map<String, String> infoNewEmailData) {
        Long memberNum = Long.parseLong(infoNewEmailData.get("memberNum"));
        String email = infoNewEmailData.get("inputEmail");
        return ResponseEntity.ok(memberService.infoNewEmailService(memberNum, email));
    }

    //회원정보수정 - 광고수신여부 변경
    @PostMapping("/infoResetAd")
    @ResponseBody
    public ResponseEntity<Boolean> infoResetAd(@RequestBody Map<String, String> infoResetAdData) {
        Long memberNum = Long.parseLong(infoResetAdData.get("memberNum"));
        String receiveAd = infoResetAdData.get("infoAd");
        return ResponseEntity.ok(memberService.infoResetAdService(memberNum, receiveAd));
    }

    //회원정보수정 - 프로필이미지 변경
    @PostMapping("/infoImgChange")
    @ResponseBody
    public ResponseEntity<Boolean> infoImgChange(@RequestBody Map<String, String> infoImgChangeData) {
        Long memberNum = Long.parseLong(infoImgChangeData.get("memberNum"));
        String pfImg = infoImgChangeData.get("infoPfImgUrl");
        return ResponseEntity.ok(memberService.infoImgChangeService(memberNum, pfImg));

    }

    //회원정보수정 - 주최자소개 변경
    @PostMapping("/newIntroduce")
    @ResponseBody
    public ResponseEntity<Boolean> newIntroduce(@RequestBody Map<String, String> newIntroduceData) {
        Long memberNum = Long.parseLong(newIntroduceData.get("memberNum"));
        String introduce = newIntroduceData.get("inputIntroduce");
        return ResponseEntity.ok(memberService.newIntroduceService(memberNum, introduce));
    }

    @PostMapping("/memberdelete")
    public ResponseEntity<Map<?, ?>> memberDelete(
            HttpServletResponse response,
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        String id = (String) Data.get("id");
        String pwd = (String) Data.get("pwd");
        Map<String ,String> map = new HashMap<>();
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map = memberService.memberDelete(memberNum, id, pwd);
            if(map.get("memberDelete").equals("OK")){
                Cookie cookie = new Cookie("token", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
                cookie.setMaxAge(0); // 유효시간을 0으로 설정
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }else {
            map.put("memberDelete", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/adminmemberselect")
    public ResponseEntity<Map<?, ?>> adminMemberSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
            map = memberService.adminMemberSelect();
        }else {
            map.put("adminMemberSelect", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/adminmembersearch")
    public ResponseEntity<Map<?, ?>> adminMemberSearch(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        String target = (String) Data.get("target");
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
                map = memberService.adminMemberSearch(target);
        }else {
            map.put("adminMemberSearch", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/adminmemberdelete")
    public ResponseEntity<Map<?, ?>> adminMemberDelete(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        List<Integer> targets = (List<Integer>) Data.get("target");
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
                map = memberService.adminMemberDelete(targets);
        }else {
            map.put("adminMemberDelete", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }

    //광고 이메일 전송
    @PostMapping("/adminnotisend")
    @ResponseBody
    public ResponseEntity<Boolean> adminNotiSend(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        String type = Data.get("mail");
        String title = Data.get("title");
        String content = Data.get("content");
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin")){
                if(type.equals("ad")){
                    return ResponseEntity.ok(emailService.sendSimpleAdMessage(title,content));
                } else return ResponseEntity.ok(emailService.sendSimpleNoticeMessage(title,content));
            }
        }
        return ResponseEntity.ok(false);
    }

}
