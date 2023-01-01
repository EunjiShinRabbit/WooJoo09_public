package com.WooJoo09.controller;

import com.WooJoo09.entity.Member;
import com.WooJoo09.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/developerkirby")
public class TestController {

    private final PasswordEncoder passwordEncoder;

    private final MemberService memberService;

    public Member createMemberAdmin() {
        return Member.createMemberAdmin(passwordEncoder);
    }
    public Member createMember1() {
        return Member.createMember1(passwordEncoder);
    }
    public Member createMember2() {
        return Member.createMember2(passwordEncoder);
    }
    public Member createMember3() {
        return Member.createMember3(passwordEncoder);
    }
    public Member createMember4() {
        return Member.createMember4(passwordEncoder);
    }
    public Member createMember5() {
        return Member.createMember5(passwordEncoder);
    }
    public Member createMember6() {
        return Member.createMember6(passwordEncoder);
    }
    public Member createMember7() {
        return Member.createMember7(passwordEncoder);
    }
    public Member createMember8() {
        return Member.createMember8(passwordEncoder);
    }
    public Member createMember9() {
        return Member.createMember9(passwordEncoder);
    }
    public Member createMember10() {
        return Member.createMember10(passwordEncoder);
    }
    public Member createMember11() {
        return Member.createMember11(passwordEncoder);
    }
    public Member createMember12() {
        return Member.createMember12(passwordEncoder);
    }
    public Member createMember13() {
        return Member.createMember13(passwordEncoder);
    }
    public Member createMember14() {
        return Member.createMember14(passwordEncoder);
    }
    public Member createMember15() {
        return Member.createMember15(passwordEncoder);
    }
    public Member createMember16() {
        return Member.createMember16(passwordEncoder);
    }
    public Member createMember17() {
        return Member.createMember17(passwordEncoder);
    }
    public Member createMember18() {
        return Member.createMember18(passwordEncoder);
    }
    public Member createMember19() {
        return Member.createMember19(passwordEncoder);
    }
    public Member createMember20() {
        return Member.createMember20(passwordEncoder);
    }
    public Member createMember21() {
        return Member.createMember21(passwordEncoder);
    }
    public Member createMember22() {
        return Member.createMember22(passwordEncoder);
    }
    public Member createMember23() {
        return Member.createMember23(passwordEncoder);
    }
    public Member createMember24() {
        return Member.createMember24(passwordEncoder);
    }
    public Member createMember25() {
        return Member.createMember25(passwordEncoder);
    }
    public Member createMember26() {
        return Member.createMember26(passwordEncoder);
    }
    public Member createMember27() {
        return Member.createMember27(passwordEncoder);
    }
    public Member createMember28() {
        return Member.createMember28(passwordEncoder);
    }
    public Member createMember29() {
        return Member.createMember29(passwordEncoder);
    }
    public Member createMember30() {
        return Member.createMember30(passwordEncoder);
    }
    public Member createMember31() {
        return Member.createMember31(passwordEncoder);
    }
    public Member createMember32() {
        return Member.createMember32(passwordEncoder);
    }
    public Member createMember33() {
        return Member.createMember33(passwordEncoder);
    }

    @GetMapping("/memberinserttest")
    public ResponseEntity<Map<?,?>> memberInsertTest() throws Exception {
        Map<String, String> map = new HashMap<>();

        Member member = createMemberAdmin();
        Member savedMember = memberService.saveMember(member);
        log.warn(savedMember.toString());

        Member member1 = createMember1();
        Member savedMember1 = memberService.saveMember(member1);
        log.warn(savedMember1.toString());

        Member member2 = createMember2();
        Member savedMember2 = memberService.saveMember(member2);
        log.warn(savedMember2.toString());

        Member member3 = createMember3();
        Member savedMember3 = memberService.saveMember(member3);
        log.warn(savedMember3.toString());

        Member member4 = createMember4();
        Member savedMember4 = memberService.saveMember(member4);
        log.warn(savedMember4.toString());

        Member member5 = createMember5();
        Member savedMember5 = memberService.saveMember(member5);
        log.warn(savedMember5.toString());

        Member member6 = createMember6();
        Member savedMember6 = memberService.saveMember(member6);
        log.warn(savedMember6.toString());

        Member member7 = createMember7();
        Member savedMember7 = memberService.saveMember(member7);
        log.warn(savedMember7.toString());

        Member member8 = createMember8();
        Member savedMember8 = memberService.saveMember(member8);
        log.warn(savedMember8.toString());

        Member member9 = createMember9();
        Member savedMember9 = memberService.saveMember(member9);
        log.warn(savedMember9.toString());

        Member member10 = createMember10();
        Member savedMember10 = memberService.saveMember(member10);
        log.warn(savedMember10.toString());

        Member member11 = createMember11();
        Member savedMember11 = memberService.saveMember(member11);
        log.warn(savedMember11.toString());

        Member member12 = createMember12();
        Member savedMember12 = memberService.saveMember(member12);
        log.warn(savedMember12.toString());

        Member member13 = createMember13();
        Member savedMember13 = memberService.saveMember(member13);
        log.warn(savedMember13.toString());

        Member member14 = createMember14();
        Member savedMember14 = memberService.saveMember(member14);
        log.warn(savedMember14.toString());

        Member member15 = createMember15();
        Member savedMember15 = memberService.saveMember(member15);
        log.warn(savedMember15.toString());

        Member member16 = createMember16();
        Member savedMember16 = memberService.saveMember(member16);
        log.warn(savedMember16.toString());

        Member member17 = createMember17();
        Member savedMember17 = memberService.saveMember(member17);
        log.warn(savedMember17.toString());

        Member member18 = createMember18();
        Member savedMember18 = memberService.saveMember(member18);
        log.warn(savedMember18.toString());

        Member member19 = createMember19();
        Member savedMember19 = memberService.saveMember(member19);
        log.warn(savedMember19.toString());

        Member member20 = createMember20();
        Member savedMember20 = memberService.saveMember(member20);
        log.warn(savedMember20.toString());

        Member member21 = createMember21();
        Member savedMember21 = memberService.saveMember(member21);
        log.warn(savedMember21.toString());

        Member member22 = createMember22();
        Member savedMember22 = memberService.saveMember(member22);
        log.warn(savedMember22.toString());

        Member member23 = createMember23();
        Member savedMember23 = memberService.saveMember(member23);
        log.warn(savedMember23.toString());

        Member member24 = createMember24();
        Member savedMember24 = memberService.saveMember(member24);
        log.warn(savedMember24.toString());

        Member member25 = createMember25();
        Member savedMember25 = memberService.saveMember(member25);
        log.warn(savedMember25.toString());

        Member member26 = createMember26();
        Member savedMember26 = memberService.saveMember(member26);
        log.warn(savedMember26.toString());

        Member member27 = createMember27();
        Member savedMember27 = memberService.saveMember(member27);
        log.warn(savedMember27.toString());

        Member member28 = createMember28();
        Member savedMember28 = memberService.saveMember(member28);
        log.warn(savedMember28.toString());

        Member member29 = createMember29();
        Member savedMember29 = memberService.saveMember(member29);
        log.warn(savedMember29.toString());

        Member member30 = createMember30();
        Member savedMember30 = memberService.saveMember(member30);
        log.warn(savedMember30.toString());

        Member member31 = createMember31();
        Member savedMember31 = memberService.saveMember(member31);
        log.warn(savedMember31.toString());

        Member member32 = createMember32();
        Member savedMember32 = memberService.saveMember(member32);
        log.warn(savedMember32.toString());

        Member member33 = createMember33();
        Member savedMember33 = memberService.saveMember(member33);
        log.warn(savedMember33.toString());

        map.put("멤버 34명 생성 테스트", "OK");
        return ResponseEntity.ok().body(map);
    }
}
