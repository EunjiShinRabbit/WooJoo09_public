package com.WooJoo09.controller;

import ch.qos.logback.core.rolling.helper.TokenConverter;
import com.WooJoo09.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/developerkirby")
public class TradeController {
    private final JwtController jwtController;
    private final TradeService tradeService;

//    public TradeController(TradeService tradeService) {
//        this.tradeService = tradeService;
//    }

    @PostMapping("/searchselect")
    public ResponseEntity<Page<?>> tradeSearchSelect(
            @CookieValue(value = "token", required = false) String token,
             @RequestBody Map<String, String> Data) throws Exception {
        int page = Integer.parseInt(Data.get("page"));
        int size = Integer.parseInt(Data.get("size"));
        String target = Data.get("target");
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) return ResponseEntity.ok().body(tradeService.tradeSearchSelect(target, page, size));
            int memberNum = Integer.parseInt(memberNumStr);
            return ResponseEntity.ok().body(tradeService.tradeSearchSelectLogin(target, memberNum, page, size));
        }else return ResponseEntity.ok().body(tradeService.tradeSearchSelect(target, page, size));
    }

    @PostMapping("/tradeselect")
    public ResponseEntity<Page<?>> tradeSearchOption(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        int page = Integer.parseInt(Data.get("page"));
        int size = Integer.parseInt(Data.get("size"));
        String option = Data.get("option");
        String city = Data.get("city");
        String town = Data.get("town");
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                if (town.length() != 0){
                    return ResponseEntity.ok().body(tradeService.tradeSelectOptionTown(option, town, page, size));
                } else if (city.length() != 0){
                    return ResponseEntity.ok().body(tradeService.tradeSelectOptionCity(option, city, page, size));
                } else return ResponseEntity.ok().body(tradeService.tradeSelectOption(option, page, size));
            }
            int memberNum = Integer.parseInt(memberNumStr);
            if (town.length() != 0){
                return ResponseEntity.ok().body(tradeService.tradeSelectOptionTownLogin(option, town, memberNum, page, size));
            } else if (city.length() != 0){
                return ResponseEntity.ok().body(tradeService.tradeSelectOptionCityLogin(option, city, memberNum, page, size));
            } else return ResponseEntity.ok().body(tradeService.tradeSelectOptionLogin(option, memberNum, page, size));
        }
        else {
            if (town.length() != 0){
                return ResponseEntity.ok().body(tradeService.tradeSelectOptionTown(option, town, page, size));
            } else if (city.length() != 0){
                return ResponseEntity.ok().body(tradeService.tradeSelectOptionCity(option, city, page, size));
            } else return ResponseEntity.ok().body(tradeService.tradeSelectOption(option, page, size));
        }
    }

    @PostMapping("/tradeselectcategory")
    public ResponseEntity<Page<?>> tradeSearchOptionCategory(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        int page = Integer.parseInt(Data.get("page"));
        int size = Integer.parseInt(Data.get("size"));
        String option = Data.get("option");
        String city = Data.get("city");
        String town = Data.get("town");
        String category = Data.get("category");
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                if (town.length() != 0){
                    return ResponseEntity.ok().body(tradeService.tradeSelectOptionTownCategory(category, option, town, page, size));
                } else if (city.length() != 0){
                    return ResponseEntity.ok().body(tradeService.tradeSelectOptionCityCategory(category, option, city, page, size));
                } else return ResponseEntity.ok().body(tradeService.tradeSelectOptionCategory(category, option, page, size));
            }
            int memberNum = Integer.parseInt(memberNumStr);
            if (town.length() != 0){
                return ResponseEntity.ok().body(tradeService.tradeSelectOptionTownLoginCategory(category, option, town, memberNum, page, size));
            } else if (city.length() != 0){
                return ResponseEntity.ok().body(tradeService.tradeSelectOptionCityLoginCategory(category, option, city, memberNum, page, size));
            } else return ResponseEntity.ok().body(tradeService.tradeSelectOptionLoginCategory(category, option, memberNum, page, size));
        }
        else {
            if (town.length() != 0){
                return ResponseEntity.ok().body(tradeService.tradeSelectOptionTownCategory(category, option, town, page, size));
            } else if (city.length() != 0){
                return ResponseEntity.ok().body(tradeService.tradeSelectOptionCityCategory(category, option, city, page, size));
            } else return ResponseEntity.ok().body(tradeService.tradeSelectOptionCategory(category, option, page, size));
        }
    }

    @PostMapping("/tradedetailselect")
    public ResponseEntity<Map<?,?>> tradeSelectDetail(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
//        List<?> list = new ArrayList<>();
        Map<?,?> map = new HashMap<>();
        int tradeNum = Integer.parseInt(Data.get("target"));
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map = tradeService.tradeDetail(tradeNum);
            }
            else {
                int memberNum = Integer.parseInt(memberNumStr);
                map = tradeService.tradeDetailLogin(tradeNum, memberNum);
            }
        }
        else {
            map = tradeService.tradeDetail(tradeNum);
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/tradedetailimgselect")
    public ResponseEntity<List<?>> tradeSelectDetailImage(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        int tradeNum = Integer.parseInt(Data.get("target"));
        List<?> list = new ArrayList<>();
        list= tradeService.tradeDetailImage(tradeNum);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/tradeinsert")
    public ResponseEntity<Map<?, ?>> tradeInsert(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        // DTO로 하는게 더 편하려나 모르겠다
        List<String> imgUrl = (List<String>) Data.get("imgUrl");
        String representUrl = (String) Data.get("representUrl");
        String category = (String) Data.get("category");
        String product = (String) Data.get("product");
        int price = Integer.parseInt((String)Data.get("price"));
        int limitPartner = Integer.parseInt((String)Data.get("limitPartner"));
        String dueDateStr = (String)Data.get("dueDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = sdf.parse(dueDateStr);
        String tradeMethod = (String)Data.get("tradeMethod");
        String city = (String)Data.get("city");
        String town = (String)Data.get("town");
        String tradePlace = (String)Data.get("tradePlace");
        String productDetail = (String)Data.get("productDetail");
        Map<String ,String> map = new HashMap<>();
        if(token != null){ // 어드민은 애초에 버튼 노출 안되게 프론트에서 처리
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map = tradeService.tradeInsert(memberNum, imgUrl, representUrl, category, product, price, limitPartner,
                    dueDate, tradeMethod, city, town, tradePlace, productDetail);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("completeTrade", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }

    @PostMapping("/tradeupdate")
    public ResponseEntity<Map<?, ?>> tradeUpdate(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        // DTO로 하는게 더 편하려나 모르겠다
        String tradeNumStr = (String) Data.get("tradeNum");
        Long tradeNum = Long.parseLong(tradeNumStr);
        List<String> imgUrl = (List<String>) Data.get("imgUrl");
        String representUrl = (String) Data.get("representUrl");
        String category = (String) Data.get("category");
        String product = (String) Data.get("product");
        int price = Integer.parseInt((String)Data.get("price"));
        int limitPartner = Integer.parseInt((String)Data.get("limitPartner"));
        String dueDateStr = (String)Data.get("dueDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = sdf.parse(dueDateStr);
        String tradeMethod = (String)Data.get("tradeMethod");
        String city = (String)Data.get("city");
        String town = (String)Data.get("town");
        String tradePlace = (String)Data.get("tradePlace");
        String productDetail = (String)Data.get("productDetail");
        Map<String ,String> map = new HashMap<>();
        if(token != null){ // 어드민은 애초에 버튼 노출 안되게 프론트에서 처리
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map = tradeService.tradeUpdate(tradeNum, memberNum, imgUrl, representUrl, category, product, price, limitPartner,
                    dueDate, tradeMethod, city, town, tradePlace, productDetail);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("completeTrade", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }

    @PostMapping("/tradedelete")
    public ResponseEntity<Map<?, ?>> tradeDelete(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        // DTO로 하는게 더 편하려나 모르겠다
        String tradeNumStr = (String) Data.get("target");
        Long tradeNum = Long.parseLong(tradeNumStr);
        Map<String ,String> map = new HashMap<>();
        if(token != null){ // 어드민은 애초에 버튼 노출 안되게 프론트에서 처리
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token); // 토큰 유효성 확인
            map = tradeService.tradeDelete(tradeNum);
        }else {
            map.put("completeDeleteTrade", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/tradecount")
    public ResponseEntity<Map<?, ?>> tradeCount(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<String ,Object> map = new HashMap<>();
        if(token != null){ // 어드민은 애초에 버튼 노출 안되게 프론트에서 처리
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map.put("countTrade", tradeService.tradeCount(memberNum)+1);
            map.put("memberNum", memberNum);
        }else {
            map.put("countTrade", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/tradeclose") // 거래 마감하기 -> 인원 꽉 안찼더라도 채팅 안받고싶을때
    public ResponseEntity<Map<?, ?>> tradeClose(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<String ,String> map = new HashMap<>();
        String tradeNumStr = Data.get("tradeNum");
        Long tradeNum = Long.parseLong(tradeNumStr);
        if(token != null){ // 어드민은 애초에 버튼 노출 안되게 프론트에서 처리
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map = tradeService.tradeClose(tradeNum);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("closeTrade", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }

    @PostMapping("/tradefinish") // 거래 종료하기
    public ResponseEntity<Map<?, ?>> tradeFinish(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String > Data) throws Exception {
        Map<String ,String> map = new HashMap<>();
        String tradeNumStr = Data.get("tradeNum");
        Long tradeNum = Long.parseLong(tradeNumStr);
        if(token != null){ // 어드민은 애초에 버튼 노출 안되게 프론트에서 처리
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map = tradeService.tradeFinish(tradeNum);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("finishTrade", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }
    @PostMapping("/tradeimgupdate")
    public ResponseEntity<Map<?, ?>> tradeImageUpdate(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Long tradeNum = Long.parseLong(Data.get("tradeNum"));
        Map<String ,?> map = new HashMap<>();
        map = tradeService.tradeImageUpdate(tradeNum);
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/countnthtrade")
    public ResponseEntity<Map<?, ?>> countNthTrade(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Long tradeNum = Long.parseLong(Data.get("tradeNum"));
        String memberNumStr = jwtController.tokenCheck(token);
        Long memberNum = Long.parseLong(memberNumStr);
        Map<String ,?> map = new HashMap<>();
        map = tradeService.countNthTrade(tradeNum, memberNum);
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/hosttradeselect")
    public ResponseEntity<Map<String,Object>> hostTradeSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        int page = Integer.parseInt(Data.get("page"));
        int size = Integer.parseInt(Data.get("size"));
        Map map = new HashMap<>();
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map.put("hostTradeSelect", "Admin");
            } else{
                Long memberNum = Long.parseLong(memberNumStr);
                map.put("hostTradeSelect", "OK");
                map.put("content", tradeService.hostTradeSelect(memberNum, page, size));
            }
        }else{
            map.put("hostTradeSelect", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/partnertradeselectreject")
    public ResponseEntity<Map<String,Object>> partnerTradeSelectReject(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        int page = Integer.parseInt(Data.get("page"));
        int size = Integer.parseInt(Data.get("size"));
        Map map = new HashMap<>();
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map.put("hostTradeSelect", "Admin");
            } else{
                Long memberNum = Long.parseLong(memberNumStr);
                map.put("hostTradeSelect", "OK");
                map.put("content", tradeService.partnerTradeSelectReject(memberNum, page, size));
            }
        }else{
            map.put("hostTradeSelect", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/partnertradeselectongoing")
    public ResponseEntity<Map<String,Object>> partnerTradeSelectOngoing(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        int page = Integer.parseInt(Data.get("page"));
        int size = Integer.parseInt(Data.get("size"));
        Map map = new HashMap<>();
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map.put("hostTradeSelect", "Admin");
            } else{
                Long memberNum = Long.parseLong(memberNumStr);
                map.put("hostTradeSelect", "OK");
                map.put("content", tradeService.partnerTradeSelectOngoing(memberNum, page, size));
            }
        }else{
            map.put("hostTradeSelect", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/partnertradeselectdone")
    public ResponseEntity<Map<String,Object>> partnerTradeSelectDone(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        int page = Integer.parseInt(Data.get("page"));
        int size = Integer.parseInt(Data.get("size"));
        Map map = new HashMap<>();
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map.put("hostTradeSelect", "Admin");
            } else{
                Long memberNum = Long.parseLong(memberNumStr);
                map.put("hostTradeSelect", "OK");
                map.put("content", tradeService.partnerTradeSelectDone(memberNum, page, size));
            }
        }else{
            map.put("hostTradeSelect", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/startradeselect")
    public ResponseEntity<Map<String,Object>> starTradeSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        int page = Integer.parseInt(Data.get("page"));
        int size = Integer.parseInt(Data.get("size"));
        Map map = new HashMap<>();
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map.put("hostTradeSelect", "Admin");
            } else{
                Long memberNum = Long.parseLong(memberNumStr);
                map.put("hostTradeSelect", "OK");
                map.put("content", tradeService.starTradeSelect(memberNum, page, size));
            }
        }else{
            map.put("hostTradeSelect", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/adminwriteselect")
    public ResponseEntity<Map<?, ?>> adminWriteSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
                map = tradeService.adminWriteSelect();
        }else {
            map.put("adminWriteSelect", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/adminwritesearch")
    public ResponseEntity<Map<?, ?>> adminWriteSearch(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        String target = (String) Data.get("target");
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
                map = tradeService.adminWriteSearch(target);
        }else {
            map.put("adminWriteSearch", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/adminwritedelete")
    public ResponseEntity<Map<?, ?>> adminWriteDelete(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        List<Integer> targets = (List<Integer>) Data.get("target");
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
                map = tradeService.adminWriteDelete(targets);
        }else {
            map.put("adminWriteDelete", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }



}
