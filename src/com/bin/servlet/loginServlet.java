package com.bin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bin.dao.basicDao;
import com.bin.service.loginService;

/**
 * @Author: Bhy
 * @Date: 2019/1/8
 */
@WebServlet(name = "loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String,String> msgs = new HashMap<>();
        String flag = request.getParameter("flag");
        msgs.put("flag",flag);
        if(flag.equals("account")){
            msgs.put("username",request.getParameter("username"));
            msgs.put("password",request.getParameter("password"));
            msgs.put("captcha",request.getParameter("captcha"));
        }
        else{
            msgs.put("phoneNumber",request.getParameter("phoneNumber"));
            msgs.put("captcha",request.getParameter("captcha"));
        }

        loginService loginService = new loginService();
        if (loginService.checkLogin(msgs)){
            HttpSession session = request.getSession();
            if (flag.equals("account")){
                session.setAttribute("name",request.getParameter("username"));//将用户信息保存
            }
            else{
                session.setAttribute("name",new basicDao().getName(msgs.get("phoneNumber")));
            }

            request.getRequestDispatcher("/resources/html/resume.html").forward(request,response);
        }
        else{
            request.getRequestDispatcher("/index.html").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
