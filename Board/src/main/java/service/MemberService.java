package service;

import dto.Member;

public interface MemberService {
	Member login(String id,String password) throws Exception;
	void join(Member member) throws Exception;
	
	//아이디 중복체크 dao에는 안씀
	String idCheck(String id)throws Exception;
	

}
