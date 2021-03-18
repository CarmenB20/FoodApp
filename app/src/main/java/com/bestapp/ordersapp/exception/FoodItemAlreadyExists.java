package com.bestapp.ordersapp.exception;

public class FoodItemAlreadyExists extends RuntimeException{
    public FoodItemAlreadyExists(String msg){
        super(msg);
    }
}
