package com.example.login.controller;

import com.example.login.pojo.User;
import com.example.login.pojo.vo.MessageModel;
import com.example.login.service.UserServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.example.login.Constant;

/*
 * controller层
 * 1.接受客户端请求
 * 2.调用service
 * 3.将消息模型设置到request中
 * 4，转发到登录页面
 * */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ==================获取前台参数==========================
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        // ======================调用service=====================
        UserServiceImp userService = new UserServiceImp(user);
        MessageModel messageModel = userService.Service();
        request.getServletContext().setAttribute("application","halo");
        // =====================响应结果==========================
        if (messageModel.getStateCode() == 200) {
            // 登录成功
            System.out.println(messageModel.getMessage());
            // 设置用户的session，将用户session值存为session对应的id可以防止重复
            request.getSession().setAttribute(Constant.USER_SESSION,request.getSession().getId());
            request.getSession().setAttribute("user",messageModel.getData());
            response.sendRedirect("/loginProject/user/home.jsp");
//            response.sendRedirect("user/home.jsp");
        } else if (messageModel.getStateCode() == 400) {
            // 登录失败
            request.setAttribute("messageModel",messageModel);
            response.setContentType("text/html;charset=utf-8");
            request.getRequestDispatcher("/index.jsp").forward(request,response);  // 刷新页面
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
