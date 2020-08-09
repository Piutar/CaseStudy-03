package com.service;

import com.dbc.CategoryDBC;
import com.dbc.InF.CategoryDBCInF;
import com.model.Category;
import com.service.InF.CategoryInF;

import java.util.List;

public class CategoryService implements CategoryInF {
    CategoryDBCInF categoryDBC = new CategoryDBC();

    @Override
    public void insert(Category category) {
        categoryDBC.insert(category);
    }

    @Override
    public void edit(Category category) {
        categoryDBC.edit(category);
    }

    @Override
    public void delete(int id) {
        categoryDBC.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryDBC.get(id);
    }

    @Override
    public Category get(String name) {
        return categoryDBC.get(name);
    }

    @Override
    public List<Category> getAll() {
        return categoryDBC.getAll();
    }

    @Override
    public List<Category> search(String username) {
        return categoryDBC.search(username);
    }
}
