package com.test.action;

import com.test.dao.entdao.UserinfoDao;
import com.test.entity.UserinfoEntity;
import com.test.util.InfoStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserinfoAction implements ActionInterface {
    @Override
    public String execute(String type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        UserinfoDao udao = new UserinfoDao();
        if (type.equals("login")) {
            String pwd = request.getParameter("passWord");
            String uname = request.getParameter("userName");
            if (udao.queryOne(uname, pwd) != null) {
                request.getSession().setAttribute("uname", uname);
                return "index.jsp";
            } else {
                return "login.jsp";
            }
        } else if (type.equals("zc")) {
            String pwd = request.getParameter("passWord");
            String uname = request.getParameter("userName");
            String email = request.getParameter("email");
            UserinfoEntity userinfoEntity = new UserinfoEntity();
            userinfoEntity.setUsername(uname);
            userinfoEntity.setEmail(email);
            userinfoEntity.setPassword(pwd);
            int i = udao.add(userinfoEntity);
            if (i > 0) {
                return "register_success.jsp";
            } else {
                return "register.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
