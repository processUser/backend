<%@ page import="com.koreait.board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% BoardVO vo =(BoardVO) request.getAttribute("detailData"); %>
    <title>수정</title>
</head>
<body>
    <form action="/mod" method="post" id="frm">
        <input type="hidden" name="iboard" value="<%= vo.getIboard()%>">
        <div>제목 <input type="text" name="title" value="<%= vo.getTitle()%>"></div>
        <div>작성자 <input type="text" name="writer" value="<%= vo.getWriter()%>"></div>
        <div>내용 <textarea name="ctnt"  cols="30" rows="10" ><%= vo.getCtnt()%></textarea></div>
        <div>
            <input type="submit" value="저장">
            <input type="reset" value="초기화">
            <input type="button" value="모두초기화" onclick="removeAll();">
        </div>
    </form>
    <script>
        function removeAll(){
            var frm = document.querySelector('#frm');
            if(frm){
                frm.title.value=''
                frm.ctnt.value=''
                frm.writer.value=''
            }
        }
    </script>
</body>
</html>