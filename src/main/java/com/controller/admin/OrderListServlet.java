package com.controller.admin;

import com.model.CartItem;
import com.service.CartItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderListServlet",urlPatterns = "/admin/order/list")
public class OrderListServlet extends HttpServlet {
    CartItemService cartItemService = new CartItemService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartItem> listCartItem =cartItemService.getAll();
        request.setAttribute("listCartItem", listCartItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/list-order.jsp");
        dispatcher.forward(request, response);
    }
}
