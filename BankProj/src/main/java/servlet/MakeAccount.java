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

/**
 * Servlet implementation class MakeAccount
 */
@WebServlet("/makeAccount")
public class MakeAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id"); // 계좌번호
		String name = request.getParameter("name"); // 이름
		Integer money = Integer.parseInt(request.getParameter("money")); // 입금액
		String type = request.getParameter("type"); // 계좌종류
		String grade = request.getParameter("grade"); // 등급
		if(grade==null) grade="";
		
		// 데이터 정보를 갖고 Account객체를 생성 후 http세션에 넣는다 (페이지마다 공유되게 하기 위해서)
		Account acc = new Account(id, name, money, type, grade);
		
		// (1) 세션에 담는다
		HttpSession session = request.getSession();
		session.setAttribute("id", acc);
		
		// (2) 리퀘스트에 담는다
		request.setAttribute("acc", acc); // acc는 객체임
		
		// 처리 후 넘어갈 응답페이지 설정
		request.setAttribute("page", "accountinfo"); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
		
		
		// *** 세션에도 넣고 요청객체에도 데이터를 넣는다. 
		
	}

}
