package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import com.example.demo.service.exceptions.DataNotFoundException;
import com.example.demo.service.exceptions.TaskNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskService.addNewTask(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") Long id) throws TaskNotFoundException{
        return taskService.getTask(id);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() throws DataNotFoundException{
        return taskService.getAllTasks();
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/tasks/{id}")
    public void patchMethod(@PathVariable("id") Long id, @RequestBody Task task){
        if (!task.isDone()) {
            taskService.markAsDone(id);
        }
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable("id") Long id){
        taskService.markAsDone(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }
}