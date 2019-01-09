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
        String booid = request.getParameter("books");
        if (request.getParameter("op").equals("show")) {
            try {
                if (request.getSession().getAttribute("bookss") != null) {
                    Map<String, String> boo = (Map<String, String>) request.getSession().getAttribute("bookss");
                    if (booid.length() > 0) {
                        String booidd = booid.substring(0, booid.length() - 1);
                        String str[] = booidd.split(",");
                        for (String strr : str) {
                            boo.put(strr, strr);
                        }
                    }
                } else {
                    if (booid != null) {
                        String booidd = booid.substring(0, booid.length() - 1);
                        String str[] = booidd.split(",");
                        for (String strr : str) {
                            books.put(strr, strr);
                        }
                        request.getSession().setAttribute("bookss", books);
                    } else {
                        System.out.println("4");
                        request.getSession().setAttribute("bookss", books);
                    }
                }
                Pager p = this.showBooks(request, response);
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
            String s11 = "";
            if (request.getSession().getAttribute("bookss") == null) {
                Map<String, String> map = new HashMap<String, String>();
                request.getSession().setAttribute("bookss", map);
            }
            Map<String, String> bb = (Map<String, String>) request.getSession().getAttribute("bookss");
            if (str != null) {
                for (String s1 : str) {
                    s11 += s1 + ",";
                }
                String booidd = s11.substring(0, s11.length() - 1);
                String strr[] = booidd.split(",");
                for (String strrr : str) {
                    books.put(strrr, strrr);
                }

            } else if (bb.keySet().iterator().hasNext()) {

            } else {
                response.sendRedirect("index.jsp");
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
