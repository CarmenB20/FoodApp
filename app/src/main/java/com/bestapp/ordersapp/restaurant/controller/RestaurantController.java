package com.bestapp.ordersapp.restaurant.controller;

import com.bestapp.ordersapp.authentication.model.persitance.Role;
import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.bestapp.ordersapp.authentication.service.UserService;
import com.bestapp.ordersapp.email.EmailSender;
import com.bestapp.ordersapp.restaurant.model.dto.RestaurantDTO;
import com.bestapp.ordersapp.restaurant.model.persistance.FoodCategoryEntity;
import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;
import com.bestapp.ordersapp.restaurant.service.FoodCategoryService;
import com.bestapp.ordersapp.restaurant.service.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private RestaurantServiceImpl restaurantServiceImpl;
    private UserService userService;
    private EmailSender emailSender;
    private FoodCategoryService foodCategoryService;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantServiceImpl,
                                UserService userService,
                                EmailSender emailSender, FoodCategoryService foodCategoryService){
        this.restaurantServiceImpl = restaurantServiceImpl;
        this.userService = userService;
        this.emailSender = emailSender;
        this.foodCategoryService = foodCategoryService;
    }

    @PostMapping("/registerRestaurant")
    public ResponseEntity<RestaurantEntity> register(@RequestBody RestaurantDTO restaurantDTO){

        UserEntity userEntity = userService.createUser(restaurantDTO.getPassword(),
                restaurantDTO.getEmail(),
                Role.ROLE_RESTAURANT);
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantDTO.getName(),
                restaurantDTO.getLocation(),
                restaurantDTO.getDescription(),
                restaurantDTO.getOpen_hour(),
                restaurantDTO.getClosing_hour(),
                userEntity);
        restaurantServiceImpl.createRestaurant(restaurantEntity);

        emailSender.send(restaurantDTO.getEmail(), buildEmail(restaurantDTO.getName()));

        return ResponseEntity.ok(restaurantEntity);
    }


    private String buildEmail(String message) {
        return "Welcome to your account " + message;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteRestaurant(@PathVariable long id){
        userService.deleteUser(restaurantServiceImpl.getRestaurantById(id).getUserEntity());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantEntity>getRestaurant(@PathVariable long id){
        return ResponseEntity.ok(restaurantServiceImpl.getRestaurantById(id));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantEntity>>getAllRestaurants(){
        return ResponseEntity.ok(restaurantServiceImpl.getAll());
    }

    @PostMapping("/{restaurantId}/foodCategories")
    public ResponseEntity<FoodCategoryEntity> createFoodCategory(@PathVariable long restaurantId,
                                                                 @RequestBody FoodCategoryEntity foodCategoryEntity){
        RestaurantEntity restaurantEntity = restaurantServiceImpl.getRestaurantById(restaurantId);
        foodCategoryEntity.setRestaurantEntity(restaurantEntity);
        foodCategoryService.createFoodCategory(foodCategoryEntity, restaurantId);

        return ResponseEntity.ok(foodCategoryEntity);
    }

    @GetMapping("/{restaurantId}/foodCategories/{foodCategoryId}")
    public ResponseEntity<FoodCategoryEntity>getFoodCategory(@PathVariable long foodCategoryId,
                                                             @PathVariable long restaurantId){
        restaurantServiceImpl.getRestaurantById(restaurantId);
        FoodCategoryEntity foodCategoryEntity =  foodCategoryService.getFoodCategory(foodCategoryId);
        return ResponseEntity.ok(foodCategoryEntity);
    }


    @PutMapping("/{restaurantId}/foodCategories/{foodCategoryId}")
    public ResponseEntity<FoodCategoryEntity>updateFoodCategory(@PathVariable long foodCategoryId,
                                                                @RequestBody FoodCategoryEntity foodCategoryEntity,
                                                                @PathVariable long restaurantId) {
        restaurantServiceImpl.getRestaurantById(restaurantId);
        foodCategoryService.updateFoodCategoryEntity(foodCategoryId, foodCategoryEntity);
        return ResponseEntity.ok(foodCategoryEntity);

    }

    @GetMapping("/{restaurantId}/foodCategories")
    public ResponseEntity<List<FoodCategoryEntity>>getAllFoodCategories(@PathVariable long restaurantId){

        restaurantServiceImpl.getRestaurantById(restaurantId);
        List<FoodCategoryEntity>foodCategoryEntities = foodCategoryService.getAllFoodCategories(restaurantId);
        return ResponseEntity.ok(foodCategoryEntities);
    }
    @DeleteMapping("/{restaurantId}/foodCategories/{foodCategoryId}")
    public ResponseEntity deleteFoodCategoryById(@PathVariable long foodCategoryId,
                                                 @PathVariable long restaurantId){
        restaurantServiceImpl.getRestaurantById(restaurantId);
        foodCategoryService.deleteFoodCategoryById(foodCategoryId, restaurantId);
        return ResponseEntity.noContent().build();
    }

}
