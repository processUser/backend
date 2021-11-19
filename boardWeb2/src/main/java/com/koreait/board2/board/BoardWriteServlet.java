package com.koreait.board2.board;

import com.koreait.board2.MyUtils;
import com.koreait.board2.model.BoardVO;
import com.koreait.board2.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //로그인 되어 있지 않으면 "/board/list"로 주소 이동
        HttpSession session = req.getSession();
        UserVO loginUser = (UserVO)session.getAttribute("loginUser");
        if( loginUser == null){
            res.sendRedirect("/board/list");
            return;
        }
        //로그인 되어 있으면 아래 소스 실행이 되도록
        MyUtils.disForward(req, res, "board/write");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        String writer = req.getParameter("iuser");
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        HttpSession session = req.getSession();
        UserVO loginUser = (UserVO)session.getAttribute("loginUser");

        BoardVO param = new BoardVO();
//        param.setWriter(Integer.parseInt(writer));
        param.setWriter(loginUser.getIuser());
        param.setTitle(title);
        param.setCtnt(ctnt);

        // TODO : 에러 예외처리
        int result = BoardDAO.insBoard(param);
        switch (result){
            case 1:
                res.sendRedirect("/board/list");
                break;
            case 0:
                req.setAttribute("err", "글 등록에 실패하였습니다.");
                req.setAttribute("writeData", param);
                doGet(req,res);
                break;
        }
    }
}
