package com.WooJoo09.service;

import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Star;
import com.WooJoo09.entity.Trade;
import com.WooJoo09.repository.MemberRepository;
import com.WooJoo09.repository.StarRepository;
import com.WooJoo09.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class StarService {
    private final StarRepository starRepository;
    private final TradeRepository tradeRepository;
    private final MemberRepository memberRepository;

    public Map<String,String> starInsert(Long tradeNum, Long memberNum){
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        if(starRepository.findByTradeNumAndMemberNum(trade, member).isEmpty()){
            Star star = new Star();
            star.setMemberNum(member);
            star.setTradeNum(trade);
            Star savedStar = starRepository.save(star);
            log.info(savedStar.toString());
            map.put("myStar", "1");
        } else map.put("myStar", "duplicate");
        return map;
    }

    public Map<String,String> starDelete(Long tradeNum, Long memberNum){
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        List<Star> star = starRepository.findByTradeNumAndMemberNum(trade, member);
        if(star.isEmpty()){
            map.put("myStar", "notData");
        } else{
            for(Star e : star){
                starRepository.delete(e);
            }
            map.put("myStar", "0");
        }
        return map;
    }
}
