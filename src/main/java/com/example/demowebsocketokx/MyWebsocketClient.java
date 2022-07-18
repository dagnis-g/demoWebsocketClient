package com.example.demowebsocketokx;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyWebsocketClient {

    private final MyWebsocketHandler myWebsocketHandler;
    private static String URL = "wss://wspap.okx.com:8443/ws/v5/private?brokerId=9999";
    
    @EventListener(ApplicationReadyEvent.class)
    public void connect() {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("x-simulated-trading", "1");
        client.doHandshake(myWebsocketHandler, headers, URI.create(URL));

        log.info("Connecting");
    }
}
