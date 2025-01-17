package ru.job4j.store.task;

import ru.job4j.model.Task;

import java.util.List;
import java.util.Optional;

public interface HqlTaskStore {
    Task add(Task task);

    boolean update(Task task);

    boolean delete(int id);

    List<Task> findAll();

    Optional<Task> findById(Integer id);

    List<Task> findDone(boolean done);

    List<Task> buttonCompleteTask(int id, Task task);

    boolean updateTask(int id, boolean done);
}
