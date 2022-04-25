package com.example.login.controller;

import com.example.login.pojo.User;
import com.example.login.pojo.vo.MessageModel;
import com.example.login.service.EmailService;
import com.example.login.service.RegisterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String email = request.getParameter("email");
        User newUser = new User();
        newUser.setUsername(uname);
        newUser.setPassword(upwd);
        newUser.setEmail(email);

        // 调用service层
        RegisterService registerService = new RegisterService(newUser);
        MessageModel messageModel = registerService.UserRegister();
        if (messageModel.getStateCode() == 200) {
            System.out.println("begin JavaMail");
            // JavaMail
            Thread thread = new Thread(new EmailService(newUser));
            thread.start();
            response.sendRedirect("/loginProject/success.jsp");
        } else if (messageModel.getStateCode() == 400) {
            request.setAttribute("ResMessageModel",messageModel);
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
