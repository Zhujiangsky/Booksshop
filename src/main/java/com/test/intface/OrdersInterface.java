package com.test.intface;

import com.test.entity.ItemsEntity;
import com.test.entity.OrdersEntity;

import java.sql.SQLException;
import java.util.List;

public interface OrdersInterface {
    public int add(OrdersEntity ordersEntity) throws SQLException, ClassNotFoundException;

    public List<OrdersEntity> queryAll(Integer currentPageNo, Integer pageSize);

    public OrdersEntity queryOne(int id);

    public int update(OrdersEntity ordersEntity);

    public Integer count(String filename) throws SQLException, ClassNotFoundException;
}

