package com.example.login.controller;

import com.example.login.Constant;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/*")
public class userFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.getAttribute(Constant.USER_SESSION);
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute(Constant.USER_SESSION) == null) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("/loginProject/index.jsp");
        } else {
            chain.doFilter(request,response);
        }
    }
}
