package com.koreait.basic.user;

import com.koreait.basic.Utils;
import com.koreait.basic.dao.UserDAO;
import com.koreait.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/password")
public class UserPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = "비밀번호 변경";
        req.setAttribute("subPage", "user/password");
        Utils.displayView(title, "user/myPage", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //현재 비밀번호가 다르다면 > 에러메시지 ('현재 비밀번호를 확인하라')> doGet
        //현재 비밀번호가 같다면 > changedUpw 값으로 비밀번호 변경 > 로그아웃 > 로그인 화면으로
        int iuser = Utils.getloginUserPk(req);
        String upw = req.getParameter("upw");
        String changedUpw = req.getParameter("changedUpw");
        UserEntity entity = new UserEntity();
        entity.setIuser(iuser);
        entity.setUpw(upw);

        UserEntity dbPw = UserDAO.selUser2(entity);

        if(BCrypt.checkpw(upw, dbPw.getUpw())){
            String hashPw = BCrypt.hashpw(changedUpw, BCrypt.gensalt()); //비밀번호 암호화
            entity.setUpw(hashPw);
            int result = UserDAO.updUser(entity);
            if(result == 1){
                HttpSession session = req.getSession();
                session.invalidate();
                res.sendRedirect("/user/login");
            }
        } else {
            req.setAttribute("err", "현재 비밀번호를 확인하라");
            doGet(req, res);
        }

    }
}
