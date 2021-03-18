package com.bestapp.ordersapp.exception;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(String msg){
        super(msg);
}
}
