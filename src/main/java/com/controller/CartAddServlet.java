package com.controller;

import com.model.CartItem;
import com.model.Product;
import com.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartAddServlet", urlPatterns = "/member/cart/add")
public class CartAddServlet extends HttpServlet {
    ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pId = request.getParameter("pId");
        String quantity= request.getParameter("quantity");
        Product product = productService.get(Integer.parseInt(pId));
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(Integer.parseInt(quantity));
        cartItem.setUnitPrice(product.getPrice());
        cartItem.setProduct(product);
        HttpSession httpSession = request.getSession();
        Object obj = httpSession.getAttribute("cart");
        if (obj == null) {
            Map<Integer, CartItem> map = new HashMap<>();
            map.put(cartItem.getProduct().getId(), cartItem);
            httpSession.setAttribute("cart", map);
        } else {
            Map<Integer, CartItem> map = (Map<Integer, CartItem>) obj;

            CartItem existedCartItem = map.get(Integer.valueOf(pId));

            if (existedCartItem == null) {
                map.put(product.getId(), cartItem);
            } else {
                existedCartItem.setQuantity(existedCartItem.getQuantity() + Integer.parseInt(quantity));
            }

            httpSession.setAttribute("cart", map);
        }
        response.sendRedirect(request.getContextPath() + "/member/cart");
    }
}
