package com.example.userapplication;

class CantDeleteUserException extends RuntimeException {
    public CantDeleteUserException(Long id) {
        super("User " + id + " does not exist and cant be deleted");
    }
}
