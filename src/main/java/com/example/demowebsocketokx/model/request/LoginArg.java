package com.example.demowebsocketokx.model.request;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class LoginArg {
    private String apiKey;
    private String passphrase;
    private String timestamp;
    private String sign;
}
