package com.bin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import com.bin.service.captchaService;

/**
 * @Author: Bhy
 * @Date: 2019/1/8
 */
@WebServlet(name = "captchaServlet")
public class captchaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Writer writer = response.getWriter();
        try {
            writer.write(new captchaService().getNewCaptcha());
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }
}
