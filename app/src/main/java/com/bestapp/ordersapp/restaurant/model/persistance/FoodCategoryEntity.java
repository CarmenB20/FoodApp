package com.bestapp.ordersapp.restaurant.model.persistance;

import com.bestapp.ordersapp.food_items.model.persistance.FoodItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "food_categories", schema = "orders")
public class FoodCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Timestamp created_on;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private RestaurantEntity restaurantEntity;



    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "foodCategoryEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true)// copii orfani--memorie
    @JsonIgnore
    private List<FoodItemEntity> foodItemEntityList;


    public FoodCategoryEntity(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }

    public List<FoodItemEntity> getFoodItemEntityList() {
        return foodItemEntityList;
    }

    public void setFoodItemEntityList(List<FoodItemEntity> foodItemEntityList) {
        this.foodItemEntityList = foodItemEntityList;
    }
}


