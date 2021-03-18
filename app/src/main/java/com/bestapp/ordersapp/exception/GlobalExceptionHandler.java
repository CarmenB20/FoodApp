package com.bestapp.ordersapp.exception;

import com.bestapp.ordersapp.authentication.controller.AuthenticationController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = AuthenticationController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<ApiError> handleWeakPass(WeakPasswordException e,
                                                   HttpServletRequest request) {
        ApiError apiError = new ApiError(409, e.getMessage(), request.getContextPath());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ApiError> handleInvalidEmail(InvalidEmailException e,
                                                       HttpServletRequest request) {
        ApiError apiError = new ApiError(406, e.getMessage(), request.getContextPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<ApiError> handleEmailInUSe(EmailInUseException e,
                                                     HttpServletRequest request) {
        ApiError apiError = new ApiError(226, e.getMessage(), request.getContextPath());
        return new ResponseEntity<>(apiError, HttpStatus.IM_USED);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ApiError> handleRestaurantNotFound(RestaurantNotFoundException e,
                                                             HttpServletRequest request) {
        ApiError apiError = new ApiError(404, e.getMessage(), request.getContextPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException e,
                                                       HttpServletRequest request) {
        ApiError apiError = new ApiError(404, e.getMessage(), request.getContextPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordDoNotMatchException.class)
    public ResponseEntity<ApiError> handlePasswordDoNotMatch(PasswordDoNotMatchException e,
                                                             HttpServletRequest request) {
        ApiError apiError = new ApiError(406, e.getMessage(), request.getContextPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_ACCEPTABLE);


    }




}
