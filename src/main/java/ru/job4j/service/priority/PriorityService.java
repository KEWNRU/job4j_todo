package ru.job4j.service.priority;

import org.springframework.stereotype.Service;
import ru.job4j.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityService {
    List<Priority> findAll();
    Optional<Priority> findById(int id);
}
