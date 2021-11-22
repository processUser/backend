<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
    <div>
        <div>
            <h1>글수정</h1>
        </div>
        <c:if test="${requestScope.err != null}">
            <div>${requestScope.err}</div>
        </c:if>
        <form action="/board/update" method="post">
            <input type="hidden" name="num" value="${requestScope.boardDetail.iboard}">
            <input type="text" name="title" id="title" value="${requestScope.boardDetail.title}">
            <textarea name="ctnt" id="" cols="30" rows="10">${requestScope.boardDetail.ctnt}</textarea>
            <div>
                <input type="submit" value="수정">
                <input type="button" value="취소">
            </div>
        </form>
    </div>

</body>
</html>