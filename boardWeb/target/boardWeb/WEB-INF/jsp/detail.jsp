<%@ page import="com.koreait.board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<% BoardVO vo =(BoardVO) request.getAttribute("detailData"); %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= vo.getTitle()%></title>
</head>
<body>
    <div>제목 <input type="text" name="title" value="<%= vo.getTitle()%>"> </div>
    <div>작성자 <input type="text" name="writer" value="<%= vo.getWriter()%>"> </div>
    <div>내용 <textarea name="ctnt"  cols="30" rows="10" ><%= vo.getCtnt()%></textarea> </div>
    <div>작성일시 <input type="text" name="rdt" value="<%= vo.getRdt()%>"> </div>
<%--    <div>제목 <%= vo.getTitle()%></div>--%>
<%--    <div>작성자 <%= vo.getWriter()%> </div>--%>
<%--    <div>내용 <%= vo.getCtnt()%></div>--%>
<%--    <div>작성일시 <%= vo.getRdt()%> </div>--%>
</body>
</html>
