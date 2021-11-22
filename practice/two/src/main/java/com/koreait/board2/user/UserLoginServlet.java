package com.koreait.board2.user;

import com.koreait.board2.MyUtils;
import com.koreait.board2.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.urlForward(req, res,"/user/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserVO param = new UserVO();
        param.setUid(uid);
        param.setUpw(upw);

        UserVO vo = UserDAO.selUser(param);
        if ( vo != null){
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", vo);
//            MyUtils.urlForward(req, res, "/board/list");
            res.sendRedirect("/board/list");
        } else {
            req.setAttribute("err", "아이디 / 비밀번호를 확인해주세요");
            doGet(req,res);
        }
    }
}
