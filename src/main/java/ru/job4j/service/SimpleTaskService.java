package ru.job4j.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Task;
import ru.job4j.store.HqlTaskStore;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
    public void update(Task task) {
        hqlTaskStore.update(task);
    }
    @Override
    public void delete(Integer id) {
        hqlTaskStore.delete(id);
    }
    @Override
    public List<Task> findAll() {
        return hqlTaskStore.findAll();
    }
    @Override
    public Task findById(Integer id) {
        return hqlTaskStore.findById(id);
    }
    @Override
    public List<Task> findDone(boolean done) {
        return hqlTaskStore.findDone(done);
    }
}