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

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO userParam = (UserVO) session.getAttribute("loginUser");
        //
        if (userParam == null){
            res.sendRedirect("/user/login");
            return;
        }
        int iboard = MyUtils.intParameter(req, "num");

        BoardVO param = new BoardVO();
        param.setIboard(iboard);
        BoardVO vo = BoardDAO.selBoard(param);

        req.setAttribute("boardDetail", vo);

        MyUtils.urlForward(req, res, "/board/update");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO userParam = (UserVO) session.getAttribute("loginUser");
        int iboard = MyUtils.intParameter(req, "num");
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        BoardVO boardParam = new BoardVO();
        boardParam.setIboard(iboard);
        boardParam.setTitle(title);
        boardParam.setCtnt(ctnt);
        boardParam.setWriter(userParam.getIuser());

        int result = BoardDAO.updBoard(boardParam);
        switch (result){
            case 1:
                res.sendRedirect("/board/detail?num="+iboard);
                break;
            default:
                req.setAttribute("err","수정에 실패하였습니다.");
                doGet(req, res);
                break;
        }
    }
}
