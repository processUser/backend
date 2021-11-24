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

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO userParam = (UserVO) session.getAttribute("loginUser");
        //
        if (userParam == null){
            res.sendRedirect("/user/login");
            return;
        }
        MyUtils.urlForward(req, res, "/board/write");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 세션 값 가져오기
        HttpSession session = req.getSession();
        UserVO userParam = (UserVO) session.getAttribute("loginUser");

        String title = MyUtils.StringReplace(req.getParameter("title"));
        String ctnt = req.getParameter("ctnt");

        BoardVO param = new BoardVO();
        param.setTitle(title);
        param.setCtnt(ctnt);
        param.setWriter(userParam.getIuser());

        int result = BoardDAO.insBoard(param);
        switch (result){
            case 1:
                res.sendRedirect("/board/list");
                break;
            default:
                req.setAttribute("writeItem", param);
                req.setAttribute("err", "글작성에 실패하였습니다.");
                MyUtils.urlForward(req, res, "/board/write");
                break;
        }
    }
}
