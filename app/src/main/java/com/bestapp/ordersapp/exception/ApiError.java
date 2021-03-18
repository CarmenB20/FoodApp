package com.bestapp.ordersapp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ApiError {
    private int status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime localDateTime;
    private String url;

    private ApiError(){
        this.localDateTime = LocalDateTime.now();
    }

    public ApiError(int status, String message,  String url) {
        this();
        this.status        = status;
        this.message       = message;
        this.url           = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

