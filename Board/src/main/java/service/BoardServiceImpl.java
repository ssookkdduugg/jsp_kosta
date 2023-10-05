package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BoardDao;
import dao.BoardDaoImpl;
import dto.Board;
import util.PageInfo;

public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao;

	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
	
	@Override
	public void boardWrite(Board board) throws Exception {
		boardDao.insertBoard(board);
	}

	@Override
	public Map<String, Object> boardListByPage(Integer page) throws Exception { // 인자로 받은 것이 현재페이지
		
		PageInfo pageInfo = new PageInfo();
		
		// 전체 행의 수(전체 데이터 수)를 이용하여 전체 페이지 수를 계산
		int boardCount = boardDao.selectBoardCount();
		int maxPage = (int)Math.ceil((double)boardCount/10);  // 10으로 나눈 목을 제외한 나머지를 살리기 위해 double로 캐스팅한뒤 올림처리한다. ex. 9.5 -> 10.0 페이지가 되도록 
		
		// 현재페이지를 통해 버튼의시작페이지번호와 버튼의끝페이지번호를 만든다
		int startPage = (page-1)/10*10+1; // 1, 11, 21, 31,...로 만든다
		int endPage = startPage+10-1;
		if(endPage>maxPage) endPage=maxPage;
		
		/* '게시글 삭제'의 예외 처리:
		 게시글(행)이 11개일때, 즉 1,2페이지가 있을때 2페이지의 하나뿐인 게시글을 삭제하게되는 상황에서 삭제완료후 1페이지로 가게하도록 한다. 
		 (삭제완료후 /boardlist?page=2로 응답페이지 위임되도록 작성되어있음)
		 maxPage는 1인데 현재페이지page는 2가 되는 상황의 처리를 하는것 (아래 if문의 조건식) */
		if(page>maxPage) page=maxPage;
		
		
		// 페이지 객체를 채운다
		pageInfo.setAllPage(maxPage);
		pageInfo.setCurPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		// 현재 페이지의 시작 행 (SELECT문의 limit절에 사용됨)
		int row = (page-1)*10+1; 
		List<Board> boardList = boardDao.selectBoardList(row-1); // DB의 모든 인덱스는 1부터 시작하지만 limit은 유일한 예외 (cf. DB에서 LIMIT으로 값을 불러올 때는 0부터 시작)
		
		// 페이지정보 객체와 리스트를 맵에 담아 호출부로 리턴
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("boardList", boardList);
		
		return map;
	}

	@Override
	public Board boardDetail(Integer num) throws Exception {
		return boardDao.selectBoard(num);
	}

	@Override
	public void boardModify(Board board) throws Exception {
		boardDao.updateBoard(board);
	}

	@Override
	public void boardRemove(Integer num) throws Exception {
		boardDao.deleteBoard(num);
	}
	
	
/*
	// 게시글 검색 기존에 내가 작성한 코드
	@Override
	public Map<String, Object> boardSearch(Map<String,Object> parammap) throws Exception { // 인자로 Integer page가 아니라 HashMap을 받음
		
		PageInfo pageInfo = new PageInfo();
		
		// 검색된 행(게시글)의 수를 이용하여 검색후 게시글목록의 페이지 수를 계산
		int boardCount = boardDao.searchBoardCount(parammap);
		int maxPage = (int)Math.ceil((double)boardCount/10);   
		
		// 현재페이지를 통해 버튼의시작페이지번호와 버튼의끝페이지번호를 만든다
		int page = (int)(parammap.get("page"));
		int startPage = (page-1)/10*10+1;
		int endPage = startPage+10-1;
		if(endPage>maxPage) endPage=maxPage;
		if(page>maxPage) page=maxPage;
		
		
		// 페이지 객체를 채운다
		pageInfo.setAllPage(maxPage);
		pageInfo.setCurPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		// 현재 페이지의 시작 행 (SELECT문의 limit절에 사용됨)
		int row = (page-1)*10+1; 
		parammap.put("row", row-1);
		List<Board> boardList = boardDao.searchBoard(parammap);
		
		// 페이지정보 객체와 리스트를 맵에 담아 호출부로 리턴
		parammap.put("pageInfo", pageInfo);
		parammap.put("boardList", boardList);
		
		return parammap;
			
		//DAO로 보낼때 map에 담겨있어야 하는것 : 사용자가 선택한 검색옵션type, 사용자가 입력한 검색어keyword, 페이지인포객체pageInfo, 현재페이지의시작행번호row, 검색된게시글목록boardList
	}
*/

	// 게시글 검색 선생님 코드
	@Override
	public Map<String, Object> boardSearch(String type, String keyword, Integer page) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("keyword", keyword);
		
		
		PageInfo pageInfo = new PageInfo();
		int boardCount = boardDao.searchBoardCount(param);
		int maxPage = (int)Math.ceil((double)boardCount/10);  // 10으로 나눈 목을 제외한 나머지를 살리기 위해 double로 캐스팅한뒤 올림처리한다. ex. 9.5 -> 10.0 페이지가 되도록 
		int startPage = (page-1)/10*10+1; // 1, 11, 21, 31,...로 만든다
		int endPage = startPage+10-1;
		if(endPage>maxPage) endPage=maxPage;
		if(page>maxPage) page=maxPage;
		
		pageInfo.setAllPage(maxPage);
		pageInfo.setCurPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		int row = (page-1)*10+1; 
		param.put("row", row-1);
		List<Board> boardList = boardDao.searchBoardList(param);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("boardList", boardList);
		map.put("type", type);
		map.put("keyword", keyword);
		
		return map;
	}	
	
	
	
	
	
	/* 페이징 관련 추가설명
	 
	페이지그룹별 시작페이지번호 계산식 
	int startPage = (page-1)/10*10+1;
	(1) (page-1)/10은 현재 페이지를 10으로 나눈 몫을 구합니다. 이렇게 하면 현재 페이지가 몇 번째 그룹에 속하는지를 알 수 있습니다. 이 값은 0, 1, 2, ...와 같이 그룹 번호를 나타냅니다.
	(2) 그 다음으로 * 10을 해서 현재 그룹의 첫 번째 페이지 번호를 계산합니다. 이 때, 0번째 그룹은 0, 1번째 그룹은 10, 2번째 그룹은 20, ...와 같이 시작 페이지를 계산합니다.
	(3) 마지막으로 + 1을 하여 현재 그룹의 시작 페이지를 설정합니다. 예를 들어, 0번째 그룹은 첫 페이지 번호가 1, 1번째 그룹은 첫 페이지 번호가 11, 2번째 그룹은 첫 페이지 번호가 21, ...와 같이 시작 페이지를 계산합니다.
	  
	  
	현재페이지의 시작행번호(select쿼리의 limit절에서 사용될 row) 계산식 
	int row = (page-1)*10+1;
	(1) (page-1)을 해주는 이유는 DB에서 LIMIT으로 값을 불러올 때는 0부터 시작하기 때문이다.
	(2) *10은 한페이지에 10개의 게시글을 표시하겠다는 의미
	(3) +1를 하는 이유는 행번호가 0부터 시작하므로 첫번째 행 번호를 구하기 위함이다. 
	 * */
}