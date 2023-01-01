package com.WooJoo09.service;

import com.WooJoo09.entity.Good;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Star;
import com.WooJoo09.entity.Trade;
import com.WooJoo09.repository.GoodRepository;
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
public class GoodService {
    private final GoodRepository goodRepository;
    private final TradeRepository tradeRepository;
    private final MemberRepository memberRepository;

    public Map<String,String> goodInsert(Long tradeNum, Long memberNum){
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        if(goodRepository.findByGoodTradeNumAndGoodMemNum(trade, member).isEmpty()){
            Good good = new Good();
            good.setGoodMemNum(member);
            good.setGoodTradeNum(trade);
            Good savedGood = goodRepository.save(good);
            log.info(savedGood.toString());
            map.put("completeGood", "OK");
        } else map.put("completeGood", "duplicate");
        return map;
    }
}
