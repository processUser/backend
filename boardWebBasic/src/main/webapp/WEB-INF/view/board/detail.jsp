<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--글번호, 글제목, 글내용, 조회수, 작성자(이름), 등록일시--%>
<div>
    <c:if test="${sessionScope.loginUser.iuser eq requestScope.data.writer}">
        <div>
            <a href="/board/del?iboard=${data.iboard}"><button>삭제</button></a>
            <a href="/board/regmod?iboard=${data.iboard}"><button>수정</button></a>
        </div>
    </c:if>
    <div>
        <div><h1>${data.title}</h1></div>
        <div>
            <ul>
                <li><c:out value="${data.iboard}"/></li>
                <li class="nm"><c:out value="${requestScope.data.writerNm}"/></li>
                <li><c:out value="${data.hit}"/></li>
                <li><c:out value="${data.rdt}"/></li>
            </ul>
        </div>
        <div><c:out value="${data.ctnt}"/></div>
    </div>
</div>