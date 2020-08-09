package com.controller;

import com.model.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CartItemRermoveServlet", urlPatterns = "/member/cart/remove")
public class CartItemRermoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Object obj = httpSession.getAttribute("cart");// Doc tu Session ra
        String pId = request.getParameter("pId");
        if (obj != null) {
            Map<Integer, CartItem> map = (Map<Integer, CartItem>) obj; // ep ve kieu cua no
            // Xoa san pham trong map
            map.remove(Integer.parseInt(pId));

            // Cap nhat lai Session
            httpSession.setAttribute("cart", map);
        }
        response.sendRedirect(request.getContextPath() + "/member/cart");
    }
}
