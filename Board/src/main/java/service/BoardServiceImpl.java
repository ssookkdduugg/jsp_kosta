package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BoardDao;
import dao.BoardDaoImpl;
import dto.Board;
import util.PageInfo;

public class BoardServiceImpl implements BoardService{
	private BoardDao boardDao;
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public void boardWrite(Board board) throws Exception {
		boardDao.insertBoard(board);
		
	}

	@Override
	public Map<String, Object> boardListByPage(Integer page) throws Exception {
		PageInfo pageInfo = new PageInfo();
		Integer boardCount = boardDao.selectBoardCount();
		Integer maxPage = (int)Math.ceil((double)boardCount/10);
		//ceil : 소수점 있으면 무조건 올려주는것 
		
		int startPage = (page-1)/10*10+1;
		//15을 10으로 만들어주는거 (page-1)/10*10
		//+1 하면 1,11,21,31 ...
		int endPage = startPage+10-1; //10,20,30....
		if(endPage>maxPage) endPage =maxPage;
		
		pageInfo.setAllPage(maxPage);
		pageInfo.setCurPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		int row = (page-1)*10+1; //현재 페이지의 시작 row
		List<Board> boardList = boardDao.selectBoardList(row-1);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("boardList", boardList);
		return map;
	
	}

	

}
