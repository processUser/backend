<%@ page import="board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BoardVO vo = (BoardVO) request.getAttribute("boardData"); %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= vo.getTitle() %></title>
    <link rel="stylesheet" href="/css/common.css">
</head>
<body>
    <div id="content">
        <div id="header">
            <h1>게시판</h1>
        </div>

        <div>
            <div><%= vo.getTitle() %></div>
            <div>
                <div><%= vo.getWriter() %></div>
                <div><%= vo.getRdt() %></div>
            </div>
            <div><%= vo.getCtnt() %></div>
        </div>
        <div>
            <a href="/list"><input type="button" value="목차"></a>
            <a href="/upd?iboard=<%= vo.getIboard() %>"><input type="button" value="수정"></a>
            <a href="/del?iboard=<%= vo.getIboard() %>"><input type="button" value="삭제"></a>
        </div>
        <div>
            <% int prevVo = (int) request.getAttribute("prevIboard"); %>
            <% int nextVo = (int) request.getAttribute("nextIboard"); %>
            <% if (prevVo != 0){%>
            <a href="/detail?iboard=<%= prevVo %>"><input type="button" value="이전"></a>
            <%}%>
            <% if(nextVo != 0){  %>
            <a href="/detail?iboard=<%= nextVo %>"><input type="button" value="다음"></a>
            <%} %>
        </div>
    </div>



</body>
</html>