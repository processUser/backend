package com.koreait.board2.board;

import com.koreait.board2.DbUtils;
import com.koreait.board2.MyUtils;
import com.koreait.board2.model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int iboard = MyUtils.intParameter(req, "num");


        BoardVO param = new BoardVO();
        param.setIboard(iboard);
        BoardVO vo = BoardDAO.selBoard(param);

        req.setAttribute("prevBoard",BoardDAO.prevBoard(iboard));
        req.setAttribute("nextBoard",BoardDAO.nextBoard(iboard));
        req.setAttribute("boardDetail", vo);

        MyUtils.urlForward(req, res, "/board/detail");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
