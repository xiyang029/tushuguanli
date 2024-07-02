<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅</title>
    <link rel="stylesheet" type="text/css" href="../css/styles1.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../js/js4.js"></script>
    <style>
        #borrowModalBookTitle {
            color: #66afe9;
        }

        .datepicker {
            width: 100%;
            height: 30px;
            border-radius: 5px;
            border: 1px solid #8e9eab;
        }

        .borrow-modal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
            overflow: auto;
        }

        .borrow-modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
        }

        .borrow-modal-content h2 {
            margin-top: 0;
        }

        .borrow-modal-content .form-group {
            margin-bottom: 10px;
        }

        .borrow-modal-content .button {
            margin-right: 10px;
        }

        .borrow-modal-content .close {
            float: right;
            cursor: pointer;
            font-size: 20px;
            font-weight: bold;
        }

        .borrow-modal-content .close:hover,
        .borrow-modal-content .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .borrow-button {
            padding: 5px 10px;
            background-color: #cccccc;
            color: #77d;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            text-decoration: none;
        }

        .borrow-button:hover {
            background-color: #005a;
        }

        .borrow-button:focus {
            outline: none;
            box-shadow: 0 0 3px #66afe9;
        }
    </style>
    <script>

        function closeModal() {
            var modal = document.getElementById('borrowModal');
            modal.style.display = 'none';
        }
    </script>
</head>
<body>
<div id="queryContent" class="content" style="display: none;">
    <h2>借阅图书</h2>
    <div id="bookList">
        <table id="bookTable">
            <thead>
            <tr>
                <th>ID</th>
                <th>书名</th>
                <th>作者</th>
                <th>是否可用</th>
                <th>借阅</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div class="pagination" id="pagination">
            <button id="prevPage" class="button" onclick="prevPage()">上一页</button>
            <button id="nextPage" class="button" onclick="nextPage()">下一页</button>
        </div>
    </div>
</div>

<div id="borrowModal" class="borrow-modal">
    <div class="borrow-modal-content">
        <h2>借阅信息</h2>
        <form action="http://localhost:8080/tushuguanli_war_exploded/borrowBookServlet" method="post" accept-charset="UTF-8">
            <div class="form-group">
                <label for="borrowBookTitle">书本名称:</label>
                <input type="text" id="borrowBookTitle" name="borrowBookTitle" readonly>
            </div>
            <div class="form-group">
                <label for="borrowBookId">书本ID:</label>
                <input type="text" id="borrowBookId" name="borrowBookId" readonly>
            </div>
            <div class="form-group">
                <label for="borrowUsername">借阅用户名:</label>
                <input type="text" id="borrowUsername" name="borrowUsername" class="datepicker" required placeholder="请输入你的用户名">
            </div>
            <div class="form-group">
                <label for="borrowTime">借阅时间:</label>
                <input type="text" id="borrowTime" name="borrowTime" class="datepicker" data-toggle="datepicker" required
                       placeholder="请输入借阅时间，格式：xxxx-xx-xx">
            </div>
            <div class="form-group">
                <button type="submit" class="button">确定</button>
                <button type="button" class="button" onclick="closeModal()">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>