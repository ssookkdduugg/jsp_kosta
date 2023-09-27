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

/**
 * Servlet implementation class AllAccountInfo
 */
@WebServlet("/allaccountInfo") // header.jsp에서 <a href="main?allaccountinfo">전체계좌조회</a>
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
		
		// 세션에 있는 모든 계좌들의 정보들을 다 보여주게한다
		
		List<Account> accs = new ArrayList<>();
		HttpSession session = request.getSession();
		
		// 세션의 모든 속성명들(계좌번호id 문자열들의 목록)을 가져와서 그것을 통해 계좌를 불러오고 List에 담는다
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			Account acc = (Account)session.getAttribute(name);
			accs.add(acc);
		}
		
		// 응답페이지 위임
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		if(accs.size()>0) {
			request.setAttribute("accs", accs);
			request.setAttribute("page", "allaccountinfo");
			
		} else {
			request.setAttribute("err", "개설된 계좌가 없습니다."); // error.jsp에서 출력해줄 문자열 지정
			request.setAttribute("page", "error");
		}
		dispatcher.forward(request, response);
	}

}
