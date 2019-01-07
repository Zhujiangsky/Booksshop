package com.test.servlet;

import com.test.dao.entdao.UserinfoDao;
import com.test.entity.UserinfoEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        if ("login".equals(request.getParameter("op"))) {
            try {
                response.getWriter().print(this.yz(request, response));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if ("pwd".equals(request.getParameter("op"))) {
            try {
                response.getWriter().print(this.pwdyz(request, response));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if ("zc".equals(request.getParameter("op"))) {
            try {
                response.getWriter().print(this.zc(request, response));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if ("pwdyz2".equals(request.getParameter("op"))) {
            try {
                response.getWriter().print(this.pwdyz2(request, response));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public String yz(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        String sss = request.getParameter("userName");
        UserinfoDao userinfoDao = new UserinfoDao();
        if (userinfoDao.queryOne(sss) != null) {
            return " ";
        } else {
            return "当前用户不存在！！！";
        }
    }

    public String zc(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        String sss = request.getParameter("userName");
        UserinfoDao userinfoDao = new UserinfoDao();
        if (userinfoDao.queryOne(sss) != null) {
            return "当前用户名已存在！！！";
        } else {
            return " ";
        }
    }

    public String pwdyz(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        String sss = request.getParameter("passWord");
        if (sss.length() > 6) {
            return " ";
        } else {
            return "密码过于简单！！！";
        }
    }

    public String pwdyz2(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        String sss = request.getParameter("rePassWord");
        String ssss = request.getParameter("passWord");
        if (sss.equals(ssss)) {
            return " ";
        } else {
            return "两次密码输入不一致！";
        }
    }
}
