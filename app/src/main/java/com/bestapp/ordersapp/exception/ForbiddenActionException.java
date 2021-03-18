package com.bestapp.ordersapp.exception;

public class ForbiddenActionException extends RuntimeException{
    public ForbiddenActionException(String msg){
        super(msg);
    }
}
