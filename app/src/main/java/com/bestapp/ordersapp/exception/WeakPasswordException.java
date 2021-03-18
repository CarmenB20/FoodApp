package com.bestapp.ordersapp.exception;

import java.lang.ref.WeakReference;

public class WeakPasswordException extends RuntimeException{
    public WeakPasswordException(String msg){
        super(msg);
    }
}
