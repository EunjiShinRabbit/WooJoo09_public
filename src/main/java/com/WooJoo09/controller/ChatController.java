package com.WooJoo09.controller;

import com.WooJoo09.service.ChatService;
import com.WooJoo09.service.PartnerService;
import com.WooJoo09.webSocket.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/developerkirby")
public class ChatController {
    private final JwtController jwtController;
    private final ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<String> createRoom(@RequestBody Map<String, String> Data) {
        String partnerNum = Data.get("partnerNum");
        if(chatService.findRoomById(partnerNum).isEmpty()){
            ChatRoom room = chatService.createRoom(partnerNum);
            log.warn("room.getRoomId()" + room.getRoomId());
            return new ResponseEntity(room.getRoomId(), HttpStatus.OK);
        }
        else {
            ChatRoom room = chatService.findRoomById(partnerNum).get();
            log.warn("room.getRoomId()" + room.getRoomId());
            return new ResponseEntity(room.getRoomId(), HttpStatus.OK);
        }
    }
    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

    @PostMapping("/chatreadcheck")
    public ResponseEntity<Map<?,?>> tradeSearchSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        Map<String, String> result = new HashMap<>();
        if(token != null){
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            if (memberNumStr.equals("admin")) {
                result.put("state", "admin");
                return ResponseEntity.ok().body(result);
            }
            int memberNum = Integer.parseInt(memberNumStr);
            return ResponseEntity.ok().body(chatService.chatReadCheck(memberNum));
        }else {
            result.put("state", "logout");
            return ResponseEntity.ok().body(result);
        }
    }

    // 삽질...
//    @PostMapping("/chatinsert")
//    // 일부러 로그인 체크 안함(공구 참여후 쿠키 사라져서 로그인 실패하면 채팅방이 생성되지 않는 문제 발생함)
//    public ResponseEntity<Map<?, ?>> partnerInsert(
//            @CookieValue(value = "token", required = false) String token,
//            @RequestBody Map<String, String> Data) throws Exception {
//        Map<String ,String> map = new HashMap<>();
//        String partnerNumStr = Data.get("partnerNum"); // 파트너 번호
//        Long partnerNum = Long.parseLong(partnerNumStr);
//        String partMemNumStr = Data.get("partMemNum"); // 공구 신청하는 회원 번호
//        Long partMemNum = Long.parseLong(partMemNumStr);
//        map = chatService.chatInsert(partnerNum, partMemNum);
//        return ResponseEntity.ok().body(map);
//    }



    // 토큰 없이 채팅 리스트 조회
//    @PostMapping("/chatListSelect")
//    public ResponseEntity<List<?>> chatSelectList(
//            @CookieValue(value = "token", required = false) String token,
//            @RequestBody Map<String, String> Data) {
//        String memberNum = Data.get("memberNum");
//        int memberNumInt = Integer.parseInt(memberNum);
//        //log.info("들어온값 " + memberNum + " 변환된값 " + memberNumInte);
//        List<?> list = new ArrayList<>();
//        list =chatService.chatList(memberNumInt);
//        return new ResponseEntity(list, HttpStatus.OK);
//    }


    @PostMapping("/chatListSelect")
    public ResponseEntity<List<?>> chatSelectList(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        //int memberNum = Integer.parseInt(Data.get("target"));
        //int memberNumInt = Integer.parseInt(memberNum);
        //log.info("들어온값 " + memberNum + " 변환된값 " + memberNumInte);
        List<Object> list = new ArrayList<>();
        if (token != null) {
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            int tokenInt = Integer.parseInt(memberNumStr);
            if (memberNumStr.equals("admin")) {
                list = (List<Object>) chatService.chatList(tokenInt);
            }
            list = (List<Object>) chatService.chatList(tokenInt);
            list.add(memberNumStr);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/chatContentSelect")
    public ResponseEntity<Map<?,?>> chatSelectContent(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> Data) throws Exception {
        List<?> list = new ArrayList<>();
        if (token != null) {
            log.info("로그인상태입니당");
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            String partnerNumStr = Data.get("partner_num");
            int partnerNum = Integer.parseInt(partnerNumStr);
            log.info("들어온값 " + partnerNumStr + " 변환된값 " + partnerNum);
            list =  chatService.chatContentService(partnerNum, memberNum);
        }
       return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/chatInsert")
    @ResponseBody
    public ResponseEntity<Boolean> chatInsert(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> InsertChat) throws Exception {
        if (token != null) {
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            String partnerNumStr = InsertChat.get("partner_num");
            Long partnerNum = Long.parseLong(partnerNumStr);
            String inputMsg = InsertChat.get("inputMsg");
            String msgType = InsertChat.get("msgType");

            return ResponseEntity.ok(chatService.ChatInsertService(partnerNum, inputMsg, memberNum, msgType));
        }
        else return ResponseEntity.ok(false);
    }

    @PostMapping("/chatIsReadInsert")
    @ResponseBody
    public ResponseEntity<Boolean> chatIsReadInsert(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, String> InsertChat) throws Exception {
        if (token != null) {
            String memberNumStr = jwtController.tokenCheck(token);
            Long memberNum = Long.parseLong(memberNumStr);
            String partnerNumStr = InsertChat.get("partner_num");
            Long partnerNum = Long.parseLong(partnerNumStr);
            String inputMsg = InsertChat.get("inputMsg");
            String msgType = InsertChat.get("msgType");

            return ResponseEntity.ok(chatService.ChatInsertService(partnerNum, inputMsg, memberNum, msgType));
        }
        else return ResponseEntity.ok(false);
    }
//    @PostMapping("/chatISReadUpdate")
//    @ResponseBody
//    public ResponseEntity<Map<?,?>> chatISReadUpdate(
//            @CookieValue(value = "token" , required = false) String token,
//            @RequestBody Map<String, String> Data) throws Exception{
//        Map<String, String> map = new HashMap<>();
//        String partner = Data.get("partnerNum");
//        Long partnerNum = Long.parseLong(partner);
//        String member= Data.get("memberNum");
//        Long memberNum = Long.parseLong(member);
//        if(token != null){
//            String memberNumStr = jwtController.tokenCheck(token);
//            map = chatService.isreadUpdate(partnerNum, memberNum);
//            return ResponseEntity.ok().body(map);
//        }else{
//            map.put("chatISReadUpdate", "isReadError");
//            return ResponseEntity.ok().body(map);
//        }
//    }

    @PostMapping("/adminchatselect")
    public ResponseEntity<Map<?, ?>> adminChatSelect(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
                map = chatService.adminChatSelect();
        }else {
            map.put("adminChatSelect", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/adminchatsearch")
    public ResponseEntity<Map<?, ?>> adminChatSearch(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        String target = (String) Data.get("target");
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
                map = chatService.adminChatSearch(target);
        }else {
            map.put("adminChatSearch", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/adminchatselectdetail")
    public ResponseEntity<Map<?, ?>> adminChatSelectDetail(
            @CookieValue(value = "token", required = false) String token,
            @RequestBody Map<String, Object> Data) throws Exception {
        String target = (String) Data.get("target");
        Map<String ,Object> map = new HashMap<>();
        if(token != null){
            String memberNumStr = jwtController.tokenCheck(token);
            if(memberNumStr.equals("admin"))
                map = chatService.adminChatSelectDetail(target);
        }else {
            map.put("adminChatSelectDetail", "permissionError");
        }
        return ResponseEntity.ok().body(map);
    }
}
