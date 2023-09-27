package dao;

import java.util.List;

import bean.Account;

public interface AccountDAO {
	//interface는 public static 변수다  
	void insertAccount(Account acc)throws Exception;
	Account selectAccount(String id) throws Exception;
	void updateBalance(Account acc)throws Exception;
	List<Account>selectAccountList() throws Exception;
}
