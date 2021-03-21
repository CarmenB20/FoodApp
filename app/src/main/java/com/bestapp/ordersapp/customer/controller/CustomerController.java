package com.bestapp.ordersapp.customer.controller;

import com.bestapp.ordersapp.authentication.model.persitance.Role;
import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.bestapp.ordersapp.authentication.service.UserService;
import com.bestapp.ordersapp.customer.model.dto.CustomerDTO;
import com.bestapp.ordersapp.customer.model.persistance.CustomerEntity;
import com.bestapp.ordersapp.customer.service.api.CustomerService;
import com.bestapp.ordersapp.customer.service.api.CustomerServiceImpl;
import com.bestapp.ordersapp.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private CustomerService customerService;
    private UserService userService;
    private EmailSender emailSender;

    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl, UserService userService, EmailSender emailSender) {
        this.customerService= customerServiceImpl;
        this.userService = userService;
        this.emailSender = emailSender;
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<?> register(@RequestBody CustomerDTO customerDTO) {

        UserEntity userEntity = userService.createUser(customerDTO.getPassword(),
                customerDTO.getEmail(), Role.ROLE_CUSTOMER);

        customerService.createCustomer(customerDTO.getName(),
                customerDTO.getPhone_number(),
                customerDTO.getAddress(),
                userEntity);

        emailSender.send(customerDTO.getEmail(), buildEmail(customerDTO.getName()));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    private String buildEmail(String message) {
        return "Welcome to your account " + message;
    }


    @GetMapping
    public ResponseEntity<List<CustomerEntity>>getAllCustomers(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity>getCustomerById(@PathVariable long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteCustomer(@PathVariable long id){
        userService.deleteUser(customerService.getCustomerById(id).getUserEntity());
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity<CustomerEntity>updateCustomer(@PathVariable long id,
                                                        @RequestBody CustomerEntity customerEntity){
        customerService.getCustomerById(id);
        customerService.updateCustomer(customerEntity, id);
        return ResponseEntity.ok(customerEntity);
    }
}
