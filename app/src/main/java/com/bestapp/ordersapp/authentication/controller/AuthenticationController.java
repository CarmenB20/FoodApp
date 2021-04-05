package com.bestapp.ordersapp.authentication.controller;

import com.bestapp.ordersapp.authentication.model.dto.ForgotPasswordDTO;
import com.bestapp.ordersapp.authentication.model.dto.JWTokenDTO;
import com.bestapp.ordersapp.authentication.model.dto.LoginRequest;
import com.bestapp.ordersapp.authentication.model.dto.ResetPasswordDTO;
import com.bestapp.ordersapp.authentication.service.UserService;
import com.bestapp.ordersapp.email.EmailSender;
import com.bestapp.ordersapp.security.jwt.JWTRedisService;
import com.bestapp.ordersapp.security.jwt.JWTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JWTokenProvider tokenProvider;
    private UserService userService;
    private EmailSender emailSender;
    private JWTRedisService jwtRedisService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JWTokenProvider tokenProvider,
                                    UserService userService, EmailSender emailSender, JWTRedisService jwtRedisService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider         = tokenProvider;
        this.userService           = userService;
        this.emailSender = emailSender;
        this.jwtRedisService = jwtRedisService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTokenDTO> login(@RequestBody LoginRequest loginRequest) {
        //  System.out.println(loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateJWToken(authentication);
        return ResponseEntity.ok(new JWTokenDTO(jwt));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO,
                                        @RequestHeader("Authorization")String jwt ){
        String userEmail = tokenProvider.getUserEmailFromJWT(jwt);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userEmail,
                        resetPasswordDTO.getCurrentPass()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userService.resetPassword(userEmail, resetPasswordDTO.getNewPass(), resetPasswordDTO.getConfirmPass());
        return ResponseEntity.ok().build();

    }
    @PostMapping("/forgotPassword")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO){
        String newPassword = userService.generateCommonLangPassword();
        userService.getUserByEmail(forgotPasswordDTO.getEmail());
        userService.resetPassword(forgotPasswordDTO.getEmail(), newPassword, newPassword);
        emailSender.send(forgotPasswordDTO.getEmail(), buildEmail(newPassword));

        return ResponseEntity.ok().build();
    }
    private String buildEmail(String message) {
        return "Your new password is: " + message;
    }


    @PostMapping("/logout")
    public ResponseEntity<?>logout(@RequestHeader ("Authorization") String jwt){

        String userEmail = tokenProvider.getUserEmailFromJWT(jwt);
        jwtRedisService.invalidateJwt(jwt, userEmail);

        return ResponseEntity.ok().build();
    }
    }

