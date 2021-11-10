<%@ page import="java.util.List" %>
<%@ page import="board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<BoardVO> list = (List<BoardVO>) request.getAttribute("listData"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <style>
        table, th, td{
            border-collapse: collapse;
            border: 1px solid #000;
            text-align: center;
        }
    </style>
</head>
<body>
    <div>
        <div>게시판</div>
    </div>
    <div>
        <table>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>작성일시</th>
            </tr>
            <% for (BoardVO vo : list) { %>
            <tr>
                <td><%= vo.getIboard() %></td>
                <td><a href="/detail?iboard=<%= vo.getIboard() %>"><%= vo.getTitle() %></a></td>
                <td><%= vo.getWrite() %></td>
                <td><%= vo.getRdt() %></td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>