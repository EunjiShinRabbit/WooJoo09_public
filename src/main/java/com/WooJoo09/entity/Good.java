package com.WooJoo09.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Getter
@Setter
@ToString
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goodTradeNum")
    private Trade goodTradeNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goodMemNum")
    private Member goodMemNum;

}
