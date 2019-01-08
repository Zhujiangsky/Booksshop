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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BooksServlet", urlPatterns = {"/BooksServlet"})
public class BooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Map<String, String> books = new HashMap<String, String>();
        if (request.getParameter("op").equals("show")) {
            try {
                Pager p = this.showBooks(request, response);
                if (request.getSession().getAttribute("bookss") != null) {
                    Map<String, String> boo = (Map<String, String>) request.getSession().getAttribute("bookss");
                    System.out.println("通过Map.keySet遍历key和value：");
                    System.out.println("修改好了吗");
                    boo.put(request.getParameter("pageIndex"), request.getParameter("books"));
                    for (String key : boo.keySet()) {
                        System.out.println("key= " + key + " and value= " + boo.get(key));
                    }

                } else {
                    if (request.getParameter("books") != null) {
                        books.put(request.getParameter("pageIndex"), request.getParameter("books"));
                        request.getSession().setAttribute("bookss", books);
                    } else {
                        request.getSession().setAttribute("bookss", books);
                    }
                }
                String s = JSON.toJSONString(p);
                response.getWriter().print(s);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (request.getParameter("op").equals("aaa")) {
            String str[] = request.getParameterValues("bookId");
            String s = request.getParameter("dqy");
            System.out.println(s);
            if (str.length > 0) {
                for (int i = 0; i < str.length; i++) {
                    System.out.println(str[i].toString());
                }
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
