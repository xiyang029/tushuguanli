<%--
  Created by IntelliJ IDEA.
  User: xi
  Date: 2024/7/1
  Time: 下午9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>搜索</title>
    <link rel="stylesheet" type="text/css" href="../css/styles1.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../js/js2.js"></script>
  </head>
  <body>
  <div id="queryContent" class="content" style="display: none;">
    <h2>查询图书</h2>
    <!-- 查询图书内容区域 -->
    <div class="sousuo">
      <input class="sousuo1" id="searchInput" type="text" placeholder="请输入搜索书本的名称">
      <button onclick="searchBooks()">搜索</button>
    </div>
    <div id="bookList">
      <!-- 查询图书内容区域 -->
      <table id="bookTable">
        <!-- 表头 -->
        <thead>
        <tr>
          <th>ID</th>
          <th>书名</th>
          <th>作者</th>
          <th>是否可用</th>
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
