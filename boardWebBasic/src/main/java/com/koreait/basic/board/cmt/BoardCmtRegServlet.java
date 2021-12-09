package com.koreait.basic.board.cmt;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardCmtEntity;
import com.koreait.basic.dao.BoardCmtDAO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/reg")
public class BoardCmtRegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        int icmt = Utils.getParameterInt(req,"icmt");
        String ctnt = req.getParameter("ctnt");
        int writer = Utils.getloginUserPk(req);

        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(iboard);
        param.setCtnt(ctnt);
        param.setWriter(writer);
        int result =0;
        if(icmt == 0){
            result = BoardCmtDAO.insBoardCmt(param);
        } else {
            param.setIcmt(icmt);
            result = BoardCmtDAO.updBoardCmt(param);
        }

        res.sendRedirect("/board/detail?nohits=1&iboard="+iboard);
    }
}
