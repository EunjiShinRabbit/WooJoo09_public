package com.WooJoo09.webSocket;

import com.fasterxml.jackson.core.JsonParser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, IMG
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime time;
}
