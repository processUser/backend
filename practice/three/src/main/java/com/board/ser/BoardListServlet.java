package com.board.ser;

import com.MyUtils;
import com.board.model.BoardDTO;
import com.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//        int iboard = MyUtils.parameterInt(req,"iboard");
//        BoardDTO param = new BoardDTO();
//        param.setIboard(iboard);

        req.setAttribute("list", BoardDAO.selBoardList());


     //페이징 처리전까지의 리스트 출력 이거 맞나여..? ;; ㅋㅋ

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
