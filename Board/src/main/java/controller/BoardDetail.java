package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Board;
import dto.Member;
import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardDetail
 */
@WebServlet("/boarddetail")
public class BoardDetail extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      // 인코딩, 파라미터값 가져오기, 
      /* cf.
       boardlist.jsp에서 글제목 클릭시 글상세페이지로 넘어가도록 해두었음
       <td><a href="boarddetail?num=${board.num }">${board.subject }</a></td>
       */
      
      request.setCharacterEncoding("utf-8");
      Integer num = Integer.parseInt(request.getParameter("num"));
      
      
      try {
         BoardService boardService = new BoardServiceImpl();
         Board board = boardService.boardDetail(num);
         request.setAttribute("board", board);
         HttpSession session = request.getSession();
         Member member = (Member)session.getAttribute("user");
         if(member!=null) {
         Boolean isLike = boardService.isBoardLike(member.getId(), num);
         request.setAttribute("select", isLike);
         }
         request.getRequestDispatcher("detailform.jsp").forward(request, response);
         
      } catch (Exception e) {
         e.printStackTrace();
         request.setAttribute("err", "글 상세 조회 실패");
         request.getRequestDispatcher("error.jsp").forward(request, response);
      }
   }
}