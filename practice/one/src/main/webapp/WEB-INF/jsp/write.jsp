<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글쓰기</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/write.css">
</head>
<body>
    <div id="content">
        <div id="header">
            <h1>글쓰기</h1>
        </div>
        <div id="writeSection">
            <form action="/write" method="post">
                <div class="writeTitle">
                    <input type="text" name="title" placeholder="제목을 입력하세요">
                </div>
                <div>
                    작성자 <input type="text" name="writer" >
                </div>
                <textarea name="ctnt" cols="30" rows="10"></textarea>
                <div class="writeButton">
                    <input type="submit" value="저장">
                    <a href="/list"><input type="button" value="취소"></a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
