package com.bestapp.ordersapp.orders.controller;

import com.bestapp.ordersapp.authentication.service.UserService;
import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;
import com.bestapp.ordersapp.customer.service.api.CustomerService;
import com.bestapp.ordersapp.orders.model.dto.OrderDTO;
import com.bestapp.ordersapp.orders.model.persistance.OrderEntity;
import com.bestapp.ordersapp.orders.service.OrderService;
import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;
import com.bestapp.ordersapp.restaurant.service.api.RestaurantService;
import com.bestapp.ordersapp.security.jwt.JWTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class OrderController {

    private OrderService orderService;
    private JWTokenProvider jwTokenProvider;
    private UserService userService;
    private RestaurantService restaurantService;
    private CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService,
                           JWTokenProvider jwTokenProvider,
                           UserService userService,
                           RestaurantService restaurantService,
                           CustomerService customerService){
        this.orderService = orderService;
        this.jwTokenProvider = jwTokenProvider;
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<OrderEntity>createOrder(@RequestBody OrderDTO orderDTO,
                                                  @RequestHeader("Authorization") String jwt){
        RestaurantEntity restaurantEntity = this.restaurantService.getRestaurantById(orderDTO.getRestaurantId());
        String email = this.jwTokenProvider.getUserEmailFromJWT(jwt);

        CustomerEntity customerEntity = this.customerService.getCustomerByEmail(email);

        OrderEntity orderEntity = this.orderService.createOrder(orderDTO, customerEntity,restaurantEntity );

        return ResponseEntity.ok(orderEntity);
    }

}
