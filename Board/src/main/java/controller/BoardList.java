package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/boardlist")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DB에서 가져온 데이터를 뷰에 넣어줘야함
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		
		// 파라미터로 ?page를 가져오지 않았을때는 curpage를 1페이지로, 갖고왔을때는 가져온 값을 세팅한다
		int curpage = 1; 
		if(page!=null) curpage = Integer.parseInt(page);
		
		try {
			BoardService boardService = new BoardServiceImpl();			
			Map<String,Object> res = boardService.boardListByPage(curpage);
			request.setAttribute("res", res);
			request.getRequestDispatcher("boardlist.jsp").forward(request, response);
			
			/* cf. BoardServiceImpl의 boardListByPage메서드를 통해 반환받은것은 페이지인포객체와 게시글목록객체이다.
			Map<String, Object> map = new HashMap<>();
			map.put("pageInfo", pageInfo);
			map.put("boardList", boardList);
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}