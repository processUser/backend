<%@ page import="com.koreait.board2.model.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
//    UserVO loginUser = (UserVO)session.getAttribute("loginUser");
    String err = (String) request.getAttribute("err");
%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mod</title>
</head>
<body>
    <div>
        <h1>글수정</h1>
        <%if(err != null) {%>
            <div><%= err%></div>
        <%}%>
        <form action="/board/mod" method="post">
            <input type="hidden" name="pk" value="${requestScope.boardData.iboard}">
            <div>
                <input type="text" class="reset" name="title" value="${requestScope.boardData.title}" placeholder="title">
            </div>
            <div>
                <textarea name="ctnt" class="reset" cols="30" rows="10">${requestScope.boardData.ctnt}</textarea>
            </div>
            <div>
                <input type="submit" value="등록">
                <input type="reset" value="초기화">
            </div>
        </form>
    </div>

</body>
</html>
