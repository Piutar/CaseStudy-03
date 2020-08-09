package com.controller;

import com.dbc.CartDBC;
import com.model.Cart;
import com.model.CartItem;
import com.model.UID;
import com.model.User;
import com.service.CartItemService;
import com.service.CartService;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "OrderServlet", urlPatterns = "/member/order")
public class OrderServlet extends HttpServlet {
    UserService userService = new UserService();
    CartService cartService = new CartService();
    CartItemService cartItemService = new CartItemService();
    long time = System.currentTimeMillis();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("account");
        User buyer = (User) obj;
        Cart cart = new Cart();
        cart.setBuyer(buyer);
        cart.setBuyDate(new java.sql.Date(time));
        cart.setId(UID.getRandomID());
        cartService.insert(cart);
        Object objCart = session.getAttribute("cart");
        if (objCart != null) {
            // ep ve dung kieu cua no khi them vao o phan them vao gio hang controller
            Map<Integer, CartItem> map = (Map<Integer, CartItem>) objCart;
            for (CartItem i : map.values()){
                i.setCart(cart);
                i.setId(UID.getRandomID());
                cartItemService.insert(i);
            }
        }
        session.removeAttribute("cart");
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
