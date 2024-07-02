package com.library.service;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.library.dao.impl.BookDaoImpl;
import com.library.dao.BookDao;
import com.library.model.Book;
import com.google.gson.Gson;

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");

        BookDaoImpl bookDAO = new BookDaoImpl();

        // 调用 findByTitleLike 方法查找包含指定关键词的书籍
        List<Book> books = bookDAO.findByTitleLike(title);

        // 设置响应内容类型为 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 创建 Gson 对象
        Gson gson = new Gson();

        // 将 books 列表转换为 JSON 字符串
        String jsonBooks = gson.toJson(books);

        // 输出 JSON 字符串到响应
        PrintWriter out = response.getWriter();
        out.print(jsonBooks);
    }
}
