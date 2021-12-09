<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/join" method="post">
        <label>아이디<input type="text" name="uid"></label>
        <label>비밀번호<input type="password" name="upw"></label>
        <label>비밀번호확인<input type="password" name="reUpw"></label>
        <label>이름<input type="text" name="nm"></label>
        <select name="gender">
            <option value="0">성별</option>
            <option value="1">남자</option>
            <option value="2">여자</option>
            <option value="3">선택안함</option>
        </select>
        <div>
            <input type="submit" value="가입">
            <input type="button" value="취소">
        </div>
    </form>
</div>
