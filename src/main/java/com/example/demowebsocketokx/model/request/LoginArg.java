package com.example.demowebsocketokx.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class LoginArg {
    private String apiKey;
    private String passphrase;
    private String timestamp;
    private String sign;
}
