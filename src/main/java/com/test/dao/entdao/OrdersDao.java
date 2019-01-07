package com.test.dao.entdao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.math.BigDecimal;

import com.test.dao.BaseDao;
import com.test.entity.OrdersEntity;
import com.test.intface.OrdersInterface;
import com.test.util.RowMapper;

public class OrdersDao extends BaseDao implements OrdersInterface {
    RowMapper<OrdersEntity> rm = new RowMapper<OrdersEntity>() {
        @Override
        public OrdersEntity mapper(Map<String, Object> map) {
            OrdersEntity orders = new OrdersEntity();
            Iterator<String> iter = map.keySet().iterator(); // 迭代器提取键值
            while (iter.hasNext()) {
                String x = iter.next();
                Object ob = map.get(x);
                if (x.equals("oid")) {
                    orders.setOid(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("username")) {
                    orders.setUsername(String.valueOf(ob));
                }
            }
            return orders;
        }
    };

    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public int add(OrdersEntity ordersEntity) {
        return 0;
    }

    @Override
    public List<OrdersEntity> queryAll(Integer currentPageNo, Integer pageSize) {
        return null;
    }

    @Override
    public OrdersEntity queryOne(int id) {
        return null;
    }

    @Override
    public int update(OrdersEntity ordersEntity) {
        return 0;
    }

    @Override
    public Integer count(String filename) throws SQLException, ClassNotFoundException {
        return null;
    }
}
