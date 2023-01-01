package com.WooJoo09.service;

import com.WooJoo09.entity.Dislike;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Star;
import com.WooJoo09.entity.Trade;
import com.WooJoo09.repository.DislikeRepository;
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
public class DislikeService {
    private final DislikeRepository dislikeRepository;
    private final TradeRepository tradeRepository;
    private final MemberRepository memberRepository;

    public Map<String,String> dislikeInsert(Long tradeNum, Long memberNum){
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        if(dislikeRepository.findByDisTradeNumAndDisMemNum(trade, member).isEmpty()){
            Dislike dislike = new Dislike();
            dislike.setDisMemNum(member);
            dislike.setDisTradeNum(trade);
            Dislike savedDislike = dislikeRepository.save(dislike);
            log.info(savedDislike.toString());
            map.put("completeDislike", "OK");
        } else map.put("completeDislike", "duplicate");
        return map;
    }
}
