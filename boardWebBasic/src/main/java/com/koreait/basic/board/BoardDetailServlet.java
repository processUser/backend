package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardCmtDTO;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardHeartEntity;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.dao.BoardCmtDAO;
import com.koreait.basic.dao.BoardDAO;
import com.koreait.basic.dao.BoardHeartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int nohits = Utils.getParameterInt(req, "nohits");
        int iboard = Utils.getParameterInt(req, "iboard");
        BoardDTO param = new BoardDTO();
        param.setIboard(iboard);

        BoardVO data = BoardDAO.selBoardDetail(param);

        BoardCmtDTO cmtParam = new BoardCmtDTO();
        cmtParam.setIboard(iboard);
        req.setAttribute("cmtList", BoardCmtDAO.selBoardCmtList(cmtParam));

        int loginUserPk = Utils.getloginUserPk(req);
        if(loginUserPk > 0) { //로그인 되어 있으면 좋아요 했나? 안 했나? 정보 가져오기.
            BoardHeartEntity bhParam = new BoardHeartEntity();
            bhParam.setIuser(loginUserPk);
            bhParam.setIboard(iboard);
            req.setAttribute("isHeart", BoardHeartDAO.selIsHeart(bhParam));
        }
        //로그인 한 사람이 pk 값과 data에 들어있는 writer값이 다르거나,
        //로그인이 안 되어 있으면 hit값을 올려주기
        if(data.getWriter() !=  loginUserPk && nohits != 1){
            BoardDAO.updBoardHitUp(param);
            data = BoardDAO.selBoardDetail(param);
        }

        req.setAttribute("data", data);
        Utils.displayView("게시판 상세보기", "board/detail", req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
