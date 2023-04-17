package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.service.exceptions.DataNotFoundException;
import com.example.demo.service.exceptions.TaskNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskService {

    Task addNewTask(Task task);
    Task getTask(Long id) throws TaskNotFoundException;

    List<Task> getAllTasks() throws DataNotFoundException;
    void markAsDone(Long id);

    void deleteTask(Long id);

    public Task updateTask(Long id, Task task);
}
