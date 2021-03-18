package com.bestapp.ordersapp.restaurant.service.api;

import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;

import java.util.List;

public interface RestaurantService {
    public RestaurantEntity createRestaurant(RestaurantEntity restaurantEntity);

    public RestaurantEntity getRestaurantById(long id);

    public List<RestaurantEntity> getAll();
}
