package com.WooJoo09.repository;

import com.WooJoo09.entity.Good;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findByGoodTradeNumAndGoodMemNum(Trade goodTradeNum, Member goodMemNum);
}
