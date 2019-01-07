<%--
  Created by IntelliJ IDEA.
  User: zhujiang
  Date: 2019/1/5
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var requset = new XMLHttpRequest();
        $.ajax({
            "url": "ShowServlet",                      // 要提交的URL路径
            "type": "post",                     // 发送请求的方式
            "data": "op=show&pageIndex=1&action=booksAction",                      // 要发送到服务器的数据
            "dataType": "json",                   // 指定传输的数据格式
            "success": function (result) {// 请求成功后要执行的代码
                var page = "";
                $("#table1").append(" <tr class=\"title\"> <th class=\"orderId\">订单编号</th> <th>订单商品</th> <th class=\"userName\">收货人</th> <th class=\"price\">订单金额</th> <th class=\"createTime\">下单时间</th><th class=\"status\">订单状态</th> </tr>");
                $.each(result.dataList, function () {
                    $("#table1").append("  <tr>\n" +
                        "                <td>" + this.oid + "</td>\n" +
                        "                <td class=\"thumb\"><img src=\"" + this.image + "\"/></td>\n" +
                        "                <td>" + this.username + "</td>\n" +
                        "                <td>￥" + this.b_price + "</td>\n" +
                        "                <td>2012-12-21 12:00:00</td>\n" +
                        "                <td>" + (this.state == 0 ? "已完成" : "未完成") + "</td>\n" +
                        "            </tr>");

                    page = "<div id='div1'><a onclick=\"onn(1)\" href=\"javascript:void(0)\">首页</a>&nbsp;" + "<a onclick=\"onn(" + (result.currentPage - 1 == 0 ? "1" : (result.currentPage - 1)) + ")\" href=\"javascript:void(0)\">上一页</a>&nbsp;<a onclick=\"onn(" + (result.currentPage == result.pageCount ? (result.pageCount) : (result.currentPage + 1)) + ")\" href=\"javascript:void(0)\">下一页</a>&nbsp;<a onclick=\"onn(" + result.pageCount + ")\" href=\"javascript:void(0)\"\">尾页</a>&nbsp;<p>共" + result.pageCount + "页，当前为第" + result.currentPage + "页</p></div>";
                });
                $("#table2").append(page);
            }
        });
    });

    function onn(e) {
        var requset = new XMLHttpRequest();
        $("#table1").find("*").remove();
        $("#table2").find("*").remove();
        $.ajax({
            "url": "ShowServlet",                      // 要提交的URL路径
            "type": "post",                     // 发送请求的方式
            "data": "op=show&pageIndex=" + e + "&action=booksAction",                      // 要发送到服务器的数据
            "dataType": "json",                   // 指定传输的数据格式
            "success": function (result) {// 请求成功后要执行的代码
                var page = "";
                $("#table1").append(" <tr class=\"title\"> <th class=\"orderId\">订单编号</th> <th>订单商品</th> <th class=\"userName\">收货人</th> <th class=\"price\">订单金额</th> <th class=\"createTime\">下单时间</th><th class=\"status\">订单状态</th> </tr>");
                $.each(result.dataList, function () {
                    $("#table1").append("  <tr>\n" +
                        "                <td>" + this.oid + "</td>\n" +
                        "                <td class=\"thumb\"><img src=\"" + this.image + "\"/></td>\n" +
                        "                <td>" + this.username + "</td>\n" +
                        "                <td>￥" + this.b_price + "</td>\n" +
                        "                <td>2012-12-21 12:00:00</td>\n" +
                        "                <td>" + (this.state == 0 ? "已完成" : "未完成") + "</td>\n" +
                        "            </tr>");

                    page = "<div id='div1'><a onclick=\"onn(1)\" href=\"javascript:void(0)\">首页</a>&nbsp;" + "<a onclick=\"onn(" + (result.currentPage - 1 == 0 ? "1" : (result.currentPage - 1)) + ")\" href=\"javascript:void(0)\">上一页</a>&nbsp;<a onclick=\"onn(" + (result.currentPage == result.pageCount ? (result.pageCount) : (result.currentPage + 1)) + ")\" href=\"javascript:void(0)\">下一页</a>&nbsp;<a onclick=\"onn(" + result.pageCount + ")\" href=\"javascript:void(0)\"\">尾页</a>&nbsp;<p>共" + result.pageCount + "页，当前为第" + result.currentPage + "页</p></div>";
                });
                $("#table2").append(page);
            }
        });
    }


</script>
<body>
<div id="header" class="wrap">
    <div id="logo">熊大网上书城</div>
    <div id="navbar">
        <div class="userMenu">
            <ul>
                <li><a href="index.jsp">User首页</a></li>
                <li class="current"><a href="orderlist.jsp">我的订单</a></li>
                <li><a href="shopping.jsp">购物车</a></li>
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
    <div class="list orderList">
        <table id="table1">

        </table>
        <div class="page-spliter" id="table2">

        </div>
        <div class="button"><input class="input-gray" type="submit" name="submit" value="查看一个月前的订单"/><input
                class="input-gray" type="submit" name="submit" value="查看一个月前的订单"/></div>
    </div>
</div>
<div id="footer" class="wrap">
    熊大网上书城 &copy; 版权所有
</div>
</body>
</html>
