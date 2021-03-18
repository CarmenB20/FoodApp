package com.bestapp.ordersapp.exception;

public class EmailInUseException extends RuntimeException{
    public EmailInUseException(String msg){
        super(msg);
    }
}
