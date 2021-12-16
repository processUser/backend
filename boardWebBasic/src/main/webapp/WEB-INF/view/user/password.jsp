<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <form action="/user/password" method="post" id="frm">
        <div><input type="password" name="upw" placeholder="현재 비밀번호"></div>
        <div><input type="password" name="changedUpw" placeholder="변경 비밀번호"></div>
        <div><input type="password" name="changedUpwConfirm" placeholder="비밀번호 확인"></div>
    </form>
    <div><input type="button" value="비밀번호 변경" id="submitBtn"></div>

</div>
<script src="/res/js/user/password.js"></script>
<c:if test="${requestScope.err != null}">
    <script>
        alert(${requestScope.err})
    </script>
</c:if>