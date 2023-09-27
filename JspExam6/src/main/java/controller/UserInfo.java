package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/userInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // url get요청 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("userInfoForm.jsp").forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// userInfoForm.jsp 안의 form 제출시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		 
		// DB에서 id로 사용자를 조회했다고 가정하여 User객체를 생성
		User user = new User(id, "홍길동", "서울시 금천구", "hong@kosta.com");
		
		// 세션에 아이디, 리퀘스트에 User객체를 담고 응답 페이지를 위임한다.
		Map<String,String> user1 = new HashMap<>();
		user1.put("id", id);
		user1.put("name", "홍길동");
		user1.put("address", "서울시금천구");
		user1.put("email", "hong@kosta.com");
		
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("userInfo.jsp").forward(request, response);
		
	}

}




