package ourbox.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.atchfile.service.AtchFileServiceImpl;
import ourbox.atchfile.service.IAtchFileService;
import ourbox.board.service.BoardServiceImpl;
import ourbox.board.service.IBoardService;
import ourbox.common.vo.AtchFileVO;
import ourbox.common.vo.BoardVO;


@WebServlet("/DetailBoardController")
public class DetailBoardController extends HttpServlet {
	
	private IAtchFileService atchFileService = AtchFileServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// parameter 받아오기
		int board_seq = Integer.parseInt(request.getParameter("boardSeq"));
		String logId = request.getParameter("logId");

		// service 객체 생성하기
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		// 메서드 사용하기
		BoardVO detailBoard = boardService.detailBoard(board_seq);
		
		AtchFileVO atchFile = new AtchFileVO();
		try {
			atchFile = atchFileService.select(detailBoard.getAtch_file_seq());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// request에 담기
		request.setAttribute("detailBoard", detailBoard);
		request.setAttribute("atchFile", atchFile);
		request.setAttribute("logId", logId);
						
		// jsp로 forward한다.
		request.getRequestDispatcher("view/board/detailBoardFile.jsp").forward(request, response);
		
		
	}

	
}
