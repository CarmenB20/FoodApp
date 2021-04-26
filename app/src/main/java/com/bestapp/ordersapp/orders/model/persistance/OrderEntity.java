package com.bestapp.ordersapp.orders.model.persistance;

import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;
import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.scripting.bsh.BshScriptUtils;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders", schema = "orders")
@JsonAutoDetect
public class OrderEntity {

    @CreationTimestamp
    private Timestamp created_on;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "order_no")
    private long orderNo;

    @Column(name = "order_description")
    private String orderDescription;


    private String info;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private RestaurantEntity restaurantEntity;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private CustomerEntity customerEntity;

    public OrderEntity(){
        super();
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

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        return "OrderEntity{" +
                "created_on=" + created_on +
                ", id=" + id +
                ", orderNo=" + orderNo +
                ", orderDescription='" + orderDescription + '\'' +
                ", info='" + info + '\'' +
                ", restaurantEntity=" + restaurantEntity +
                ", customerEntity=" + customerEntity +
                '}';
    }
}
