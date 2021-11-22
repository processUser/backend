<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/join.css">
</head>
<body>
    <div id="content">
        <div id="header">
            <h1>회원가입</h1>
        </div>
        <div id="joinForm">
            <form action="/user/join" method="post">
                <div>
                    <label for="uid">아이디</label>
                    <input type="text" class="text" name="uid" id="uid">
                    <span></span>
                </div>
                <div>
                    <label for="upw">비밀번호</label>
                    <input type="password" class="text" name="upw" id="upw">
                    <span></span>
                </div>
                <div>
                    <label for="upwCheck">비밀번호 확인</label>
                    <input type="password" class="text" id="upwCheck">
                    <span></span>
                </div>
                <div>
                    <label for="nm">이름</label>
                    <input type="text" class="text" name="nm" id="nm">
                    <span></span>
                </div>
                <div>
                    <label for="gender">성별</label>
                    <select name="gender" id="gender">
                        <option value="0" selected>성별</option>
                        <option value="1">남자</option>
                        <option value="2">여자</option>
                        <option value="3">선택안함</option>
                    </select>
                    <span></span>
                </div>
                <div>
                    <input type="submit" class="submit" value="회원가입">
                    <input type="button" class="submit" value="취소">
                </div>
            </form>
        </div>
    </div>
</body>
</html>