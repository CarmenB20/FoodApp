package com.bestapp.ordersapp.authentication.service;

import com.bestapp.ordersapp.authentication.model.persitance.Role;
import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import com.bestapp.ordersapp.authentication.dao.UserRepository;
import com.bestapp.ordersapp.email.EmailValidator;
import com.bestapp.ordersapp.exception.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private EmailValidator emailValidator;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, EmailValidator emailValidator) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
    }


    public UserEntity createUser(String password, String email, Role role) {

        UserEntity userEntity = new UserEntity();
        if (!isPasswordSecure(password)) {
            throw new WeakPasswordException("The password must have at least 8 characters and need to contain digits and letters");
        }
        if (!emailValidator.isValid(email)) {
            throw new InvalidEmailException("Invalid email format!");

        }
        if (userRepository.EmailAlreadyInUse(email)) {
            throw new EmailInUseException("Email already in use!Please type another email!");
        }
        userEntity.setEmail(email);
        userEntity.setPassword(bCryptPasswordEncoder.encode(password));
        userEntity.setRole(role);
        this.userRepository.save(userEntity);
        return userEntity;


    }

    private boolean isPasswordSecure(String password) {
        if (password.length() < 8) {
            return false;
        }
        int countDigits = 0;
        int countLetters = 0;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                countLetters++;
            }
            if (Character.isDigit(password.charAt(i))) {
                countDigits++;
            }
        }
        if (countDigits == 0 || countLetters == 0) {
            return false;
        }
        return true;
    }

    public UserEntity getUserById(long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id" + id + "not found!");
        }
        return userRepository.findById(id).get();
    }

    public UserEntity getUserByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email " + email);
        }
        return user.get();

    }



    public void deleteUser(UserEntity userEntity) {
        this.userRepository.delete(userEntity);
    }

    public void resetPassword(String email, String newPass, String confirmedPass) {
        if (!newPass.equals(confirmedPass)) {
            throw new PasswordDoNotMatchException("Password does not match!");
        }
        if (!isPasswordSecure(newPass)) {
            throw new WeakPasswordException("The password must have at least 8 characters and need to contain digits and letters");
        }
        userRepository.resetPassword(email, this.bCryptPasswordEncoder.encode(newPass));

    }
    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

}
