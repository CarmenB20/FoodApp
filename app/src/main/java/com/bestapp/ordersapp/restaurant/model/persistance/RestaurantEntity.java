package com.bestapp.ordersapp.restaurant.model.persistance;

import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "restaurants", schema = "orders")
@JsonAutoDetect
public class RestaurantEntity {

    @CreationTimestamp
    private Timestamp created_on;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String location;
    private String description;

    private LocalTime open_hour;
    private LocalTime closing_hour;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;



    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "restaurantEntity")
    private List<FoodCategoryEntity> foodCategoryEntities;



    public RestaurantEntity(String name, String location, String description,
                            LocalTime open_hour, LocalTime closing_hour, UserEntity userEntity){
        this.name = name;
        this.location = location;
        this.description = description;
        this.open_hour = open_hour;
        this.closing_hour = closing_hour;
        this.userEntity = userEntity;

    }
    public RestaurantEntity(){
        super();
    }


    public List<FoodCategoryEntity> getFoodCategoryEntities() {
        return foodCategoryEntities;
    }

    public void setFoodCategoryEntities(List<FoodCategoryEntity> foodCategoryEntities) {
        this.foodCategoryEntities = foodCategoryEntities;
    }
    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getOpen_hour() {
        return open_hour;
    }

    public void setOpen_hour(LocalTime open_hour) {
        this.open_hour = open_hour;
    }

    public LocalTime getClosing_hour() {
        return closing_hour;
    }

    public void setClosing_hour(LocalTime closing_hour) {
        this.closing_hour = closing_hour;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "RestaurantEntity{" +
                "created_on=" + created_on +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", open_hour=" + open_hour +
                ", closing_hour=" + closing_hour +
                ", userEntity=" + userEntity +
                '}';
    }
}
