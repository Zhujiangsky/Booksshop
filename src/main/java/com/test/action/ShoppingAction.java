package com.test.action;

import com.alibaba.fastjson.JSON;
import com.test.dao.entdao.BooksDao;
import com.test.dao.entdao.BooksVoDao;
import com.test.entity.BooksEntity;
import com.test.entity.BooksVo;
import com.test.util.Pager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingAction implements ActionInterface {
    @Override
    public String execute(String type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        if (type.equals("zf")) {
            return "shopping.jsp";
        } else {
            Pager p = this.showBooks(request, response);
            return JSON.toJSONString(p);
        }

    }

    private Pager getUsersPage(BooksDao booksDao, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        Pager pageObj = new Pager();
        pageObj.setCurrentPage(1);//设置当前页码
        pageObj.setRowPerPage(5);//设置每页显示条数
        pageObj.setRowCount(1);
        return pageObj;
    }

    private Pager showBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        BooksDao booksDao = new BooksDao();
        Pager pager = this.getUsersPage(booksDao, request);
        List<BooksEntity> list = new ArrayList<BooksEntity>();
        Map<String, String> map = (Map<String, String>) request.getSession().getAttribute("bookss");
        for (String str : map.keySet()) {
            BooksEntity booksEntity = booksDao.queryOne(Integer.parseInt(str));
            list.add(booksEntity);
        }
        pager.setDataList(list);
        return pager;
    }
}
