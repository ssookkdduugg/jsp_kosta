package service;

import java.util.Map;

import dto.Board;

public interface BoardService {
	void boardWrite(Board board) throws Exception;
	Map<String, Object> boardListByPage(Integer page) throws Exception;
	//페이지인포 정보, 보드목록 가져와야한다. 그래서 map 씀
	
	

}
