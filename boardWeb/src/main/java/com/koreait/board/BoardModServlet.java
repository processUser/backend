package com.koreait.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String iboard = req.getParameter("iboard");
        BoardVO vo = new BoardVO();
        vo.setIboard(Integer.parseInt(iboard));

        BoardVO voData = BoardDAO.selBoardDetail(vo);
        req.setAttribute("detailData", voData);

        String path = "/WEB-INF/jsp/mod.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(path);
        rd.forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String striboard = req.getParameter("iboard");
        int iboard = Integer.parseInt(striboard);
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        String writer = req.getParameter("writer");

        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);
        vo.setTitle(title);
        vo.setCtnt(ctnt);
        vo.setWriter(writer);

        int i = BoardDAO.updBoard(vo);
        switch (i) {
            case 1:
                res.sendRedirect("/detail?iboard="+iboard);
                break;
        }
    }
}
