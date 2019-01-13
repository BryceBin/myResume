package com.bin.servlet;

import com.bin.service.captchaService;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import com.bin.service.getUserInfoService;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 */
@WebServlet(name = "getUserInfoServlet")
public class getUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("name");
        System.out.println("useId = "+userId);
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        Writer writer = response.getWriter();
        try {
            String info = new getUserInfoService().getInfo(userId);
            System.out.println(info);
            writer.write(info);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }
}
