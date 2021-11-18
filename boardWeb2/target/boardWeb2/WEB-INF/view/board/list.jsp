<%@ page import="com.koreait.board2.model.UserVO" %>
<%@ page import="com.koreait.board2.model.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserVO loginUser = (UserVO)session.getAttribute("loginUser");
    List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List</title>
</head>
<body>
    <div>
    <% if(loginUser != null) { %>
        <%=loginUser.getNm()%>(<%=loginUser.getUid()%>)님 환영합니다. <a href="/user/logout">로그아웃</a>
        <a href="/board/write">글쓰기</a>

    <% } else { %>
        <a href="/user/login">로그인</a>
    <% } %>
    </div>
    <h1>리스트</h1>
    <table>
        <tr>
            <th>번호</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <%
            int i = list.size();
            for(BoardVO vo : list ){
        %>
        <tr>
            <td><%= i %></td>
            <td><a href="/board/detail?pk=<%= vo.getIboard()%>"><%= vo.getTitle()%></a></td>
            <td><%= vo.getWriterNm()%></td>
            <td><%= vo.getRdt()%></td>
        </tr>
        <% i--; } %>
    </table>

</body>
</html>