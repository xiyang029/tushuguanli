package com.library.service;

import com.google.gson.Gson;
import com.library.dao.BorrowDao;
import com.library.dao.impl.BorrowDaoImpl;
import com.library.model.Borrow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/fetchBorrow")
public class FetchBorrowServlet extends HttpServlet {

    private BorrowDao borrowDao;

    @Override
    public void init() throws ServletException {
        super.init();
        borrowDao = new BorrowDaoImpl(); // 初始化 BorrowDao 实现
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Borrow> borrowList = borrowDao.findAll(); // 获取所有借阅记录

        Gson gson = new Gson();
        String json = gson.toJson(borrowList); // 将借阅记录列表转换为 JSON 字符串

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json); // 输出 JSON 字符串
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp); // 处理 POST 请求
    }
}