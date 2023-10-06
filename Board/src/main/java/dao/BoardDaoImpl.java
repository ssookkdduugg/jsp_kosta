package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Board;
import util.MybatisSqlSessionFactory;

public class BoardDaoImpl implements BoardDao {

	SqlSession sqlSession = MybatisSqlSessionFactory.getsqlSessionFactory().openSession();
	
	@Override
	public void insertBoard(Board board) throws Exception {
		sqlSession.insert("mapper.board.insertBoard", board);
		sqlSession.commit();
	}

	@Override
	public List<Board> selectBoardList(Integer row) throws Exception {
		return sqlSession.selectList("mapper.board.selectBoardList", row);
	}

	
	// 페이징 처리를 위한 전체 데이터 수 (전체 행의 개수를 알아야 전체 페이지를 얻을 수 있음)
	@Override
	public Integer selectBoardCount() throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoardCount");
	}

	
	// 게시글 상세
	@Override
	public Board selectBoard(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoard", num);
	}
	
	
	// 게시글 수정
	@Override
	public void updateBoard(Board board) throws Exception {
		sqlSession.update("mapper.board.updateBoard", board);
		sqlSession.commit();
	}

	// 게시글 삭제
	@Override
	public void deleteBoard(Integer num) throws Exception {
		sqlSession.update("mapper.board.deleteBoard", num);
		sqlSession.commit();
	}

	
/*
	// 게시글 검색 기존에 내가 작성한 코드
	@Override
	public List<Board> searchBoardList(Map<String, Object> parammap) throws Exception {
	    return sqlSession.selectList("mapper.board.searchBoardList", parammap);
	}

	@Override
	public Integer searchBoardCount(Map<String,Object> parammap) throws Exception {
		return sqlSession.selectOne("mapper.board.searchBoardCount", parammap);
	}
*/	
	
	
	// 게시글 검색 선생님 코드
	@Override
	public List<Board> searchBoardList(Map<String, Object> parammap) throws Exception {
		return sqlSession.selectList("mapper.board.searchBoardList", parammap);
	}
	
	@Override
	public Integer searchBoardCount(Map<String,Object> parammap) throws Exception {
		return sqlSession.selectOne("mapper.board.searchBoardCount", parammap);
	}

	@Override
	public void updateBoardViewCount(Integer num) throws Exception {
		sqlSession.update("mapper.board.updateBoardViewCount",num);
		sqlSession.commit();
		
		
	}

	//boardlike테이블에 관련된거 
	@Override
	public Integer selectBoardLike(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.boardlike.selectBoardLike",param);
	}

	@Override
	public void insertBoardLike(Map<String, Object> param) throws Exception {
		sqlSession.insert("mapper.boardlike.insertBoardLike",param);
		sqlSession.commit();
		
	}

	@Override
	public void deleteBoardLike(Map<String, Object> param) throws Exception {
		sqlSession.insert("mapper.boardlike.deleteBoardLike",param);
		sqlSession.commit();
	}

	@Override
	public void plusBoardLikeCount(Integer num) throws Exception {
		sqlSession.update("mapper.board.plusBoardLikeCount",num);
		sqlSession.commit();
	}

	@Override
	public void minusBoardLikeCount(Integer num) throws Exception {
		sqlSession.update("mapper.board.minusBoardLikeCount",num);
		sqlSession.commit();
		
	}

	@Override
	public Integer selectLikeCount(Integer num) throws Exception {
		
		return sqlSession.selectOne("mapper.board.selectLikeCount",num);
	}

	
	
}