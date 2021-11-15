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
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/list.css">
</head>
<body>
    <div id="content">
        <div id="header">
            <h1>게시판</h1>
        </div>

        <div id="section">
            <table id="tableBoard">
                <tr class="thWarp">
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>작성일시</th>
                </tr>
                <% for (BoardVO vo : list) { %>
                <tr class="tdWarp">
                    <td><%= vo.getIboard() %></td>
                    <td><a href="/detail?iboard=<%= vo.getIboard() %>"><%= vo.getTitle() %></a></td>
                    <td><%= vo.getWriter() %></td>
                    <td><%= vo.getRdt() %></td>
                </tr>
                <% } %>
            </table>
            <div id="article">
                <a href="/write"><input type="button" value="글쓰기"></a>
                <form action="/list" method="get">
                    <select name="choice" >
                        <option value="1" selected>제목+내용</option>
                        <option value="2" >제목</option>
                        <option value="3" >작성자</option>
                    </select>
                    <input type="text" name="search">
                    <input type="submit" value="검색">
                </form>
            </div>
        </div>
    </div>
</body>
</html>