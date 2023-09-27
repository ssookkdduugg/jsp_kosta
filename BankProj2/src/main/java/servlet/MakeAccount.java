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
@WebServlet("/makeaccount")
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
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 2-1. request에 담긴 form사용자입력값을 가져온다
		String id= request.getParameter("id");
		String name= request.getParameter("name");
		Integer money= Integer.parseInt(request.getParameter("money"));
		String type= request.getParameter("type");
		String grade= request.getParameter("grade");
		if(grade==null) grade="";
		// 2-2. 꺼낸 데이터로 Account객체 생성하고 세션에 담는다
		Account acc = new Account(id,name,money,type,grade);
		HttpSession session = request.getSession();
		session.setAttribute(id, acc); // 키를 변수id로 한다
		// 2-3. 생성된 Account객체를 request에도 담는다
		request.setAttribute("acc", acc);
		
		// 3. 응답페이지를 위임한다
		RequestDispatcher dispatcher = request.getRequestDispatcher("accountinfo.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = null;

		// 로그인되지 않은 상태에서 '계좌개설'메뉴 버튼을 눌렀다면
		HttpSession session = request.getSession();
		if(session.getAttribute("id")==null) {
			request.setAttribute("err", "먼저 로그인을 해주세요");
			request.getRequestDispatcher("error.jsp").forward(request, response); // 메서드 체이닝(위임할 페이지 지정 및 위임까지 이어 작성)
			return;
		}
		
		dispatcher = request.getRequestDispatcher("makeaccount.jsp"); 
		// 응답페이지 위임
		dispatcher.forward(request, response);
	}
}