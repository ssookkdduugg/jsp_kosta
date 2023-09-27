package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/fileUpload")
public class FileUpload extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.getRequestDispatcher("fileUploadForm.jsp").forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      /* 
       <form action="fileUpload" method="post" enctype="multipart/form-data">가 제출되어 넘어오면
       기존의 Request객체로는 충분치 않고 MultipartRequest객체를 생성해서 받아야함
       
       즉, multipart/form-data 요청을 처리하기 위해선 라이브러리에서 제공하는 MultipartRequest 클래스를 이용!
       */
      
      String uploadPath = request.getRealPath("upload"); // 파일을 업로드할 폴더를 정의
      int size = 10*1024*1024; // 최대 파일 크기를 10MB로 설정
      MultipartRequest multi = new MultipartRequest(request, uploadPath, size
                                       , "utf-8", new DefaultFileRenamePolicy());
      
      
      String name = multi.getParameter("name");       // 업로더 이름
      String title = multi.getParameter("title");    // 파일 제목
     // File file = multi.getFile("file");             // 업로드된 파일
      
      
//      FileInputStream fis = new FileInputStream("file"); // 업로드된 파일을 위한 FileInputStream 열기
      String orgFileName = multi.getOriginalFileName("file"); // 원래 파일 이름 가져오기
//      FileOutputStream dfile = new FileOutputStream(uploadPath + "/" + orgFileName); // 대상 파일에 대한 출력 스트림 만들기
//      
//      byte[] buff = new byte[1024];
//      int len = 0;
//      while((len=fis.read(buff))>0) {
//         dfile.write(buff, 0, len); // 파일 내용을 대상 파일로 복사하기
//      }
      
      System.out.println("name: " + name);
      System.out.println("title: " + title);
      System.out.println("orgFileName: " + orgFileName);
//      dfile.close(); // 출력 스트림 닫기
//      fis.close(); // 입력 스트림 닫기
      
      request.setAttribute("name", name);
      request.setAttribute("title", title);
      request.setAttribute("file", orgFileName);
      request.getRequestDispatcher("fileUploadResult.jsp").forward(request, response);
      
      
      
   }

}