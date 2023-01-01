package com.WooJoo09.repository;

import com.WooJoo09.constant.IsRepresent;
import com.WooJoo09.entity.ProductImg;
import com.WooJoo09.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
    List<ProductImg> findByTradeNum(Trade tradeNum);
    List<ProductImg> findByTradeNumAndIsRepresent(Trade tradeNum, IsRepresent isRepresent);
}
