package com.WooJoo09.controller;

import com.WooJoo09.dto.MemberDTO;
import com.WooJoo09.dto.TokenDataResponse;
import com.WooJoo09.dto.TokenResponse;
import com.WooJoo09.dto.TokenResponseNoData;
import com.WooJoo09.entity.Member;
import com.WooJoo09.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/developerkirby")
public class JwtController {

    private final JwtProvider jwtProvider;

//    //==토큰 생성 컨트롤러==//
//    @GetMapping(value = "/tokenCreate/{memberNum}")
//    public TokenResponse createToken(@PathVariable("memberNum") String memberNum) throws Exception {
//        String token = jwtProvider.createToken(memberNum); // 토큰 생성
//        Claims claims = jwtProvider.parseJwtToken("Bearer "+ token); // 토큰 검증
//
//        TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(), claims.getIssuedAt().toString(), claims.getExpiration().toString());
//        TokenResponse tokenResponse = new TokenResponse("200", "OK", tokenDataResponse);
//
//        return tokenResponse;
//    }

    // 토큰 발급해서 쿠키에 넣어주는 친구
    @PostMapping("/test")
    @ResponseBody
    public ResponseEntity<?> postLogin(HttpServletResponse response, @RequestBody Map<String, String> Data) throws Exception {

        // Data가 프론트에서 넘겨준 정보
        String content =  Data.get("content");
        log.info(content);

        // Data가 프론트에서 온거니까, Data.get("id")하면 아이디 -> 1) 아이디가 'woojoo09' 면 어드민쪽 로직으로
        // 2) 어드민이 아니면 일반 유저니까, 저 id를 가지고 service에서 db를 가서 id랑 일치하는 회원 있는지 확인하면서 memberNum을 가지고와서 저장
        // -> 없으면 존재하지 않는 회원
        // db에 있으면 db에 있는 암호화된 비번이랑 Data.get("pwd")를 matches 함수로비교(test에 있음 함수 찾아서 쓰면 됨....) ->
        // matches가 true면 로그인 성공 된 거니까 토큰가을 발급하는데, 앞에서 찾았던 memberNum으로 토큰 발급

        long memberNum = Long.valueOf(10); // 이부분이 db에서 id로 가지고 온 memberNum
        String memberNumStr = Long.toString(memberNum);
        String jwtToken = tokenCreate(memberNumStr); // 회원번호를 가지고 토큰 만들기
        // 쿠키 만들기
        Cookie cookie = new Cookie("token",jwtToken); // 생성된 토큰을 cookie에 세팅
        cookie.setMaxAge(60 * 60); // 유효기간 60분으로 설정
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie); // 응답에 쿠키 추가


        // return response entity
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

//    // 토큰 생성용 함수
//    public TokenResponse tokenCreate(String memberNum) throws Exception {
//        String token = jwtProvider.createToken(memberNum); // 토큰 생성
//        Claims claims = jwtProvider.parseJwtToken("Bearer "+ token); // 토큰 검증
//
//        TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(), claims.getIssuedAt().toString(), claims.getExpiration().toString());
//        TokenResponse tokenResponse = new TokenResponse("200", "OK", tokenDataResponse);
//
//        return tokenResponse;
//    }

    // 토큰 생성만 하는 함수
    public String tokenCreate(String memberNum) throws Exception {
        String token = jwtProvider.createToken(memberNum); // 토큰 생성
        return token;
    }

//    //==토큰 인증 컨트롤러==//
//    @GetMapping(value = "/checkToken")
//    public TokenResponseNoData checkToken(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, String> Data) throws Exception {
//        Claims claims = jwtProvider.parseJwtToken(token);
//        TokenResponseNoData tokenResponseNoData = new TokenResponseNoData("200", "success");
//        return tokenResponseNoData;
//    }

//    //==토큰 인증 + memberNum 반환 컨트롤러==//
//    @GetMapping(value = "/checkTokenMember")
//    public TokenResponse checkTokenMember(@RequestHeader(value = "Authorization") String token) throws Exception {
//        Claims claims = jwtProvider.parseJwtToken(token);
//
//        TokenResponse tokenResponse = new TokenResponse("200", "success", claims.getSubject());
//        return tokenResponse;
//    }

    // 토큰 인증 함수
    public String tokenCheck(String token) throws Exception {
        Claims claims = jwtProvider.parseJwtToken(token); // Bearer 제거안하는 함수
        String memberNum = claims.getSubject();
        log.info("tokencheck 함수 토큰에 담긴 회원정보 memberNum " + memberNum);
        return memberNum; // 토큰 만들때 사용한 memberNum 리턴
    }
//
//    @PostMapping(value = "/checkTokenCookie")
//    public ResponseEntity<?> checkTokenCookie(@CookieValue(value = "token", required = false) String token, @RequestBody Map<String, String> Data) throws Exception {
//        if(token != null){
//            List<MemberDTO> list = new ArrayList<>();
//            MemberDTO memberDTO = new MemberDTO();
//            String memberNum;
//            String content = Data.get("content");
//            log.info(content);
//            log.warn(token);
//            memberNum = tokenCheck(token);
//            memberDTO.setMemberNum(Long.parseLong(memberNum));
//            list.add(memberDTO);
//            return new ResponseEntity(list, HttpStatus.OK);
//        }else{
//            Map<String, String> result = new HashMap<>();
//            result.put("result", "NOK");
//            return new ResponseEntity(result, HttpStatus.OK);
//        }
//    }

    @PostMapping(value = "/tokencheck")
    public ResponseEntity<?> checkTokenCookie(@CookieValue(value = "token", required = false) String token) throws Exception {
        Map<String, String> result = new HashMap<>();
        if(token != null){
            String memberNum = tokenCheck(token);
            log.info("/tokencheck 토큰에 담긴 회원정보 memberNum" + memberNum);
            result.put("result", "OK");
            result.put("memberNum", memberNum);
        }else{
            result.put("result", "NOK");
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
