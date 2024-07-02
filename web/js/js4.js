
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

    // 创建借阅按钮列
    var borrowCell = row.insertCell();
    borrowCell.innerHTML = `<button class="borrow-button" data-book-id="${book.id}" data-book-title="${book.title}">借阅</button>`;

    // 绑定借阅按钮点击事件
    borrowCell.querySelector('.borrow-button').addEventListener('click', function() {
      // 获取书籍标题
      var bookTitle = this.getAttribute('data-book-title');
      // 调用函数打开弹出框，并传递书籍 ID 和标题
      openModal(book.id, book.title);
    });
  });

// 打开弹出框
  // 打开弹出框
  function openModal(bookId, bookTitle) {
    var modal = document.getElementById('borrowModal');
    modal.style.display = 'block';
    // 在弹出框中填充书籍标题
    // 设置隐藏域的书籍 ID
    document.getElementById('borrowBookTitle').value = bookTitle;
    document.getElementById('borrowBookId').value = bookId;
  }




  // 更新分页按钮的状态
  document.getElementById('prevPage').disabled = page <= 1;
  document.getElementById('nextPage').disabled = end >= booksData.length;
}

// 获取所有书籍数据
function fetchBooks() {
  fetch('http://localhost:8080/tushuguanli_war_exploded/fetchBooks')
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

