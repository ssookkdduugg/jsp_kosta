package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginSelf
 */
@WebServlet("/login2")
public class LoginSelf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSelf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String num = request.getParameter("num");
		String hobby = request.getParameter("hobby");
		
		System.out.println(name);
		System.out.println(addr);
		System.out.println(num);
		System.out.println(hobby);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("memResult.jsp");
		dispatcher.forward(request, response); //post방식은 url에 id,pw안나오니까 보안,대용량데이터 넘길때 주로 사용


	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("memReg.jsp"); //방법이 2가지 request.forward view(jsp나 html파일에 위임 넘겨주는거: 너가 프론트에뿌려) 
													//백엔드에서만보임. sendredirect 응답을 바로 페이지로 꽂아주기 url이 바뀐다.
													//sendredirect는 서버-> 클라이언트 응답 무조건 처음에해줘도 클라이언트가 정보를 받았어도
													//클라이언트가 다시 서버에 재요청하고 그제서야 화면에 보여진다. 
	}
}