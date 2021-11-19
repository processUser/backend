<%@ page import="com.koreait.board2.model.UserVO" %>
<%@ page import="com.koreait.board2.model.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserVO loginUser = (UserVO)session.getAttribute("loginUser");
    List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
    int maxPage = (int) request.getAttribute("maxPage");
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
            for(BoardVO vo : list ){
        %>
        <tr>
            <td><%= vo.getIboard()%></td>
            <td><a href="/board/detail?pk=<%= vo.getIboard()%>"><%= vo.getTitle()%></a></td>
            <td><%= vo.getWriterNm()%></td>
            <td><%= vo.getRdt()%></td>
        </tr>
        <% } %>
    </table>
    <div style="margin: 20px; display: flex; justify-content: center;">
        <%for (int j = 1; j<= maxPage; j++){%>
        <span>&nbsp;<a href="/board/list?page=<%=j%>"><%=j%></a>&nbsp;</span>

        <%}%>
    </div>
</body>
</html>