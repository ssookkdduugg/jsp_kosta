package dao;

import java.util.List;


import bean.Member;

public interface MemberDAO {
	void insertMember(Member member)throws Exception;
	Member selectMember(String id) throws Exception;
	
}
