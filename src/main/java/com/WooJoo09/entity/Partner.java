package com.WooJoo09.entity;

import com.WooJoo09.constant.AcceptTrade;
import com.WooJoo09.constant.TradeMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @OneToMany(mappedBy = "partnerNum", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Long partnerNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tradeNum")
    private Trade tradeNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partMemNum")
    private Member partMemNum;

    @Column(length = 20)
    private String deliveryCompany;

    @Column(length = 30)
    private String deliveryNum;

    @Column(length = 20)
    private String bank;

    @Column(length = 30)
    private String accountNum;

    @Column(length = 10)
    private String accountHolder;

    @Column(length = 100)
    private String deliveryAddress;

    @Column(length = 10)
    private String deliveryName;

    @Column(length = 20)
    private String deliveryPhone;

    @Enumerated(EnumType.STRING)
    private AcceptTrade acceptTrade;

}
