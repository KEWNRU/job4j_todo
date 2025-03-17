package ru.job4j.store.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Priority;
import ru.job4j.store.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlPriorityStore implements PriorityStore {
    private final CrudRepository crudRepository;

    @Override
    public List<Priority> findAll() {
        return crudRepository.query("from Priority", Priority.class);
    }

    @Override
    public Optional<Priority> findById(int id) {
        return crudRepository.optional("from Priority where id = :id", Priority.class,
                Map.of("id", id));

    }
}
