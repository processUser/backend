package com.user;

import com.MyUtils;
import com.dao.UserDAO;
import com.user.model.LoginResult;
import com.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.playView("user/login", "로그인", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        entity.setUpw(upw);



        LoginResult result = UserDAO.login(entity);

        res.sendRedirect("/board/list");
    }
}
