package com.service.InF;

import com.model.Category;

import java.util.List;

public interface CategoryInF {
    void insert(Category category);

    void edit(Category category);

    void delete(int id);

    Category get(int id);

    Category get(String name);

    List<Category> getAll();

    List<Category> search(String username);
}
