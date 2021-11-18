package com.koreait.board2.board;

import com.koreait.board2.model.BoardVO;
import com.koreait.board2.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO uvo = (UserVO) session.getAttribute("loginUser");
        if(uvo == null){
            //res.sendRedirect("/board/detail?err=1");
            req.setAttribute("err", "로그인해주세요");
            req.getRequestDispatcher("/board/detail").forward(req, res);
            return;
        }

        BoardVO bvo = new BoardVO();
        bvo.setIboard(Integer.parseInt(req.getParameter("iboard")));
        bvo.setWriter(uvo.getIuser());

        int result = BoardDAO.delBoard(bvo);
        if(result == 0){
            req.setAttribute("err", "자신이 작성한 글이 아닙니다.");
            req.getRequestDispatcher("/board/detail").forward(req, res);
            return;
        }
        res.sendRedirect("/board/list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
