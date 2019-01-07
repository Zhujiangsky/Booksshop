package com.test.intface;

import com.test.entity.BooksEntity;
import com.test.entity.ItemsEntity;

import java.sql.SQLException;
import java.util.List;

public interface ItemsInterface {
    public int add(ItemsEntity itemsEntity);

    public List<ItemsEntity> queryAll(Integer currentPageNo, Integer pageSize);

    public ItemsEntity queryOne(int id);

    public int update(ItemsEntity itemsEntity);

    public Integer count(String filename) throws SQLException, ClassNotFoundException;
}

