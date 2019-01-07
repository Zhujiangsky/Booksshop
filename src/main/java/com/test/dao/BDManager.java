package com.test.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDManager {
    private static DataSource dataSource;
    private static Context ctx;

    static {
        init();
    }

    private static void init() {

        try {
            ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/bookshop");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public static Connection openConnection() throws SQLException {
        return dataSource.getConnection();
    }


    public static void colse(Connection con, ResultSet rs, PreparedStatement ps) throws SQLException {
        if (con != null) {
            con.close();
        }
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
    }
}
