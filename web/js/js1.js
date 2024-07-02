let currentPage = 1;
const pageSize = 10;
let booksData = [];

// 初始化页面时，设置查询内容不可见，并且执行初始的查询页面显示操作
window.onload = function() {
    document.getElementById('queryContent').style.display = 'none';
    document.getElementById('borrowContent').style.display = 'none';
    document.getElementById('returnContent').style.display = 'none';
    showQuery(); // 执行初始的查询页面显示操作
};

// 显示所有书籍
function showBooks(page) {
    const start = (page - 1) * pageSize;
    const end = start + pageSize;
    const paginatedBooks = booksData.slice(start, end);

    const tableBody = document.getElementById('bookTable').getElementsByTagName('tbody')[0];
    tableBody.innerHTML = '';

    paginatedBooks.forEach(book => {
        const row = tableBody.insertRow();
        row.insertCell().textContent = book.id;
        row.insertCell().textContent = book.title;
        row.insertCell().textContent = book.author;
        row.insertCell().textContent = book.available ? "是" : "否";
    });

    // 更新分页按钮的状态
    document.getElementById('prevPage').disabled = page <= 1;
    document.getElementById('nextPage').disabled = end >= booksData.length;
}

// 获取所有书籍数据
function fetchBooks() {
    fetch('../fetchBooks')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            booksData = data;
            showBooks(currentPage); // 显示第一页的书籍数据
        })
        .catch(error => console.error('Error:', error));
}

// 搜索书籍
function searchBooks() {
    const searchTerm = document.getElementById('searchInput').value.trim();

    // 使用 AJAX 发送 GET 请求
    $.ajax({
        type: 'GET',
        url: '../searchBook',
        data: {
            title: searchTerm
        },
        dataType: 'json',
        success: function(response) {
            const tableBody = document.getElementById('bookTable').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = '';

            if (response.length > 0) {
                response.forEach(function(book) {
                    const row = tableBody.insertRow();
                    row.insertCell().textContent = book.id;
                    row.insertCell().textContent = book.title;
                    row.insertCell().textContent = book.author;
                    row.insertCell().textContent = book.available ? "是" : "否";
                });
            } else {
                tableBody.innerHTML = '<tr><td colspan="4">未找到匹配的书籍。</td></tr>';
            }

            // 隐藏分页按钮
            document.getElementById('pagination').style.display = 'none';
        },
        error: function() {
            alert('搜索失败，请重试！');
        }
    });

    // 隐藏分页按钮
    document.getElementById('pagination').style.display = 'none';

    // 如果搜索词为空，则刷新页面
    if (searchTerm === '') {
        location.reload();
    }
}


// 显示查询页面并获取图书数据
function showQuery() {
    document.getElementById('queryContent').style.display = 'block';
    document.getElementById('borrowContent').style.display = 'none';
    document.getElementById('returnContent').style.display = 'none';
    fetchBooks(); // 获取并显示所有书籍数据
}

// 显示借书页面
function showBorrow() {
    document.getElementById('queryContent').style.display = 'none';
    document.getElementById('borrowContent').style.display = 'block';
    document.getElementById('returnContent').style.display = 'none';
}

// 显示还书页面
function showReturn() {
    document.getElementById('queryContent').style.display = 'none';
    document.getElementById('borrowContent').style.display = 'none';
    document.getElementById('returnContent').style.display = 'block';
}

// 上一页
function prevPage() {
    if (currentPage > 1) {
        currentPage--;
        showBooks(currentPage);
    }
}

// 下一页
function nextPage() {
    if ((currentPage * pageSize) < booksData.length) {
        currentPage++;
        showBooks(currentPage);
    }
}
