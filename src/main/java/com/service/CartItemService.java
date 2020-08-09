package com.service;

import com.dbc.CartItemDBC;
import com.dbc.InF.CartItemDBCInF;
import com.model.CartItem;

import java.util.List;

public class CartItemService implements CartItemDBCInF {
    CartItemDBCInF cartItemDBC = new CartItemDBC();

    @Override
    public void insert(CartItem cartItem) {
        cartItemDBC.insert(cartItem);
    }

    @Override
    public void edit(CartItem cartItem) {
        CartItem oldCartItem = cartItemDBC.get(cartItem.getId());
        oldCartItem.setCart(cartItem.getCart());
        oldCartItem.setProduct(cartItem.getProduct());
        oldCartItem.setQuantity(cartItem.getQuantity());
        oldCartItem.setUnitPrice(cartItem.getUnitPrice());
        cartItemDBC.edit(oldCartItem);
    }

    @Override
    public void delete(String id) {
        cartItemDBC.delete(id);
    }

    @Override
    public CartItem get(String name) {
        return cartItemDBC.get(name);
    }

    @Override
    public CartItem get(int id) {
        return cartItemDBC.get(id);
    }

    @Override
    public List<CartItem> getAll() {
        return cartItemDBC.getAll();
    }

    @Override
    public List<CartItem> search(String name) {
        return cartItemDBC.search(name);
    }
}
