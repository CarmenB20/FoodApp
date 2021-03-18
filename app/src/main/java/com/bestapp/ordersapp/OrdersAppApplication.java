package com.bestapp.ordersapp;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableTransactionManagement
@SpringBootApplication
public class OrdersAppApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(final String[] args) {
		SpringApplication.run(OrdersAppApplication.class, args);
	}
}
