package com.WooJoo09.entity;

import com.WooJoo09.constant.IsRepresent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tradeNum")
    private Trade tradeNum;

    @Column(nullable = false, length = 500)
    private String imgUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IsRepresent isRepresent;

}
