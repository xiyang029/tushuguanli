<%--
  Created by IntelliJ IDEA.
  User: xi
  Date: 2024/7/1
  Time: 下午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title class="biaoti">图书借阅系统</title>
    <link rel="stylesheet" type="text/css" href="../css/styles1.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../js/js3.js"></script> <!-- 引入新的 js3.js 文件 -->
</head>
<body>
<div class="biaoti"><h1>图书借阅系统</h1></div>
<div class="container">
    <div class="box left-box">
        <!-- 修改按钮的点击事件，调用新的函数来加载 iframe -->
        <button class="button" onclick="loadPage('search.jsp')">查询</button>
        <button class="button" onclick="loadPage('borrow.jsp')">借阅</button>
        <button class="button" onclick="loadPage('return.jsp')">归还</button>
    </div>
    <div class="box right-box">
        <!-- 右侧内容显示区域，使用 iframe 来加载页面 -->
        <iframe id="contentFrame" width="100%" height="100%" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>
