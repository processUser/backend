<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--param.  - 쿼리스트링에 담겨있는 값을 가져올때.--%>
<div>
    <form action="/board/regmod" method="post">
        <input type="hidden" name="iboard" value="${param.iboard}">
        <label >제목: <input type="text" name="title" value="<c:out value="${requestScope.data.title}" />"></label>
        <label >내용: <textarea name="ctnt"><c:out value="${requestScope.data.ctnt}" /></textarea></label>
        <div>
            <input type="submit" value="${title}">
            <input type="reset" value="초기화">
        </div>
    </form>
</div>

