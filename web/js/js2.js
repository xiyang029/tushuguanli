// 定义全局变量
var currentPage = 1;
var pageSize = 10;
var booksData = [];

// JavaScript function to initialize the page
window.onload = function() {
    // Display the query content div
    document.getElementById('queryContent').style.display = 'block';
    // Fetch all books
    fetchBooks();
}

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

// 分页按钮的函数
function prevPage() {
    if (currentPage > 1) {
        currentPage--;
        showBooks(currentPage);
    }
}

function nextPage() {
    if ((currentPage * pageSize) < booksData.length) {
        currentPage++;
        showBooks(currentPage);
    }
}