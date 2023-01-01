package com.WooJoo09.service;

import com.WooJoo09.constant.*;
import com.WooJoo09.entity.*;
import com.WooJoo09.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class TradeService {
    private final TradeRepository tradeRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final ProductImgRepository productImgRepository;

    private final PartnerRepository partnerRepository;

    public Page<?> tradeSearchSelect(String target, int page, int size) {
        return tradeRepository.findTradeByProduct("%" + target + "%", PageRequest.of(page, size));
    }

    public Page<?> tradeSearchSelectLogin(String target, int memberNum, int page, int size) {
        return tradeRepository.findTradeByProductLogin("%" + target + "%", memberNum, PageRequest.of(page, size));
    }

    public Page<?> tradeSelectOption(String option, int page, int size) {
        return tradeRepository.findTradeOption(option, PageRequest.of(page, size));
    }
    public Page<?> tradeSelectOptionLogin(String option, int memberNum, int page, int size) {
        return tradeRepository.findTradeOptionLogin(option, memberNum, PageRequest.of(page, size));
    }

    public Page<?> tradeSelectOptionCity(String option, String city, int page, int size) {
        return tradeRepository.findTradeOptionCity(option, city, PageRequest.of(page, size));
    }
    public Page<?> tradeSelectOptionCityLogin(String option, String city, int memberNum, int page, int size) {
        return tradeRepository.findTradeOptionCityLogin(option, city, memberNum, PageRequest.of(page, size));
    }

    public Page<?> tradeSelectOptionTown(String option, String town, int page, int size) {
        return tradeRepository.findTradeOptionTown(option, town, PageRequest.of(page, size));
    }
    public Page<?> tradeSelectOptionTownLogin(String option, String town, int memberNum, int page, int size) {
        return tradeRepository.findTradeOptionTownLogin(option, town, memberNum, PageRequest.of(page, size));
    }

    public Page<?> tradeSelectOptionCategory(String category, String option, int page, int size) {
        return tradeRepository.findTradeOptionCategory(category, option, PageRequest.of(page, size));
    }
    public Page<?> tradeSelectOptionLoginCategory(String category, String option, int memberNum, int page, int size) {
        return tradeRepository.findTradeOptionLoginCategory(category, option, memberNum, PageRequest.of(page, size));
    }

    public Page<?> tradeSelectOptionCityCategory(String category, String option, String city, int page, int size) {
        return tradeRepository.findTradeOptionCityCategory(category, option, city, PageRequest.of(page, size));
    }
    public Page<?> tradeSelectOptionCityLoginCategory(String category, String option, String city, int memberNum, int page, int size) {
        return tradeRepository.findTradeOptionCityLoginCategory(category, option, city, memberNum, PageRequest.of(page, size));
    }

    public Page<?> tradeSelectOptionTownCategory(String category, String option, String town, int page, int size) {
        return tradeRepository.findTradeOptionTownCategory(category, option, town, PageRequest.of(page, size));
    }
    public Page<?> tradeSelectOptionTownLoginCategory(String category, String option, String town, int memberNum, int page, int size) {
        return tradeRepository.findTradeOptionTownLoginCategory(category, option, town, memberNum, PageRequest.of(page, size));
    }

    public Map<?, ?> tradeDetail(int tradeNum){
        Map<String,Map<?,?>> map = new HashMap<>();
        map.put("detail", tradeRepository.tradeDetailSelect(tradeNum));
        map.put("member", tradeRepository.tradeDetailMemberSelect(tradeNum));
        return map;
    }
    public Map<?, ?> tradeDetailLogin(int tradeNum, int memberNum){
        Map<String,Map<?,?>> map = new HashMap<>();
        map.put("detail", tradeRepository.tradeDetailSelectLogin(tradeNum, memberNum));
        map.put("member", tradeRepository.tradeDetailMemberSelect(tradeNum));
        return map;
    }
    public List<?> tradeDetailImage(int tradeNum){
        List<Map<?, ?>> list = new ArrayList<>();
        list = (tradeRepository.tradeDetailImgSelect(tradeNum));
        return list;
    }

    public Map<String, String> tradeInsert(Long memberNum, List<String> imgUrl, String representUrl, String categoryName, String product, int price,
    int limitPartner, Date dueDate, String tradeMethod, String city, String town, String tradePlace, String productDetail){
        Map<String ,String> map = new HashMap<>();
        Trade trade= new Trade();
        Member member = memberRepository.findByMemberNum(memberNum);
        trade.setHost(member);
        Category category = categoryRepository.findByCategoryName(categoryName);
        trade.setCategory(category);
        trade.setLimitPartner(limitPartner);
        trade.setProduct(product);
        trade.setPrice(price);
        trade.setCity(city);
        trade.setTown(town);
        trade.setDueDate(dueDate);
        switch (tradeMethod){
            case "both" : trade.setTradeMethod(TradeMethod.BOTH); break;
            case "delivery" : trade.setTradeMethod(TradeMethod.DELIVERY); break;
            case "direct" : trade.setTradeMethod(TradeMethod.DIRECT); break;
        }
        trade.setProductDetail(productDetail);
        trade.setTradePlace(tradePlace);
        trade.setDoneTrade(DoneTrade.ONGOING);
        Trade savedTrade = tradeRepository.save(trade);
        log.info(savedTrade.toString());
        if(productImgRepository.findByTradeNum(savedTrade).isEmpty() 
                && representUrl.length() > 0){
            log.info("이미지 있움");
            ProductImg productImg = new ProductImg();
            productImg.setTradeNum(savedTrade);
            productImg.setImgUrl(representUrl);
            productImg.setIsRepresent(IsRepresent.REPRESENT);
            ProductImg savedRepresentImg = productImgRepository.save(productImg);
            log.info(savedRepresentImg.toString());
            for (String e : imgUrl){
                ProductImg productImg2 = new ProductImg();
                productImg2.setTradeNum(savedTrade);
                productImg2.setImgUrl(e);
                productImg2.setIsRepresent(IsRepresent.NOREPRESENT);
                ProductImg savedRepresentImg2 = productImgRepository.save(productImg2);
                log.info(savedRepresentImg2.toString());
            }
            map.put("completeTrade", "OK");
        } else {
            map.put("completeTrade", "OK");
        }
        return map;
    }

    public Map<String, String> tradeUpdate(Long tradeNum, Long memberNum, List<String> imgUrl, String representUrl, String categoryName, String product, int price,
                                           int limitPartner, Date dueDate, String tradeMethod, String city, String town, String tradePlace, String productDetail){
        Map<String ,String> map = new HashMap<>();
        Trade trade= tradeRepository.findByTradeNum(tradeNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        trade.setHost(member);
        Category category = categoryRepository.findByCategoryName(categoryName);
        trade.setCategory(category);
        trade.setLimitPartner(limitPartner);
        trade.setProduct(product);
        trade.setPrice(price);
        trade.setCity(city);
        trade.setTown(town);
        trade.setDueDate(dueDate);
        switch (tradeMethod){
            case "both" : trade.setTradeMethod(TradeMethod.BOTH); break;
            case "delivery" : trade.setTradeMethod(TradeMethod.DELIVERY); break;
            case "direct" : trade.setTradeMethod(TradeMethod.DIRECT); break;
        }
        trade.setProductDetail(productDetail);
        trade.setTradePlace(tradePlace);
        trade.setDoneTrade(DoneTrade.ONGOING);
        Trade savedTrade = tradeRepository.save(trade);
        log.info(savedTrade.toString());
        List<ProductImg> imgList = productImgRepository.findByTradeNum(savedTrade);
        productImgRepository.deleteAll(imgList);

        ProductImg productImg = new ProductImg();
        productImg.setTradeNum(savedTrade);
        productImg.setImgUrl(representUrl);
        productImg.setIsRepresent(IsRepresent.REPRESENT);
        ProductImg savedRepresentImg = productImgRepository.save(productImg);
        log.info(savedRepresentImg.toString());
        for (String e : imgUrl){
            ProductImg productImg2 = new ProductImg();
            productImg2.setTradeNum(savedTrade);
            productImg2.setImgUrl(e);
            productImg2.setIsRepresent(IsRepresent.NOREPRESENT);
            ProductImg savedRepresentImg2 = productImgRepository.save(productImg2);
            log.info(savedRepresentImg2.toString());
            }
        map.put("completeTrade", "OK");
        return map;
    }

    public Map<String, String> tradeDelete(Long tradeNum){
        Map<String ,String> map = new HashMap<>();
        Trade trade= tradeRepository.findByTradeNum(tradeNum);
        if (trade == null) {
            map.put("completeDeleteTrade", "notData");
            return map;
        }
        trade.setDoneTrade(DoneTrade.DELETE);
        Trade savedTrade = tradeRepository.save(trade);
        List<Partner> partners = partnerRepository.findByTradeNum(trade);
        if(!partners.isEmpty()){
            for(Partner e : partners){
                e.setAcceptTrade(AcceptTrade.DELETE);
                Partner savedPartner = partnerRepository.save(e);
            }
        }
        log.info(savedTrade.toString());
        map.put("completeDeleteTrade", "OK");
        return map;
    }

    public Long tradeCount(Long memberNum){
        Member member = memberRepository.findByMemberNum(memberNum);
        return tradeRepository.countByHost(member);
    }

    public Map<String, String> tradeClose(Long tradeNum){
        Map<String ,String> map = new HashMap<>();
        Trade trade= tradeRepository.findByTradeNum(tradeNum);
        if (trade == null) {
            map.put("closeTrade", "notData");
            return map;
        }
        if (tradeRepository.findByTradeNumAndDoneTrade(tradeNum, DoneTrade.FULL).isEmpty()) {
            trade.setDoneTrade(DoneTrade.FULL);
            Trade savedTrade = tradeRepository.save(trade);
            log.info(savedTrade.toString());
            map.put("closeTrade", "OK");
        } else {
            map.put("closeTrade", "duplicate");
        }
        return map;
    }

    public Map<String, String> tradeFinish(Long tradeNum){
        Map<String ,String> map = new HashMap<>();
        Trade trade= tradeRepository.findByTradeNum(tradeNum);
        if (trade == null) {
            map.put("finishTrade", "notData");
            return map;
        }
        if (tradeRepository.findByTradeNumAndDoneTrade(tradeNum, DoneTrade.DONE).isEmpty()) {
            trade.setDoneTrade(DoneTrade.DONE);
            Trade savedTrade = tradeRepository.save(trade);
            log.info(savedTrade.toString());
            map.put("finishTrade", "OK");
        }else {
            map.put("finishTrade", "duplicate");
        }
        return map;
    }

    public Map<String, Object> tradeImageUpdate(Long tradeNum){
        Map<String ,Object> map = new HashMap<>();
        Trade trade= tradeRepository.findByTradeNum(tradeNum);
        if (trade == null) {
            map.put("tradeImageUpdate", "noTradeData");
            return map;
        }else {
            if (productImgRepository.findByTradeNumAndIsRepresent(trade, IsRepresent.REPRESENT).isEmpty()) {
                map.put("tradeImageUpdate", "noImgData");
            } else{
                List<ProductImg> representImg = productImgRepository.findByTradeNumAndIsRepresent(trade, IsRepresent.REPRESENT);
                List<String> list1 = new ArrayList<>();
                for (ProductImg e : representImg){
                    String str = e.getImgUrl();
                    list1.add(str);
                }
                map.put("representImg", list1);
                if(!productImgRepository.findByTradeNumAndIsRepresent(trade, IsRepresent.NOREPRESENT).isEmpty()){
                    List<ProductImg> imgList = productImgRepository.findByTradeNumAndIsRepresent(trade, IsRepresent.NOREPRESENT);
                    List<String> list2 = new ArrayList<>();
                    for (ProductImg e : imgList){
                        String str = e.getImgUrl();
                        list2.add(str);
                    }
                    map.put("images", list2);
                }
                map.put("tradeImageUpdate", "OK");
            }
        }
        return map;
    }

    public Map<String, String> countNthTrade(Long tradeNum, Long memberNum){
        Map<String ,String> map = new HashMap<>();
        Member member = memberRepository.findByMemberNum(memberNum);
        List<Trade> trades = tradeRepository.findByHostOrderByWriteDate(member);
        int cnt = 1;
        for (Trade e : trades){
            if(e.getTradeNum() == tradeNum){
                map.put("numOfTrade", String.valueOf(cnt));
                return map;
            }
            cnt++;
        }
        map.put("numOfTrade", "error");
        return map;
    }


    public Page<?> hostTradeSelect(Long memberNum, int page, int size) {
        return tradeRepository.hostTradeSelect(memberNum, PageRequest.of(page, size));
    }

    public Page<?> partnerTradeSelectReject(Long memberNum, int page, int size) {
        return tradeRepository.partnerTradeSelectReject(memberNum, PageRequest.of(page, size));
    }

    public Page<?> partnerTradeSelectOngoing(Long memberNum, int page, int size) {
        return tradeRepository.partnerTradeSelectOngoing(memberNum, PageRequest.of(page, size));
    }

    public Page<?> partnerTradeSelectDone(Long memberNum, int page, int size) {
        return tradeRepository.partnerTradeSelectDone(memberNum, PageRequest.of(page, size));
    }

    public Page<?> starTradeSelect(Long memberNum, int page, int size) {
        return tradeRepository.starTradeSelect(memberNum, PageRequest.of(page, size));
    }

    public Map<String, Object> adminWriteSelect(){
        Map<String,Object> map = new HashMap<>();
        map.put("writeData", tradeRepository.adminWriteSelect());
        map.put("adminWriteSelect", "OK");
        return map;
    }

    public Map<String, Object> adminWriteSearch(String target){
        Map<String,Object> map = new HashMap<>();
        map.put("writeData", tradeRepository.adminWriteSearch("%"+target+"%"));
        map.put("adminWriteSearch", "OK");
        return map;
    }

    public Map<String, Object> adminWriteDelete(List<Integer> targets){
        Map<String,Object> map = new HashMap<>();
        for (Integer e : targets){
            Long tradeNum = Long.valueOf(e);
            Trade trade = tradeRepository.findByTradeNum(tradeNum);
            trade.setDoneTrade(DoneTrade.DELETE);
            Trade savedTrade = tradeRepository.save(trade);
            log.info(savedTrade.toString());
        }
        map.put("adminWriteDelete", "OK");
        return map;
    }
}