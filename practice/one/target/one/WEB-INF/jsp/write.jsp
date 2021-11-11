<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글쓰기</title>
</head>
<body>
    <form action="/write" method="post">
        제목 <input type="text" name="title" >
        작성자 <input type="text" name="writer" >
        <textarea name="ctnt" cols="30" rows="10"></textarea>
        <input type="submit" value="저장">
        <a href="/list"><input type="button" value="취소"></a>
    </form>
</body>
</html>
