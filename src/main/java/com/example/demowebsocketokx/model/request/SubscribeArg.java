package com.example.demowebsocketokx.model.request;

import lombok.Builder;

@Builder
public class SubscribeArg {
    private String channel;
    private String instType;
}
