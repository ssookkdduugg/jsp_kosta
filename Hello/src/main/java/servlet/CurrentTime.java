package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CurrentTime
 */
@WebServlet("/CurrentTime")
public class CurrentTime extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrentTime() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      response.setCharacterEncoding("utf-8");
      Calendar c = Calendar.getInstance();
      int hour = c.get(Calendar.HOUR_OF_DAY);
      int minute = c.get(Calendar.MINUTE);
      int second = c.get(Calendar.SECOND);
/*
      PrintWriter out = response.getWriter();
      out.write("<html><head><title>현재시간</title></head>");
      out.write("<body><h1>현재시간은 ");
      out.write(hour + "");
      out.write("시 ");
      out.write(minute + "");
      out.write("분 ");
      out.write(second + "");
      out.write("초 입니다.</h1></body></html>");
      out.close();
*/
      // PrintWriter out = ... 부터의 코드 대신 jsp페이지를 통해 출력하도록 위임할 수 있다. 아래 코드와 같이..
      
      // *** MVC 패턴
      // Main, Service, DTO -----> 모델 / 비즈니스로직 (처리 기능)
      // (여기서의) 서블릿 -----> (스프링 프레임워크에서) 컨트롤러
      // 컨트롤러는 요청 처리도 하고 모델과 뷰를 매칭한다 (모델의 결과를 받아서 뷰에게 데이터를 실어서 출력을 위임한다)
      
      request.setAttribute("hour", hour); 
      request.setAttribute("minute", minute);
      request.setAttribute("second", second);
      RequestDispatcher dispatcher = request.getRequestDispatcher("CurrentTime.jsp");
      dispatcher.forward(request, response);
      // forward(위임): 뷰가 출력할 데이터를 맵형식으로 request에 담아서 건네면서 출력처리를 위임한다
      
      // jsp의 최종목표는 백엔드로부터 받은 데이터를 출력하는 것
      // 서블릿자바코드에서 거의모든 가공처리를 하고 jsp에는 최종데이터만 넘겨준다
   }

}