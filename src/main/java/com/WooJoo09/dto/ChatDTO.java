package com.WooJoo09.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatDTO {
    private Long chatNum;
    private Long partnerNum; // 거래 참여 번호
    private Long sender;
    private Long receiver;
    private String chatContent;
    private LocalDateTime chatTime;
    private String isRead;
    private String isImg;

    private String productName;
    private int price;
    private String doneTrade;
    private String imgUrl;

    private String hostNickname;
    private String hostIsActive;

    private String partnerNickname;
    private String partnerIsActive;
}
