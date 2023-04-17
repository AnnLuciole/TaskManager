package com.example.demo.service.impl;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import com.example.demo.service.exceptions.DataNotFoundException;
import com.example.demo.service.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service("taskServiceImpl")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public Task addNewTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTask(@PathVariable("id") Long id) throws TaskNotFoundException {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() throws DataNotFoundException {
        List<Task> allTasks = new ArrayList<>();
        taskRepository.findAll().iterator().forEachRemaining(allTasks::add);
        if(allTasks.isEmpty()) {
            throw new DataNotFoundException();
        }
        return allTasks;
    }

    @Override
    @Transactional
    public void markAsDone(@PathVariable("id") Long id) {
        taskRepository.markAsDone(id);
    }

    @Override
    @Transactional
    public void deleteTask(@PathVariable("id") Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }
}
