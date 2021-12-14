package com.koreait.basic.user;

import com.koreait.basic.Utils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/user/profile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = "프로필";
        req.setAttribute("subPage", "user/profile");
        Utils.displayView(title, "user/myPage", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = Utils.getloginUserPk(req);
        int maxSize = 10_485_760; //1024 * 1024 *10
        ServletContext application = req.getServletContext();
//                            //저장 위치 : 서버 경로 + /res/img/profile/ + loginUserPk
//        String targetPath = req.getContextPath() + "/res/img/profile/" + loginUserPk;
        String targetPath = application.getRealPath("/res/img/profile/" + loginUserPk);
        //폴더 자동 생성
        File file = new File(targetPath);
        file.mkdir();

        System.out.println("targetPath : "+targetPath);

        // new DefaultFileRenamePolicy() 는 a 파일이 있는경우 a파일을 a1 으로 바꾼후 저장 해줌
        MultipartRequest mr = new MultipartRequest(req, targetPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
        Enumeration files = mr.getFileNames();

        String fileNm = (String) files.nextElement();
        System.out.println("fileNm : "+ fileNm);
    }
}
