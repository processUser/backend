<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/board/regmod" method="post">
        <label >제목: <input type="text" name="title"></label>
        <label >내용: <textarea name="ctnt"></textarea></label>
        <div>
            <input type="submit" value="등록">
            <input type="reset" value="초기화">
        </div>
    </form>
</div>
