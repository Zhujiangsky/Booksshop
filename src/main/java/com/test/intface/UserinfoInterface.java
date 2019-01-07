package com.test.intface;

import com.test.entity.BooksEntity;
import com.test.entity.UserinfoEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserinfoInterface {
    public int add(UserinfoEntity userinfoEntity) throws SQLException, ClassNotFoundException;

    public List<UserinfoEntity> queryAll(Integer currentPageNo, Integer pageSize);

    public UserinfoEntity queryOne(int id);

    public int update(UserinfoEntity userinfoEntity);

    public Integer count(String filename) throws SQLException, ClassNotFoundException;

}
