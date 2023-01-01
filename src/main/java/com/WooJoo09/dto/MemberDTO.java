package com.WooJoo09.dto;

import com.WooJoo09.constant.IsActive;
import com.WooJoo09.constant.ReceiveAd;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class MemberDTO {
    private long MemberNum;
    private String id;
    private String pwd;
    private String nickname;
    private String realName;
    private String email;
    private String phone;
    private Date birthDate;
    private LocalDateTime regDate;
    private String receiveAd;
    private String pfImg;
    private String introduce;
    private String isActive;
    private String grade;
}
