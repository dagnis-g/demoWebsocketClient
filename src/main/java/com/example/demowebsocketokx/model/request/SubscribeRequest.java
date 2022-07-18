package com.example.demowebsocketokx.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SubscribeRequest {
    private String op;
    private List<SubscribeArg> args;
}
