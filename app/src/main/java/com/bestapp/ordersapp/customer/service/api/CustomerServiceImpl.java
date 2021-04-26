package com.bestapp.ordersapp.customer.service.api;

import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.bestapp.ordersapp.authentication.service.UserService;
import com.bestapp.ordersapp.customer.dao.CustomerRepository;
import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;
import com.bestapp.ordersapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private UserService userService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, UserService userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    public CustomerEntity createCustomer(String name, String phone_number,
                                         String address, UserEntity userEntity) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName(name);
        customerEntity.setPhone_number(phone_number);
        customerEntity.setAddress(address);
        customerEntity.setUserEntity(userEntity);

        return this.customerRepository.save(customerEntity);
    }


    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }


    public CustomerEntity getCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Customer with id:" + id + "does not exist!"));

    }

    public CustomerEntity updateCustomer(CustomerEntity customerEntity, long customerId) {
        CustomerEntity customer = getCustomerById(customerId);

        customer.setName(customerEntity.getName().toLowerCase());
        customer.setAddress(customerEntity.getAddress().toLowerCase());
        customer.setPhone_number(customerEntity.getPhone_number());

        return this.customerRepository.save(customer);

    }
    public CustomerEntity getCustomerByEmail(String email){
        return this.customerRepository.findCustomerByEmail(email).orElseThrow( () ->
                new RuntimeException("customer not found!") );

    }
}