package ru.job4j.store.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Category;
import ru.job4j.store.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlCategoryStore implements CategoryStore {
    private final CrudRepository crudRepository;

    @Override
    public List<Category> findAll() {
        return crudRepository.query("from Category", Category.class);
    }

    @Override
    public List<Category> findByListId(List<Integer> categoriesId) {
        return crudRepository.query("from Category where id = :id", Category.class,
                Map.of("id", categoriesId));
    }


}
