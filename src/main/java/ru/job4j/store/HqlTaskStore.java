package ru.job4j.store;

import ru.job4j.model.Task;

import java.util.List;

public interface HqlTaskStore {
    Task add(Task task);

    void update(Task task);

    void delete(int id);

    List<Task> findAll();

    Task findById(Integer id);

    List<Task> findDone(boolean done);
}