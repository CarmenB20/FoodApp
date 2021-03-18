package com.bestapp.ordersapp.restaurant.dao;

import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
