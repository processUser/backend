package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ins")
public class InsBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 양이 적고 노출가능할때 get 사용
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 양이 많고 노출되면 안될때 post 사용.
        // 제목, 내용, 글쓴이 값이 넘어온다.
        String json = Utils.getJson(req);

        Gson gson = new Gson();
        BoardVO vo = gson.fromJson(json, BoardVO.class);

        int result = BoardDAO.insBoard(vo);


        ResultVO rvo = new ResultVO();
        rvo.setResult(result);
        String rvoJson = gson.toJson(rvo);

        res.setContentType("text/plain;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        PrintWriter out = res.getWriter();  // 응답 json 형대로 값을 준다
        out.println(rvoJson);
    }
}
