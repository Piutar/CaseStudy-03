package com.service.InF;

import com.model.CartItem;

import java.util.List;

public interface CartItemInF {
    void insert(CartItem cartItem);

    void edit(CartItem cartItem);

    void delete(int id);

    CartItem get(int id);

    List<CartItem> getAll();

    List<CartItem> search(String keyword);
}
