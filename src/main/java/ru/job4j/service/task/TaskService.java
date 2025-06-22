package ru.job4j.service.task;

import ru.job4j.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void add(Task task);

    boolean update(Task task);

    boolean delete(Integer id);

    List<Task> findAll();

    Optional<Task> findById(Integer id);

    List<Task> findDone(boolean done);

    boolean updateTask(int id, boolean done);

}