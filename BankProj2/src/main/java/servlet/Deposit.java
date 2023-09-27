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
@WebServlet("/deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2-1. request에 담긴 form사용자 입력값을 가져온다
		String id = request.getParameter("id");
		Integer money = Integer.parseInt(request.getParameter("money"));
		// 2-2. 세션에 담겨있는 Account객체들 중에서 계좌번호id(키)를 통해 특정 acc(벨류)를 찾아 새로운 Account객체를 만든다(담는다)
		HttpSession session = request.getSession();
		Account acc = (Account)session.getAttribute(id);
		
		// 3. 응답 페이지 위임
		RequestDispatcher dispatcher = request.getRequestDispatcher("accountinfo.jsp");
		if(acc!=null) {
			acc.deposit(money); // 세션에서 찾아둔 특정계좌에 입금처리를 하고 
			request.setAttribute("acc", acc); // 응답페이지로 넘길 request객체에 키를 "acc", 벨류를 (입금처리완료된)Account객체로 세팅한다
		} else {
			request.setAttribute("err", "계좌번호가 틀립니다.");
			request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;

		// 로그인되지 않은 상태에서 '계좌조회'메뉴 버튼을 눌렀다면
		HttpSession session = req.getSession();
		if(session.getAttribute("id")==null) {
			req.setAttribute("err", "먼저 로그인을 해주세요");
			req.getRequestDispatcher("error.jsp").forward(req, resp); // 메서드 체이닝(위임할 페이지 지정 및 위임까지 이어 작성)
			return;
		}
		
		dispatcher = req.getRequestDispatcher("deposit.jsp");
		dispatcher.forward(req, resp);
	}

}
