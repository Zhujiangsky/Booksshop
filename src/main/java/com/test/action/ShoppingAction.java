package com.test.action;

import com.alibaba.fastjson.JSON;
import com.test.dao.entdao.BooksDao;
import com.test.dao.entdao.BooksVoDao;
import com.test.dao.entdao.ItemsDao;
import com.test.dao.entdao.OrdersDao;
import com.test.entity.BooksEntity;
import com.test.entity.BooksVo;
import com.test.entity.ItemsEntity;
import com.test.entity.OrdersEntity;
import com.test.util.Pager;
import org.openjdk.tools.javac.jvm.Items;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShoppingAction implements ActionInterface {
    @Override
    public String execute(String type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        if (type.equals("zf")) {
            Map<String, String> map = (Map<String, String>) request.getSession().getAttribute("bookss");
            if (map.size() > 0) {
                return "shopping.jsp";
            } else {
                return "index.jsp";
            }
        } else if (type.equals("show")) {
            Pager p = this.showBooks(request, response);
            return JSON.toJSONString(p);
        } else if (type.equals("zff")) {
            Map<String, String> map = (Map<String, String>) request.getSession().getAttribute("bookss");
            String stt = request.getParameter("delete");
            Iterator<String> item = map.keySet().iterator();
            while (item.hasNext()) {
                String key = item.next();
                if (stt.equals(key)) {
                    item.remove();
                }
            }
            Pager p = this.showBooks(request, response);
            return JSON.toJSONString(p);
        } else {
            String str[] = request.getParameterValues("nums");
            if (str == null) {
                return "index.jsp";
            } else {
                String strprice[] = request.getParameterValues("price");
                OrdersDao od = new OrdersDao();
                OrdersEntity oe = new OrdersEntity();
                Map<String, String> map = (Map<String, String>) request.getSession().getAttribute("bookss");
                oe.setUsername((String) request.getSession().getAttribute("uname"));
                od.add(oe);
                Integer t = od.queryMaxId();
                System.out.println(t);
                int i = 0;
                double d = 0;
                List<ItemsEntity> list = new ArrayList<ItemsEntity>();
                for (String str1 : map.keySet()) {
                    ItemsEntity it = new ItemsEntity();
                    it.setOid(t);
                    double s = Integer.parseInt(str[i]) * Double.parseDouble(strprice[i]);
                    it.setPrice(s + "");
                    it.setCount(Integer.parseInt(str[i]));
                    it.setBid(Integer.parseInt(str1));
                    d += s;
                    i++;
                    list.add(it);
                }
                ItemsDao itd = new ItemsDao();
                for (int k = 0; k < list.size(); k++) {
                    ItemsEntity it = list.get(k);
                    it.setTotal_price(d + "");
                    itd.add(it);
                }
                request.getSession().removeAttribute("bookss");
                return "shopping-success.jsp";
            }
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
