package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Account;

public class AccountDAO {

	public void insertAccount(Account acc) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into account values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, acc.getId());
			pstmt.setString(2, acc.getName());
			pstmt.setInt(3, acc.getBalance());
			pstmt.setString(4, acc.getType());
			pstmt.setString(5, acc.getGrade());
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JdbcUtil.close(conn);
	}

	public Account selectAccount(String id) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account acc = null;
		String sql = "select * from account where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				acc = new Account();
				acc.setId(rs.getString("id"));
				acc.setName(rs.getString("name"));
				acc.setBalance(rs.getInt("balance"));
				acc.setType(rs.getString("type"));
				acc.setGrade(rs.getString("grade"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		JdbcUtil.close(conn);
		return acc;
	}
	
	public List<Account> allselectAccount() {
		Connection conn = JdbcUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Account>  accs = new ArrayList<>();
		String sql = "select * from account";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs != null) {
				while(rs.next()) {
					Account acc = new Account(rs.getString("id"),rs.getString("name"),
							rs.getInt("balance"),rs.getString("type"),rs.getString("grade"));
					accs.add(acc);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		JdbcUtil.close(conn);
		return accs;
	}
	
	
	public int updateAccount(String id, Integer money) {
		
		int cnt = 0; 
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update account set balance = balance+? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, id);
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		JdbcUtil.close(conn);
		return cnt;
	}
	
	
	
	
	

}
