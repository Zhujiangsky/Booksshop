<%--
  Created by IntelliJ IDEA.
  User: zhujiang
  Date: 2019/1/5
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div id="header" class="wrap">
    <div id="logo">熊大网上书城</div>
    <div id="navbar">
        <div class="userMenu">
            <ul>
                <li><a href="index.jsp">User首页</a></li>
                <li><a href="orderlist.jsp">我的订单</a></li>
                <li class="current"><a href="shopping.jsp">购物车</a></li>
                <li><a href="login.jsp">注销</a></li>
            </ul>
        </div>
        <form method="get" name="search" action="">
            搜索：<input class="input-text" type="text" name="keywords"/><input class="input-btn" type="submit"
                                                                             name="submit" value=""/>
        </form>
    </div>
</div>
<div id="content" class="wrap">
    <div class="list bookList">
        <form method="post" name="shoping" action="shopping-success.jsp">
            <table>
                <tr class="title">
                    <th class="view">图片预览</th>
                    <th>书名</th>
                    <th class="nums">数量</th>
                    <th class="price">价格</th>
                </tr>
                <tr>
                    <td class="thumb"><img src="images/book/book_03.gif"/></td>
                    <td class="title">天堂之旅</td>
                    <td><input class="input-text" type="text" name="nums" value="1"/></td>
                    <td>￥<span>25.00</span></td>
                </tr>
            </table>
            <div class="button">
                <h4>总价：￥<span>65.00</span>元</h4>
                <input class="input-chart" type="submit" name="submit" value=""/>
            </div>
        </form>
    </div>
</div>
<div id="footer" class="wrap">
    熊大网上书城 &copy; 版权所有
</div>
</body>
</html>
