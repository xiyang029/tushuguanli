package com.library.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.library.dao.impl.BorrowDaoImpl;

@WebServlet("/delborrow")
public class delborrowservlet extends HttpServlet {
    private BorrowDaoImpl borrowDAO; // 假设有一个 BorrowDAOImpl 实例

    @Override
    public void init() throws ServletException {
        super.init();
        borrowDAO = new BorrowDaoImpl(); // 在 Servlet 初始化时实例化 BorrowDAOImpl
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取前端传来的借阅记录 ID
        String borrowIdStr = request.getParameter("borrowId");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8"); // 添加这行
        if (borrowIdStr != null && !borrowIdStr.isEmpty()) {
            try {
                int borrowId = Integer.parseInt(borrowIdStr);

                // 调用 BorrowDAOImpl 中的 delete 方法删除借阅记录
                borrowDAO.delete(borrowId);

                // 返回成功消息给前端
                response.getWriter().write("借阅记录删除成功");
            } catch (NumberFormatException e) {
                // 如果参数无法解析为整数，返回错误消息给前端
                response.getWriter().write("借阅记录ID无效");
            } catch (Exception e) {
                // 处理其他异常情况
                response.getWriter().write("删除借阅记录失败：" + e.getMessage());
            }
        } else {
            // 如果没有收到有效的 borrowId 参数，返回错误消息给前端
            response.getWriter().write("未提供有效的借阅记录 ID");
        }
    }

    @Override
    public void destroy() {
        // 在 Servlet 销毁时执行的清理工作，如果有需要可以在此处理
    }
}
