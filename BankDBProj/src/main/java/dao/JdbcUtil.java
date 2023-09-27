package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {

   // 커넥션풀로부터 연결 객체 생성하는 메소드
   public static Connection getConnection() {
      
      Connection conn = null;
      try {
         
         // 정해진 절차
         Context initCtx = new InitialContext();
         Context envCtx = (Context) initCtx.lookup("java:comp/env");
         DataSource ds = (DataSource) envCtx.lookup("jdbc/kosta");
         conn = ds.getConnection();
         
      } catch (Exception e) {
         e.printStackTrace();
         
      }
      return conn;
   }
   
   // 연결객체 닫는 메소드
   public static void close(Connection conn) {
      
         try {
            if(conn!=null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
   }
   
   
}