<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            "url": "ShoppingServlet",                      // 要提交的URL路径
            "type": "post",                     // 发送请求的方式
            "data": "action=shoppingAction&op=xianshi",                      // 要发送到服务器的数据
            "dataType": "json",                   // 指定传输的数据格式
            "success": function (result) {// 请求成功后要执行的代码
                $("#table3").append("<tr class=\"title\">\n" +
                    "                    <th class=\"view\">图片预览</th>\n" +
                    "                    <th>书名</th>\n" +
                    "                    <th class=\"nums\">数量</th>\n" +
                    "                    <th class=\"price\">价格</th>\n" +
                    "                    <th class=\"price\">操作</th>\n" +
                    "                </tr>");
                var sum = 0;
                $.each(result.dataList, function () {
                    $("#table3").append("<tr>\n" +
                        "                    <td class=\"thumb\"><img src=\"" + this.image + "\" / > </td>\n" +
                        "                    <td class=\"title\">" + this.bookname + "</td>\n" +
                        "                    <td><input class=\"input-text\" type=\"text\" name=\"nums\" onblur='update(" + this.bid + ")' id=\"" + this.bid + "\" value=\"1\"/></td>\n" +
                        "                    <td>￥<input type='hidden' value='" + this.b_price + "' name='price' ><span >" + this.b_price + "</span></td>\n" +
                        "                    <td><input type='button' value='删除' onclick='deletee(" + this.bid + ")' ></td>\n" +
                        "                </tr>"
                    );
                    sum += parseFloat(this.b_price);
                });
                $("#span1").html(sum);
            }
        });
    });

    function deletee(e) {
        $.ajax({
            "url": "ShoppingServlet",                      // 要提交的URL路径
            "type": "post",                     // 发送请求的方式
            "data": "action=shoppingAction&op=delete&delete=" + e,                      // 要发送到服务器的数据
            "dataType": "json",                   // 指定传输的数据格式
            "success": function (result) {// 请求成功后要执行的代码
                $("#div1").children().children("table").remove();
                $("#div1").children().children("div").remove();
                $("#div1").children().append(" <table id=\"table3\">\n" +
                    "            </table>");
                $("#table3").append("<tr class=\"title\">\n" +
                    "                    <th class=\"view\">图片预览</th>\n" +
                    "                    <th>书名</th>\n" +
                    "                    <th class=\"nums\">数量</th>\n" +
                    "                    <th class=\"price\">价格</th>\n" +
                    "                    <th class=\"price\">操作</th>\n" +
                    "                </tr>");
                var sum = 0;
                $.each(result.dataList, function () {
                    $("#table3").append("<tr>\n" +
                        "                    <td class=\"thumb\"><img src=\"" + this.image + "\" / > </td>\n" +
                        "                    <td class=\"title\">" + this.bookname + "</td>\n" +
                        "                    <td><input class=\"input-text\" type=\"text\" name=\"nums\" onblur='update(" + this.bid + ")' id=\"" + this.bid + "\" value=\"1\"/></td>\n" +
                        "                    <td>￥<input type='hidden' value='" + this.b_price + "' name='price' ><span >" + this.b_price + "</span></td>\n" +
                        "                    <td><input type='button' value='删除' onclick='deletee(" + this.bid + ")' ></td>\n" +
                        "                </tr>"
                    );
                    sum += parseFloat(this.b_price);
                });
                $("#div1").children().append(" <div class=\"button\">\n" +
                    "                <h4>总价：￥<span id=\"span1\"></span>元</h4>\n" +
                    "                <input class=\"input-chart\" type=\"submit\" name=\"submit\" value=\"\"/>\n" +
                    "            </div>");
                $("#span1").html(sum);
            }
        });
    }

    function update(e) {
        var sum = 0;
        var sss = $("#" + e).val();
        var ssss = $("#" + e).parent().next().children("input").val();
        $("#" + e).parent().next().children("span").html((parseFloat(ssss) * parseInt(sss)));
        var list = document.getElementsByName("nums");
        var list1 = document.getElementsByName("price");
        for (var i = 0; i < list.length; i++) {
            sum += (parseInt(list[i].value) * parseFloat(list1[i].value));
        }
        $("#span1").html(sum);
    }

</script>
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
    <div class="list bookList" id="div1">
        <form method="post" name="shoping" action="ShoppingServlet?action=shoppingAction&op=gm">
            <table id="table3">
            </table>
            <div class="button">
                <h4>总价：￥<span id="span1"></span>元</h4>
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
