package com.bestapp.ordersapp.exception;

public class CategoryExistsException extends RuntimeException{
    public CategoryExistsException(String msg){
        super(msg);
    }
}

