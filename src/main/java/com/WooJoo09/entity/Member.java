package com.WooJoo09.entity;

import com.WooJoo09.constant.IsActive;
import com.WooJoo09.constant.ReceiveAd;
import com.WooJoo09.dto.MemberDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Member {
    //fetch의 디폴트 값은 @xxToOne에서는 EAGER, @xxToMany에서는 LAZY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @OneToMany(mappedBy = "host", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @OneToMany(mappedBy = "partMemNum", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @OneToMany(mappedBy = "sender", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @OneToMany(mappedBy = "complainant", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @OneToMany(mappedBy = "goodMemNum", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @OneToMany(mappedBy = "disMemNum", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Long memberNum;

    @Column(unique = true, nullable = false, length = 20)
    private String id;

    @Column(nullable = false, length = 200)
    private String pwd;

    @Column(unique = true, nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String realName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String phone;

    @Column(nullable = false)
    private Date birthDate; // 시분초 없이 년월일만

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime regDate;

    // Enum 타입에 포함된 이름 자체를 db에 넣겠다는 의미
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReceiveAd receiveAd;

    @Column(length = 500)
    private String pfImg;

    @Column(length = 1000)
    private String introduce;

    @Enumerated(EnumType.STRING)
    private IsActive isActive;

    public static Member createMemberAdmin(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("admin");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("관리자");
        member.setRealName("관리자");
        member.setEmail("developerkirby1@gmail.com");
        member.setPhone("010-9999-9999");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("2022/12/26");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.POSITIVE);
        member.setIsActive(IsActive.ACTIVE);
        return member;
    }
    // 테스트용
    public static Member createMember1(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("hothobbang");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("따뜻한호빵");
        member.setRealName("강정환");
        member.setEmail("123hothobbang456@gmail.comm");
        member.setPhone("010-1111-1111");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1980/01/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember2(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("healing11");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("여유");
        member.setRealName("강감찬");
        member.setEmail("hea99ling11@gmail.com");
        member.setPhone("010-1111-1112");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1999/01/10");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember3(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("dohyundad7");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("도현아빠");
        member.setRealName("이도훈");
        member.setEmail("dohyun898dad7@gmail.comm");
        member.setPhone("010-1111-1113");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1977/07/07");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
//        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }


    public static Member createMember4(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("grassflower");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("들꽃향기");
        member.setRealName("김풀꽃");
        member.setEmail("grass565flo11wer@gmail.comm");
        member.setPhone("010-1111-1114");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1995/06/05");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
//        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember5(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("poppi6060");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("뽀삐");
        member.setRealName("이강쥐");
        member.setEmail("poprtrtypi6060@gmail.comm");
        member.setPhone("010-1111-1115");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1990/11/20");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember6(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("mukboprincess");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("먹보공주");
        member.setRealName("송주영");
        member.setEmail("mukbop34234rincess@gmail.comm");
        member.setPhone("010-1111-1116");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1998/07/21");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
//        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember7(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("babamba");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("바밤바");
        member.setRealName("문신애");
        member.setEmail("babam342411ba@gmail.comm");
        member.setPhone("010-1111-1117");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1989/03/15");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember8(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("lobinhoot");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("로빈훗");
        member.setRealName("류희재");
        member.setEmail("lob3434inhoot@gmail.comm");
        member.setPhone("010-1111-1118");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1985/12/07");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember9(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("roserose");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("장미");
        member.setRealName("최진규");
        member.setEmail("ro243se234@gmail.comm");
        member.setPhone("010-1111-1119");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1993/05/12");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
//        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember10(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("daldaguri");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("달다구리");
        member.setRealName("채주화");
        member.setEmail("dald364gur77i@gmail.com");
        member.setPhone("010-1111-1110");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1992/08/24");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }public static Member createMember11(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("roottree");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("뿌리나무");
        member.setRealName("");
        member.setEmail("roott798ree00@gmail.com");
        member.setPhone("010-1111-1011");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1997/02/26");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
//        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember12(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("greenstar");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("초록별");
        member.setRealName("문창섭");
        member.setEmail("green11r1258@gmail.comn");
        member.setPhone("010-1111-1122");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1981/01/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember13(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("positivepower");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("긍정의 힘");
        member.setRealName("문희현");
        member.setEmail("positiv85wer12@gmail.comn");
        member.setPhone("010-1111-1123");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1982/10/15");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember14(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("gangdongmom");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("강동구맘");
        member.setRealName("민유경");
        member.setEmail("gangdonom1555@gmail.comn");
        member.setPhone("010-1111-1124");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1982/11/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        // member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember15(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("deliciousfood");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("미식가");
        member.setRealName("안경신");
        member.setEmail("delicioud0022@gmail.com");
        member.setPhone("010-1111-1125");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1982/11/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember16(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("clover");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("네잎클로버");
        member.setRealName("안기영");
        member.setEmail("22clover47@gmail.com");
        member.setPhone("010-1111-1126");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1981/11/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        //member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember17(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("coldBrew");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("콜드브루");
        member.setRealName("안상훈");
        member.setEmail("15cold12B22@gmail.com");
        member.setPhone("010-1111-1127");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1996/10/25");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember18(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("fullsun");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("햇살가득");
        member.setRealName("안윤재");
        member.setEmail("25fulls1255@gmail.com");
        member.setPhone("010-1111-1128");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1991/01/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember19(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("lego");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("레고");
        member.setRealName("양경우");
        member.setEmail("358lgo125@gmail.com");
        member.setPhone("010-1111-1129");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1990/01/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        //     member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember20(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("yujin");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("유진");
        member.setRealName("안유진");
        member.setEmail("yu12ji12355@gmail.com");
        member.setPhone("010-1111-1130");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1981/01/20");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember21(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("bluegreencloud");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("하늘초록구름");
        member.setRealName("오기선");
        member.setEmail("bluegreen11ud15@gmail.com");
        member.setPhone("010-1111-1131");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1981/10/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember22(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("todayDetective");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("오늘의탐정");
        member.setRealName("오세창");
        member.setEmail("today225D15@gmail.com");
        member.setPhone("010-1111-1132");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1981/10/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        // member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }
    public static Member createMember23(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("teddybear");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("테디베어");
        member.setRealName("김도현");
        member.setEmail("teddybr5684@gmail.comm");
        member.setPhone("010-2222-1123");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1992/08/29");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember24(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("reveca");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("레베카");
        member.setRealName("이지훈");
        member.setEmail("revec59736@gmail.comm");
        member.setPhone("010-2222-1124");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1998/03/05");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember25(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("chicken");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("치킨");
        member.setRealName("송진우");
        member.setEmail("chicken3574@gmail.comm");
        member.setPhone("010-2222-1125");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1999/01/03");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember26(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("dropthebeat");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("드랍더비트");
        member.setRealName("최성민");
        member.setEmail("droptheat357454@gmail.comm");
        member.setPhone("010-2222-1126");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1996/05/05");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember27(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("orion");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("오리온자리");
        member.setRealName("허민규");
        member.setEmail("ori1212on@gmail.comm");
        member.setPhone("010-2222-1127");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1995/06/20");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember28(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("dangidangi");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("꿀단지하우스");
        member.setRealName("이정현");
        member.setEmail("dangidangi239@gmail.comm");
        member.setPhone("010-2222-1128");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1997/07/03");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember29(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("banybany");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("바니바니당근당근");
        member.setRealName("송지원");
        member.setEmail("banybany789@gmail.comm");
        member.setPhone("010-2222-1129");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("2000/02/02");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember30(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("treasurehouse");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("보물창고");
        member.setRealName("도재현");
        member.setEmail("treaehouse@gmail.comm");
        member.setPhone("010-2222-1130");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1993/11/23");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember31(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("cooltrade");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("쿨거래환영");
        member.setRealName("이지훈");
        member.setEmail("cool3254tde@gmail.comm");
        member.setPhone("010-2222-1131");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1996/08/10");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember32(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("flying");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("날다람쥐");
        member.setRealName("김시윤");
        member.setEmail("flying15368@gmail.comm");
        member.setPhone("010-2222-1132");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1991/04/23");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

    public static Member createMember33(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId("kirby");
        String password = passwordEncoder.encode("#%^45weg2%^@#z");
        member.setPwd(password);
        member.setNickname("커비");
        member.setRealName("커비");
        member.setEmail("kirby1234@gmail.comm");
        member.setPhone("010-2222-1133");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse("1999/01/01");
            member.setBirthDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        member.setRegDate(LocalDateTime.now());
        member.setReceiveAd(ReceiveAd.NEGATIVE);
        member.setIsActive(IsActive.ACTIVE);
//        member.setReceiveAd(ReceiveAd.POSITIVE);
        return member;
    }

}
