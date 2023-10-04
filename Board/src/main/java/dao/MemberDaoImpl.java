package dao;

import org.apache.ibatis.session.SqlSession;

import dto.Member;
import util.MybatisSqlSessionFactory;

public class MemberDaoImpl implements MemberDao {
	SqlSession sqlSession = MybatisSqlSessionFactory.getsqlSessionFactory().openSession();
	
	@Override
	public Member selectMember(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.selectMember",id);
	}

	@Override
	public Member insertMember(Member member) throws Exception {
		sqlSession.insert("mapper.member.insertMember",member);
		sqlSession.commit();
		return member;
	}
	
	

}
