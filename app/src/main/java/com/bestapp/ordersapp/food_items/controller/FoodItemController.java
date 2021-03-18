package com.bestapp.ordersapp.food_items.controller;

import com.bestapp.ordersapp.food_items.model.persistance.FoodItemEntity;
import com.bestapp.ordersapp.food_items.service.FoodItemService;
import com.bestapp.ordersapp.restaurant.model.persistance.FoodCategoryEntity;
import com.bestapp.ordersapp.restaurant.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/foodCategory")
public class FoodItemController {

    private FoodItemService foodItemService;
    private FoodCategoryService foodCategoryService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService, FoodCategoryService foodCategoryService){
        this.foodItemService = foodItemService;
        this.foodCategoryService = foodCategoryService;
    }
    //TODO limit this to restaurants only
    @PostMapping("/{foodCategoryId}/foodItem")
    public ResponseEntity<FoodItemEntity> createFoodItem(@PathVariable long foodCategoryId,
                                                         @RequestBody FoodItemEntity foodItemEntity){
        FoodCategoryEntity foodCategoryEntity=  foodCategoryService.getFoodCategory(foodCategoryId);
        foodItemEntity.setFoodCategoryEntity(foodCategoryEntity);
        return ResponseEntity.ok(foodItemService.createFoodItem(foodCategoryId, foodItemEntity));


    }
}
