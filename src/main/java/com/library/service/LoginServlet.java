package com.library.service;

import com.library.dao.UserDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao; // 引入 UserDao

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao(); // 在初始化时实例化 UserDao
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 调用 UserDao 的方法验证用户
        boolean isValidUser = userDao.validateUser(username, password);

        if (isValidUser) {
            // 登录成功，重定向到成功页面或者其他逻辑
            response.sendRedirect(request.getContextPath() + "/views/index.jsp");
        } else {
            // 登录失败，重定向回登录页面，可以附带错误信息
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}
