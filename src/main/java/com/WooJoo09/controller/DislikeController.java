package com.WooJoo09.controller;

import com.WooJoo09.service.DislikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/developerkirby")
public class DislikeController {
    private final DislikeService dislikeService;
    private final JwtController jwtController;

    @PostMapping("/dislikeinsert")
    public ResponseEntity<Map<?, ?>> starInsert(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<String ,String> map = new HashMap<>();
        String target = Data.get("target");
        Long tradeNum = Long.parseLong(target);
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            map = dislikeService.dislikeInsert(tradeNum, memberNum);
            return ResponseEntity.ok().body(map);
        }else {
            map.put("completeDislike", "loginError");
            return ResponseEntity.ok().body(map);
        }
    }
}
