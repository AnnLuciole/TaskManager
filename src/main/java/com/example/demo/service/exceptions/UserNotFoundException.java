package com.example.demo.service.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User with this login not found.");
    }
}
