package com.bin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.bin.service.*;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 */
@WebServlet(name = "registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        registerService register = new registerService();
        String userId = request.getParameter("username");
        String pwd = request.getParameter("pwd1th");
        if (!register.registerSuccess(userId,pwd)){
            request.getRequestDispatcher("/register").forward(request,response);
        }
        else{
            request.getRequestDispatcher("/index.html").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
