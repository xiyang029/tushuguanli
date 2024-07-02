package com.library.dao;

import com.library.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    List<Book> findByTitleLike(String title);

    void save(Book book);

    void update(Book book);

    void delete(int id);
}
