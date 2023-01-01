package com.WooJoo09.controller;

import com.WooJoo09.service.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/developerkirby")
public class BannerController {
    private final BannerService bannerService;
    private final JwtController jwtController;

    @PostMapping("/adminbannerselect")
    public ResponseEntity<Map<?,?>> adminBannerSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<?,?> map = new HashMap<>();
        map = bannerService.adminBannerSelect();
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/bannerselect")
    public ResponseEntity<Map<?,?>> bannerSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<?,?> map = new HashMap<>();
        map = bannerService.bannerSelect();
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/bannerinsert")
    public ResponseEntity<Map<?,?>> bannerInsert(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        String bannerName = (String) Data.get("bannerName");
        String imgUrl = (String) Data.get("imgUrl");
        String directUrl = (String) Data.get("directUrl");
        Map<String, String> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map = bannerService.bannerInsert(bannerName, imgUrl, directUrl);
            }
            else {
                map.put("bannerInsert", "permissionError");
            }
        }
        else {
            map.put("bannerInsert", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/bannerupdate")
    public ResponseEntity<Map<?,?>> bannerUpdate(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        String bannerNumStr = (String) Data.get("bannerNum");
        Long bannerNum = Long.parseLong(bannerNumStr);
        String bannerName = (String) Data.get("bannerName");
        String imgUrl = (String) Data.get("imgUrl");
        String directUrl = (String) Data.get("directUrl");
        String isActive = (String) Data.get("isActive");
        Map<String, String> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map = bannerService.bannerUpdate(bannerNum, bannerName, imgUrl, directUrl, isActive);
            }
            else {
                map.put("bannerUpdate", "permissionError");
            }
        }
        else {
            map.put("bannerUpdate", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/bannerdelete")
    public ResponseEntity<Map<?,?>> bannerDelete(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        List<Integer> bannerNums = (List<Integer>) Data.get("bannerNum");
        Map<String, String> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                map = bannerService.bannerDelete(bannerNums);
            }
            else {
                map.put("bannerDelete", "permissionError");
            }
        }
        else {
            map.put("bannerDelete", "loginError");
        }
        return ResponseEntity.ok().body(map);
    }

//    @PostMapping("/bannerdelete")
//    public ResponseEntity<Map<?,?>> bannerDelete(
//            @CookieValue(value = "token", required = false) String token,
//            @RequestBody Map<String, Object> Data) throws Exception {
//        String bannerNumStr = (String) Data.get("bannerNum");
//        Long bannerNum = Long.parseLong(bannerNumStr);
//        Map<String, String> map = new HashMap<>();
//        if(token != null){
//            String memberNumStr = jwtController.tokenCheck(token);
//            if (memberNumStr.equals("admin")) {
//                map = bannerService.bannerDelete(bannerNum);
//            }
//            else {
//                map.put("bannerDelete", "authorityError");
//            }
//        }
//        else {
//            map.put("bannerDelete", "loginError");
//        }
//        return ResponseEntity.ok().body(map);
//    }
}
