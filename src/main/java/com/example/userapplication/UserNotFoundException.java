package com.example.userapplication;

/**
 * Thrown when a user does not exist in the database
 */
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
