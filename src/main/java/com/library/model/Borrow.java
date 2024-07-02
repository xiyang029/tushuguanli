package com.library.model;

import java.util.Date;

public class Borrow {
    private int id;
    private String username; // 对应用户的用户名
    private int bookId; // 对应书籍的 ID
    private String title; // 对应书籍的标题
    private Date borrowDate;
    private Date returnDate;

    // 构造函数
    public Borrow(int id, String username, int bookId, String title, Date borrowDate, Date returnDate) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.title = title;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // 默认构造函数
    public Borrow() {
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", bookId=" + bookId +
                ", title='" + title + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
