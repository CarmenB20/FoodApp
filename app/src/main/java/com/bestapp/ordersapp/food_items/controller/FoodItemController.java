package com.bestapp.ordersapp.food_items.controller;

import com.bestapp.ordersapp.food_items.model.persistance.FoodItemEntity;
import com.bestapp.ordersapp.food_items.service.FoodItemService;
import com.bestapp.ordersapp.restaurant.model.persistance.FoodCategoryEntity;
import com.bestapp.ordersapp.restaurant.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PostMapping("/{foodCategoryId}/foodItem")
    public ResponseEntity<FoodItemEntity> createFoodItem(@PathVariable long foodCategoryId,
                                                         @RequestBody FoodItemEntity foodItemEntity){
        FoodCategoryEntity foodCategoryEntity=  foodCategoryService.getFoodCategory(foodCategoryId);
        foodItemEntity.setFoodCategoryEntity(foodCategoryEntity);
        return ResponseEntity.ok(foodItemService.createFoodItem(foodCategoryId, foodItemEntity));


    }
    @DeleteMapping("/{foodCategoryId}/{id}")
    public ResponseEntity deleteFoodItem(@PathVariable long id){
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.ok("Food Item with id " + id + "deleted!");
    }
    @PutMapping("/foodItemUpdate/{id}")
    public ResponseEntity<FoodItemEntity>updateFoodItem( @PathVariable long id,
                                                         @RequestBody FoodItemEntity foodItemEntity){
        foodItemService.getFoodItemById(id);
        foodItemService.updateFoodItem(id, foodItemEntity);
        return ResponseEntity.ok(foodItemEntity);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<FoodItemEntity>>getAllFoodItems(@PathVariable long id){
        foodCategoryService.getFoodCategory(id);

        List<FoodItemEntity> foodItemEntities = foodItemService.getAllFoodItems(id);
        return ResponseEntity.ok(foodItemEntities);
    }
}
