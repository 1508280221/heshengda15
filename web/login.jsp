<%@ page import="com.heshengda15.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: 15082
  Date: 2022/11/13
  Time: 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String username = null;
    String password = null;
    Cookie[] cookies = request.getCookies();
    if (cookies!=null){
        //遍历Cookie
        for (Cookie cookie : cookies) {
            if ("name".equals(cookie.getName())){
                username = cookie.getValue();

            }
            if ("password".equals(cookie.getName())){
                password = cookie.getValue();
            }
        }
        if (username != null && password != null){
            response.sendRedirect("/userServlet?pageNo=1");
        }
    }
%>
<head>
    <title>登录页面</title>
    <link rel="stylesheet" type="text/css" href="css/style.css  ">
</head>
<body>
<div class="box">
    <form autocomplete="off" action="${pageContext.request.contextPath}/LoginServlet" method="post">
        <h2>登 录</h2>
        <div class="inputBox">
            <input type="text" required="required" name="name">
            <span>用户名</span>
            <i></i>
        </div>
        <div class="inputBox">
            <input type="password" required="required" name="password">
            <span>密码</span>
            <i></i>
        </div>
        <div class="links">
            <a href="#">忘记密码？</a>
            <a href="${pageContext.request.contextPath}/register.jsp">注 册</a>
        </div>
        <input type="submit" value="登录">
    </form>
</div>
</body>
</html>
