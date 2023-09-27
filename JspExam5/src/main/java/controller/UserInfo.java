package controller;

import java.io.IOException;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("UserInfoForm.jsp").forward(request, response);
	}
    
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		//db에서 id로 사용자조회
		User user = new User(id,"홍길동","서울시 금천구","hong@kosta.com");
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("userInfo.jsp").forward(request, response);
	}

}
