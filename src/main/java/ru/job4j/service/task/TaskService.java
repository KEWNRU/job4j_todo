package ru.job4j.service.task;

import ru.job4j.dto.TaskDto;
import ru.job4j.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> add(Task task);

    boolean update(Task task);

    boolean delete(Integer id);

    List<TaskDto> findAll();

    Optional<TaskDto> findById(Integer id);

    List<Task> findDone(boolean done);

    boolean updateTask(int id, boolean done);

}