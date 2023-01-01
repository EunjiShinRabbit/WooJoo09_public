package com.WooJoo09.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complainNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complainTrade")
    private Trade complainTrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complainant")
    private Member complainant;
}
