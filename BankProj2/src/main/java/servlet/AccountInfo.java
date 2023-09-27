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
@WebServlet("/accountinfo")
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
		// 2-1. 사용자가 form에 입력한 계좌번호 가져오기
		String id = request.getParameter("id");
		// 2-2. 계좌번호를 이용하여 세션에서 특정계좌를 찾아 Account객체에 담는다
		HttpSession session = request.getSession();
		Account acc = (Account)session.getAttribute(id);
		// 3. 계좌찾기에 성공했을시 request에 계좌를 담아서 accountinfo.jsp로 응답을 위임한다
		RequestDispatcher dispatcher = request.getRequestDispatcher("accountinfo.jsp");
		if(acc!=null) {
			request.setAttribute("acc", acc);
		} else {
			request.setAttribute("err", "계좌번호가 틀립니다.");
			dispatcher = request.getRequestDispatcher("error.jsp");
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
		
		dispatcher = req.getRequestDispatcher("accountinfoform.jsp");
		dispatcher.forward(req, resp);
	}

}
