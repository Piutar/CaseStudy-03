package com.dbc.InF;

import java.util.List;

import com.model.Category;

public interface CategoryDBCInF {
	void insert(Category category);

	void edit(Category category);

	void delete(int id);

	Category get(int id);
	
	Category get(String name);

	List<Category> getAll();

	List<Category> search(String username);
}
