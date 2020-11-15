package ourbox.board.dao;

import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {

	private SqlMapClient smc;
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardDao getInstance() {
		
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		
		return boardDao;
	}
	
	@Override
	public List<BoardVO> selectPage(Map<String, Integer> map) throws SQLException {
		return smc.queryForList("board.selectPage", map);
	}

	@Override
	public int getTotalCount() throws SQLException {
		return (Integer) smc.queryForObject("board.getTotalCount");
	}

	@Override
	public BoardVO detailBoard(int board_seq) throws SQLException {
		return (BoardVO) smc.queryForObject("board.detailBoard", board_seq);
	}


	@Override
	public int insertBoard(BoardVO board) throws SQLException {
		return (Integer) smc.insert("board.insertBoard", board);
	}
	

	@Override
	public List<BoardVO> searchBoard(BoardVO board) throws SQLException {
		return smc.queryForList("board.searchBoard", board);
	}
	
	@Override
	public int deleteBoard(int board_seq) throws SQLException {
		return smc.delete("board.deleteBoard", board_seq);
	}
	

	@Override
	public int updateBoard(BoardVO board) throws SQLException {
		return smc.update("board.updateBoard", board);
	}






}
