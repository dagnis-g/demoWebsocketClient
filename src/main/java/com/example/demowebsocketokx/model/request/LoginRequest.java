package com.example.demowebsocketokx.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Builder
@Data
public class LoginRequest {
    private String op;
    private List<LoginArg> args;
}
