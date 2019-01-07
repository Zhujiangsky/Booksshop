<%--
  Created by IntelliJ IDEA.
  User: zhujiang
  Date: 2019/1/5
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    var request = new XMLHttpRequest();

    function dd() {
        request.open("post", "MyServlet", true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.send("op=login&userName=" + $("#userName").val());
    }

    request.onreadystatechange = function (ev) {
        if (request.readyState == 4 && request.status == 200) {
            $("#eeee").html(request.responseText);
        }
    };
    var request1 = new XMLHttpRequest();

    function ddd() {
        request1.open("post", "MyServlet", true);
        request1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request1.send("op=pwd&passWord=" + $("#passWord").val());
    }

    request1.onreadystatechange = function (ev) {
        if (request1.readyState == 4 && request1.status == 200) {
            $("#eeeee").html(request1.responseText);
        }
    }
</script>
<body>
<div id="header" class="wrap">
    <div id="logo">熊大网上书城</div>
    <div id="navbar">
        <form method="get" name="search" action="">
            搜索：<input class="input-text" type="text" name="keywords"/><input class="input-btn" type="submit"
                                                                             name="submit" value=""/>
        </form>
    </div>
</div>
<div id="login">
    <h2>用户登陆</h2>
    <form method="post" action="LoginServlet?op=login&action=userAction">
        <dl>
            <dt>用户名：</dt>
            <dd id="ddd"><input class="input-text" type="text" name="userName" id="userName" onblur="dd()"/><span
                    id="eeee">当前用户不存在！！！</span></dd>
            <dt>密　码：</dt>
            <dd><input class="input-text" type="password" id="passWord" onblur="ddd()" name="passWord"/><span
                    id="eeeee">密码过于简单！！！</span>
            </dd>
            <dt></dt>
            <dd class="button"><input class="input-btn" type="submit" name="submit" value=""/><input class="input-reg"
                                                                                                     type="button"
                                                                                                     name="register"
                                                                                                     value=""
                                                                                                     onclick="window.location='register.jsp';"/>
            </dd>
        </dl>
    </form>
</div>
<div id="footer" class="wrap">
    熊大网上书城 &copy; 版权所有
</div>
</body>
</html>
