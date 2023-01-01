package com.WooJoo09.service;

import com.WooJoo09.constant.AcceptTrade;
import com.WooJoo09.constant.IsRead;
import com.WooJoo09.constant.MsgType;
import com.WooJoo09.entity.Chat;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Partner;
import com.WooJoo09.entity.Trade;
import com.WooJoo09.repository.*;
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
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final TradeRepository tradeRepository;
    private final MemberRepository memberRepository;
    private final ChatRepository chatRepository;

    public Map<String,String> partnerInsert(Long tradeNum, Long memberNum){
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        if(partnerRepository.findByTradeNumAndPartMemNum(trade, member).isEmpty()){
            Partner partner = new Partner();
            partner.setPartMemNum(member);
            partner.setTradeNum(trade);
            partner.setAcceptTrade(AcceptTrade.REJECT);
            Partner savedPartner = partnerRepository.save(partner);
            log.info(savedPartner.toString());
            if(chatRepository.findByPartnerNum(partner).isEmpty()){
                Chat chat = new Chat();
                chat.setSender(member);
                chat.setPartnerNum(partner);
                chat.setChatContent("채팅방이 개설되었습니다");
                chat.setMsgType(MsgType.ENTER);
                chat.setIsRead(IsRead.UNREAD);
                Chat savedChat = chatRepository.save(chat);
                log.info(savedChat.toString());
                map.put("completePartner", "OK");
                map.put("partnerNum", savedPartner.getPartnerNum().toString());
            } else map.put("completePartner", "duplicateChat");
        } else map.put("completePartner", "duplicate");
        return map;
    }

    public Map<String,String> partnerDelete(Long tradeNum, Long memberNum){
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        List<Partner> partner = partnerRepository.findByTradeNumAndPartMemNum(trade, member);
        if(partner.isEmpty()){
            map.put("deletePartner", "notData");
        } else{
            for(Partner e : partner){
                e.setAcceptTrade(AcceptTrade.DELETE);
                Partner savedPartner = partnerRepository.save(e);
            }
            map.put("deletePartner", "OK");
        }
        return map;
    }

    public Map<String,String> partnerAccept(Long tradeNum, Long memberNum){
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        List<Partner> partner = partnerRepository.findByTradeNumAndPartMemNum(trade, member);
        if(partner.isEmpty()){
            map.put("deletePartner", "notData");
        } else{
            for(Partner e : partner){
                e.setAcceptTrade(AcceptTrade.ACCEPT);
                Partner savedPartner = partnerRepository.save(e);
            }
            map.put("deletePartner", "OK");
        }
        return map;
    }

    // 진짜로 삭제하는 로직이라서 필요없음..
//    public Map<String,String> partnerDelete(Long tradeNum, Long memberNum){
//        Map<String, String> map = new HashMap<>();
//        Trade trade = tradeRepository.findByTradeNum(tradeNum);
//        Member member = memberRepository.findByMemberNum(memberNum);
//        List<Partner> partner = partnerRepository.findByTradeNumAndPartMemNum(trade, member);
//        if(partner.isEmpty()){
//            map.put("deletePartner", "notData");
//        } else{
//            for(Partner e : partner){
//                List<Chat> chats = chatRepository.findByPartnerNum(e);
//                chatRepository.deleteAll(chats);
//                partnerRepository.delete(e);
//            }
//            map.put("deletePartner", "OK");
//        }
//        return map;
//    }

    public boolean accountsendService(Long partnerNum, String bank, String account, String accountholder) {
//        Partner partner = partnerRepository.findByPartnerNum(partnerNum);
//        Member member = memberRepository.findByMemberNum(memberNum);
//        Chat chat = new Chat();
        Partner partner= partnerRepository.findByPartnerNum(partnerNum);
        partner.setBank(bank);
        partner.setAccountNum(account);
        partner.setAccountHolder(accountholder);

        Partner savedAcoount = partnerRepository.save(partner);
        log.info(savedAcoount.toString());
        return true;
    }
    public boolean deliverysendService(Long partnerNum, String deliveryCompany, String deliveryNum) {
        // 전송 받은 값만 저장하기 위해서 findBypartnerNum으로 찾고 해당 값만 저장
        Partner partner= partnerRepository.findByPartnerNum(partnerNum);
        partner.setDeliveryCompany(deliveryCompany);
        partner.setDeliveryNum(deliveryNum);

        Partner savedDeliveryNum = partnerRepository.save(partner);
        log.info(savedDeliveryNum.toString());
        return true;
    }
    public boolean deliveryaddrsendService(Long partnerNum, String deliveryAddress, String deliveryName, String deliveryPhone) {
        // 전송 받은 값만 저장하기 위해서 findByPartnerNum으로 찾고 해당 값만 저장
        Partner partner= partnerRepository.findByPartnerNum(partnerNum);
        partner.setDeliveryAddress(deliveryAddress);
        partner.setDeliveryName(deliveryName);
        partner.setDeliveryPhone(deliveryPhone);

        Partner savedDeliveryAddr = partnerRepository.save(partner);
        log.info(savedDeliveryAddr.toString());
        return true;
    }
    public List<?> chatDeliSelectService(int partnerNum){
        List<Map<String, List<?>>> result = new ArrayList<>();
        Map<String, List<?>>map = new HashMap<>();
        map.put("chatDeliaddress", partnerRepository.chatDeliSelect(partnerNum));
        System.out.print(map);
        for(int i = 0; i < map.size(); i++){
            result.add(map);
        }
        return result;
    }


    public List<?> chatAccountSelectService(int partnerNum){
        List<Map<String, List<?>>> result = new ArrayList<>();
        Map<String, List<?>>map = new HashMap<>();
        map.put("chatAccount", partnerRepository.chatAccountSelect(partnerNum));
        System.out.print(map);
        for(int i = 0; i < map.size(); i++){
            result.add(map);
        }
        return result;
    }

    public List<?> chatDeliveryNumSelectService(int partnerNum){
        List<Map<String, List<?>>> result = new ArrayList<>();
        Map<String, List<?>>map = new HashMap<>();
        map.put("chatDeliNum", partnerRepository.chatDeliveryNumSelect(partnerNum));
        System.out.print(map);
        for(int i = 0; i < map.size(); i++){
            result.add(map);
        }
        return result;
    }


}
