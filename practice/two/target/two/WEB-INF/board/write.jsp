<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h1>글쓰기</h1>
    </div>
    <c:if test="${requestScope.err != null}">
        <div>${requestScope.err}</div>
    </c:if>
    <form action="/board/write" method="post">
        <label for="title">제목</label>
        <input type="text" name="title" id="title" value="${writeItem.title}">
        <textarea name="ctnt" id="" cols="30" rows="10">${writeItem.ctnt}</textarea>
        <div>
            <input type="submit" value="저장">
            <input type="button" value="취소">
        </div>
    </form>
</div>
</body>
</html>