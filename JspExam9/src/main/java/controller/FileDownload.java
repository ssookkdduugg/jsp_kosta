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
 * Servlet implementation class FileDownload
 */
@WebServlet("/fileDown")
public class FileDownload extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.setCharacterEncoding("utf-8");
      // 파일명
      String fileName = request.getParameter("file");
      // 파일경로
      ServletContext context = request.getServletContext();
      String filePath = context.getRealPath("upload")+ "\\" + fileName;
      
      
      FileInputStream in = new FileInputStream(filePath); // 파일을 읽어들이기 위한 FileInputStream 열기
      
      
      String sMimeType = request.getServletContext().getMimeType(filePath); // 파일의 MIME 유형을 가져오기
      
      if(sMimeType==null) {
         sMimeType = "application/octet-stream"; 
         // octet-stream: 8비트로 된 일련의 데이터를 뜻한다. 파일 타입 딱히 없고 그냥 파일이야.라고 알려주게하는것...
      }

      
      response.setContentType(sMimeType);
      // 브라우저에게 기존처럼 html모드가 아닌 파일 다운로드 모드로 바꾸라고 파일 형식을 알려준다.

      String sEncoding = new String(fileName.getBytes("utf-8"), "8859_1"); //한글 파일명 깨짐 방지 
      response.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);
      
      OutputStream out = response.getOutputStream();
      byte[] buff = new byte[1024];
      int len = 0;
      while((len=in.read(buff))>0) {
         out.write(buff, 0, len);
      }
      out.close();
      in.close();
      
      
      // 딱히 응답 뷰 페이지는 없음
      
      
      /* cf.
       "Multi Purpose Internet Mail Extensions" 유형의 약어인 MIME 유형:
       파일의 데이터 유형에 레이블을 지정하고 설명하는 방법으로,
       웹 브라우저와 이메일 클라이언트에 파일 처리 방법을 알려준다
       
       요약하면 
       MIME : 다양한 유형의 파일로 수행할 작업을 컴퓨터에 알려주는 레이블
       * */
   }

}