<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/res/css/board/list.css">
<div>
    <div>
        <form action="/board/list" method="get" id="searchFrm">
            <div>
                <select name="searchType">
                    <option value="1" ${param.searchType == 1 ? 'selected' : ''}>제목</option>
                    <option value="2" ${param.searchType == 2 ? 'selected' : ''}>내용</option>
                    <option value="3" ${param.searchType == 3 ? 'selected' : ''}>제목/내용</option>
                    <option value="4" ${param.searchType == 4 ? 'selected' : ''}>글쓴이</option>
                    <option value="5" ${param.searchType == 5 ? 'selected' : ''}>전체</option>
                </select>
                <input type="search" name="searchText" value="${param.searchText}">
                <input type="submit" value="검색">

                나타내는 행 수:
                <select name="rowCnt">
                    <c:forEach var="cnt" begin="5" end="30" step="5">
                        <option value="${cnt}" ${param.rowCnt == cnt ? 'selected' : ''}>${cnt}개</option>
                    </c:forEach>
                </select>
            </div>

        </form>
    </div>
<c:choose>
    <c:when test="${requestScope.maxPageNum <= 0}">
        <div>글이 없습니다.</div>
    </c:when>
    <c:otherwise>
    <table id="boardTable">
        <tr>
            <th>no</th>
            <th>title</th>
            <th>writer</th>
            <th>hits</th>
            <th>reg-datetime</th>
        </tr>
        <c:forEach items="${requestScope.list}" var="item">
            <c:set var="eachTitle" value="${fn:replace(fn:replace(item.title, '>', '&gt;'), '<', '&lt;')}" />
            <c:if test="${param.searchType == 1 || param.searchType == 3 || param.searchType == 5}">
                <c:set var="eachTitle" value="${fn:replace(eachTitle, param.searchText, '<mark>'+=param.searchText+='</mark>')}" />
            </c:if>
            <c:set var="eachWriterNm" value="${fn:replace(fn:replace(item.writerNm, '>', '&gt;'), '<', '&lt;')}"/>
            <c:if test="${param.searchType == 4 || param.searchType == 5}">
                <c:set var="eachWriterNm" value="${fn:replace(eachWriterNm, param.searchText, '<mark>'+=param.searchText+='</mark>')}" />
            </c:if>

            <c:set var="pImg" value="DefaultAccountTile.png"/>
            <c:if test="${item.profileImg != null}">
                <c:set var="pImg" value="profile/${item.writer}/${item.profileImg}"/>
            </c:if>

            <tr class="record" onclick="moveToDetail(${item.iboard})">
                <td><c:out value="${item.iboard}"/></td>
                <td>${eachTitle}</td>
                <td><div class="circular--img circular--size40"><img src="/res/img/${pImg}"></div>${eachWriterNm}</td>
                <td><c:out value="${item.hit}"/></td>
                <td><c:out value="${item.rdt}"/></td>
            </tr>
        </c:forEach>
    </table>
    <div class="pageContainer">

        <c:set var="selectedPage" value="${param.page == null? 1 : param.page}" /> <%--setAttribute 하는 것--%>
            <%--        // jstl foreach 문을 items 사용하지 않고 작성--%>
        <c:forEach var="page" begin="1" end="${requestScope.maxPageNum}">

            <div><a href="/board/list?page=${page}&searchText=${param.searchText}&searchType=${param.searchType}&rowCnt${param.rowCnt}"><span class="${selectedPage == page ? 'selected':''}">${page}</span></a></div>

        </c:forEach>

    </div>
    </c:otherwise>
</c:choose>
</div>
<script src="/res/js/board/list.js?v=1"></script>
