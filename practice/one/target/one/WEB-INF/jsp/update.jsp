<%@ page import="board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BoardVO vo = (BoardVO) request.getAttribute("boardVo");%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= vo.getTitle() %></title>
</head>
<body>
    <form action="/upd" method="post">
        <input type="hidden" name="iboard" value="<%= vo.getIboard() %>">
        제목<input type="text" name="title" value="<%= vo.getTitle() %>">
        작성자<input type="text" name="writer" value="<%= vo.getWriter() %>">
        <textarea name="ctnt" cols="30" rows="10"><%= vo.getCtnt() %></textarea>
        <input type="submit" value="저장">
        <a href="/detail?iboard=<%= vo.getIboard() %>"><input type="button" value="취소"></a>
    </form>
</body>
</html>
