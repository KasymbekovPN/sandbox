package org.kpn.ch17;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //<
        System.out.println("message: " + message.toString());
        //<
        session.sendMessage(new TextMessage(message.getPayload() + " !!!"));
    }
}
