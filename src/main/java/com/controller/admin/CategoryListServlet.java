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
import java.util.List;

@WebServlet(name = "CategoryListServlet", urlPatterns = "/admin/category/list")
public class CategoryListServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();
    Category category = new Category();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> cateList = categoryService.getAll();
        request.setAttribute("cateList", cateList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/category.jsp");
        dispatcher.forward(request, response);
    }
}
