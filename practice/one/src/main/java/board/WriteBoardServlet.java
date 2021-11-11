package board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/write")
public class WriteBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = "WEB-INF/jsp/write.jsp";
        req.getRequestDispatcher(path).forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardVO vo = new BoardVO();
        vo.setTitle(req.getParameter("title"));
        vo.setCtnt(req.getParameter("ctnt"));
        vo.setWriter(req.getParameter("writer"));
        int i = BoardDAO.insertBoard(vo);
        switch (i){
            case 1:
                res.sendRedirect("/list");
                break;
            default:
                res.sendRedirect("/write");
                break;        }
    }
}
