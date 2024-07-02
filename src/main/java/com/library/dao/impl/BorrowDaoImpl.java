package com.library.dao.impl;

import com.library.dao.BorrowDao;
import com.library.model.Borrow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {

    // 数据库连接信息
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";

    // SQL查询语句
    private static final String SELECT_BY_USER_ID_SQL = "SELECT * FROM borrow WHERE user_id=?";
    private static final String SELECT_BY_USERNAME_SQL = "SELECT * FROM borrow WHERE username=?";
    private static final String SELECT_BY_BOOK_TITLE_SQL = "SELECT * FROM borrow WHERE title LIKE ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM borrow";
    private static final String INSERT_SQL = "INSERT INTO borrow (username, book_id, title, borrow_date, return_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE borrow SET username=?, book_id=?, title=?, borrow_date=?, return_date=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM borrow WHERE id=?";

    // 获取数据库连接的方法
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    @Override
    public List<Borrow> findByUserId(int userId) {
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_USER_ID_SQL)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                Date borrowDate = rs.getDate("borrow_date");
                Date returnDate = rs.getDate("return_date");

                Borrow borrow = new Borrow(id, username, bookId, title, borrowDate, returnDate);
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public List<Borrow> findByUsername(String username) {
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_USERNAME_SQL)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                Date borrowDate = rs.getDate("borrow_date");
                Date returnDate = rs.getDate("return_date");

                Borrow borrow = new Borrow(id, username, bookId, title, borrowDate, returnDate);
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public List<Borrow> findByBookTitle(String bookTitle) {
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_BOOK_TITLE_SQL)) {

            stmt.setString(1, "%" + bookTitle + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                Date borrowDate = rs.getDate("borrow_date");
                Date returnDate = rs.getDate("return_date");

                Borrow borrow = new Borrow(id, username, bookId, title, borrowDate, returnDate);
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public List<Borrow> findAll() {
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                Date borrowDate = rs.getDate("borrow_date");
                Date returnDate = rs.getDate("return_date");

                Borrow borrow = new Borrow(id, username, bookId, title, borrowDate, returnDate);
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public void save(Borrow borrow) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, borrow.getUsername());
            stmt.setInt(2, borrow.getBookId());
            stmt.setString(3, borrow.getTitle());
            stmt.setDate(4, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(5, new java.sql.Date(borrow.getReturnDate().getTime()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("创建借阅记录失败，未插入任何行。");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    borrow.setId(id);  // 设置生成的ID到Borrow对象
                } else {
                    throw new SQLException("创建借阅记录失败，未获取到ID。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Borrow borrow) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {

            stmt.setString(1, borrow.getUsername());
            stmt.setInt(2, borrow.getBookId());
            stmt.setString(3, borrow.getTitle());
            stmt.setDate(4, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(5, new java.sql.Date(borrow.getReturnDate().getTime()));
            stmt.setInt(6, borrow.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("更新借阅记录失败，未更新任何行。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int borrowId) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {

            stmt.setInt(1, borrowId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("删除借阅记录失败，未删除任何行。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}