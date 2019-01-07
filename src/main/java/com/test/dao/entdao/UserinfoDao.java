package com.test.dao.entdao;


import com.test.dao.BaseDao;
import com.test.entity.UserinfoEntity;
import com.test.intface.UserinfoInterface;
import com.test.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Iterator;


public class UserinfoDao extends BaseDao implements UserinfoInterface {
    RowMapper<UserinfoEntity> rm = new RowMapper<UserinfoEntity>() {
        @Override
        public UserinfoEntity mapper(Map<String, Object> map) {
            UserinfoEntity userinfo = new UserinfoEntity();
            Iterator<String> iter = map.keySet().iterator(); // 迭代器提取键值
            while (iter.hasNext()) {
                String x = iter.next();
                Object ob = map.get(x);
                if (x.equals("username")) {
                    userinfo.setUsername(String.valueOf(ob));
                } else if (x.equals("password")) {
                    userinfo.setPassword(String.valueOf(ob));
                } else if (x.equals("email")) {
                    userinfo.setEmail(String.valueOf(ob));
                }
            }
            return userinfo;
        }
    };

    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public int add(UserinfoEntity userinfoEntity) throws SQLException, ClassNotFoundException {
        String sql = "insert into userinfo(username,password,email) values(?,?,?)";
        return this.executeUpdate(sql, userinfoEntity.getUsername(), userinfoEntity.getPassword(), userinfoEntity.getEmail());

    }

    @Override
    public List<UserinfoEntity> queryAll(Integer currentPageNo, Integer pageSize) {
        return null;
    }

    @Override
    public UserinfoEntity queryOne(int id) {
        return null;
    }

    @Override
    public int update(UserinfoEntity userinfoEntity) {
        return 0;
    }

    @Override
    public Integer count(String filename) throws SQLException, ClassNotFoundException {
        return null;
    }

    public UserinfoEntity queryOne(String uname) throws SQLException, ClassNotFoundException {
        String sql = "select * from userinfo where username=?";
        return this.executeQueryOne(sql, rm, uname);
    }


    public UserinfoEntity queryOne(String uname, String pwd) throws SQLException, ClassNotFoundException {
        String sql = "select * from userinfo where username=? and password=?";
        return this.executeQueryOne(sql, rm, uname, pwd);
    }
}
