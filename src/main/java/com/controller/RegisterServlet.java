package com.controller;

import com.model.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    public static final String REGISTER = "/view/client/view/register.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserService userService = new UserService();
        User user = new User(email, username,password);
        String alertMsg = "";

        if (userService.checkExistEmail(email)) {
            alertMsg = "Email already exist!";
            request.setAttribute("alert", alertMsg);
            request.getRequestDispatcher(REGISTER).forward(request, response);
            return;
        } else
        if (userService.checkExistUsername(username)) {
            alertMsg = "Username already exist!";
            request.setAttribute("alert", alertMsg);
            request.getRequestDispatcher(REGISTER).forward(request, response);
            return;
        }
        userService.insert(user);
        request.getRequestDispatcher("/view/client/view/login.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }
        request.getRequestDispatcher(REGISTER).forward(request, response);
    }
}
