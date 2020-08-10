package com.controller.admin;

import com.model.User;
import com.service.UserService;
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

@WebServlet(name = "UserAddServlet", urlPatterns = "/admin/user/add")
public class UserAddServlet extends HttpServlet {
    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for (FileItem item : items) {
                switch (item.getFieldName()){
                    case "username":
                        user.setUsername(item.getString());
                        break;
                    case "password":
                        user.setPassword(item.getString());
                        break;
                    case "email":
                        user.setEmail(item.getString());
                        break;
                    case "role":
                        user.setRoleId(Integer.parseInt(item.getString()));
                        break;
                    case "avatar":
                        String dir = "/upload";
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = originalFileName.substring(index + 1);
                        String fileName = System.currentTimeMillis() + "." + ext;
                        File file = new File(dir + "/" + fileName);
                        item.write(file);
                        user.setAvatar(fileName);
                        break;
                }
            }

            userService.insert(user);

            response.sendRedirect(request.getContextPath() + "/admin/user/list");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/user/add?e=1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eString = request.getParameter("e");
        if (eString != null) {
            if (eString.equals("1")) {
                request.setAttribute("errMsg", "Username da ton tai!!!");
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/add-user.jsp");
        dispatcher.forward(request, response);
    }
}
