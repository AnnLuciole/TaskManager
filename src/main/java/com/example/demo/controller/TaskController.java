package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return taskRepository.findById(id).get();
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        taskRepository.findAll().iterator().forEachRemaining(allTasks::add);
        return allTasks;
    }

    @PutMapping("/tasks/{id}")
    public String updateTask(@PathVariable("id") Long id) {
        if (taskRepository.existsById(id)) {
            Task updatedTask = taskRepository.findById(id).get();
            updatedTask.setDone(true);
            taskRepository.save(updatedTask);
            return "Task updated";
        }
        return "Update failed";
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return "Task deleted";
        }
        return "Delete failed";
    }
}