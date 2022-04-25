<%@ page import="com.example.login.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: hujingjing
  Date: 2022/4/12
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
</head>
<body>
<h1>Welcome!</h1>
<p>您的信息为：</p>
<p>账号:${user.username}</p>
<p>密码：${user.password}</p>
<p>邮箱：${user.email}</p>
<a href="${pageContext.request.contextPath}/loginout">注销</a>
</body>
</html>
