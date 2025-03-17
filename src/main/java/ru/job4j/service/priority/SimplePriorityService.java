package ru.job4j.service.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Priority;
import ru.job4j.store.priority.HqlPriorityStore;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class SimplePriorityService implements PriorityService {
    private final HqlPriorityStore hqlPriorityStore;

    @Override
    public List<Priority> findAll() {
        return hqlPriorityStore.findAll();
    }

    @Override
    public Optional<Priority> findById(int id) {
        return hqlPriorityStore.findById(id);
    }
}
