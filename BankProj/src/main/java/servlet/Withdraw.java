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
 * Servlet implementation class Deposit
 */
@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
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
		String id = request.getParameter("id"); //form에 입력된 계좌번호
		Integer money = Integer.parseInt(request.getParameter("money")); // form에 입력된 출금액
		HttpSession session = request.getSession();
		Account acc = (Account)session.getAttribute(id);
		
		
		// 3. 응답페이지 위임
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp"); // 메인페이지로 이동시키되
		if(acc!=null) {
			acc.withdraw(money);
			request.setAttribute("acc", acc);
			request.setAttribute("page", "accountinfo"); // URI뒤로 붙을 page파라미터값을 문자열로 넘겨서 인클루드 되도록 함(인클루드처리는 jsp가 한다)
			
		} else {
			request.setAttribute("err", "계좌번호가 틀립니다");
			request.setAttribute("page", "error");
		}
		dispatcher.forward(request, response);
	}

}
