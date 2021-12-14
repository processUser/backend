<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/res/css/board/detail.css">
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
<%--       좋아요--%>
    <c:if test="${sessionScope.loginUser != null}">
        <c:choose>
            <c:when test="${requestScope.isHeart == 1}">
                <a href="/board/heart?proc=2&iboard=${requestScope.data.iboard}"><span class="material-icons">thumb_up</span></a>
            </c:when>
            <c:otherwise>
                <a href="/board/heart?proc=1&iboard=${requestScope.data.iboard}"><span class="material-icons-outlined">thumb_up</span></a>
            </c:otherwise>
        </c:choose>
    </c:if>

    </div>
<%--    댓글--%>
    <div>---- 댓글 ----</div>
    <ul>
        <c:forEach var="list" items="${requestScope.cmtList}">
        <li>
            <span><c:out value="${list.writerNm}"/></span>
            <span><c:out value="${list.rdt}"/></span>
            <c:if test="${list.writer == sessionScope.loginUser.iuser}">
                <button onclick="openModForm(${list.icmt},'${list.ctnt}');">수정</button>
                <button onclick="isDelCmt(${data.iboard}, ${list.icmt});">삭제</button>
            </c:if>
            <div><c:out value="${list.ctnt}"/></div>
        </li>
        </c:forEach>
    </ul>
<%--댓글 검색--%>
    <c:if test="${sessionScope.loginUser != null}">
        <div>
            <form action="/board/cmt/reg" method="post">
                <input type="hidden" name="iboard" value="${requestScope.data.iboard}">
                <input type="text" name="ctnt" placeholder="댓글 내용">
                <input type="submit" value="등록">
            </form>
        </div>
    </c:if>
</div>
<div class="cmtModContainer">
    <div class="cmtmodBody">
        <form action="/board/cmt/reg" method="post" id="cmtModFrm">
            <input type="hidden" name="iboard" value="${requestScope.data.iboard}">
            <input type="hidden" name="icmt">
            <div><input type="text" name="ctnt" placeholder="댓글 내용"></div>
            <div>
                <input type="submit" value="수정">
                <input type="button" value="취소" id="btnCancel">
            </div>
        </form>
    </div>
</div>
<script src="/res/js/board/detail.js?v=1"></script>