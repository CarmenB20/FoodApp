package com.bestapp.ordersapp.exception;

public class FoodItemNotFoundException extends RuntimeException{
    public FoodItemNotFoundException(String msg){
        super(msg);
    }
}
