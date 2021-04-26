package com.bestapp.ordersapp.customer.service.api;

import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;

import java.util.List;

public interface CustomerService {
    public CustomerEntity createCustomer(String name, String phone_number,
                                         String address, UserEntity userEntity);

    public List<CustomerEntity> getAll();

    public CustomerEntity getCustomerById(long id);

    public CustomerEntity updateCustomer(CustomerEntity updatedCustomer,
                                         long id);

    public CustomerEntity getCustomerByEmail(String email);
}
