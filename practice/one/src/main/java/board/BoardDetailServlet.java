package board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String striboard = req.getParameter("iboard");
        BoardVO param = new BoardVO();
        param.setIboard(Integer.parseInt(striboard));

        BoardVO vo = BoardDAO.selBoard(param);
        int prevIboard = BoardDAO.selPrevBoard(param); // 이전글 - 최신글 가져오기
        int nextIboard = BoardDAO.selNextBoard(param); // 다음글 - 이전작성 글 가져오기

        req.setAttribute("boardData",vo);
        req.setAttribute("prevIboard", prevIboard);
        req.setAttribute("nextIboard", nextIboard);

        String path = "WEB-INF/jsp/detail.jsp";
        req.getRequestDispatcher(path).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
