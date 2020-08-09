package com.controller;

import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "WaitingServlet", urlPatterns="/waiting")
public class WaitingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User u=(User) session.getAttribute("account");
            request.setAttribute("username", u.getUsername());
            if(u.getRoleId()==1) {
                response.sendRedirect(request.getContextPath()+"/admin");
            }else {
                response.sendRedirect(request.getContextPath()+"/home");
            }

        }else {
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }
}
