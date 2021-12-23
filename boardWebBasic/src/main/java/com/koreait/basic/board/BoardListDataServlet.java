package com.koreait.basic.board;

import com.google.gson.Gson;
import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/board/listData")
// ajax 통신을 통한 list JSON 파일 전달
public class BoardListDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int searchType = Utils.getParameterInt(req,"searchType", 0);
        String searchText = req.getParameter("searchText");
        int rowCnt = Utils.getParameterInt(req, "rowCnt", 5);
        int page = Utils.getParameterInt(req, "page",1);

        String json = Utils.getJson(req);

        System.out.println(json);
        System.out.println(rowCnt);
        System.out.println(page);

        BoardDTO param = new BoardDTO();
        param.setSearchType(searchType);
        param.setSearchText(searchText);
        // 페이징
        param.setRowCnt(rowCnt);
        param.setPage(page);
        int startIdx = (param.getPage() - 1) * param.getRowCnt(); // 0 / 5 / 10
        param.setStartIdx(startIdx);

        int maxPageNum = BoardDAO.getMaxPageNum(param);
//---------------------------------------------------------------------------
        // ajax 통신 리스트 뿌리기
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        List<BoardVO> list = BoardDAO.selBoardList(param);

        Map<String, Object> map = new HashMap<>();
        map.put("maxPageNum", maxPageNum);
        map.put("list", list);

        Gson gson = new Gson(); // 객체 생성
        PrintWriter pw = res.getWriter();
        pw.print(gson.toJson(map));
        System.out.println("gson.toJson(map) : "+gson.toJson(map));

        pw.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
