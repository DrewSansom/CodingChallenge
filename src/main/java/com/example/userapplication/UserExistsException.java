package com.example.userapplication;

class UserExistsException extends RuntimeException {
    UserExistsException(String firstName, String lastName) {
        super("User with the name " + firstName + " " + lastName + " already exists");
    }
}
