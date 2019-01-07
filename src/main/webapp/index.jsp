<%--
  Created by IntelliJ IDEA.
  User: zhujiang
  Date: 2019/1/5
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    var check_val = [];
    $(document).ready(function () {
        var page = "";
        var requset = new XMLHttpRequest();
        $.ajax({
            "url": "BooksServlet",                      // 要提交的URL路径
            "type": "post",                     // 发送请求的方式
            "data": "op=show&pageIndex=1",                      // 要发送到服务器的数据
            "dataType": "json",                   // 指定传输的数据格式
            "success": function (result) {// 请求成功后要执行的代码
                alert("ss");
                $("#table1").append("<tr class=\"title\">\n" +
                    "                    <th class=\"checker\"></th>\n" +
                    "                    <th>书名</th>\n" +
                    "                    <th class=\"price\">价格</th>\n" +
                    "                    <th class=\"store\">库存</th>\n" +
                    "                    <th class=\"view\">图片预览</th>\n" +
                    "                </tr>");
                var i = 0;
                $.each(result.dataList, function () {
                    if (i % 2 != 0) {
                        $("#table1").append("<tr class=\"odd\">\n" +
                            "                    <td><input type=\"checkbox\" name=\"bookId\" value=\"" + this.bid + "\"/><input type='hidden' value='" + this.bid + "'  /></td>\n" +
                            "                    <td class=\"title\">" + this.bookname + "</td>\n" +
                            "                    <td>￥" + this.b_price + "</td>\n" +
                            "                    <td>" + this.stock + "</td>\n" +
                            "                    <td class=\"thumb\"><img src=\"" + this.image + "\"/></td>\n" +
                            "                </tr>");

                    } else {
                        $("#table1").append("<tr>\n" +
                            "                    <td><input type=\"checkbox\" name=\"bookId\" value=\"" + this.bid + "\"/></td>\n" +
                            "                    <td class=\"title\">" + this.bookname + "</td>\n" +
                            "                    <td>￥" + this.b_price + "</td>\n" +
                            "                    <td>" + this.stock + "</td>\n" +
                            "                    <td class=\"thumb\"><img src=\"" + this.image + "\"/></td>\n" +
                            "                </tr>");
                    }
                    i++;
                    page = "<div class=\"page-spliter\" id=\"fy\"><a onclick=\"onn(1)\" href=\"javascript:void(0)\">首页</a>&nbsp;" + "<a onclick=\"onn(" + (result.currentPage - 1 == 0 ? "1" : (result.currentPage - 1)) + ")\" href=\"javascript:void(0)\">上一页</a>&nbsp;<a onclick=\"onn(" + (result.currentPage == result.pageCount ? (result.pageCount) : (result.currentPage + 1)) + ")\" href=\"javascript:void(0)\">下一页</a>&nbsp;<a onclick=\"onn(" + result.pageCount + ")\" href=\"javascript:void(0)\"\">尾页</a>&nbsp;<p>共" + result.pageCount + "页，当前为第" + result.currentPage + "<input type='hidden' name=\"dqy\"  value=\"" + result.currentPage + "\"/>页</p></div>";
                });
                $("#table1").after(page);
            }
        });
    });
    var request = new XMLHttpRequest();

    function onn(e) {
        var s = "";
        var o = document.getElementsByName("bookId");
        for (var k in o) {
            if (o[k].checked) {
                check_val.push(o[k].value);
                s += o[k].value + ",";
            }
        }
        request.open("post", "BooksServlet", true);
        request.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded");
        request.send("op=show&pageIndex=" + e + "&books=" + s);
    }

    request.onreadystatechange = function (ev) {
        $("#table1").find("*").remove();
        $("#fy").remove();
        var s = "";
        var page = "";
        $("#table1").append("<tr class=\"title\">\n" +
            "                    <th class=\"checker\"></th>\n" +
            "                    <th>书名</th>\n" +
            "                    <th class=\"price\">价格</th>\n" +
            "                    <th class=\"store\">库存</th>\n" +
            "                    <th class=\"view\">图片预览</th>\n" +
            "                </tr>");
        if (request.readyState == 4 && request.status == 200) {
            var ss = request.responseText;
            var json = eval("(" + ss + ")");
            var i = 0;
            $(json.dataList).each(function () {
                if (i % 2 != 0) {
                    $("#table1").append("<tr class=\"odd\">\n" +
                        "                    <td><input type=\"checkbox\" name=\"bookId\" value=\"" + this.bid + "\"/></td>\n" +
                        "                    <td class=\"title\">" + this.bookname + "</td>\n" +
                        "                    <td>￥" + this.b_price + "</td>\n" +
                        "                    <td>" + this.stock + "</td>\n" +
                        "                    <td class=\"thumb\"><img src=\"" + this.image + "\"/></td>\n" +
                        "                </tr>");

                } else {
                    $("#table1").append("<tr>\n" +
                        "                    <td><input type=\"checkbox\" name=\"bookId\" value=\"" + this.bid + "\"/></td>\n" +
                        "                    <td class=\"title\">" + this.bookname + "</td>\n" +
                        "                    <td>￥" + this.b_price + "</td>\n" +
                        "                    <td>" + this.stock + "</td>\n" +
                        "                    <td class=\"thumb\"><img src=\"" + this.image + "\"/></td>\n" +
                        "                </tr>");
                }
                i++;
                page = "<div class=\"page-spliter\" id=\"fy\"><a onclick=\"onn(1)\" href=\"javascript:void(0)\">首页</a>&nbsp;" + "<a onclick=\"onn(" + (json.currentPage - 1 == 0 ? "1" : (json.currentPage - 1)) + ")\" href=\"javascript:void(0)\">上一页</a>&nbsp;<a onclick=\"onn(" + (json.currentPage == json.pageCount ? (json.pageCount) : (json.currentPage + 1)) + ")\" href=\"javascript:void(0)\">下一页</a>&nbsp;<a onclick=\"onn(" + json.pageCount + ")\" href=\"javascript:void(0)\"\">尾页</a>&nbsp;<p>共" + json.pageCount + "页，当前为第" + json.currentPage + "<input type='hidden' name=\"dqy\"  value=\"" + json.currentPage + "\"/>页</p></div>";
            });
            $("#table1").after(page);
        }
    }
</script>
<body>
<div id="header" class="wrap">
    <div id="logo">熊大网上书城</div>
    <div id="navbar">
        <div class="userMenu">
            <ul>
                <li class="current"><a href="index.jsp">User首页</a></li>
                <li><a href="orderlist.jsp">我的订单</a></li>
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
    <div class="list bookList">
        <form method="post" name="shoping" action="BooksServlet?op=aaa">
            <table id="table1">
            </table>
            <div class="page-spliter" id="fy">
            </div>
            <div class="button"><input class="input-btn" type="submit" name="submit" value=""/></div>
        </form>
    </div>
</div>
<div id="footer" class="wrap">
    熊大网上书城 &copy; 版权所有
</div>
</body>
</html>
