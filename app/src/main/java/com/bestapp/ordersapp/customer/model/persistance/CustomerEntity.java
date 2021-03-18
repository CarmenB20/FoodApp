package com.bestapp.ordersapp.customer.model.persistance;

import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "customers", schema= "orders")
@JsonAutoDetect
public class CustomerEntity {
    @CreationTimestamp
    private Timestamp created_on;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String phone_number;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;

    public Timestamp getCreated_on() {
        return created_on;
    }

    public CustomerEntity(){
        super();
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
