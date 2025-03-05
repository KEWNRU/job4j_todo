package ru.job4j.store.task;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;
import ru.job4j.store.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore implements HqlTaskStore {
    private final CrudRepository crudRepository;

    @Override
    public Task add(Task task) {
        crudRepository.run(session -> session.save(task));
        return task;
    }

    @Override
    public boolean update(Task task) {
        return crudRepository.booleanQuery("update Task title =: title where id =: id",
                Map.of("title", task.getTitle(),
                        "priority", task.getPriority().getId(),
                        "id", task.getId()));
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.booleanQuery("delete Task where id =: id", Map.of("id", id));
    }

    @Override
    public List<Task> findAll() {
        return crudRepository.query("from Task f join fetch f.priority", Task.class);
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return crudRepository.optional("from Task where id = :id", Task.class,
                Map.of("id", id));
    }

    @Override
    public List<Task> findDone(boolean done) {
        return crudRepository.query("from Task where done = :done order by id", Task.class,
                Map.of("done", done));
    }

    @Override
    public boolean updateTask(int id, boolean done) {
        return crudRepository.booleanQuery("update from Task set done =: done where id =:id",
                Map.of("done", done,
                        "id", id));
    }
}