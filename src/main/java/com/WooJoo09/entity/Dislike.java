package com.WooJoo09.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Getter
@Setter
@ToString
public class Dislike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dislikeNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disTradeNum")
    private Trade disTradeNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disMemNum")
    private Member disMemNum;
}
