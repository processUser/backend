<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/join" method="post" id="frm" onsubmit="return joinChk();">
        <%-- required 는 하나의 값이 있는지 체크한다. --%>
        <input type="text" name="uid" placeholder="id" required>
        <input type="password" name="upw" placeholder="password" required>
        <input type="password" id="reupw" placeholder="password comfirm" required>
        <input type="text" name="nm" placeholder="name" required>
        <label ><input type="radio" value="1" name="gender">male</label>
        <label ><input type="radio" value="2" name="gender">female</label>
        <label ><input type="radio" value="3" name="gender"></label>
        <div>
            <input type="submit" value="join">
            <input type="reset" value="reset">
        </div>
    </form>
</div>
<script src="/res/js/user/join.js"></script>