package ru.job4j.store.priority;

import ru.job4j.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityStore {
    List<Priority> findAll();
    Optional<Priority> findById(int id);
}
