package board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String choice = req.getParameter("choice");
        String search = req.getParameter("search");

        List<BoardVO> list;

        if(choice==null&&search==null||search.equals("")) {
            list = BoardDAO.selBoardList(); // list 객체 주소값이 리턴된다.
        } else {
            list = BoardDAO.searchBoard(choice, search); // list 객체 주소값이 리턴된다.
        }
        req.setAttribute("listData", list);

        // WEB-INF > .jsp 접근 하기 ==>
        String path = "WEB-INF/jsp/list.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(path);
        rd.forward(req, res);
        // <====
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String choice = req.getParameter("choice");
        String search = req.getParameter("search");

    }
}
