package com.WooJoo09.service;

import com.WooJoo09.constant.IsActive;
import com.WooJoo09.constant.IsRead;
import com.WooJoo09.constant.MsgType;
import com.WooJoo09.entity.Chat;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Partner;
import com.WooJoo09.repository.ChatRepository;
import com.WooJoo09.repository.MemberRepository;
import com.WooJoo09.repository.PartnerRepository;
import com.WooJoo09.webSocket.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final PartnerRepository partnerRepository;
    private final MemberRepository memberRepository;

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms; // key 와 value, 개설된 방의정보를 가지고 옴

    @PostConstruct // 의존성 주입 이후 초기화를 수행하는 메소드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }
    public Optional<ChatRoom> findRoomById(String roomId) {
//        log.warn("chatRooms.get(roomId) : " + chatRooms.get(roomId).toString());
        return Optional.ofNullable(chatRooms.get(roomId));
    }

//    // 방을 만들기
//    public ChatRoom createRoom(String name) {
//        String randomId = UUID.randomUUID().toString();
//        log.info("UUID : " + randomId);
//        ChatRoom chatRoom = ChatRoom.builder()
//                .roomId(randomId)
//                .name(name)
//                .build();
//        chatRooms.put(randomId, chatRoom);
//        return chatRoom;
//    }

    // 방을 만들기
    public ChatRoom createRoom(String roomId) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(roomId)
                .build();
        chatRooms.put(roomId, chatRoom);
        log.info("chatRoom : " + chatRoom);
        return chatRoom;
    }

    // 제네릭 타입 T
    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch(IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public Map<?,?> chatReadCheck(int memberNum) {
        Map<String, String> result = new HashMap<>();
        result.put("countUnreadChat", chatRepository.chatReadCheck(memberNum));
        result.put("state", "login");
        return result;
    }

// ㅠㅠ 삽질...
//    public Map<String,String> chatInsert(Long partnerNum, Long memberNum){
//        Map<String, String> map = new HashMap<>();
//        Partner partner = partnerRepository.findByPartnerNum(partnerNum);
//        if(partner == null) {
//            map.put("completePartner", "notPartnerData");
//            return map;
//        }
//        Member member = memberRepository.findByMemberNum(memberNum);
//        if(member == null) {
//            map.put("completePartner", "notMemberData");
//            return map;
//        }
//        if(chatRepository.findByPartnerNum(partner).isEmpty()){
//            Chat chat = new Chat();
//            chat.setSender(member);
//            chat.setPartnerNum(partner);
//            chat.setChatContent("채팅방이 개설되었습니다");
//            chat.setMsgType(MsgType.ENTER);
//            chat.setIsRead(IsRead.UNREAD);
//            Chat savedChat = chatRepository.save(chat);
//            log.info(savedChat.toString());
//            map.put("completePartner", "OK");
//        } else map.put("completePartner", "duplicate");
//        return map;
//    }

    public List<?> chatList(int memberNum){
        List<Map<?,?>> result = new ArrayList<>();
        Map<String, List<Map<?,?>>> map = new HashMap<>();
        map.put("chatListContent", chatRepository.chatList(memberNum));
        System.out.print(map);
        for(int i = 0; i < map.size(); i++){
            result.add(map);
        }
        log.warn("result" + result);
        return result;
    }


    public List<?> chatContentService(int partnerNum, long memberNum){
        List<Map<String, List<?>>> result = new ArrayList<>();
        Map<String, List<?>>map = new HashMap<>();
        Partner partner = partnerRepository.findByPartnerNum((long) partnerNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        // 내가 아닌 상대방 채팅만 읽음처리 하기
        List<Chat> chats = chatRepository.findBySenderNotAndPartnerNum(member, partner);
        for(Chat e : chats){
            e.setIsRead(IsRead.READ);
            chatRepository.save(e);
        }
        map.put("chattingContent", chatRepository.chatContent(partnerNum));
        System.out.print(map);
        for(int i = 0; i < map.size(); i++){
            result.add(map);
        }
        return result;
    }
    public boolean ChatInsertService(Long partnerNum, String inputMsg, Long memberNum, String msgType) {
        Partner partner = partnerRepository.findByPartnerNum(partnerNum);
        Member member = memberRepository.findByMemberNum(memberNum);
        Chat chat = new Chat();
        chat.setPartnerNum(partner);
        chat.setChatContent(inputMsg);
        chat.setSender(member);
        chat.setIsRead(IsRead.UNREAD);
        if (msgType.equals("text")) chat.setMsgType(MsgType.TEXT);
        else if (msgType.equals("enter")) chat.setMsgType(MsgType.ENTER);
        else chat.setMsgType(MsgType.IMG);
        Chat savedChat = chatRepository.save(chat);
        log.info(savedChat.toString());
        return true;
    }
//    public Map<String, String> isreadUpdate(Long partnerNum, Long memberNum) {
//        Map<String, String> map = new HashMap<>();
//        Partner partner = partnerRepository.findByPartnerNum(partnerNum);
//        Member member = memberRepository.findByMemberNum(memberNum);
//        List<Chat> chat = chatRepository.findByPartnerNumAndSender(partner, member);
//        if(chat.isEmpty()){
//            map.put("isreadUpdate", "null");
//        }else{
//            for(Chat e : chat) {
//                e.setIsRead(IsRead.READ);
//                Chat savedIsReadUpdate = chatRepository.save(e);
//                log.info(savedIsReadUpdate.toString());
//            }
//            map.put("isreadUpdate", "OK");
//        }
//          return map;
//    }

    public Map<String, Object> adminChatSelect(){
        Map<String,Object> map = new HashMap<>();
        map.put("chatData", chatRepository.adminChatSelect());
        map.put("adminChatSelect", "OK");
        return map;
    }

    public Map<String, Object> adminChatSearch(String target){
        Map<String,Object> map = new HashMap<>();
        map.put("chatData", chatRepository.adminChatSearch("%"+target+"%"));
        map.put("adminChatSearch", "OK");
        return map;
    }

    public Map<String, Object> adminChatSelectDetail(String target){
        Map<String,Object> map = new HashMap<>();
        int partnerNum = Integer.parseInt(target);
        map.put("chatDetailData", chatRepository.chatContent(partnerNum));
        map.put("adminChatSelectDetail", "OK");
        return map;
    }

}
