<%@ page import="board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BoardVO vo = (BoardVO) request.getAttribute("boardVo");%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= vo.getTitle() %></title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/write.css">
</head>
<body>
    <div id="content">
        <div id="header">
            <h1>게시판</h1>
        </div>
        <div id="writeSection">
            <form action="/upd" method="post">
                <input type="hidden" name="iboard" value="<%= vo.getIboard() %>" placeholder="제목을 입력하세요">
                <div class="writeTitle">
                    <input type="text" name="title" value="<%= vo.getTitle() %>">
                </div>
                <div>
                    작성자<input type="text" name="writer" value="<%= vo.getWriter() %>">
                </div>
                <textarea name="ctnt" cols="30" rows="10"><%= vo.getCtnt() %></textarea>
                <div class="writeButton">
                    <input type="submit" value="수정">
                    <a href="/detail?iboard=<%= vo.getIboard() %>"><input type="button" value="취소"></a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
