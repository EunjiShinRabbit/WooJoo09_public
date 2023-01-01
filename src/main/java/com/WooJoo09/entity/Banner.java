package com.WooJoo09.entity;

import com.WooJoo09.constant.IsActive;
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
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerNum;

    private String bannerName;

    private String imgUrl;

    private String directUrl;

    @Enumerated(EnumType.STRING)
    private IsActive isActive;

}