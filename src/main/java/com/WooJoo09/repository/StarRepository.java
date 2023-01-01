package com.WooJoo09.repository;

import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Star;
import com.WooJoo09.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StarRepository extends JpaRepository<Star, Long> {
    List<Star> findByTradeNumAndMemberNum(Trade tradeNum, Member memberNum);
}
