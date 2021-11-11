<%@ page import="board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BoardVO vo = (BoardVO) request.getAttribute("boardData"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= vo.getTitle() %></title>
</head>
<body>
    <div>
        <div>게시판</div>
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

</body>
</html>