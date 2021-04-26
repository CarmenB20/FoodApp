package com.bestapp.ordersapp.customer.dao;


import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "select c.* from orders.customers c join orders.users u on u.id = c.user_id where u.email =:email", nativeQuery = true)
    public Optional<CustomerEntity> findCustomerByEmail(String email);
}
