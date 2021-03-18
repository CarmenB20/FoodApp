package com.bestapp.ordersapp.customer.dao;


import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
