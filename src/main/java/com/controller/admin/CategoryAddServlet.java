package com.controller.admin;

import com.model.Category;
import com.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryAddServlet", urlPatterns = "/admin/category/add")
public class CategoryAddServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");


        Category category = new Category();
        category.setName(name);


        categoryService.insert(category);

        response.sendRedirect(request.getContextPath() + "/admin/category/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/category.jsp");
        dispatcher.forward(request, response);
    }
}
