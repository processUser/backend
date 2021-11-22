<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/list.css">
</head>
<body>
    <div id="content">
        <div id="header">
            <h1>게시판</h1>
        </div>
        <div id="navWrap">
            <c:choose>
                <c:when test="${sessionScope.loginUser == null}">
                    <ul>
                        <li><a href="/user/join">회원가입</a></li>
                        <li><a href="/user/login">로그인</a></li>
                    </ul>
                </c:when>
                <c:when test="${sessionScope.loginUser != null}">
                    <div>${sessionScope.loginUser.nm}(${sessionScope.loginUser.uid})</div>
                    <a href="/user/logout">로그아웃</a>
                </c:when>
            </c:choose>
        </div>
        <div id="writeWrap">
            <a href="/board/write">글쓰기</a>
        </div>
        <div id="tableWrap">
            <table>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일시</th>
                </tr>
                <c:forEach var="boardVO" items="${requestScope.boardList}">
                    <tr>
                        <td>${boardVO.iboard}</td>
                        <td><a href="/board/detail?num=${boardVO.iboard}">${boardVO.title}</a></td>
                        <td>${boardVO.writerNm}</td>
                        <td>${boardVO.mdt}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="pageWrap">
            <ul>
                <c:forEach var="index" begin="1" end="${requestScope.maxPage}">
                    <li><a href="/board/list?page=${index}">${index}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>