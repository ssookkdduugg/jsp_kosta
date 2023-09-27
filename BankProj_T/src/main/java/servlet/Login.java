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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 2. 요청객체로부터 정보 꺼내기
		String id = request.getParameter("id"); //form에 입력된 아이디
		String password = request.getParameter("password"); 
		
		// 세션에 멤버가 있는지 확인하기
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		
		// (1) null이 아니면 즉 회원가입이 되어있다면 아이디와 비밀번호를 비교한다
		if(member!=null) {
			// (2) 아이디 비교
			if(member.getId().equals(id)) {
				// (3) 비밀번호 비교
				if(member.getPassword().equals(password)) {
					// 정상 로그인처리
					session.setAttribute("id", id); // 다른 페이지에서도 로그인됐다고 알 수 있게, 아이디를 가지고 사용할 수 있게 세션에 속성으로 아이디를 넣는다
					request.setAttribute("page", "makeaccount");
				} else {
					request.setAttribute("err", "비밀번호가 틀립니다.");
					request.setAttribute("page", "error");
				}
			} else {
				request.setAttribute("err", "아이디가 틀립니다.");
				request.setAttribute("page", "error");
			}
			
		// 회원가입 되어있지 않다면	
		} else {
			request.setAttribute("err", "회원가입이 되어있지 않습니다.");
			request.setAttribute("page", "error");
		}
		
		// 3. 응답페이지 위임
		dispatcher.forward(request, response);
		
	}

}
