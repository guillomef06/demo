package com.example.demo.dataTransferObjects;

public class TokenRO {
    private String token;

    private TokenRO() {}

    public TokenRO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
