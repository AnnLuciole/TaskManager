package com.example.demo.service.exceptions;

public class DataNotFoundException extends Exception{

    public DataNotFoundException() {
        super("This table is empty.");
    }
}
