package com.example.userapplication;

/**
 * Thrown when a user already exists in the database
 */
class UserExistsException extends RuntimeException {
    UserExistsException(String firstName, String lastName) {
        super("User with the name " + firstName + " " + lastName + " already exists");
    }
}
