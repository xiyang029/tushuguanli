$(document).ready(function() {
    function loadBorrowData() {
        $.ajax({
            url: 'http://localhost:8080/tushuguanli_war_exploded/fetchBorrow',
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                // 清空表格内容
                $('#bookTable tbody').empty();

                // 遍历 JSON 数据并填充表格
                data.forEach(function(borrow) {
                    var borrowDate = parseChineseDate(borrow.borrowDate);
                    var returnDate = borrow.returnDate ? parseChineseDate(borrow.returnDate) : '未归还';

                    // 构建行
                    var row = '<tr>' +
                        '<td>' + borrow.id + '</td>' +
                        '<td>' + borrow.username + '</td>' +
                        '<td>' + borrow.bookId + '</td>' +
                        '<td>' + borrow.title + '</td>' +
                        '<td>' + borrowDate + '</td>' +
                        '<td>' + returnDate + '</td>' +
                        '<td><button class="return-button" data-id="' + borrow.id + '">归还</button></td>' +
                        '</tr>';

                    // 将行添加到表格中
                    $('#bookTable tbody').append(row);
                });

                // 显示内容
                $('#queryContent').show();
            },
            error: function() {
                alert('获取借阅数据失败，请稍后再试。');
            }
        });
    }

// 解析中文日期函数
    function parseChineseDate(dateString) {
        // 中文月份转换
        var monthMapping = {
            '一月': '01',
            '二月': '02',
            '三月': '03',
            '四月': '04',
            '五月': '05',
            '六月': '06',
            '七月': '07',
            '八月': '08',
            '九月': '09',
            '十月': '10',
            '十一月': '11',
            '十二月': '12'
        };

        // 使用正则表达式提取月份和日期
        var regex = /(\S+) (\d+), (\d+)/;
        var matches = dateString.match(regex);

        if (matches && matches.length === 4) {
            var month = monthMapping[matches[1]];
            var day = matches[2].padStart(2, '0'); // 补全两位数字
            var year = matches[3];

            // 返回格式化的日期字符串
            return `${year}-${month}-${day}`;
        } else {
            return '无效日期';
        }
    }

    // 点击归还按钮的事件处理函数
    $(document).on('click', '.return-button', function() {
        var borrowId = $(this).data('id');
        var borrowTitle = $(this).closest('tr').find('td:eq(3)').text();
        var borrowUsername = $(this).closest('tr').find('td:eq(1)').text();

        // 确认对话框
        if (confirm('确认归还书籍 "' + borrowTitle + '" (借阅人: ' + borrowUsername + ')?')) {
            returnBook(borrowId);
        }
    });

    // 发送 AJAX 请求删除借阅记录
    function returnBook(borrowId) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/tushuguanli_war_exploded/delborrow',
            data: { borrowId: borrowId },
            success: function(response) {
                alert('成功删除借阅记录: ' + response);
                loadBorrowData(); // 刷新表格数据
            },
            error: function() {
                alert('删除借阅记录失败');
            }
        });
    }

    // 初始加载数据
    loadBorrowData();
});