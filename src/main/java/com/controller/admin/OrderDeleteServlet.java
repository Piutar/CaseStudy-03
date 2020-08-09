package com.controller.admin;

import com.service.CartItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderDeleteServlet", urlPatterns="/admin/order/delete")
public class OrderDeleteServlet extends HttpServlet {
    CartItemService cartItemService = new CartItemService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id= request.getParameter("id");
        cartItemService.delete(id);
        response.sendRedirect(request.getContextPath()+"/admin/order/list");

    }
}
