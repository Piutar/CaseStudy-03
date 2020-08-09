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

@WebServlet(name = "CategoryEditServlet", urlPatterns = "/admin/category/edit")
public class CategoryEditServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        System.out.println(request.getParameter("id"));
        category.setId(Integer.parseInt(request.getParameter("id")));
        category.setName(request.getParameter("name"));
        categoryService.edit(category);

        response.sendRedirect(request.getContextPath()+"/admin/category/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Category category = categoryService.get(Integer.parseInt(id));

        request.setAttribute("category", category);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/category-edit.jsp");
        dispatcher.forward(request, response);
    }
}
