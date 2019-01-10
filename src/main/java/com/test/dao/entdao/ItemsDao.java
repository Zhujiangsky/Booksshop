package com.test.dao.entdao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.math.BigDecimal;


import com.test.dao.BaseDao;
import com.test.entity.ItemsEntity;
import com.test.intface.ItemsInterface;
import com.test.util.RowMapper;

public class ItemsDao extends BaseDao implements ItemsInterface {
    RowMapper<ItemsEntity> rm = new RowMapper<ItemsEntity>() {
        @Override
        public ItemsEntity mapper(Map<String, Object> map) {
            ItemsEntity items = new ItemsEntity();
            Iterator<String> iter = map.keySet().iterator(); // 迭代器提取键值
            while (iter.hasNext()) {
                String x = iter.next();
                Object ob = map.get(x);
                if (x.equals("iid")) {
                    items.setIid(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("oid")) {
                    items.setOid(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("bid")) {
                    items.setBid(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("createdate")) {
                    items.setCreatedate(String.valueOf(ob));
                } else if (x.equals("count")) {
                    items.setCount(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("price")) {
                    items.setPrice(String.valueOf(ob));
                } else if (x.equals("state")) {
                    items.setState(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("total_price")) {
                    items.setTotal_price(String.valueOf(ob));
                }
            }
            return items;
        }
    };

    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public int add(ItemsEntity itemsEntity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT into items(oid,bid,createdate,count,price,state,total_price) VALUES(?,?,now(),?,?,0,?)";
        return this.executeUpdate(sql, itemsEntity.getOid(), itemsEntity.getBid(), itemsEntity.getCount(), itemsEntity.getPrice(), itemsEntity.getTotal_price());
    }

    @Override
    public List<ItemsEntity> queryAll(Integer currentPageNo, Integer pageSize) {
        return null;
    }

    @Override
    public ItemsEntity queryOne(int id) {
        return null;
    }

    @Override
    public int update(ItemsEntity itemsEntity) {
        return 0;
    }

    @Override
    public Integer count(String filename) throws SQLException, ClassNotFoundException {
        return null;
    }
}
