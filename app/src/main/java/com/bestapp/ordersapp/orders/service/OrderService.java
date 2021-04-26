package com.bestapp.ordersapp.orders.service;


import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;
import com.bestapp.ordersapp.orders.dao.OrderRepository;
import com.bestapp.ordersapp.orders.model.dto.OrderDTO;
import com.bestapp.ordersapp.orders.model.persistance.OrderEntity;
import com.bestapp.ordersapp.restaurant.model.persistance.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;

    }

    public OrderEntity createOrder(OrderDTO orderDTO,
                                   CustomerEntity customerEntity,
                                   RestaurantEntity restaurantEntity){


        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setInfo(orderDTO.getInfo());
        orderEntity.setOrderDescription(orderDTO.getOrderDescription());
        orderEntity.setOrderNo(1);
        orderEntity.setRestaurantEntity(restaurantEntity);
        orderEntity.setCustomerEntity(customerEntity);

        return this.orderRepository.save(orderEntity);
    }
}
