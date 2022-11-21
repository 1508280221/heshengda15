<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<head>
    <title>用户信息列表管理</title>
</head>

<script type="text/javascript">
    function show(obj) {

        var x = window.screen.height;
        var y = window.screen.width;
        var h = 768;
        var w = 1024;
        var model = "title=word,resizable=yes,scrollbars=yes,height=" + h + ",width=" + w + ",status=yes,toolbar=no,menubar=no,location=no,top=" + (x - h) / 2 + ",left=" + (y - w) / 2;
        var url = "";

        url = "addUser.jsp";//弹出窗口的页面内容
        var oOpen = window.open(url, "adviceDetail", model);
        oOpen.focus();
    }

    function showMod(id) {

        var x = window.screen.height;
        var y = window.screen.width;
        var h = 768;
        var w = 1024;
        var model = "title=word,resizable=yes,scrollbars=yes,height=" + h + ",width=" + w + ",status=yes,toolbar=no,menubar=no,location=no,top=" + (x - h) / 2 + ",left=" + (y - w) / 2;
        var url = "";

        url = "modUser.jsp?id=" + id;//弹出窗口页内容
        var oOpen = window.open(url, "adviceDetail", model);
        oOpen.focus();
    }
</script>

<body>
<h2 align="center" style="margin-top: 30px">用户信息页</h2>
<table align="center" style="margin-top:50px;line-height: 50px;text-align:center;width:70%">
    <tr>
        <td></td>
        <td cols="3">
            <form action="userServlet?pageNo=1" method="post">
                <input type="text" class="form-control" name="content" value="${content}" placeholder="请输入搜索内容"
                       style="float:left">
        </td>
        <td></td>
        <td><input type="submit" class="btn btn-primary" value="搜 索"/>
            </form></td>
        <td></td>
        <td></td>
        <td><a class="btn btn-primary" onClick="show(this)" >添加用户</a></td>
    </tr>
    <tr>
        <td>ID</td>
        <td>账号</td>
        <td>密码</td>
        <td>邮箱</td>
        <td>性别</td>
        <td>生日</td>
        <td>操作</td>
        <c:forEach items="${list}" var="t">
    <tr>
        <td>${t.id}</td>
        <td>${t.name}</td>
        <td>${t.password}</td>
        <td>${t.email}</td>
        <td>${t.gender}</td>
        <td>${t.birthday}</td>
        <td><a onClick="showMod(${t.id})" >修改</a>&nbsp;&nbsp;<a href="userApi?method=delUser&id=${t.id}">删除</a></td>
    </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <c:if test="${pageBean.pageNo==1}" var="flag"></c:if>
        <c:if test="${flag}">
            <td><a href="userServlet?pageNo=1">首页</a>&nbsp;&nbsp;<a href="userServlet?pageNo=1">上一页</a>&nbsp;&nbsp;第${pageBean.pageNo}页
                &nbsp;&nbsp;共${pageBean.pageTotal}页 &nbsp;&nbsp;<a
                        href="userServlet?pageNo=${pageBean.pageNo+1}">下一页</a>&nbsp;&nbsp; <a
                        href="userServlet?pageNo=${pageBean.pageTotal}">尾页</a></td>
        </c:if>
        <c:if test="${!flag}">
            <td><a href="userServlet?pageNo=1">首页</a>&nbsp;&nbsp;<a
                    href="userServlet?pageNo=${pageBean.pageNo-1}">上一页</a>&nbsp;&nbsp;第${pageBean.pageNo}页
                &nbsp;&nbsp;共${pageBean.pageTotal}页&nbsp;&nbsp; <a
                        href="userServlet?pageNo=${pageBean.pageNo+1}">下一页</a>&nbsp;&nbsp; <a
                        href="userServlet?pageNo=${pageBean.pageTotal}">尾页</a></td>
        </c:if>
        <td></td>
        <td></td>
    </tr>
    </tr>
</table>
</body>
</html>