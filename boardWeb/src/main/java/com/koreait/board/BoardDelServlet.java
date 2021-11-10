package com.koreait.board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String strIboard = req.getParameter("iboard");
        System.out.println("strIboard : "+strIboard);
        int iboard = Integer.parseInt(strIboard);
        int i = BoardDAO.delBoard(iboard);
        switch (i){
            case 1:
//                req.getRequestDispatcher("/list").forward(req,res); // req 나 res이 유지된다.
                                                                        // get에서 사용시 get으로, post에서 사용시 post로 적용
                res.sendRedirect("/list"); // req 나 res이 새로 생성된다.
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
