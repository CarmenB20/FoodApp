package com.bestapp.ordersapp.authentication.service;

import com.bestapp.ordersapp.security.model.CurrentUser;
import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.bestapp.ordersapp.authentication.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found with email " + email );
        }
        return CurrentUser.create(user.get());
    }

    public UserDetails loadUserById(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found for id " + userId );
        }
        return CurrentUser.create(user.get());
    }
}
