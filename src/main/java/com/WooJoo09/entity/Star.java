package com.WooJoo09.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long StarNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tradeNum")
    private Trade tradeNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member memberNum;
}
