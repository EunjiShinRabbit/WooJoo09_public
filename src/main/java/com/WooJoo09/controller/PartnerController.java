package com.WooJoo09.controller;

import com.WooJoo09.constant.IsRead;
import com.WooJoo09.constant.MsgType;
import com.WooJoo09.entity.Chat;
import com.WooJoo09.entity.Partner;
import com.WooJoo09.repository.PartnerRepository;
import com.WooJoo09.service.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/developerkirby")
public class PartnerController {
    private final PartnerService partnerService;
    private final JwtController jwtController;

    @PostMapping("/partnerinsert")
    public ResponseEntity<Map<?, ?>> partnerInsert(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<String ,String> map = new HashMap<>();
        String target = Data.get("target");
        Long tradeNum = Long.parseLong(target);
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map = partnerService.partnerInsert(tradeNum, memberNum);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("completePartner", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }

    @PostMapping("/partnerdelete") // 파트너 쪽에서 거래 거절하는거(공구 나가기)
    public ResponseEntity<Map<?, ?>> partnerDelete(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<String ,String> map = new HashMap<>();
        String target = Data.get("target"); // 파트너 번호
        Long tradeNum = Long.parseLong(target);
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map = partnerService.partnerDelete(tradeNum, memberNum);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("deletePartner", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }

    @PostMapping("/partnerdeletehost") // 호스트 쪽에서 거래 거절하는거
    public ResponseEntity<Map<?, ?>> partnerDeleteHost(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<String ,String> map = new HashMap<>();
        String target = Data.get("target"); // 거래 번호
        Long tradeNum = Long.parseLong(target);
        String partner = Data.get("partner"); // 파트너 멤버 번호
        Long partMemNum = Long.parseLong(partner);
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token); // 토큰 체크는 정상적으로 진행
            map = partnerService.partnerDelete(tradeNum, partMemNum);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("deletePartnerHost", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }

    @PostMapping("/partneraccept") // 호스트 쪽에서 거래 승인하는거
    public ResponseEntity<Map<?, ?>> partnerAccept(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<String ,String> map = new HashMap<>();
        String target = Data.get("target"); // 거래 번호
        Long tradeNum = Long.parseLong(target);
        String partner = Data.get("partner"); // 파트너 멤버 번호
        Long partMemNum = Long.parseLong(partner);
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token); // 토큰 체크는 정상적으로 진행
            map = partnerService.partnerAccept(tradeNum, partMemNum);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("deletePartnerHost", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }

    @PostMapping("/accountsend")
    @ResponseBody
    public ResponseEntity<Boolean> accountsend(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> sendAccount) throws Exception {
        if (token != null) {
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
//            Map partnerNumMap = (LinkedHashMap)sendAccount.get("partner_num");
            String partnerNumStr = (String) sendAccount.get("partner_num");
            Long partnerNum = Long.parseLong(partnerNumStr);
            log.warn("partner_num : " + partnerNum);
            String bank = (String)sendAccount.get("bank");
            String account = (String)sendAccount.get("account");
            String accountholder = (String)sendAccount.get("accountholder");

            return ResponseEntity.ok(partnerService.accountsendService(partnerNum, bank, account, accountholder));
        }
        else return ResponseEntity.ok(false);
    }

    @PostMapping("/deliverysend")
    @ResponseBody
    public ResponseEntity<Boolean> deliverysend(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> sendDelivery) throws Exception {
        if (token != null) {
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
//            Map partnerNumMap = (LinkedHashMap)sendDelivery.get("partner_num");
            String partnerNumStr = (String) sendDelivery.get("partner_num");
            Long partnerNum = Long.parseLong(partnerNumStr);
            log.warn("partner_num : " + partnerNum);
            String deliveryCompany = (String)sendDelivery.get("deliveryCompany");
            String deliveryNum = (String)sendDelivery.get("deliveryNum");

            return ResponseEntity.ok(partnerService.deliverysendService(partnerNum, deliveryCompany, deliveryNum));
        }
        else return ResponseEntity.ok(false);
    }

    @PostMapping("/deliveryaddrsend")
    @ResponseBody
    public ResponseEntity<Boolean> deliveryaddrsend(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> sendDeliveryaddr) throws Exception {
        if (token != null) {
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            Map partnerNumMap = (LinkedHashMap)sendDeliveryaddr.get("partner_num");
            String partnerNumStr = (String) partnerNumMap.get("partner_num");
            Long partnerNum = Long.parseLong(partnerNumStr);
            log.warn("partner_num : " + partnerNum);
            String deliveryAddress = (String)sendDeliveryaddr.get("deliveryAddress");
            String deliveryName = (String)sendDeliveryaddr.get("deliveryName");
            String deliveryPhone = (String)sendDeliveryaddr.get("deliveryPhone");

            return ResponseEntity.ok(partnerService.deliveryaddrsendService(partnerNum, deliveryAddress, deliveryName, deliveryPhone));
        }
        else return ResponseEntity.ok(false);
    }
    @PostMapping("/chatDeliSelect")
    public ResponseEntity<Map<?,?>> chatDeliSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        String partnerNumStr = Data.get("partner_num");
        int partnerNum = Integer.parseInt(partnerNumStr);
        log.info("들어온값 " + partnerNumStr + " 변환된값 " + partnerNum);
        Map<?, ?> map = new HashMap<>();
        List<?> list = new ArrayList<>();
        list =  partnerService.chatDeliSelectService(partnerNum);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/chatAccountSelect")
    public ResponseEntity<Map<?,?>> chatAccountSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        String partnerNumStr = Data.get("partner_num");
        int partnerNum = Integer.parseInt(partnerNumStr);
        log.info("들어온값 " + partnerNumStr + " 변환된값 " + partnerNum);
        Map<?, ?> map = new HashMap<>();
        List<?> list = new ArrayList<>();
        list =  partnerService.chatAccountSelectService(partnerNum);
        return new ResponseEntity(list, HttpStatus.OK);
    }


    @PostMapping("/chatDeliNumSelect")
    public ResponseEntity<Map<?,?>> chatDeliNumSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        String partnerNumStr = Data.get("partner_num");
        int partnerNum = Integer.parseInt(partnerNumStr);
        log.info("들어온값 " + partnerNumStr + " 변환된값 " + partnerNum);
        Map<?, ?> map = new HashMap<>();
        List<?> list = new ArrayList<>();
        list =  partnerService.chatDeliveryNumSelectService(partnerNum);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
