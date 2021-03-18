package com.bestapp.ordersapp.restaurant.service;

import com.bestapp.ordersapp.exception.CategoryExistsException;
import com.bestapp.ordersapp.exception.CategoryNotFoundException;
import com.bestapp.ordersapp.restaurant.dao.FoodCategoryRepository;
import com.bestapp.ordersapp.restaurant.model.persistance.FoodCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryService {
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository){
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public FoodCategoryEntity createFoodCategory(FoodCategoryEntity foodCategoryEntity, long restaurantId){
        if(foodCategoryRepository.findByNameAndRestaurantId(foodCategoryEntity.getName().toLowerCase(), restaurantId).isPresent()){
            throw new CategoryExistsException("This food category already exists!");
        }
        foodCategoryEntity.setName(foodCategoryEntity.getName().toLowerCase());
        return this.foodCategoryRepository.save(foodCategoryEntity);
    }


    public FoodCategoryEntity getFoodCategory(Long foodCategoryId){
        Optional<FoodCategoryEntity> foodCategoryEntity =  foodCategoryRepository.findById(foodCategoryId);
        if(foodCategoryEntity.isEmpty()){
            throw new CategoryNotFoundException("This category was not found!");
        }
        return foodCategoryEntity.get();
    }



    public FoodCategoryEntity updateFoodCategoryEntity(long foodCategoryId,
                                                       FoodCategoryEntity foodCategoryEntity){
        FoodCategoryEntity toBeUpdatedFoodCategory = getFoodCategory(foodCategoryId);
        toBeUpdatedFoodCategory.setName(foodCategoryEntity.getName().toLowerCase());
        return foodCategoryRepository.save(toBeUpdatedFoodCategory);
    }

    public List<FoodCategoryEntity> getAllFoodCategories(long restaurantId){
        return foodCategoryRepository.findFoodCategoriesByRestaurantId(restaurantId);
    }

    public void deleteFoodCategoryById(long foodCatId, long restaurantId){
        foodCategoryRepository.deleteFoodCategoryById(foodCatId, restaurantId);
    }
}
