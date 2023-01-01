package com.WooJoo09.service;

import com.WooJoo09.constant.ReceiveAd;
import com.WooJoo09.entity.Member;
import com.WooJoo09.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

@PropertySource("classpath:application.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    private final MemberRepository memberRepository;

    //인증번호 생성
    private final String ePw = createKey();

    @Value("${spring.mail.username}")
    private String id;

    // 비밀번호 찾기 인증번호 이메일
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
        log.info("보내는 대상 : "+ to);
        log.info("인증 번호 : " + ePw);
        MimeMessage  message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        message.setSubject("우주공구 인증 코드"); //메일 제목

        // 메일 내용 메일의 subtype을 html로 지정하여 html문법 사용 가능
        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">비밀번호 찾기 인증 코드</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 화면에 입력해주세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += ePw;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,"WooJoo09")); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    // 가입 축하 이메일
    public MimeMessage createRegMessage(String to) throws MessagingException, UnsupportedEncodingException {
        log.info("보내는 대상 : "+ to);
        MimeMessage  message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        message.setSubject("우주공구 회원이 되신 것을 환영합니다!"); //메일 제목

        message.setText(setContext(), "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,"WooJoo09")); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    // 타임리프 설정
    private String setContext() {
        Context context = new Context();
        return templateEngine.process("regCelebrate", context);
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

     /*
        메일 발송
        sendSimpleMessage의 매개변수로 들어온 to는 인증번호를 받을 메일주소
        MimeMessage 객체 안에 내가 전송할 메일의 내용을 담아준다.
        bean으로 등록해둔 javaMailSender 객체를 사용하여 이메일 send
     */
    // 비밀번호찾기 인증번호 전송
    public String sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);
        try{
            javaMailSender.send(message); // 메일 발송
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw; // 메일로 보냈던 인증 코드를 서버로 리턴
    }

    //가입 축하 이메일
    public boolean sendSimpleRegMessage(String to) throws Exception {
        MimeMessage message = createRegMessage(to);
        try{
            javaMailSender.send(message); // 메일 발송
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return true;
    }

    // 어드민 광고 이메일
    public MimeMessage createAdMessage(String to, String title, String content) throws MessagingException, UnsupportedEncodingException {
        log.info("보내는 대상 : "+ to);
        log.info("인증 번호 : " + ePw);
        MimeMessage  message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        message.setSubject(title); //메일 제목

        // 메일 내용 메일의 subtype을 html로 지정하여 html문법 사용 가능
        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">우주공구 광고</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">";
        msg += content;
        msg += "</p>";

        message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,"WooJoo09")); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    // 어드민 광고 이메일
    public MimeMessage createNoticeMessage(String to, String title, String content) throws MessagingException, UnsupportedEncodingException {
        log.info("보내는 대상 : "+ to);
        log.info("인증 번호 : " + ePw);
        MimeMessage  message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        message.setSubject(title); //메일 제목

        // 메일 내용 메일의 subtype을 html로 지정하여 html문법 사용 가능
        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">우주공구 공지</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">";
        msg += content;
        msg += "</p>";

        message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,"WooJoo09")); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    //공지 이메일
    public boolean sendSimpleNoticeMessage(String title, String content) throws Exception {
        List<Member> emails = memberRepository.findAll();
        for (Member e : emails){
            String to = e.getEmail();
            MimeMessage message = createNoticeMessage(to, title, content);
            try{
                javaMailSender.send(message); // 메일 발송
            }catch(MailException es){
                es.printStackTrace();
                throw new IllegalArgumentException();
            }
        }
        return true;
    }

    // 광고 이메일
    public boolean sendSimpleAdMessage(String title, String content) throws Exception {
        List<Member> emails = memberRepository.findByReceiveAd(ReceiveAd.POSITIVE);
        for (Member e : emails){
            String to = e.getEmail();
            MimeMessage message = createAdMessage(to, title, content);
            try{
                javaMailSender.send(message); // 메일 발송
            }catch(MailException es){
                es.printStackTrace();
                throw new IllegalArgumentException();
            }
        }
        return true;
    }
}
