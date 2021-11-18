<%@ page import="com.koreait.board2.model.BoardVO" %>
<%@ page import="com.koreait.board2.model.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO vo = (BoardVO) request.getAttribute("item");
    UserVO loginUser = (UserVO) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.item.title}</title>
</head>
<body>
<%if(loginUser != null && vo.getWriter() == loginUser.getIuser()){%>
    <a href=""><input type="button" value="수정"></a>
    <a href="/board/del?iboard=${requestScope.item.iboard}"><input type="button" value="삭제"></a>
<%}%>
<div>${requestScope.err}</div>
<div><a href="/board/list">list</a></div>
<div>${requestScope.item.iboard}</div>
<div>${requestScope.item.title}</div>
<div>${requestScope.item.writerNm}</div>
<div>${requestScope.item.rdt}</div>
<div>${requestScope.item.ctnt}</div>
</body>
</html>
