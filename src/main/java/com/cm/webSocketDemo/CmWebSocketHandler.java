package com.cm.webSocketDemo;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CmWebSocketHandler extends TextWebSocketHandler {
    private static final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
            // Handle incoming messages here
        String receivedMessage = (String) message.getPayload();
        System.out.println(receivedMessage);
            // Process the message and send a response if needed
        String responseMessage = "Received: " + receivedMessage;
        session.sendMessage(new TextMessage(responseMessage));
        forwardMessageToAllClients(responseMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Perform actions when a new WebSocket connection is established
        // Add the new session to the list of active sessions
        sessions.add(session);

        // Optionally, you can send a welcome message to the new client
        try {
            session.sendMessage(new TextMessage("Welcome to the WebSocket server!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Perform actions when a WebSocket connection is closed

        // Remove the closed session from the list of active sessions
        sessions.remove(session);
    }

    // Helper method to forward a message to all connected clients
    private void forwardMessageToAllClients(String message) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}