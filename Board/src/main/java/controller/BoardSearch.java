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
 * Servlet implementation class BoardSearch
 */
@WebServlet("/search")
public class BoardSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearch() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		 boardlist.jsp의 <form action="./search" method="post">로 인한 post요청		
		 서비스의 메소드를 호출할때 전달할 인자: 사용자가 선택한 검색옵션type, 사용자가 입력한 검색어keyword, 현재페이지번호page가 담겨있어야함 
		 */ 
		
		request.setCharacterEncoding("utf-8");
		// Request에서 서비스로 넘겨야하는 파라미터값 가져오기
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		String page = request.getParameter("page"); // 파라미터 벨류는 일단 String임 (서비스의 인자로 넣을때는 Integer로 파싱한 curpage를 전달)
		int curpage = 1; 
		if(page!=null) curpage = Integer.parseInt(page);
		
		if(type.equals("all")) { response.sendRedirect("boardlist"); return; }
		
		try {
			BoardService boardService = new BoardServiceImpl();
			Map<String,Object> res = boardService.boardSearch(type, keyword, curpage);
			request.setAttribute("res", res);
			request.getRequestDispatcher("boardlist.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}