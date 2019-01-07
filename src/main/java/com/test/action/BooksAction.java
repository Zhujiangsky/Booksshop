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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksAction implements ActionInterface {
    @Override
    public String execute(String type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Pager p = this.showBooks(request, response);
        return JSON.toJSONString(p);
    }


    private Pager getUsersPage(BooksVoDao booksDao, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String pageIndex = request.getParameter("pageIndex");
        String s = (String) request.getSession().getAttribute("uname");
        //获得当前页
        if (pageIndex == null || (pageIndex = pageIndex.trim()).length() == 0) {
            pageIndex = "1";
        }
        int count = booksDao.count("", s);
        int currPageNo = Integer.parseInt(pageIndex);
        if (currPageNo > count) {
            currPageNo = count;
        }
        Pager pageObj = new Pager();
        pageObj.setCurrentPage(currPageNo);//设置当前页码
        pageObj.setRowPerPage(5);//设置每页显示条数
        pageObj.setRowCount(count);
        return pageObj;
    }


    private Pager showBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        BooksVoDao booksDao = new BooksVoDao();
        Pager pager = this.getUsersPage(booksDao, request);
        List<BooksVo> booksEntityList = booksDao.queryAll((String) request.getSession().getAttribute("uname"),
                pager.getCurrentPage(), pager.getRowPerPage());
        pager.setDataList(booksEntityList);
        return pager;
    }
}
