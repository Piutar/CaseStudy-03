package com.controller;

import com.model.Product;
import com.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductSearchServlet", urlPatterns="/product/seach")
public class ProductSearchServlet extends HttpServlet {
    ProductService productService=new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        List<Product> productSeachByName =productService.seachByName(name);
        request.setAttribute("productSeachByName", productSeachByName);
        request.getRequestDispatcher("/view/client/view/product-seach-by-name.jsp").forward(request, response);

    }
}
