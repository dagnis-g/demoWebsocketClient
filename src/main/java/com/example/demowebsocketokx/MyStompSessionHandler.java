package com.example.demowebsocketokx;

import com.example.demowebsocketokx.encoder.LoginRequestEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

@Slf4j
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("New session established : " + session.getSessionId());
//        session.subscribe("/topic/messages", this);
//        log.info("Subscribed to /topic/messages");
        LoginRequestEncoder encoder = new LoginRequestEncoder();
        try {
            session.send("wss://wspap.okx.com:8443/ws/v5/private?brokerId="
//                            + Secrets.BROKER_ID,
                            + "9999",
                    encoder.encode());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        log.info(String.valueOf(headers));
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Received payload: " + payload);
        log.info("Received headers: " + headers);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error(String.valueOf(session.toString()));
        log.error(String.valueOf(exception.toString()));
    }

}
