package com.controller.admin;

import com.model.Category;
import com.model.Product;
import com.service.CategoryService;
import com.service.ProductService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductAddServlet", urlPatterns = "/admin/product/add")
public class ProductAddServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for (FileItem item : items) {
                if (item.getFieldName().equals("name")) {
                    product.setName(item.getString());
                } else if (item.getFieldName().equals("category")) {
                    product.setCategory(categoryService.get(Integer.parseInt(item.getString())));
                } else if (item.getFieldName().equals("price")) {
                    product.setPrice(Long.parseLong(item.getString()));
                } else if (item.getFieldName().equals("des")) {
                    product.setDes(item.getString());
                } else if (item.getFieldName().equals("image")) {
                    final String dir = "/upload";

                    String originalFileName = item.getName();
                    int index = originalFileName.lastIndexOf(".");
                    String ext = originalFileName.substring(index + 1);
                    String fileName = System.currentTimeMillis() + "." + ext;
                    File file = new File(dir + "/" + fileName);
                    item.write(file);
                    product.setImage(fileName);
                }
            }

            productService.insert(product);

            response.sendRedirect(request.getContextPath() + "/admin/product/list");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.getAll();

        request.setAttribute("categories", categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/add-product.jsp");
        dispatcher.forward(request, response);
    }
}
