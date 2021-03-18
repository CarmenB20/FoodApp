package com.bestapp.ordersapp.restaurant.model.dto;

import java.time.LocalTime;

public class RestaurantDTO {
    private String email;
    private String password;
    private String name;
    private String location;
    private String description;

    private LocalTime open_hour;
    private LocalTime closing_hour;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", open_hour=" + open_hour +
                ", end_hour=" + closing_hour +
                '}';
    }
}
