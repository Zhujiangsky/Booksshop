package com.test.dao.entdao;

import com.test.dao.BDManager;
import com.test.dao.BaseDao;
import com.test.entity.BooksEntity;
import com.test.intface.BooksInterface;
import com.test.util.Pager;
import com.test.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class BooksDao extends BaseDao implements BooksInterface {
    RowMapper<BooksEntity> rm = new RowMapper<BooksEntity>() {
        @Override
        public BooksEntity mapper(Map<String, Object> map) {
            BooksEntity books = new BooksEntity();
            Iterator<String> iter = map.keySet().iterator(); // 迭代器提取键值
            while (iter.hasNext()) {
                String x = iter.next();
                Object ob = map.get(x);
                if (x.equals("bid")) {
                    books.setBid(Integer.parseInt(String.valueOf(ob)));
                } else if (x.equals("bookname")) {
                    books.setBookname(String.valueOf(ob));
                } else if (x.equals("b_price")) {
                    books.setB_price(String.valueOf(ob));
                } else if (x.equals("image")) {
                    books.setImage(String.valueOf(ob));
                } else if (x.equals("stock")) {
                    books.setStock(Integer.parseInt(String.valueOf(ob)));
                }
            }
            return books;
        }
    };

    @Override
    public int add(BooksEntity booksEntity) {
        return 0;
    }

    @Override
    public List<BooksEntity> queryAll(Integer currentPageNo, Integer pageSize) throws SQLException, ClassNotFoundException {
        StringBuffer sql = new StringBuffer("select bid,bookname,b_price,image,stock from books where 1=1");
        int total = this.count("id");
        Pager pager = new Pager(total, pageSize, currentPageNo);
        sql.append(" limit " + (pager.getCurrentPage() - 1) * pager.getRowPerPage() + "," + pager.getRowPerPage());
        return this.executeQuery(sql.toString(), rm);
    }

    @Override
    public BooksEntity queryOne(int id) throws SQLException, ClassNotFoundException {
        StringBuffer sql = new StringBuffer("SELECT books.bid,books.b_price,books.image,books.bookname from books where 1=1 and bid=?");
        return this.executeQueryOne(sql.toString(), rm, id);
    }

    @Override
    public int update(BooksEntity booksEntity) {
        return 0;
    }

    @Override
    public Integer count(String filename) throws SQLException, ClassNotFoundException {
        List<Object> paramsList = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select count(1) as " + filename + " from books where 1=1 ");
        Integer count = 0;
        this.createStatement(sql.toString());
        ResultSet rs = this.pstm.executeQuery();
        try {
            if (rs.next()) {
                count = rs.getInt(filename);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BDManager.colse(this.connection, this.rs, this.pstm);
        return count;
    }

    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        return null;
    }
}
