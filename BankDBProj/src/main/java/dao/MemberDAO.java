package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Member;

public class MemberDAO {
   
   // 인서트
   public int insertMember(Member member) {
      
      // 연결객체 열기
      Connection conn = JdbcUtil.getConnection();
      
      int cnt = 0;
      PreparedStatement pstmt = null;
      String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?)";
      
      try {
         // 미완성쿼리문용 실행객체를 만든다
         pstmt = conn.prepareStatement(sql);
         
         // 미완성쿼리문을 완성시킨다
         pstmt.setString(1, member.getId());
         pstmt.setString(2, member.getName());
         pstmt.setString(3, member.getPassword());
         pstmt.setString(4, member.getEmail());
         pstmt.setString(5, member.getAddress());
         
         // 완성한 쿼리문을 실행
         cnt = pstmt.executeUpdate(); 
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(pstmt!=null) pstmt.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      // 연결객체 닫기
      JdbcUtil.close(conn);
      
      return cnt;
   }
   
   public Member selectMember(String id) {
	   Connection conn = JdbcUtil.getConnection();
	   String sql = "select * from member where id=?";
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Member member = null;
	   try {
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setString(1, id);
		   rs = pstmt.executeQuery();
		   if(rs!=null & rs.next()) {
			   member = new Member();
			   member.setId(rs.getString("id"));
			   member.setPassword(rs.getString("password"));
			   member.setName(rs.getString("name"));
			   member.setAddress(rs.getString("address"));
			   member.setEmail(rs.getString("email"));
		   }
	   }catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	   
	   JdbcUtil.close(conn);
	   return member;
	   
	   
   }
   
   
   
   
   
}