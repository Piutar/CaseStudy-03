package com.dbc.InF;

import java.util.List;

import com.model.CartItem;

public interface CartItemDBCInF {
	void insert(CartItem cartItem);

	void edit(CartItem cartItem);

	void delete(String id);

	CartItem get(String name);

	CartItem get(int id);

	List<CartItem> getAll();

	List<CartItem> search(String name);
}
