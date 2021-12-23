<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/res/css/board/list.css">
<div>
    <div>
        <form id="searchFrm">
            <div>
                <select name="searchType">
                    <option value="1" ${param.searchType == 1 ? 'selected' : ''}>제목</option>
                    <option value="2" ${param.searchType == 2 ? 'selected' : ''}>내용</option>
                    <option value="3" ${param.searchType == 3 ? 'selected' : ''}>제목/내용</option>
                    <option value="4" ${param.searchType == 4 ? 'selected' : ''}>글쓴이</option>
                    <option value="5" ${param.searchType == 5 ? 'selected' : ''}>전체</option>
                </select>
                <input type="search" name="searchText" value="${param.searchText}">
                <input type="submit" value="검색" id="btn">

                나타내는 행 수:
                <select name="rowCnt">
                    <c:forEach var="cnt" begin="5" end="30" step="5">
                        <option value="${cnt}" ${param.rowCnt == cnt ? 'selected' : ''}>${cnt}개</option>
                    </c:forEach>
                </select>
            </div>

        </form>
    </div>

<%--  글 리스트  --%>
<div class="tableWarp"></div>


</div>
<script src="/res/js/board/list2.js?v=3"></script>
