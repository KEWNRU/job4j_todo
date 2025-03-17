package ru.job4j.store.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;
import ru.job4j.store.CrudRepository;
import ru.job4j.store.priority.PriorityStore;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore implements HqlTaskStore {
    private final CrudRepository crudRepository;
    private final PriorityStore priorityStore;

    @Override
    public Optional<Task> add(Task task) {
        try {
            task.setPriority(priorityStore.findById(task.getPriority().getId()).get());
            crudRepository.run(session -> session.save(task));
            return Optional.of(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Task task) {
        return crudRepository.booleanQuery("update Task title =: title where id =: id",
                Map.of("title", task.getTitle(),
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
        return crudRepository.optional("from Task f join fetch f.priority where f.id = :id", Task.class,
                Map.of("id", id));
    }

    @Override
    public List<Task> findDone(boolean done) {
        return crudRepository.query("from Task f join fetch f.priority where done = :done", Task.class,
                Map.of("done", done));
    }

    @Override
    public boolean updateTask(int id, boolean done) {
        return crudRepository.booleanQuery("update from Task set done =: done where id =:id",
                Map.of("done", done,
                        "id", id));
    }
}