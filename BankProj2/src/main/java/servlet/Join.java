package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;

/**
 * Servlet implementation class Join
 */
@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	RequestDispatcher dispatcher = req.getRequestDispatcher("join.jsp");
    	dispatcher.forward(req, resp);
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 2-1. 요청객체에서 form 사용자 입력 데이터 꺼내기
		String id = request.getParameter("id"); //form에 입력된 아이디
		String name= request.getParameter("name"); 
		String password = request.getParameter("password"); 
		String email = request.getParameter("email"); 
		String address = request.getParameter("address"); 
		
		// 2-2. 꺼낸 데이터로 Member객체 생성하여 세션에 담기 - 현재 학습을 위해  
		HttpSession session = request.getSession();
		Member member = new Member(id, name, password, email, address);
		session.setAttribute("member", member);
		
		// 3. 응답페이지 위임
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp"); 
		dispatcher.forward(request, response);
		// 기존 BankProj와 달리 인클루드하기위해 필요한 문자열을 page라는 키값과 함께 맵형식으로 request에 담아 넘기지 않고 바로 페이지를 지정하여 위임한다.
		
		
		/*
		1. 실제: 
		계좌개설시 벨류-Account객체를 DB에 저장*
		회원가입시 벨류-Member객체를 DB에 저장*
		로그인시 벨류-사용자입력문자열아이디를 세션에 저장
		
		2. 학습을 위해: 
		계좌개설시 변수id(사용자입력계좌번호)를 키로 하여 각 acc를 저장 - 세션에*
		회원가입시 문자열"member"를 키로 하여 Member객체를 저장 - 세션에*
		로그인시 문자열"id"를 키로 하여 변수id(사용자입력값)을 저장 - 세션에
		
		3. 전체계좌조회시 
		1) 세션에서 getAtrributeNames()하여 모든 키들을 Enum으로 받는다
		2) 키네임이 member/id인 경우를 제외한 나머지 경우의 키들(사용자입력값들로, 각각 고유한 계좌번호)만 짝지은 벨류를 추출하여  Account에 담아서 List에 추가한뒤 List를 리퀘스트에 담는다
		3) 응답페이지 위임한다
		
		4. 계좌처럼 회원 또한 여러번 생성하여 세션에 저장하고 싶다면 컬렉션에 담은 뒤 accList와 memberList 또는 accMap/memberMap 자체를 세션에 저장할 수 있다
		*/
	
	}
}
