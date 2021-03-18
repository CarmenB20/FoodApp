package com.bestapp.ordersapp.authentication.model.dto;

public class JWTokenDTO {
    public String token;

    public JWTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
