package com.user;

import com.MyUtils;
import com.dao.UserDAO;
import com.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/join")
public class joinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.playView("user/join", "회원가입", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");
        String nm = req.getParameter("nm");
        int gender = MyUtils.parameterInt(req, "gender");

        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        entity.setUpw(upw);
        entity.setNm(nm);
        entity.setGender(gender);

        int result = UserDAO.join(entity);

        res.sendRedirect("/user/login");
    }
}
