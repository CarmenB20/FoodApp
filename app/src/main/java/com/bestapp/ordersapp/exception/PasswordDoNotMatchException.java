package com.bestapp.ordersapp.exception;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException(String msg){
        super(msg);
    }
}
