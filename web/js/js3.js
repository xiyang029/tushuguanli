// js3.js 文件用于处理页面的交互逻辑

function loadPage(pageUrl) {
    var iframe = document.getElementById('contentFrame');

    // 设置 iframe 的 src 属性加载页面
    iframe.src = pageUrl;
}
// 页面加载时，默认显示 search.jsp 页面
window.onload = function() {
    loadPage('search.jsp');
};