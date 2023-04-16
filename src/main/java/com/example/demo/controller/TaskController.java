package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @PatchMapping("/tasks/{id}")
    public void patchMethod(@PathVariable("id") Long id, @RequestBody Task task){
        if (!task.isDone()) {
            taskRepository.markAsDone(id);
        }
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable("id") Long id){
        taskRepository.markAsDone(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskRepository.deleteById(id);
    }
}