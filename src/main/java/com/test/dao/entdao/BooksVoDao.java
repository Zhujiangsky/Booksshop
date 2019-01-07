package com.test.dao.entdao;


import com.test.dao.BDManager;
import com.test.dao.BaseDao;
import com.test.entity.BooksEntity;
import com.test.entity.BooksVo;
import com.test.intface.BooksVoInterface;
import com.test.util.Pager;
import com.test.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.math.BigDecimal;


public class BooksVoDao extends BaseDao implements BooksVoInterface {
    RowMapper<BooksVo> rm = new RowMapper<BooksVo>() {
        @Override
        public BooksVo mapper(Map<String, Object> map) {
            BooksVo booksvo = new BooksVo();
            Iterator<String> iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String x = iter.next();
                Object ob = map.get(x);
                if (x.equals("b_price")) {
                    booksvo.setB_price(String.valueOf(ob));
                } else if (x.equals("createdate")) {
                    booksvo.setCreatedate(java.sql.Date.valueOf(String.valueOf(ob).substring(0, 10)));
                } else if (x.equals("state")) {
                    booksvo.setState(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("image")) {
                    booksvo.setImage(String.valueOf(ob));
                } else if (x.equals("oid")) {
                    booksvo.setOid(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("username")) {
                    booksvo.setUsername(String.valueOf(ob));
                }
            }
            return booksvo;
        }
    };

    @Override
    public List<BooksVo> queryAll(String uname, Integer currentPageNo, Integer pageSize) throws SQLException, ClassNotFoundException {
        StringBuffer sql = new StringBuffer("select books.b_price,items.createdate,items.state,books.image,items.oid,orders.username FROM items INNER JOIN orders on items.oid=orders.oid INNER JOIN books ON items.bid=books.bid WHERE orders.username=?");
        int total = this.count("", uname);
        Pager pager = new Pager(total, pageSize, currentPageNo);
        sql.append(" limit " + (pager.getCurrentPage() - 1) * pager.getRowPerPage() + "," + pager.getRowPerPage());
        return this.executeQuery(sql.toString(), rm, uname);

    }

    @Override
    public Integer count(String filename, String uname) throws SQLException, ClassNotFoundException {
        List<Object> paramsList = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select count(1) as count,books.b_price,items.createdate,items.state,books.image,items.oid,orders.username FROM items INNER JOIN orders on items.oid=orders.oid INNER JOIN books ON items.bid=books.bid WHERE orders.username=? ");
        Integer count = 0;
        this.createStatement(sql.toString(), uname);
        ResultSet rs = this.pstm.executeQuery();
        try {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BDManager.colse(this.connection, this.rs, this.pstm);
        return count;
    }

    @Override
    public BooksEntity queryOne(int id) {
        return null;
    }

    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        return null;
    }
}
