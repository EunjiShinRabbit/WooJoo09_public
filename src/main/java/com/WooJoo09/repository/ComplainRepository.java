package com.WooJoo09.repository;

import com.WooJoo09.controller.JwtController;
import com.WooJoo09.entity.Complain;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ComplainRepository extends JpaRepository<Complain, Long> {
    List<Complain> findByComplainTradeAndComplainant(Trade complainTrade, Member complainant);
    Long countComplainByComplainTrade(Trade complainTrade);
}
