<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h1>${requestScope.title}</h1>
<c:choose>
    <c:when test="${fn:length(requestScope.list) == 0}">
        <div>글이없다</div>
    </c:when>
    <c:otherwise>
        <div>
            <div>
                <table id="boardTable">
                    <colgroup>
                        <col width="20%">
                        <col>
                        <col>
                        <col width="100px">
                        <col>
                    </colgroup>
                    <tr>
                        <th>no</th>
                        <th>title</th>
                        <c:choose>
                            <c:when test="${param.type eq 1}">
                                <th>hit</th>
                            </c:when>
                            <c:otherwise>
                                <th>count</th>
                            </c:otherwise>
                        </c:choose>
                        <th>writer</th>
                        <th>rdt</th>
                    </tr>
                    <c:forEach items="${requestScope.list}" var="item">
                        <c:set var="eachTitle" value="${fn:replace(fn:replace(item.title, '>', '&gt;'), '<', '&lt;')}"/>
                        <c:if test="${param.searchText != null && (param.searchType == 1 || param.searchType == 3 || param.searchType == 5)}">
                            <c:set var="eachTitle" value="${fn:replace(eachTitle, param.searchText, '<mark>' += param.searchText += '</mark>')}" />
                        </c:if>

                        <c:set var="eachWriterNm" value="${item.writerNm}" />
                        <c:if test="${param.searchText != null && (param.searchType == 4 || param.searchType == 5)}">
                            <c:set var="eachWriterNm" value="${fn:replace(eachWriterNm, param.searchText, '<mark>' += param.searchText += '</mark>')}" />
                        </c:if>

                        <tr class="record" onclick="moveToDetail(${item.iboard});">
                            <td>${item.iboard}</td>
                            <td>${eachTitle}</td>
                            <c:choose>
                                <c:when test="${param.type eq 1}">
                                    <td>${item.hit}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${item.cnt}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${eachWriterNm}</td>
                            <td>${item.rdt}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>