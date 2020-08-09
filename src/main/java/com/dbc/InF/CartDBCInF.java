package com.dbc.InF;

import java.util.List;

import com.model.Cart;

public interface CartDBCInF {
	void insert(Cart cart);

	void edit(Cart cart);

	void delete(int id);

	Cart get(String name);

	Cart get(int id);

	List<Cart> getAll();

	List<Cart> search(String name);
}
