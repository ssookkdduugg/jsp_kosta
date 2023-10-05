package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Board;
import dto.Member;
import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardModify
 */
@WebServlet("/boardmodify")
public class BoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 detailform.jsp에서 아래요소를 눌렀을때의 get요청
		 <a href="boardmodify?num=${board.num}">수정</a>&nbsp;&nbsp;
		 */
		
		request.setCharacterEncoding("utf-8");
		Integer num = Integer.parseInt(request.getParameter("num"));
		
		try {
			BoardService boardService = new BoardServiceImpl();
			Board board = boardService.boardDetail(num);
			request.setAttribute("board", board);
			request.getRequestDispatcher("modifyform.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 수정 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 modifyform.jsp에서 form이 제출되었을때의 post요청
		 */
		
		
		request.setCharacterEncoding("utf-8");
		
		/* 파일 업로드 시작 */
		String uploadPath = request.getServletContext().getRealPath("upload"); // 파일을 업로드할 폴더를 정의
		int size = 10*1024*1024; // 최대 파일 크기를 10MB로 설정
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy()); //form태그의 속성 enctype="multipart/form-data" 때문에 그냥 HttpRequest객체로는 불충분하여 MultipartRequest객체를 써야함
		/* 파일 업로드 끝 */
		Integer num = Integer.parseInt(multi.getParameter("num"));
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		String orgFileName = multi.getOriginalFileName("file");
		
		// 객체에 값 세팅(witer필드는 세팅할 필요없음)
		Board board = new Board();
		board.setNum(num);
		board.setSubject(subject);
		board.setContent(content);
		board.setFileurl(orgFileName);
		
		// 서비스의 메서드 호출 후 응답페이지 지정
		try {
			BoardService boardService = new BoardServiceImpl();
			boardService.boardModify(board);
			response.sendRedirect("boarddetail?num="+board.getNum());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 수정 오류");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}