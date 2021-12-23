package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int searchType = Utils.getParameterInt(req,"searchType", 0);
        String searchText = req.getParameter("searchText");

        int rowCnt = Utils.getParameterInt(req, "rowCnt", 5); // 몇개씩 보여주는지
        int page = Utils.getParameterInt(req, "page",1); // 선택한 페이지

        BoardDTO param = new BoardDTO();
        param.setSearchType(searchType);
        param.setSearchText(searchText);

        // 페이징
        param.setRowCnt(rowCnt);
        param.setPage(page);
        int startIdx = (param.getPage() - 1) * param.getRowCnt(); // 0 / 5 / 10
        param.setStartIdx(startIdx);

        int maxPageNum = BoardDAO.getMaxPageNum(param);

        System.out.println("RowCnt"+param.getRowCnt());
        System.out.println("Page"+param.getPage());
        System.out.println("StartIdx"+param.getStartIdx());
        System.out.println("maxPageNum"+maxPageNum);


        req.setAttribute("maxPageNum", maxPageNum);
        req.setAttribute("list", BoardDAO.selBoardList(param));


        Utils.displayView("게시판", "board/list", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
