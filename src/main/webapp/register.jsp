<%--
  Created by IntelliJ IDEA.
  User: zhujiang
  Date: 2019/1/5
  Time: 8:30
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
    var request = new XMLHttpRequest();

    function zcd() {
        request.open("post", "MyServlet", true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.send("op=zc&userName=" + $("#userName").val());
    }

    request.onreadystatechange = function (ev) {
        if (request.readyState == 4 && request.status == 200) {
            $("#eeee").html(request.responseText);
        }
    };
    var request1 = new XMLHttpRequest();

    function zcdd() {
        request1.open("post", "MyServlet", true);
        request1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request1.send("op=pwd&passWord=" + $("#passWord").val());
    }

    request1.onreadystatechange = function (ev) {
        if (request1.readyState == 4 && request1.status == 200) {
            $("#eeeee").html(request1.responseText);
        }
    };


    var request2 = new XMLHttpRequest();

    function zcddd() {
        request2.open("post", "MyServlet", true);
        request2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request2.send("op=pwdyz2&passWord=" + $("#passWord").val() + "&rePassWord=" + $("#rePassWord").val());
    }

    request2.onreadystatechange = function (ev) {
        if (request2.readyState == 4 && request2.status == 200) {
            $("#eeeeee").html(request2.responseText);
        }
    };
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
<div id="register">
    <div class="title">
        <h2>欢迎注册熊大网上书城</h2>
    </div>
    <div class="steps">
        <ul class="clearfix">
            <li class="current">1.填写注册信息</li>
            <li class="unpass">2.注册成功</li>
        </ul>
    </div>
    <form method="post" action="AddServlet?action=userAction">
        <dl>
            <dt>用 户 名：</dt>
            <dd><input class="input-text" type="text" id="userName" onblur="zcd()"
                       name="userName"/><span id="eeee">当前用户已存在！</span></dd>
            <dt>密　　码：</dt>
            <dd><input class="input-text" type="password" id="passWord" onblur="zcdd()"
                       name="passWord"/><span id="eeeee">密码过于简单！</span></dd>
            <dt>确认密码：</dt>
            <dd><input class="input-text" type="password" id="rePassWord" onblur="zcddd()" name="rePassWord"/><span
                    id="eeeeee">两次密码输入不一致！</span>
            </dd>
            <dt>Email地址：</dt>
            <dd><input class="input-text" type="text" id="email" onblur="dddddd()" name="email"/><span>邮箱输入不正确！</span>
            </dd>
            <dt></dt>
            <dd class="button"><input class="input-reg" type="submit" name="register" value=""/></dd>
        </dl>
    </form>
</div>
<div id="footer" class="wrap">
    熊大网上书城 &copy; 版权所有
</div>
</body>
</html>
