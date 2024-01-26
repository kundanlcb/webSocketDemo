package com.cm.webSocketDemo;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class CmWebSocketHandler extends TextWebSocketHandler {
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
            // Handle incoming messages here
        String receivedMessage = (String) message.getPayload();
        System.out.println(receivedMessage);
            // Process the message and send a response if needed
        session.sendMessage(new TextMessage("Received: " + receivedMessage));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Perform actions when a new WebSocket connection is established
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
            // Perform actions when a WebSocket connection is closed
    }
}