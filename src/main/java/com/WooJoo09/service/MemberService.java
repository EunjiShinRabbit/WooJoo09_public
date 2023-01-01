package com.WooJoo09.service;

import com.WooJoo09.constant.IsActive;
import com.WooJoo09.constant.ReceiveAd;
import com.WooJoo09.controller.JwtController;
import com.WooJoo09.dto.MemberDTO;
import com.WooJoo09.entity.Member;
import com.WooJoo09.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final JwtController jwtController;

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    //로그인
    public Map<?, ?> loginService(String id, String pwd) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Optional<Member> loginMember = memberRepository.findById(id);
        if(loginMember.isEmpty()) {
            map.put("login", false);
        }else {
            if (!passwordEncoder.matches(pwd, loginMember.get().getPwd())){
                map.put("login", false);
            } else {
                if(loginMember.get().getIsActive() == IsActive.ACTIVE){
                    String token;
                    if(id.equals("admin")){
                        log.info("관리자입니다 관리자용 토큰 발급합니다");
                        token = jwtController.tokenCreate("admin");
                        map.put("token", token);
                    } else {
                        token = jwtController.tokenCreate(loginMember.get().getMemberNum().toString());
                        map.put("token", token);
                    }
                    map.put("login", true);
                }
                else map.put("login", false);
            }
        }
        return map;
    }

    //회원가입
    public boolean regMember(String id, String pwd, String nickname, String realName,
                             String email, Date birthDate, String phone, String receiveAd, String isActive) {
        Member member = new Member();
        member.setId(id);
        member.setPwd(passwordEncoder.encode(pwd));
        member.setNickname(nickname);
        member.setRealName(realName);
        member.setEmail(email);
        member.setBirthDate(birthDate);
        member.setPhone(phone);
        member.setReceiveAd(ReceiveAd.valueOf(receiveAd));
        member.setIsActive(IsActive.valueOf(isActive));
        member.setIntroduce("안녕하세요. " + nickname + "의 상점입니다.");

        Member rst = memberRepository.save(member);
        log.warn(rst.toString());

        return true;
    }

    //아이디 중복확인
    public boolean regIdDupCk(String id) {
//        Optional<Member> member = memberRepository.findById(id);
//        if(memberRepository.findById(id).isEmpty()) return true;
//        else return false;
        return memberRepository.findById(id).isEmpty();
    }

    //닉네임 중복확인
    public boolean regNickDupCk(String nickname) {
        return memberRepository.findByNickname(nickname).isEmpty();
    }

    //아이디 찾기
    public boolean findId(String realName, String email) {
//        List<Member> memberList = memberRepository.findByRealNameAndEmail(realName, email);
//        for(Member e : memberList) {
//            return true;
//        }
        return !memberRepository.findByRealNameAndEmail(realName, email).isEmpty();
    }

    //이메일로 회원정보 받아오기
    public List<MemberDTO> getMemberList(String email) {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        List<Member> memberList = memberRepository.findByEmail(email);
        for(Member e : memberList) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setId(e.getId());
            memberDTO.setRealName(e.getRealName());
            memberDTO.setNickname(e.getNickname());
            memberDTO.setEmail(e.getEmail());
            memberDTO.setBirthDate(e.getBirthDate());
            memberDTO.setPhone(e.getPhone());

            memberDTOS.add(memberDTO);
        }
        return memberDTOS;
    }

    //비밀번호 찾기
    public boolean findPwd(String id, String email) {
        return !memberRepository.findByIdAndEmail(id, email).isEmpty();
    }

    //비밀번호 재설정
    public boolean memberIdService(String id, String newPwd) {
        Optional<Member> memberId = memberRepository.findById(id);
        if(memberId.isEmpty()) return false;
        Member member = memberId.get();
        member.setPwd(passwordEncoder.encode(newPwd));
        Member savedMember = memberRepository.save(member);
        log.info(savedMember.toString());
        return true;
    }

    //전화번호 중복체크
    public boolean getPhoneVer(String phone) {
        return memberRepository.findByPhone(phone).isEmpty();
    }

    //회원번호로 회원정보 가져오기
    public MemberDTO memberNumList(Long memberNum) {
        Member member = memberRepository.findByMemberNum(memberNum);
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setMemberNum(member.getMemberNum());
            memberDTO.setId(member.getId());
            memberDTO.setNickname(member.getNickname());
            memberDTO.setRealName(member.getRealName());
            memberDTO.setEmail(member.getEmail());
            memberDTO.setPhone(member.getPhone());
            memberDTO.setBirthDate(member.getBirthDate());
            memberDTO.setRegDate(member.getRegDate());
            memberDTO.setPfImg(member.getPfImg());
            if(member.getReceiveAd() == ReceiveAd.POSITIVE) memberDTO.setReceiveAd("POSITIVE");
            else memberDTO.setReceiveAd("NEGATIVE");
            String grade = memberRepository.memberGrade(memberNum).get("grade");
            memberDTO.setGrade(grade);
            memberDTO.setIntroduce(member.getIntroduce());
            return memberDTO;
    }

    //닉네임 변경
    public boolean newNick(Long memberNum, String nickName) {
        Member memberInfo = memberRepository.findByMemberNum(memberNum);
        memberInfo.setNickname(nickName);
        Member savedMember = memberRepository.save(memberInfo);
        log.info(savedMember.toString());
        return  true;
    }

    //현재 비밀번호 맞는지 체크
    public boolean currentPwdService(Long memberNum, String pwd) {
//        return !memberRepository.findByMemberNumAndPwd(memberNum, pwd).isEmpty();
        Member member = memberRepository.findByMemberNum(memberNum);
        if(passwordEncoder.matches(pwd, member.getPwd())){
            return true;
        } else return false;
    }

    //회원정보수정 - 비밀번호 변경
    public boolean infoNewPwdService(Long memberNum, String pwd) {
        Member member = memberRepository.findByMemberNum(memberNum);
        if(member == null) {
            return false;
        }
        member.setPwd(passwordEncoder.encode(pwd));
        Member savedMember = memberRepository.save(member);
        log.info(savedMember.toString());
        return true;
    }

    //회원정보수정 - 이메일 변경
    public boolean infoNewEmailService(Long memberNum, String email) {
        Member member = memberRepository.findByMemberNum(memberNum);
        if(member == null) return false;
        member.setEmail(email);
        Member savedMember = memberRepository.save(member);
        log.info(savedMember.toString());
        return true;
    }

    //회원정보수정 - 광고수신여부 변경
    public boolean infoResetAdService(Long memberNum, String receiveAd) {
        Member member = memberRepository.findByMemberNum(memberNum);
        if(member == null) return false;
        member.setReceiveAd(ReceiveAd.valueOf(receiveAd));
        Member savedMember = memberRepository.save(member);
        log.info(savedMember.toString());
        return true;
    }

    //회원정보수정 - 프로필이미지 변경
    public boolean infoImgChangeService(Long memberNum, String pfImg) {
        Member member = memberRepository.findByMemberNum(memberNum);
        if(member == null) return false;
        member.setPfImg(pfImg);
        Member savedMember = memberRepository.save(member);
        log.info(savedMember.toString());
        return true;
    }

    //회원정보수정 - 주최자 소개 변경
    public boolean newIntroduceService(Long memberNum, String introduce) {
        Member member = memberRepository.findByMemberNum(memberNum);
        if(member == null) return false;
        member.setIntroduce(introduce);
        Member savedMember = memberRepository.save(member);
        log.info(savedMember.toString());
        return true;
    }

    public Map<String, String> memberDelete(Long memberNum, String id, String pwd){
        Map<String ,String> map = new HashMap<>();
        Optional<Member> member= memberRepository.findByMemberNumAndId(memberNum, id);
        if (member.isEmpty()) {
            map.put("memberDelete", "notData");
            return map;
        }
        if(passwordEncoder.matches(pwd, member.get().getPwd())){
            member.get().setIsActive(IsActive.INACTIVE);
            Member savedMember = memberRepository.save(member.get());
            log.info(savedMember.toString());
            map.put("memberDelete", "OK");
            return map;
        }
        map.put("memberDelete", "NOK");
        return map;
    }

    public Map<String, Object> adminMemberSelect(){
        Map<String,Object> map = new HashMap<>();
        map.put("memberData", memberRepository.adminMemberSelect());
        map.put("adminMemberSelect", "OK");
        return map;
    }

    public Map<String, Object> adminMemberSearch(String target){
        Map<String,Object> map = new HashMap<>();
        map.put("memberData", memberRepository.adminMemberSearch("%"+target+"%"));
        map.put("adminMemberSearch", "OK");
        return map;
    }

    public Map<String, Object> adminMemberDelete(List<Integer> targets){
        Map<String,Object> map = new HashMap<>();
        for (Integer e : targets){
            Long memberNum = Long.valueOf(e);
            Member member = memberRepository.findByMemberNum(memberNum);
            member.setIsActive(IsActive.INACTIVE);
            Member savedMember = memberRepository.save(member);
            log.info(savedMember.toString());
        }
        map.put("adminMemberDelete", "OK");
        return map;
    }
}