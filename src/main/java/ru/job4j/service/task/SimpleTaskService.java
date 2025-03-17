package ru.job4j.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.dto.TaskDto;
import ru.job4j.model.Task;
import ru.job4j.store.priority.HqlPriorityStore;
import ru.job4j.store.task.HqlTaskStore;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final HqlTaskStore hqlTaskStore;
    private final HqlPriorityStore hqlPriorityStore;

    @Override
    public Optional<Task> add(Task task) {
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
    public List<TaskDto> findAll() {
        return hqlTaskStore.findAll().stream().map(task -> new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreated().toLocalDate(),
                task.isDone(),
                task.getUser().getName(),
                hqlPriorityStore.findById(task.getPriority().getId()).get().getName()
        )).collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDto> findById(Integer id) {
        var task = hqlTaskStore.findById(id);
        TaskDto taskDto = null;
        if (task.isPresent()) {
            taskDto = new TaskDto(
                    task.get().getId(),
                    task.get().getTitle(),
                    task.get().getDescription(),
                    task.get().getCreated().toLocalDate(),
                    task.get().isDone(),
                    task.get().getUser().getName(),
                    hqlPriorityStore.findById(task.get().getPriority().getId()).get().getName()
            );
        }
        return Optional.ofNullable(taskDto);
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