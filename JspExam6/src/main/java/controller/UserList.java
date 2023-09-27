package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/userList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터없이 
		List<User> userList = new ArrayList<>();
		userList.add(new User("hong","홍길동","서울시 금천구","hong@kosta.com"));
		userList.add(new User("gong","고길동","서울시 영등포구","gong@kosta.com"));
		userList.add(new User("kong","공길동","서울시 강남구","kong@kosta.com"));
		userList.add(new User("song","송길동","서울시 마포구","song@kosta.com"));
		userList.add(new User("tong","통길동","서울시 서초구","tong@kosta.com"));
		
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("userList.jsp").forward(request, response);
		
		
	}

}
