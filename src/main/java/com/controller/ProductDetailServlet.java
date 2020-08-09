package com.controller;

import com.model.Category;
import com.model.Product;
import com.service.CategoryService;
import com.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductDetailServlet", urlPatterns = "/product/detail")
public class ProductDetailServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.get(id);

        List<Category> categories = categoryService.getAll();

        request.setAttribute("categories", categories);
        request.setAttribute("product", product);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/view/product-detail.jsp");
        dispatcher.forward(request, response);

    }
}
