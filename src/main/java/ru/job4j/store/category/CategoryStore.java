package ru.job4j.store.category;

import ru.job4j.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryStore {
    List<Category> findAll();

    List<Category> findByListId(List<Integer> categoriesId);

}
