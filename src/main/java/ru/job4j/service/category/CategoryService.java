package ru.job4j.service.category;

import ru.job4j.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findByListId(List<Integer> categoryId);
}
