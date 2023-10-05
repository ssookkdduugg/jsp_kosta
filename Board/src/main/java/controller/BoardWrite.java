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
 * Servlet implementation class BoardWrite
 */
@WebServlet("/boardwrite")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get방식 요청 방법(1) .jsp에서 버튼을 눌렀을때 외에
		// get방식 요청 방법(2) url로 다이렉트로 접근할때도 로그인 여부를 검사하여 로그인안되어있다면 접근불가하도록 처리
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("writeform.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		
		컨트롤러에서 하는 일 : 화면에서 받은 값을 객체에 담음
		
		 JspExam9의 FileUpload.java 참고
		 <form action="fileUpload" method="post" enctype="multipart/form-data">가 제출되어 넘어오면
		 기존의 Request객체로는 충분치 않고 MultipartRequest객체를 생성해서 받아야함
		 
		 즉, multipart/form-data 요청을 처리하기 위해선 라이브러리에서 제공하는 MultipartRequest 클래스를 이용!
		 */
		
		request.setCharacterEncoding("utf-8");
		
		/* 파일 업로드 시작 */
		String uploadPath = request.getServletContext().getRealPath("upload"); // 파일을 업로드할 폴더를 정의
		/*
		 대입연산자 우측은 상대경로로, .metadata 안의 server.core 안의 upload 폴더이다. (톰캣 서버 내부에 파일을 업로드하게되는것)
		 그래서 프로젝트를 clean하게 되면 내부파일이 초기화된다.
		 
		 절대경로로 지정하는 방법은 대입연산자 우측을 "C://upload" 이런식으로 작성하여서 톰캣 서버가 설치된 시스템에 파일을 업로드하게 하는 것이다.
		 
		 참고로 DB에는 파일자체가 아니라 파일명을 저장한다
		 * */
		
		int size = 10*1024*1024; // 최대 파일 크기를 10MB로 설정
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
		/* 파일 업로드 끝 */
		
		
		String subject = multi.getParameter("subject"); 
		String content = multi.getParameter("content");
		String orgFileName = multi.getOriginalFileName("file"); // 파일 이름 가져오기
		
		// 로그인된 유저 정보 가져오기
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");
		String writer = member.getId();
		
		// 객체에 값 세팅 (게시글번호num은 오토인크리멘트되는 값이므로 넣어줄필요도 없고 넣을수도없다)
		Board board = new Board();
		board.setSubject(subject);
		board.setContent(content);
		board.setFileurl(orgFileName);
		board.setWriter(writer);
		
		// 서비스의 메서드 호출 후 응답페이지 지정
		try {
			BoardService boardService = new BoardServiceImpl();
			boardService.boardWrite(board);
//			response.sendRedirect("boardlist");
			response.sendRedirect("boarddetail?num="+board.getNum()); // 글작성완료 후 게시글목록페이지가 아니라 게시글상세페이지로 이동시키기
			/*
			 매퍼Board.xml에서 insertBoard에서 useGeneratedKeys와 keyProperty를 사용하였기 때문에
			 글작성완료 후 글상세페이지로 바로 이동할때 Board객체의 num필드에 담긴 게시글넘버(마이바티스가 자동으로 넣어준 DB에서 인크리멘트되어 삽입된값)를 바로 활용할 수 있는것이다.
			 */
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}