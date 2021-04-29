package com.example.userapplication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Responsible for handling custom exceptions for the controller by 1) returning the correct HTTP messages and displaying
 * the correct error messsages.
 */
@ControllerAdvice
class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userExistsHandler(UserExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CantDeleteUserException.class)
    @ResponseStatus(HttpStatus.GONE)
    String cantDeleteUserHandler(CantDeleteUserException ex) {
        return ex.getMessage();
    }
}
