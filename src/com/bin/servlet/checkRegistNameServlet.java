package com.bin.servlet;

import com.bin.service.captchaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import com.bin.service.registerService;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 */
@WebServlet(name = "checkRegistNameServlet")
public class checkRegistNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Writer writer = response.getWriter();
        try {
            String name = request.getParameter("name");
            System.out.println(name);
            writer.write(new registerService().checkNameUseable(name));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }
}
