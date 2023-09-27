package service;

import java.util.List;

import dao.AccountDAO;
import dto.Account;

public class AccountService {
	
	private AccountDAO dao = new AccountDAO();
	
	public void makeAccount(Account acc)throws Exception{
		Account sacc = dao.selectAccount(acc.getId());
		if(sacc !=null) throw new Exception("계좌번호가 중복됩니다");
		dao.insertAccount(acc);
		
		
	}
	
	public Account selectAccount(String id)throws Exception{
		Account acc = dao.selectAccount(id);
		if(acc==null) throw new Exception("없는 계좌입니다.");
		
		return acc;
	}
	
	public List<Account> allselectAccount()throws Exception{
		List<Account> acclist = dao.allselectAccount();
		if(acclist==null) throw new Exception("생성된 계좌가 없습니다.");
		return acclist;
		
		
	}
	
	public Account deposit(String id, Integer money) throws Exception {
		Account acc = dao.selectAccount(id);
		if(acc==null) throw new Exception("없는 계좌입니다.");
		
		int cnt = dao.updateAccount(id,money);
		//update,insert는 결과를 int로 줌 1 or -1 
		if(cnt<0) System.out.println("입금 실패----");
		
		Account updatedAcc = dao.selectAccount(id);
		
		return updatedAcc;
		
	}
	
	public Account withdraw(String id, Integer money) throws Exception {
		Account acc = dao.selectAccount(id);
		if(acc==null) throw new Exception("없는 계좌입니다.");
		
		int cnt = dao.updateAccount(id,-money);
		//update,insert는 결과를 int로 줌 1 or -1 
		if(cnt<0) System.out.println("출금 실패----");
		
		Account updatedAcc = dao.selectAccount(id);
		
		return updatedAcc;
		
		
	}
	

	
	
	
	

}
