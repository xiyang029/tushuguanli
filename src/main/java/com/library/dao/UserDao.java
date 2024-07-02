package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    // 数据库连接相关信息，这里假设使用 MySQL 数据库
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";

    // SQL 查询语句，根据实际表名和字段名修改
    private static final String QUERY_USER_SQL = "SELECT * FROM users WHERE username = ? AND password = ?";

    // 验证用户是否存在的方法
    public boolean validateUser(String username, String password) {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");

            // 建立数据库连接
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // 创建 PreparedStatement 对象
            stmt = conn.prepareStatement(QUERY_USER_SQL);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // 执行查询
            rs = stmt.executeQuery();

            // 如果查询结果有数据，则说明用户存在且密码正确
            if (rs.next()) {
                isValid = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // 可以根据实际情况处理异常
        } finally {
            // 关闭 ResultSet 对象
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // 关闭 PreparedStatement 对象
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // 关闭 Connection 对象
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValid;
    }
}
