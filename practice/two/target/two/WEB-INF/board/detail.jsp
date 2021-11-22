<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>게시판</h1>
        </div>
        <c:if test="${requestScope.err != null}">
            <div>${requestScope.err}</div>
        </c:if>
        <div>
            <div>${boardDetail.title}</div>
            <div>${boardDetail.writerNm}</div>
            <div>${boardDetail.mdt}</div>
            <div>${boardDetail.ctnt}</div>
        </div>
        <div>
            <ul>
                <c:if test="${requestScope.prevBoard != 0}">
                    <li><a href="/board/detail?num=${requestScope.prevBoard}">이전글</a></li>
                </c:if>
<%--                <li><a href="javascript:history.back()">목차</a></li>--%>
                <li><a href="/board/list">목차</a></li>
                <c:if test="${requestScope.nextBoard != 0}">
                    <li><a href="/board/detail?num=${requestScope.nextBoard}">다음글</a></li>
                </c:if>
            </ul>
        </div>
        <div>
            <ul>
                <li><a href="/board/update?num=${boardDetail.iboard}">수정</a></li>
                <li><a href="/board/delete?num=${boardDetail.iboard}">삭제</a></li>
            </ul>
        </div>
    </div>
</body>
</html>