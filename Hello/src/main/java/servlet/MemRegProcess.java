package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemRegServlet
 */
@WebServlet("/memReg")
public class MemRegProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemRegProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    // url요청은 무조건 get방식 요청이므로 이 doGet메소드가 응답을 처리한다 (처리내용: memReg.html 뷰를 지정)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// (1) 서블릿이 뷰를 지정하는 방식 중 리다이렉트(==재요청) 방식: 서버를 향한 새로운 URL요청이므로, 주소창의 URL이 변한다
//		resp.sendRedirect("memReg.html");
		
		
		// (2) 서블릿이 뷰를 지정하는 방식 중 포워드(==응답 위임) 방식: 요청과 응답이 유지되기 때문에 주소창의 URL이 변하지 않는다
		RequestDispatcher dispatcher = req.getRequestDispatcher("memReg.html");
		dispatcher.forward(req,  resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String tel = req.getParameter("tel");
		String hobby = req.getParameter("hobby");
		System.out.println("name:"+name+",address:"+address+",tel:"+tel+",hobby:"+hobby);
		
		// memRegSuccess.jsp로 출력 위임하기
		RequestDispatcher dispatcher = req.getRequestDispatcher("memRegSuccess.jsp");
		dispatcher.forward(req,  resp);
	}
	
	
	
}
