package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageView
 */
@WebServlet("/imgView")
public class ImageView extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageView() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.setCharacterEncoding("utf-8");
      
      // 1. 파일명
      String fileName = request.getParameter("file");
      // 파일경로
      ServletContext context = request.getServletContext();
      String filePath = context.getRealPath("upload")+"\\"+fileName;
      // 2. 파일을 읽어들이기 위해 FileInputStream 열고 파일을 내보내기 위해 FileOutputStream 열기
      FileInputStream fis = new FileInputStream(filePath);
      OutputStream out = response.getOutputStream();
      
      // 3. 파일 데이터를 작은 덩어리로 나누어 읽어서 출력 스트림을 통해 브라우저로 전달하기
      byte[] buff = new byte[1024];
      int len = 0;
      while((len=fis.read(buff))>0) {
         out.write(buff, 0, len);
      }
      
      // 4. 스트림 닫기
      fis.close();
      out.close();
   }

}