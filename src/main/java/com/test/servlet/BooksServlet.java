package com.test.servlet;

import com.alibaba.fastjson.JSON;
import com.test.dao.entdao.BooksDao;
import com.test.entity.BooksEntity;
import com.test.util.Pager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BooksServlet", urlPatterns = {"/BooksServlet"})
public class BooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String ss = "";
        if (request.getParameter("op").equals("show")) {
            try {
                Pager p = this.showBooks(request, response);
                if (request.getSession().getAttribute("bookss") != null) {
                    ss = (String) request.getSession().getAttribute("bookss");
                    ss += request.getParameter("books");
                    request.getSession().setAttribute("bookss", ss);

                } else {
                    ss = request.getParameter("books");
                    request.getSession().setAttribute("bookss", ss);
                }
                String s = JSON.toJSONString(p);
                System.out.println(s);
                response.getWriter().print(s);
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


    private Pager getUsersPage(BooksDao booksDao, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String pageIndex = request.getParameter("pageIndex");
        //获得当前页
        if (pageIndex == null || (pageIndex = pageIndex.trim()).length() == 0) {
            pageIndex = "1";
        }
        int count = booksDao.count("id");
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
        BooksDao booksDao = new BooksDao();
        Pager pager = this.getUsersPage(booksDao, request);
        List<BooksEntity> booksEntityList = booksDao.queryAll(pager.getCurrentPage(), pager.getRowPerPage());
        pager.setDataList(booksEntityList);
        return pager;
    }
}
