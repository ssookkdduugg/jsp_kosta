package dao;

import java.util.List;
import java.util.Map;

import dto.Board;

public interface BoardDao {
	
	void insertBoard(Board board) throws Exception;
	List<Board> selectBoardList(Integer row) throws Exception;
	
	// 페이징 처리를 위해 필요한 전체 데이터 개수 (전체 행의 개수를 알아야 전체 페이지를 얻을 수 있음)
	Integer selectBoardCount() throws Exception;
	
	// 게시글 상세
	Board selectBoard(Integer num) throws Exception; // 여기서 dao의 파라미터명이 num인 것은 매퍼Board.xml의 #{num}과 이름을 맞춰줘야하기때문

	// 게시글 수정
	void updateBoard(Board board) throws Exception;
	
	// 게시글 삭제
	void deleteBoard(Integer num) throws Exception;
	
/*	
	// 게시글 검색 기존에 내가 작성한 코드
	List<Board> searchBoard(Map<String,Object> parammap) throws Exception;
	// 게시글 검색시 페이징 처리를 위해 필요한 검색된 행의 수
	Integer searchBoardCount(Map<String,Object> parammap) throws Exception;
*/	
	// 게시글 검색 선생님 코드
	List<Board> searchBoardList(Map<String,Object> parammap) throws Exception;
	Integer searchBoardCount(Map<String,Object> parammap) throws Exception;
}