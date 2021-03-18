package com.bestapp.ordersapp.food_items.model.persistance;

import com.bestapp.ordersapp.restaurant.model.persistance.FoodCategoryEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "food_items", schema = "orders")
public class FoodItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private Timestamp created_on;

    private String name;
    private String description;
    private double price;
    private double weight;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCategoryEntity foodCategoryEntity;



    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public FoodCategoryEntity getFoodCategoryEntity() {
        return foodCategoryEntity;
    }

    public void setFoodCategoryEntity(FoodCategoryEntity foodCategoryEntity) {
        this.foodCategoryEntity = foodCategoryEntity;
    }
}
