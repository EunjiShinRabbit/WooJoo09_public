package com.WooJoo09.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class MemGrade {
    @Id
    private String grade;
    private int lowGood;
    private int highGood;
}

