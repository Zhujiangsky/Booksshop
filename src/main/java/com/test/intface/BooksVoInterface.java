package com.test.intface;

import com.test.entity.BooksEntity;
import com.test.entity.BooksVo;

import java.sql.SQLException;
import java.util.List;

public interface BooksVoInterface {
    public List<BooksVo> queryAll(String uname, Integer currentPageNo, Integer pageSize) throws SQLException, ClassNotFoundException;

    public Integer count(String filename, String uname) throws SQLException, ClassNotFoundException;

    public BooksEntity queryOne(int id);
}
