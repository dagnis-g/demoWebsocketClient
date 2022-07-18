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
    private static String URL = "wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999";
//    private static String URL = "ws://demo.piesocket.com/v3/channel_1?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self"; // for testing only

    @EventListener(ApplicationReadyEvent.class)
    public void connect() {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("x-simulated-trading", "1");
        client.doHandshake(myWebsocketHandler, headers, URI.create(URL));

        log.info("Connecting maybe");
    }
}
