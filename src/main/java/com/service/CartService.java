package com.service;

import com.dbc.CartDBC;
import com.dbc.InF.CartDBCInF;
import com.model.Cart;
import com.service.InF.CartInF;

import java.util.List;

public class CartService implements CartInF {
    CartDBCInF cartDBC = new CartDBC();

    @Override
    public void insert(Cart cart) {
        cartDBC.insert(cart);
    }

    @Override
    public void edit(Cart cart) {
        Cart oldCart = cartDBC.get(cart.getId());
        oldCart.setBuyDate(cart.getBuyDate());
        oldCart.setBuyer(cart.getBuyer());
        cartDBC.edit(oldCart);
    }

    @Override
    public void delete(int id) {
        cartDBC.delete(id);
    }

    @Override
    public Cart get(int id) {
        return cartDBC.get(id);
    }

    @Override
    public List<Cart> getAll() {
        return cartDBC.getAll();
    }

    @Override
    public List<Cart> search(String keyword) {
        return cartDBC.search(keyword);
    }
}
