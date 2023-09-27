package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Account;
import dto.Member;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    // 로그인 버튼을 눌렀을때 로그인 페이지로 이동하도록 응답페이지 위임처리
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    	dispatcher.forward(request, response);
	}
    
    // 로그인 폼에서 확인을 눌렀을때
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 2-1. 요청객체로부터 정보 꺼내기
		String id = request.getParameter("id"); //form에 입력된 아이디
		String password = request.getParameter("password"); 
		
		// 2-2. 해당 ip의 세션에서(회원가입을 통해 저장되어있을) 키member를 이용하여 벨류인 Member객체를 가져온다 cf. memeber가 여러개일수는 없다(하나의 키)
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		RequestDispatcher dispatcher = null;
		// (1) 회원가입이 되어있지 않을때
		if(member==null) {
			request.setAttribute("err", "회원가입이 되어있지 않습니다.");
			dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		// (2) 아이디 비교
		if(member.getId().equals(id)==false) {
			request.setAttribute("err", "아이디가 틀립니다.");
			dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		// (3) 비밀번호 비교
		if(member.getPassword().equals(password)==false) {
			request.setAttribute("err", "비밀번호가 틀립니다.");
			dispatcher = request.getRequestDispatcher("error.jsp");
			return;
		}
		
		// 정상 로그인처리
		session.setAttribute("id", id); // 다른 페이지에서도 로그인됐음을 알 수 있게, 아이디를 가지고 사용할 수 있게 '세션'에 아이디를 넣는다
		// 3. 응답페이지 위임
		dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
		
		
		/*
			회원가입시 세션에 member를 키로 하고 Member객체를 저장
			로그인시 id를 키로 하고 변수에 담은 request에서뽑아낸id실제문자열을 저장
		*/
		
	}
}
