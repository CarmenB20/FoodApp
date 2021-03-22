package com.bestapp.ordersapp.restaurant.service;


import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.bestapp.ordersapp.authentication.service.UserService;
import com.bestapp.ordersapp.exception.IllegalHourException;
import com.bestapp.ordersapp.exception.RestaurantNotFoundException;
import com.bestapp.ordersapp.restaurant.dao.RestaurantRepository;
import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;
import com.bestapp.ordersapp.restaurant.service.api.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {


   private RestaurantRepository restaurantRepository;
    private UserService userService;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UserService userService){
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }

    public RestaurantEntity createRestaurant(RestaurantEntity restaurantEntity){

        if(restaurantEntity.getOpen_hour().isAfter(restaurantEntity.getClosing_hour())){
            throw new IllegalHourException("Opening hour for restaurant: " + restaurantEntity.getOpen_hour() + "should not be after closing hour : "
                    + restaurantEntity.getClosing_hour() + "!");
        }
        return this.restaurantRepository.save(restaurantEntity);

    }

    public RestaurantEntity getRestaurantById(long id){
        Optional<RestaurantEntity> restaurantEntity = this.restaurantRepository.findById(id);
        if(restaurantEntity.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant with id " + id + "does not exist!");
        }
        return restaurantEntity.get();

    }
    public List<RestaurantEntity> getAll() {
        return restaurantRepository.findAll();
    }

    public RestaurantEntity updateRestaurant(long id, RestaurantEntity restaurantUpdated){
        RestaurantEntity restaurant = getRestaurantById(id);
        restaurant.setDescription(restaurantUpdated.getDescription().toLowerCase());
        restaurant.setClosing_hour(restaurantUpdated.getClosing_hour());
        restaurant.setOpen_hour(restaurantUpdated.getOpen_hour());
        restaurant.setLocation(restaurantUpdated.getLocation().toLowerCase());
        restaurant.setName(restaurantUpdated.getName().toLowerCase());

        return restaurantRepository.save(restaurant);

    }
}

