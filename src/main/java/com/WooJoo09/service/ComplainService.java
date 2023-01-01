package com.WooJoo09.service;

import com.WooJoo09.constant.DoneTrade;
import com.WooJoo09.entity.Complain;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Star;
import com.WooJoo09.entity.Trade;
import com.WooJoo09.repository.ComplainRepository;
import com.WooJoo09.repository.MemberRepository;
import com.WooJoo09.repository.StarRepository;
import com.WooJoo09.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class ComplainService {
    private final ComplainRepository complainRepository;
    private final TradeRepository tradeRepository;
    private final MemberRepository memberRepository;

    public Map<String,String> complainInsert(Long tradeNum, Long memberNum){
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        if(complainRepository.findByComplainTradeAndComplainant(trade, member).isEmpty()){
            Complain complain = new Complain();
            complain.setComplainant(member);
            complain.setComplainTrade(trade);
            Complain savedComplain = complainRepository.save(complain);
            log.info(savedComplain.toString());
            if(complainRepository.countComplainByComplainTrade(trade) > 5){
                trade.setDoneTrade(DoneTrade.DELETE);
                Trade savedTrade = tradeRepository.save(trade);
                log.info(savedTrade.toString());
            }
            map.put("complainComplete", "OK");
        } else map.put("complainComplete", "duplicate");
        return map;
    }
}
