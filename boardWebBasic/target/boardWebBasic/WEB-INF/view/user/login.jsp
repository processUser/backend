<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/login" method="post" id="frm">
        <input type="text" name="uid" placeholder="id">
        <input type="password" name="upw" placeholder="password">
        <div>
            <input type="submit" value="login">
        </div>
    </form>
    <div>
        <a href="/user/join">join</a>
    </div>
</div>
<script src="/res/js/user/login.js"></script>