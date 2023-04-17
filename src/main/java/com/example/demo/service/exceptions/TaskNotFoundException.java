package com.example.demo.service.exceptions;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException() {
        super("Task not found.");
    }
}
