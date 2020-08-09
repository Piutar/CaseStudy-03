package com.controller;

import com.model.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login2", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        String alter = "";

        if (username == ""){
            alter = "Username or password cannot be left blank!";
            System.out.println(alter);
            request.setAttribute("alert", alter);
            request.getRequestDispatcher("/view/client/view/login.jsp").forward(request, response);
            return;
        }

        UserService service = new UserService();
        User user = service.login(username, password);
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("account", user);
            response.sendRedirect(request.getContextPath()+"/waiting");
        } else {
            alter = "Username or password is incorrect";
            request.setAttribute("alert", alter);

            request.getRequestDispatcher("/view/client/view/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            response.sendRedirect(request.getContextPath()+ "/waiting");
        } else {
            response.sendRedirect(request.getContextPath()+ "/view/client/view/login.jsp");
        }
    }
}
