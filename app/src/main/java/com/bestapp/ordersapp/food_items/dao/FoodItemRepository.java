package com.bestapp.ordersapp.food_items.dao;

import com.bestapp.ordersapp.food_items.model.persistance.FoodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodItemRepository  extends JpaRepository<FoodItemEntity, Long> {

    @Query(value = "select * from orders.food_items where food_category_id = :foodCategoryId and name = :foodItemName", nativeQuery = true)
    Optional<FoodItemEntity> getFoodItemByFoodCategoryId (long foodCategoryId, String foodItemName);
}


