package com.bestapp.ordersapp.food_items.service;

import com.bestapp.ordersapp.exception.FoodItemAlreadyExists;
import com.bestapp.ordersapp.food_items.dao.FoodItemRepository;
import com.bestapp.ordersapp.food_items.model.persistance.FoodItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {
    private FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository){
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItemEntity createFoodItem(long foodCategoryId, FoodItemEntity foodItemEntity){
        if(foodItemRepository.getFoodItemByFoodCategoryId(foodCategoryId,
                foodItemEntity.getName().toLowerCase()).isPresent()){
            throw new FoodItemAlreadyExists("The item already exists! ");
        }
        return foodItemRepository.save(foodItemEntity);
    }
}
