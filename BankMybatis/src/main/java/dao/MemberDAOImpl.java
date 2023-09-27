package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.Member;
import util.MybatisSqlSessionFactory;

public class MemberDAOImpl implements MemberDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getsqlSessionFactory().openSession();

	@Override
	public void insertMember(Member member) throws Exception {
		sqlSession.insert("mapper.member.insertMember",member);
		sqlSession.commit();
		
	}

	@Override
	public Member selectMember(String id) throws Exception {
		Member member = sqlSession.selectOne("mapper.member.selectMember",id);
		return member;
	}

	

}
