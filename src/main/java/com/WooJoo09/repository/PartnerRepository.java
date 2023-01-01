package com.WooJoo09.repository;

import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Partner;
import com.WooJoo09.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByTradeNumAndPartMemNum(Trade tradeNum, Member partMemNum);
    Partner findByPartnerNum(Long partnerNum);

    List<Partner> findByTradeNum(Trade tradeNum);

    @Query(
            value = "select delivery_name, delivery_address, delivery_phone from partner p where partner_num = :partner_num" ,
            nativeQuery = true
    )
    List<Map<?,?>> chatDeliSelect (@Param("partner_num") int partnerNum);

    @Query(
            value = "select account_holder, account_num, bank from partner p where partner_num = :partner_num" ,
            nativeQuery = true
    )
    List<Map<?,?>> chatAccountSelect (@Param("partner_num") int partnerNum);

    @Query(
            value = "select delivery_company, delivery_num from partner p where partner_num = :partner_num" ,
            nativeQuery = true
    )
    List<Map<?,?>> chatDeliveryNumSelect (@Param("partner_num") int partnerNum);
}
