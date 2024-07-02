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
    <title>gu归还图书</title>
    <link rel="stylesheet" type="text/css" href="../css/styles1.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../js/js5.js"></script>
    <style>
        .return-button {
            border-radius: 10px;
            padding: 5px 10px;
            background-color: #cccccc;
            color: #77d;
            border: none;
            cursor: pointer;
            text-decoration: none;
            transition: box-shadow 0.3s ease; /* 添加过渡效果 */
        }

        .return-button:hover {
            background-color: #005a;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); /* 悬停时的阴影效果 */
        }

        .return-button:focus {
            outline: none; /* 移除默认的焦点样式 */
        }
    </style>
</head>
<body>
<div id="queryContent" class="content" style="display: none;">
    <h2>借阅图书</h2>
    <!-- 查询图书内容区域 -->
    <div id="bookList">
        <!-- 查询图书内容区域 -->
        <table id="bookTable">
            <!-- 表头 -->
            <thead>
            <tr>
                <th>ID</th>
                <th>用户名</th>
                <th>书名ID</th>
                <th>书名</th>
                <th>借阅时间</th>
                <th>归还时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <!-- 表体，用于填充数据 -->
            <tbody>
            <!-- 这里将用 JavaScript 填充数据 -->
            </tbody>
        </table>
        <div class="pagination" id="pagination">
            <button id="prevPage" class="button" onclick="prevPage()">上一页</button>
            <button id="nextPage" class="button" onclick="nextPage()">下一页</button>
        </div>
    </div>
</div>
</body>
</html>
