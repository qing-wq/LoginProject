<%--
  Created by IntelliJ IDEA.
  User: hujingjing
  Date: 2022/4/14
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post">
用户名：<input type="text" name="uname" id="uname" value="${ResMessageModel.data.username}"><br>
密码：<input type="password" name="upwd" id="upwd" value="${ResMessageModel.data.password}"><br>
邮箱：<input type="email" name="email" id="email" value=""><br>
    <span style="color: red">${ResMessageModel.message}</span><br>
  <input type="submit" value="注册">
</form>
</body>
</html>
