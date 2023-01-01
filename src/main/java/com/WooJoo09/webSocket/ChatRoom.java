package com.WooJoo09.webSocket;

import com.WooJoo09.constant.MsgType;
import com.WooJoo09.entity.Chat;
import com.WooJoo09.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
@Slf4j
@Getter
public class ChatRoom {
    private String roomId;
//    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();
//    @Builder
//    public ChatRoom(String roomId, String name) {
//        this.roomId = roomId;
//        this.name = name;
//    }
    @Builder
    public ChatRoom(String roomId) {
        this.roomId = roomId;
    }
    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        log.info("handlerActions 실행?");
        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
        }
        sendMessage(chatMessage, chatService);
    }
    private <T> void sendMessage(T message, ChatService chatService) {
        // forEach() : 스트림의 요소를 하나씩 참조해서 람다식으로 처리하는 반복자
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }

}