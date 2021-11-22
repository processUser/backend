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

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
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

        BoardVO boardParam = new BoardVO();
        boardParam.setIboard(iboard);
        boardParam.setWriter(userParam.getIuser());

        int result = BoardDAO.delBoard(boardParam);
        switch (result){
            case 1:
                res.sendRedirect("/board/list");
                break;
            default:
                req.setAttribute("err", "삭제실패");
                req.getRequestDispatcher("/board/detail").forward(req,res);
                return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
