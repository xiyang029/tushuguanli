package com.library.dao;

import com.library.model.Borrow;

import java.util.List;

public interface BorrowDao {

    // 根据用户 ID 查找借阅记录列表
    List<Borrow> findByUserId(int userId);

    // 根据用户名查找借阅记录列表
    List<Borrow> findByUsername(String username);

    // 根据书籍标题进行模糊查询借阅记录列表
    List<Borrow> findByBookTitle(String bookTitle);

    // 查找所有的借阅记录列表
    List<Borrow> findAll();

    // 保存新的借阅记录
    void save(Borrow borrowRecord);

    // 更新已有的借阅记录
    void update(Borrow borrowRecord);

    // 删除指定 ID 的借阅记录
    void delete(int borrowId);
}

