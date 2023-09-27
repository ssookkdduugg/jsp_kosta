package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
 * Servlet implementation class AllAccountInfo
 */
@WebServlet("/allaccountinfo")
public class AllAccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllAccountInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;

		// 로그인되지 않은 상태에서 '전체계좌조회'메뉴 버튼을 눌렀다면
		HttpSession session = request.getSession();
		if(session.getAttribute("id")==null) {
			request.setAttribute("err", "먼저 로그인을 해주세요");
			request.getRequestDispatcher("error.jsp").forward(request, response); // 메서드 체이닝(위임할 페이지 지정 및 위임까지 이어 작성)
			return;
		}
		
		// 1. Account들을 담을 리스트를 생성
		List<Account> accs = new ArrayList<>();
		
		// 2. 세션에 있는 모든 키 목록을 가져온다(반환타입이 Enumeration<String>이다)
		session = request.getSession();
		Enumeration<String> e = session.getAttributeNames();
		
		// 3.키 하나하나의 벨류를 추출하여 리스트에 담는 작업을 반복한다
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			if(name.equals("member")||name.equals("id")) continue; 
			/* 
			 즉 세션에 담긴 키들 중 Account랑 짝지은 키(계좌번호를 의미하는 키)가 아닌 경우를 걸러주는것(세션이란 한 공간을 쓰기 때문에 구분해야함)
			 회원가입과 로그인시에는 세션에 키를 각각 member, id로 하여 벨류를 담을 것이므로 그 경우를 걸러주는것 
			 Join.java 코드를 참고
			 HttpSession session = request.getSession();
			 Member member = new Member(id, name, password, email, address);
			 session.setAttribute("member", member); 
			 */
			
			Account acc= (Account)session.getAttribute(name);
			accs.add(acc);
		}
		
		if(accs.size()>0) {
			request.setAttribute("accs", accs);
			dispatcher = request.getRequestDispatcher("allaccountinfo.jsp");
		} else { // accs.size==0
			request.setAttribute("err", "개설된 계좌가 없습니다.");
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}

}
