package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.Board;

public interface BoardService {
	
	void boardWrite(Board board) throws Exception;
	
	Map<String,Object> boardListByPage(Integer page) throws Exception; // 맵<키,벨류>
	
	Board boardDetail(Integer num) throws Exception;
	
	void boardModify(Board board) throws Exception;
	
	void boardRemove(Integer num) throws Exception;
	
//	Map<String, Object> boardSearch(Map<String, Object> parammap) throws Exception; // 게시글 검색 내가 작성한 코드
	Map<String, Object> boardSearch(String type, String keyword, Integer page) throws Exception; // 게시글 검색 선생님 코드
	
}