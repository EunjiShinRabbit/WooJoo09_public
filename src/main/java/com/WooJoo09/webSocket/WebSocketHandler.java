package com.WooJoo09.webSocket;

import com.WooJoo09.entity.Chat;
import com.WooJoo09.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component // bean 에 등록하겠다
public class WebSocketHandler extends TextWebSocketHandler {
    // 텍스트만 받을거기 때문에 TextWebSocketHandler 를 상속받음
    private final ObjectMapper objectMapper;
    // jackson 잭슨(제이슨을 파싱하는 도구 중 하나) 소속으로 프론트에서 오는것을 파싱해야되어서 필요
    // Servlet 에서 썼던 simpleJSON 같은 기능
    // chatMessage 객체로 변경하기 위해 사용
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.warn("{}", payload);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom = chatService.findRoomById(chatMessage.getRoomId()).get();
        log.info("session : " + session.toString());
        log.info("chatMessage : " + chatMessage.toString());
        chatRoom.handlerActions(session, chatMessage, chatService);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.warn("커넥션 끊겨서 세션 삭제함~ 삭제하는 세션 : " + session.toString() );
        List<ChatRoom> chatRooms = chatService.findAllRoom();
        for(ChatRoom e : chatRooms){
            e.getSessions().remove(session);
        }
    }
}