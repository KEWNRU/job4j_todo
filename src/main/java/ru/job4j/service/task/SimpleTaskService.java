package ru.job4j.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Task;
import ru.job4j.store.task.HqlTaskStore;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final HqlTaskStore hqlTaskStore;

    @Override
    public Task add(Task task) {
        task.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        return hqlTaskStore.add(task);
    }

    @Override
    public boolean update(Task task) {
        return hqlTaskStore.update(task);
    }

    @Override
    public boolean delete(Integer id) {
        return hqlTaskStore.delete(id);
    }

    @Override
    public List<Task> findAll() {
        return hqlTaskStore.findAll();
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return hqlTaskStore.findById(id);
    }

    @Override
    public List<Task> findDone(boolean done) {
        return hqlTaskStore.findDone(done);
    }


    @Override
    public boolean updateTask(int id, boolean done) {
        return hqlTaskStore.updateTask(id, done);
    }
}