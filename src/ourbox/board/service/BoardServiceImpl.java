package ourbox.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ourbox.board.dao.BoardDaoImpl;
import ourbox.board.dao.IBoardDao;
import ourbox.common.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {

	private static IBoardService boardService;
	
	private IBoardDao boardDao;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public List<BoardVO> selectPage(Map<String, Integer> map) {
		List<BoardVO> selectPage = null;
		
		try {
			selectPage = boardDao.selectPage(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectPage;
	}

	@Override
	public int getTotalCount() {
		int count = 0;
		
		try {
			count = boardDao.getTotalCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	@Override
	public BoardVO detailBoard(int board_seq) {
		BoardVO detailBoard = null;
		
		try {
			detailBoard = boardDao.detailBoard(board_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return detailBoard;
	}
	
	@Override
	public int insertBoard(BoardVO board) {
		int cnt = 0;
		
		try {
			cnt = boardDao.insertBoard(board);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	@Override
	public List<BoardVO> searchBoard(BoardVO board) {
		List<BoardVO> searchBoard = null;
		
		try {
			searchBoard = boardDao.searchBoard(board);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return searchBoard;
	}

	@Override
	public int deleteBoard(int board_seq) {
		int cnt = 0;
		
		try {
			cnt = boardDao.deleteBoard(board_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;

	}

	@Override
	public int updateBoard(BoardVO board) {
		int cnt = 0;
		
		try {
			cnt = boardDao.updateBoard(board);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}





	
	

}
