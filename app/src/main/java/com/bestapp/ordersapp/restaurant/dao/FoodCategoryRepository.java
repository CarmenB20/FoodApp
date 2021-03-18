package com.bestapp.ordersapp.restaurant.dao;

import com.bestapp.ordersapp.restaurant.model.persistance.FoodCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategoryEntity, Long> {


    @Query(value = "select * from orders.food_categories fc where restaurant_id = :restaurantId and name = :name", nativeQuery = true)
    Optional<FoodCategoryEntity> findByNameAndRestaurantId(String name, long restaurantId);


    @Query(value = "select * from orders.food_categories fc where restaurant_id = :restaurantId", nativeQuery = true)
    List<FoodCategoryEntity> findFoodCategoriesByRestaurantId(long restaurantId);


    @Modifying
    @Transactional
    @Query(value = "delete from orders.food_categories where restaurant_id = :restaurantId and id = :foodCategoryId", nativeQuery = true)
    void deleteFoodCategoryById(long foodCategoryId, long restaurantId);

}