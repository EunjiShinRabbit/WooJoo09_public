package com.WooJoo09.dto;

import com.WooJoo09.constant.AcceptTrade;
import com.WooJoo09.entity.Trade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerDTO {
    private Long partnerNum;
    private String deliveryCompany;
    private String deliveryNum;
    private String bank;
    private String accountNum;
    private String accountHolder;
    private String deliveryAddress;
    private String deliveryName;
    private String deliveryPhone;
    private String acceptTrade;

    // tradeNum 으로 가지고 오는것
    private String productName;
    private int price;
    private String doneTrade;
    private String imgUrl;

    // memberNum(주최자, host) 으로 가지고 오는 것
    private String hostNickname;
    private String hostIsActive;

    // partMemNum(참여자, partner) 으로 가지고 오는 것
    private String partnerNickname;
    private String partnerIsActive;

}
