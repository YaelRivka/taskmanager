package com.example.taskmanager.services;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Optional<Task> findTaskById(int id) {
        return taskRepository.findTaskById(id);
    }

    public boolean markTaskAsCompleted(int id) {
        return taskRepository.markTaskAsCompleted(id);
    }

    public boolean deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }
}
