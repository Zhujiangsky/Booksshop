package com.test.intface;

import com.test.entity.BooksEntity;

import java.sql.SQLException;
import java.util.List;

public interface BooksInterface {
    public int add(BooksEntity booksEntity);

    public List<BooksEntity> queryAll(Integer currentPageNo, Integer pageSize) throws SQLException, ClassNotFoundException;

    public BooksEntity queryOne(int id) throws SQLException, ClassNotFoundException;

    public int update(BooksEntity booksEntity);

    public Integer count(String filename) throws SQLException, ClassNotFoundException;
}
