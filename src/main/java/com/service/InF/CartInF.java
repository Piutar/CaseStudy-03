package com.service.InF;

import com.model.Cart;

import java.util.List;

public interface CartInF {
    void insert(Cart cart);

    void edit(Cart newCart);

    void delete(int id);

    Cart get(int id);

    List<Cart> getAll();

    List<Cart> search(String keyword);
}
