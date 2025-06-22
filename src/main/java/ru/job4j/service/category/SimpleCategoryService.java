package ru.job4j.service.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Category;
import ru.job4j.store.CrudRepository;
import ru.job4j.store.category.CategoryStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {
    private final CategoryStore categoryStore;
    private final CrudRepository crudRepository;

    @Override
    public List<Category> findAll() {
        return categoryStore.findAll();
    }

    @Override
    public List<Category> findByListId(List<Integer> categoryId) {
        return categoryStore.findByListId(categoryId);
    }
}
