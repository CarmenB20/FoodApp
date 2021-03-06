package com.bestapp.ordersapp.restaurant.controller;

import com.bestapp.ordersapp.authentication.model.persitance.Role;
import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.bestapp.ordersapp.authentication.service.UserService;
import com.bestapp.ordersapp.email.EmailSender;
import com.bestapp.ordersapp.exception.ForbiddenActionException;
import com.bestapp.ordersapp.restaurant.model.dto.RestaurantDTO;
import com.bestapp.ordersapp.restaurant.model.persistance.FoodCategoryEntity;
import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;
import com.bestapp.ordersapp.restaurant.service.FoodCategoryService;
import com.bestapp.ordersapp.restaurant.service.RestaurantServiceImpl;
import com.bestapp.ordersapp.security.jwt.JWTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private RestaurantServiceImpl restaurantServiceImpl;
    private UserService userService;
    private EmailSender emailSender;
    private FoodCategoryService foodCategoryService;
    private JWTokenProvider jwTokenProvider;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantServiceImpl,
                                UserService userService,
                                EmailSender emailSender, FoodCategoryService foodCategoryService, JWTokenProvider jwTokenProvider){
        this.restaurantServiceImpl = restaurantServiceImpl;
        this.userService = userService;
        this.emailSender = emailSender;
        this.foodCategoryService = foodCategoryService;
        this.jwTokenProvider = jwTokenProvider;
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


    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteRestaurant(@PathVariable long id,
                                             @RequestHeader("Authorization")String jwt){
        UserEntity userEntity = restaurantServiceImpl.getRestaurantById(id).getUserEntity();

        String email = jwTokenProvider.getUserEmailFromJWT(jwt);
        if(!userEntity.getEmail().equals(email)){
            throw new ForbiddenActionException("You do not have access to this restaurant!");
        }

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantEntity>updateRestaurant(@PathVariable long id,
                                                            @RequestBody RestaurantEntity restaurantUpdated) {

        RestaurantEntity restaurantEntity = restaurantServiceImpl.getRestaurantById(id);
        if(restaurantEntity.getId() != id ){ 
            throw new ForbiddenActionException("You do not have access to this restaurant!");
        }
        restaurantServiceImpl.updateRestaurant(id, restaurantUpdated);
        return ResponseEntity.ok(restaurantUpdated);
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


    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
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
    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @DeleteMapping("/{restaurantId}/foodCategories/{foodCategoryId}")
    public ResponseEntity deleteFoodCategoryById(@PathVariable long foodCategoryId,
                                                 @PathVariable long restaurantId){
        restaurantServiceImpl.getRestaurantById(restaurantId);
        foodCategoryService.deleteFoodCategoryById(foodCategoryId, restaurantId);
        return ResponseEntity.noContent().build();
    }

}
