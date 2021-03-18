package com.bestapp.ordersapp.authentication.dao;

import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "(SELECT CAST (COUNT(*) AS BIT) FROM orders.users u WHERE  u.email = :email)" , nativeQuery = true)
    boolean EmailAlreadyInUse(String email);



    @Modifying
    @Transactional
    @Query(value = " UPDATE orders.users " +
            "SET password = :userPassword " +
            "WHERE email = :userEmail", nativeQuery = true)
    public void resetPassword(String userEmail, String userPassword);


}