<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
    <div id="content">
        <div id="header">
            <h1>로그인</h1>
        </div>
        <c:if test="${requestScope.err != null}">
            <div id="err">${requestScope.err}</div>
        </c:if>
        <div id="loginForm">
            <form action="/user/login" method="post">
                <input type="text" name="uid" class="text" placeholder="아이디">
                <input type="password" name="upw" class="text" placeholder="비밀번호">
                <input type="submit" class="submit" value="로그인">
            </form>
        </div>
        <div id="infoWrap">
            <ul>
                <li><a href="">아이디/비밀번호 찾기</a></li>
                <li><a href="/user/join">회원가입</a></li>
            </ul>
        </div>
    </div>
</body>
</html>