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

@WebServlet(name = "ShowServlet", urlPatterns = "/ShowServlet", initParams = {
        @WebInitParam(name = "actionconfig", value = "action.properties")})
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Properties actionParams;
    private static InputStream is;
    private static Map<String, ActionInterface> actionMap;

    public ShowServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("ddddd");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String actionName = request.getParameter("action");
        if (actionName == null) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            ActionInterface action = actionMap.get(actionName);
            if (action == null) {
                throw new ServletException("请求的action不存在！");
            }
            String viewPath = null;
            try {
                viewPath = action.execute("", request, response);
                System.out.println(viewPath);
                response.getWriter().print(viewPath);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
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
