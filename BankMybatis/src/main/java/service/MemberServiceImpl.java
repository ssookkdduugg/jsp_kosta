package service;

import bean.Member;
import dao.MemberDAO;
import dao.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDao;
	
	public MemberServiceImpl() {
		memberDao = new MemberDAOImpl();
	}

	@Override
	public Member login(String id, String password) throws Exception {
		return memberDao.selectMember(id);
		
	}

	@Override
	public void join(Member member) throws Exception {
		memberDao.insertMember(member);
		
	}
	
	
	
	
	
	
	
}
