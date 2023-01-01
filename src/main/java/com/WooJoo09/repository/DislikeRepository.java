package com.WooJoo09.repository;
import com.WooJoo09.entity.Dislike;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikeRepository extends JpaRepository<Dislike, Long> {
    List<Dislike> findByDisTradeNumAndDisMemNum(Trade disTradeNum, Member disMemNum);
}
