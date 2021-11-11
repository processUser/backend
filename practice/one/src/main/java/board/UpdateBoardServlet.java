package board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/upd")
public class UpdateBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String striboard = request.getParameter("iboard");
        int iboard = Integer.parseInt(striboard);
        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);
        request.setAttribute("boardVo",BoardDAO.selBoard(vo));

        String path = "WEB-INF/jsp/update.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardVO vo = new BoardVO();
        vo.setIboard(Integer.parseInt(request.getParameter("iboard")));
        vo.setTitle(request.getParameter("title"));
        vo.setCtnt(request.getParameter("ctnt"));
        vo.setWriter(request.getParameter("writer"));

        int i = BoardDAO.updBoard(vo);
        switch (i){
            case 1:
                response.sendRedirect("/detail?iboard="+vo.getIboard());
        }
    }
}
