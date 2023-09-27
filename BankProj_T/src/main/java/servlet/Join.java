package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;

/**
 * Servlet implementation class Join
 */
@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
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
		String name= request.getParameter("name"); //form에 입력된 이름
		String password = request.getParameter("password"); 
		String email = request.getParameter("email"); 
		String address = request.getParameter("address"); 
		
		HttpSession session = request.getSession();
		Member member = new Member(id, name, password, email, address);
		session.setAttribute("member", member);
		
		// 3. 응답페이지 위임
		request.setAttribute("page", "login"); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
		
	}

}
