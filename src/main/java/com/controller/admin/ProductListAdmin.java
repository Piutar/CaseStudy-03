package com.controller.admin;

import com.model.Product;
import com.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductListAdmin", urlPatterns = "/admin/product/list")
public class ProductListAdmin extends HttpServlet {
    ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> proList = productService.getAll();
        request.setAttribute("proList", proList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/list-product.jsp");
        dispatcher.forward(request, response);
    }
}
