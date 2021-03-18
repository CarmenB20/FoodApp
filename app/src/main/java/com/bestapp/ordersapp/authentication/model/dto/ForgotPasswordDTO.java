package com.bestapp.ordersapp.authentication.model.dto;

public class ForgotPasswordDTO {
    private String email;

    public ForgotPasswordDTO(){
        super();
    }

    public ForgotPasswordDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ForgotPasswordDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
