package com.library.service;

import com.library.dao.BorrowDao;
import com.library.dao.impl.BorrowDaoImpl;
import com.library.model.Borrow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/borrowBookServlet")
public class BorrowBookServlet extends HttpServlet {

    private BorrowDao borrowDao = new BorrowDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应的字符编码为 UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 获取请求参数
        String borrowBookId = request.getParameter("borrowBookId");
        String borrowUsername = request.getParameter("borrowUsername");
        String borrowTime = request.getParameter("borrowTime");
        String borrowTitle = request.getParameter("borrowBookTitle");

        // 打印输出以调试
        System.out.println("Book ID: " + borrowBookId);
        System.out.println("Username: " + borrowUsername);
        System.out.println("Borrow Time: " + borrowTime);
        System.out.println("Borrow Title: " + borrowTitle);

        try {
            // 数据校验：确保必要字段不为空
            if (borrowBookId == null || borrowUsername == null || borrowTime == null || borrowTitle == null ||
                    borrowBookId.isEmpty() || borrowUsername.isEmpty() || borrowTime.isEmpty() || borrowTitle.isEmpty()) {
                // 处理错误：例如跳转到错误页面或返回错误信息
                response.sendRedirect("error.jsp?message=缺少必要信息");
                return;
            }

            // 创建 Borrow 对象
            Borrow borrow = new Borrow();
            borrow.setUsername(borrowUsername);
            borrow.setBookId(Integer.parseInt(borrowBookId));
            borrow.setTitle(borrowTitle);

            // 设置借阅日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            borrow.setBorrowDate(sdf.parse(borrowTime));

            // 计算归还日期 (例子：借阅时间 + 14天)
            Date returnDate = new Date(borrow.getBorrowDate().getTime() + 14 * 24 * 60 * 60 * 1000L);
            borrow.setReturnDate(returnDate);

            // 调用 BorrowDao 的 save 方法插入借阅记录
            borrowDao.save(borrow);

            // 重定向到成功页面
            response.sendRedirect("http://localhost:8080/tushuguanli_war_exploded/views/borrow.jsp");

        } catch (ParseException e) {
            // 处理日期解析异常
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=日期格式错误");
        } catch (NumberFormatException e) {
            // 处理数字格式异常
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=图书ID格式错误");
        } catch (Exception e) {
            // 处理其他异常
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=系统错误");
        }
    }
}