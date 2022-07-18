package com.example.demowebsocketokx;

import com.example.demowebsocketokx.encoder.LoginRequestEncoder;
import com.example.demowebsocketokx.model.request.SubscribeArg;
import com.example.demowebsocketokx.model.request.SubscribeRequest;
import com.example.demowebsocketokx.model.response.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MyWebsocketHandler implements WebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished. session: {}", session);
        var login = new LoginRequestEncoder().encode();

        String loginJson = mapper.writeValueAsString(login);

        log.info("Sending login: {}", loginJson);
        session.sendMessage(new TextMessage(loginJson));

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("handleMessage. session: {}. message: {}", session, message);
        log.info("Payload {}", message.getPayload());

        //how to do this ????
        try {
            var loginResponse = mapper.readValue(message.getPayload().toString(), LoginResponse.class);

            var subscribeArg = SubscribeArg.builder()
                    .instType("ANY")
                    .channel("orders")
                    .build();
            List<SubscribeArg> args = new ArrayList<>();
            args.add(subscribeArg);
            var subscribeRequest = SubscribeRequest.builder()
                    .op("subscribe")
                    .args(args)
                    .build();

            if (loginResponse.getCode().equals("0")) {
                String subscribeJson = mapper.writeValueAsString(subscribeRequest);
                session.sendMessage(new TextMessage(subscribeJson));
            }

        } catch (Exception e) {
            log.info("Not login detail");
        }

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("handleTransportError. session: {}", session, exception);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("afterConnectionClosed. session: {}. closeStatus: {}", session, closeStatus);

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
