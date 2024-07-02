<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<div class="container">

    <div id="loginForm">
        <form action="login" method="post">
            <div class="button-container">
                <h3 id="loginBtn" onclick="showLogin()">登录</h3>
            </div>
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username"><br>

            <label for="password">密码：</label>
            <input type="password" id="password" name="password"><br>

            <input type="submit" value="登录">
        </form>
    </div>

</div>

</body>
</html>