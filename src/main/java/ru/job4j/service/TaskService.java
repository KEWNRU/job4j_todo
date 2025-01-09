package ru.job4j.service;

import ru.job4j.model.Task;

import java.util.List;

public interface TaskService {
    Task add(Task task);

    void update(Task task);

    void delete(Integer id);

    List<Task> findAll();

    Task findById(Integer id);

    List<Task> findDone(boolean done);
}