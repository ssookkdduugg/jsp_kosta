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
 * Servlet implementation class AccountInfo
 */
@WebServlet("/accountInfo")
public class AccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInfo() {
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
		String id = request.getParameter("id"); // form태그의 name=id를 지정하여 사용자가 입력한 계좌번호를 request에서 꺼내서 변수에 담는다
		HttpSession session = request.getSession();
		Account acc = (Account)session.getAttribute(id); // 세션에 그 id벨류를 저장한다(사용자별로 모든페이지 이동시에도 공유되도록)
		
		// 3. 응답페이지 위임
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		if(acc!=null) {
			request.setAttribute("acc", acc);
			request.setAttribute("page", "accountinfo");
			
		} else {
			request.setAttribute("err", "계좌번호가 틀립니다.");
			request.setAttribute("page", "error");
		}
		dispatcher.forward(request, response);
	}

}
