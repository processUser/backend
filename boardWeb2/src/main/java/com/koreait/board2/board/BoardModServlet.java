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

@WebServlet("/board/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(MyUtils.isLogout(req)){
            res.sendRedirect("/user/login");
            return;
        }

        int pk = MyUtils.getParameterInt(req,"pk");
        BoardVO param = new BoardVO();
        param.setIboard(pk);
        param = BoardDAO.selDetail(param);
        if(req.getAttribute("boardData") == null){
            req.setAttribute("boardData", param);
        }
        MyUtils.disForward(req, res, "board/mod");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO uvo =(UserVO) session.getAttribute("loginUser");
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        int iboard = MyUtils.getParameterInt(req, "pk");

        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);
        vo.setWriter(uvo.getIuser());
        vo.setTitle(title);
        vo.setCtnt(ctnt);

        int result = BoardDAO.updBoard(vo);
        if(result == 1) {
            res.sendRedirect("/board/detail?pk="+iboard);
            return;
        } else {
            req.setAttribute("err", "수정에 실패하였습니다.");
            req.setAttribute("boardData", vo);
            doGet(req,res);
            return;
        }
    }
}
