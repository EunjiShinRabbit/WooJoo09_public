package com.WooJoo09.dto;

import com.WooJoo09.constant.DoneTrade;
import com.WooJoo09.constant.TradeMethod;
import com.WooJoo09.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TradeDTO {
    private Long tradeNum;
    private String categoryName;
    private int limitPartner; // 공구 총 참여자수
    private int acceptPartner; // 모집된 공구 참여자수
    private String product;
    private int price;
    private String city;
    private String town;
    private LocalDateTime writeDate;
    private Date dueDate;
    private String tradeMethod;
    private String productDetail;
    private String tradePlace;
    private String doneTrade;

    private int countStar;
    private int myStar;
    private int myGood;
    private int myDislike;

    private String nickname;
    private String grade;
    private String pfImg;
    private String introduce;
    private String isActive;
    private int countHost; // 거래 주최 수
    private int countPartner; // 거래 참여 수

    private String representImg; // 대표이미지
    private List<String> imgList; // 상품 이미지 리스트(대표이미지 포함)

    public TradeDTO(Long tradeNum, String categoryName, String product,
                    int price, String city, String town, Date dueDate,
                    String tradeMethod, LocalDateTime writeDate,
                    int limitPartner, String doneTrade, int acceptPartner,
                    String representImg, int countStar
    ){
        this.tradeNum = tradeNum;
        this.categoryName = categoryName;
        this.product = product;
        this.price = price;
        this.city = city;
        this.town = town;
        this.dueDate = dueDate;
        this.tradeMethod = tradeMethod;
        this.writeDate = writeDate;
        this.limitPartner = limitPartner;
        this.doneTrade = doneTrade;
        this.acceptPartner = acceptPartner;
        this.representImg = representImg;
        this.countStar = countStar;
    }

}
