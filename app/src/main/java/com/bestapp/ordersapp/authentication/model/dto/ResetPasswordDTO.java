package com.bestapp.ordersapp.authentication.model.dto;

public class ResetPasswordDTO {
    private String currentPass;
    private String newPass;
    private String confirmPass;

    public ResetPasswordDTO(String currentPass, String newPass, String confirmPass) {
        this.currentPass = currentPass;
        this.newPass = newPass;
        this.confirmPass = confirmPass;
    }

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}

