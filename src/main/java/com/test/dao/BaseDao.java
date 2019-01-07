package com.test.dao;

import com.test.util.RowMapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDao {
    protected Connection connection;
    protected PreparedStatement pstm;
    protected ResultSet rs;
    static Logger logger = Logger.getLogger(BaseDao.class);

    public void createStatement(String sql, Object... objects) throws ClassNotFoundException, SQLException {
        connection = BDManager.openConnection();
        pstm = connection.prepareStatement(sql);
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                pstm.setObject((i + 1), objects[i]);
            }
        }
    }

    public ResultSet executeQuery(String sql, Object[] params) {
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            pstm = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstm.setObject(i + 1, params[i]);
            }
            rs = pstm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    private Connection getConnection() {
        try {
            connection = BDManager.openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int executeUpdate(String sql, Object... objects) throws SQLException, ClassNotFoundException {
        this.createStatement(sql, objects);
        int row = pstm.executeUpdate();
        BDManager.colse(connection, rs, pstm);
        return row;
    }

    public <T> List<T> executeQuery(String sql, RowMapper<T> rm, Object... objects)
            throws ClassNotFoundException, SQLException {
        this.createStatement(sql, objects);
        rs = pstm.executeQuery();
        List<T> list = new ArrayList<T>();
        ResultSetMetaData x = rs.getMetaData();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < x.getColumnCount(); i++) {
                String y = x.getColumnName(i + 1);
                map.put(y, rs.getObject(y));
            }
            T t = rm.mapper(map);
            list.add(t);
        }
        BDManager.colse(connection, rs, pstm);
        return list;
    }

    public <T> T executeQueryOne(String sql, RowMapper<T> rm, Object... objects)
            throws ClassNotFoundException, SQLException {
        // 调用创建执行对象的方法
        this.createStatement(sql, objects);
        // 执行sql语句，获得结果集
        rs = pstm.executeQuery();
        T t = null;
        // 获取表头
        ResultSetMetaData x = rs.getMetaData();
        // 读取数据，进行封装
        if (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < x.getColumnCount(); i++) {
                String y = x.getColumnName(i + 1);// 取列名
                Object z = rs.getObject(y);// 根据列名，取值
                map.put(y, z);// 存放map集合
            }
            // 调用映射器的方法，封装为一个对象
            t = rm.mapper(map);
        }
        // 关闭连接
        BDManager.colse(connection, rs, pstm);
        return t;
    }

    public int executeUpdate1(String sql, Object[] params) {
        int updateRows = 0;
        try {
            connection = this.getConnection();
            pstm = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstm.setObject(i + 1, params[i]);
            }
            updateRows = pstm.executeUpdate();
            this.closeResource(null);
        } catch (Exception e) {
            e.printStackTrace();
            updateRows = -1;
        }

        return updateRows;
    }

    public int executeInsert(String sql, Object[] params) {
        Long id = 0L;
        try {
            connection = this.getConnection();
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                pstm.setObject(i + 1, params[i]);
            }
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
                System.out.println("数据主键：" + id);
            }
            this.closeResource(rs);
        } catch (Exception e) {
            e.printStackTrace();
            id = 0L;
        }

        return id.intValue();
    }

    //释放资源
    public void closeResource(ResultSet reSet) throws SQLException {
        BDManager.colse(connection, rs, pstm);
    }

    /**
     * 需要重写的方法
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public abstract Object tableToClass(ResultSet rs) throws Exception;
}
