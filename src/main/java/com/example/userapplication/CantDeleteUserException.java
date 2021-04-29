package com.example.userapplication;

/**
 * Thrown when the user cannot be deleted
 */
class CantDeleteUserException extends RuntimeException {
    public CantDeleteUserException(Long id) {
        super("User " + id + " does not exist and cant be deleted");
    }
}
