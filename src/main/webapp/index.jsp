<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post" id="loginForm" class="box">
    <h1>login</h1>
    <input type="text" name="username" id="username" placeholder="Username" value=${messageModel.data.username}>
    <input type="password" name="password" id="password" placeholder="Password" value=${messageModel.data.password}>
    <span id="message" style="color: red">${messageModel.message}</span>
<%--    <input type="submit" value="登录">--%>
    <button id="loginBtn" value="login">Login</button>
    <button id="register" value="注册">Register</button>
</form>
<a href="${pageContext.request.contextPath}/register.jsp">注册</a>
</body>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    $("#loginBtn").click(function (){
        var uname = $("#username").val();
        var pwd = $("#password").val();
        if(uname.length == 0||pwd.length == 0) {
            $("#message").html("用户名和密码不能为空");
            return;
        }
        $("#loginForm").submit();
    })
</script>
</html>