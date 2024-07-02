package com.library.service;

import com.google.gson.Gson;
import com.library.dao.BookDao;
import com.library.dao.impl.BookDaoImpl;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/fetchBooks")
public class FetchBooksServlet extends HttpServlet {

    private BookDao bookDao;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDao = new BookDaoImpl(); // 初始化 BookDao 实例
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        List<Book> books = bookDao.findAll();

        Gson gson = new Gson();
        String json = gson.toJson(books);

        out.print(json);
        out.close();
    }
}
