<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/res/css/board/list.css">
<div>
    <table id="boardTable">
        <tr>
            <th>no</th>
            <th>title</th>
            <th>writer</th>
            <th>hits</th>
            <th>reg-datetime</th>
        </tr>
        <c:forEach items="${requestScope.list}" var="item">
            <tr class="record" onclick="moveToDetail(${item.iboard})">
                <td><c:out value="${item.iboard}"/></td>
                <td><c:out value="${item.title}"/></td>
                <td><c:out value="${item.writerNm}"/></td>
                <td><c:out value="${item.hit}"/></td>
                <td><c:out value="${item.rdt}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<script src="/res/js/board/list.js"></script>
