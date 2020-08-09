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

@WebServlet(name = "ProductEditServlet", urlPatterns = "/admin/product/edit")
public class ProductEditServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);

            for (FileItem item : items) {
                switch (item.getFieldName()){
                    case "id":
                        product.setId(Integer.parseInt(item.getString()));
                        break;
                    case "name":
                        product.setName(item.getString());
                        break;
                    case "price":
                        product.setPrice(Long.parseLong(item.getString()));
                        break;
                    case "des":
                        product.setDes(item.getString());
                        System.out.println(item.getString());
                        break;
                    case "cate":
                        product.setCategory(categoryService.get(item.getString()));
                        break;
                    case "image":
                        if (item.getSize() > 0) {// neu co file d
                            final String dir = "/upload";

                            String originalFileName = item.getName();
                            int index = originalFileName.lastIndexOf(".");
                            String ext = originalFileName.substring(index + 1);
                            String fileName = System.currentTimeMillis() + "." + ext;
                            File file = new File(dir + "/" + fileName);
                            item.write(file);

                            product.setImage(fileName);

                        } else {

                            product.setImage(null);
                        }
                }
            }
            productService.edit(product);

            response.sendRedirect(request.getContextPath() + "/admin/product/list");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Product product = productService.get(Integer.parseInt(id));
        List<Category> categories = categoryService.getAll();

        request.setAttribute("categories", categories);

        request.setAttribute("product", product);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/edit-product.jsp");
        dispatcher.forward(request, response);
    }
}
