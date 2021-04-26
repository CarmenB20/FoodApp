package com.bestapp.ordersapp.orders.dao;

import com.bestapp.ordersapp.orders.model.persistance.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
