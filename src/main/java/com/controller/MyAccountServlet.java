package com.controller;

import com.model.User;
import com.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet(name = "MyAccountServlet", urlPatterns = "/member/myaccount")
public class MyAccountServlet extends HttpServlet {
    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = "";
        User user = new User();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for (FileItem item : items) {
                if (item.getFieldName().equals("id")) {
                    id = item.getString();
                    user.setId(Integer.parseInt(item.getString()));
                } else if (item.getFieldName().equals("email")) {
                    user.setEmail(item.getString());
                } else if (item.getFieldName().equals("username")) {
                    user.setUsername(item.getString());
                } else if (item.getFieldName().equals("password")) {
                    user.setPassword(item.getString());
                } else if (item.getFieldName().equals("role")) {
                    user.setRoleId(Integer.parseInt(item.getString()));
                } else if (item.getFieldName().equals("avatar")) {
                    if (item.getSize() > 0) {// neu co file d
                        final String dir = "/upload";
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = originalFileName.substring(index + 1);
                        String fileName = System.currentTimeMillis() + "." + ext;
                        File file = new File(dir + "/" + fileName);
                        item.write(file);

                        user.setAvatar(fileName);
                    } else {
                        user.setAvatar(null);
                    }
                }
            }

            userService.edit(user);
            User u = userService.get(Integer.parseInt(id));
            HttpSession session = request.getSession(true);
            session.setAttribute("account", u);

            response.sendRedirect(request.getContextPath() + "/member/myaccount");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/member/myaccount");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/view/myaccount.jsp");
        dispatcher.forward(request, response);
    }
}
