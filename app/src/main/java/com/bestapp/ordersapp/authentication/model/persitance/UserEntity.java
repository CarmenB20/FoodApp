package com.bestapp.ordersapp.authentication.model.persitance;

import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;
import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "users", schema = "orders")
@JsonAutoDetect
public class UserEntity {
    @CreationTimestamp
    private Timestamp created_on;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;


    @OneToOne(
            mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private RestaurantEntity restaurantEntity;


    @OneToOne(mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private CustomerEntity customerEntity;

    public UserEntity(){
        super();

    }
    public UserEntity(String email, String password, Role role){
        this.email = email;
        this.password = password;
        this.role = role;
    }



    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Timestamp getCreated_on() {
        return this.created_on;
    }

    public void setCreated_on(final Timestamp created_on) {
        this.created_on = created_on;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "created_on=" + created_on +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", restaurantEntity=" + restaurantEntity +
                ", customerEntity=" + customerEntity +
                '}';
    }
}
