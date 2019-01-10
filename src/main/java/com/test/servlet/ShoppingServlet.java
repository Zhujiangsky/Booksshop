package com.test.servlet;

import com.test.action.ActionInterface;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@WebServlet(name = "ShoppingServlet", urlPatterns = "/ShoppingServlet", initParams = {
        @WebInitParam(name = "actionconfig", value = "action.properties")})
public class ShoppingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Properties actionParams;
    private static InputStream is;
    private static Map<String, ActionInterface> actionMap;

    public ShoppingServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String str[] = request.getParameterValues("bookId");
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
                bb.put(strrr, strrr);
            }
        }
        PrintWriter out = response.getWriter();
        String actionName = request.getParameter("action");
        String op = request.getParameter("op");
        if (op.equals("zf")) {
            if (actionName == null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                ActionInterface action = actionMap.get(actionName);
                if (action == null) {
                    throw new ServletException("请求的action不存在！");
                }
                String viewPath = null;
                try {
                    viewPath = action.execute("zf", request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(viewPath).forward(request, response);
            }
        } else if (op.equals("xianshi")) {
            if (actionName == null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                ActionInterface action = actionMap.get(actionName);
                if (action == null) {
                    throw new ServletException("请求的action不存在！");
                }
                String viewPath = null;
                try {
                    viewPath = action.execute("show", request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                response.getWriter().print(viewPath);
            }
        }

        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String properFile = this.getInitParameter("actionconfig");
        is = LoginServlet.class.getClassLoader().getResourceAsStream(properFile);
        actionParams = new Properties();
        try {
            actionParams.load(is);
            initActionMap(actionParams);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("配置文件读取错误！");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ServletException("实例化Action对象出错！");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ServletException("安全权限异常错误！");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ServletException("加载class文件错误！");
        }
    }

    private void initActionMap(Properties actionParams) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        actionMap = new HashMap<String, ActionInterface>();
        Set<Object> keys = actionParams.keySet();//返回属性key的集合
        for (Object key : keys) {
            String className = actionParams.getProperty(key.toString());
            ActionInterface action = (ActionInterface) Class.forName(className.toString()).newInstance();
            actionMap.put(key.toString(), action);
        }
    }
}
