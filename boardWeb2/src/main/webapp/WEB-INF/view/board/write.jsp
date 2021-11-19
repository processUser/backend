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
    <title>글쓰기</title>
</head>
<body>
    <div>
        <h1>글쓰기</h1>
        <%if(err != null) {%>
            <div><%= err%></div>
        <%}%>
        <form action="/board/write" method="post">
<%--            해당방법은 보안상 위험하다.--%>
<%--            <input type="hidden" name="iuser" value="<%= loginUser.getIuser()%>">--%>
            <div>
                <input type="text" name="title" placeholder="title" value="${requestScope.writeData.title}">
            </div>
            <div>
                <textarea name="ctnt" cols="30" rows="10">${requestScope.writeData.ctnt}</textarea>
            </div>
            <div>
                <input type="submit" value="등록">
                <input type="reset" value="초기화">
            </div>
        </form>
    </div>
</body>
</html>
