package com.library.dao.impl;

import com.library.dao.BookDao;
import com.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    // 数据库连接信息
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";

    // SQL查询语句
    private static final String SELECT_ALL_SQL = "SELECT * FROM books";
    private static final String SELECT_BY_TITLE_SQL = "SELECT * FROM books WHERE title LIKE ?";
    private static final String INSERT_SQL = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE books SET title=?, author=?, available=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM books WHERE id=?";

    // 获取数据库连接的方法
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                boolean available = rs.getBoolean("available");
                Book book = new Book(id, title, author, available);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findByTitleLike(String title) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE title LIKE ?")) {

            stmt.setString(1, "%" + title + "%"); // 使用 % 模糊匹配
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String bookTitle = rs.getString("title");
                String author = rs.getString("author");
                boolean available = rs.getBoolean("available");
                Book book = new Book(id, bookTitle, author, available);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }



    @Override
    public void save(Book book) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setBoolean(3, book.isAvailable());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("创建图书失败，未插入任何行。");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    book.setId(id);  // 设置生成的ID到Book对象
                } else {
                    throw new SQLException("创建图书失败，未获取到ID。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setBoolean(3, book.isAvailable());
            stmt.setInt(4, book.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("更新图书失败，未更新任何行。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("删除图书失败，未删除任何行。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
