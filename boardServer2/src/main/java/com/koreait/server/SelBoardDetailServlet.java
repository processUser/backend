package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/one")
public class SelBoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // ?iboard=3
        int iboard = Integer.parseInt(req.getParameter("iboard"));
        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);

        res.setContentType("text/plain;charset=UTF-8");

        Gson gson = new Gson();
        String json = gson.toJson(BoardDAO.selBoard(vo));
        PrintWriter out = res.getWriter();
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
