package ourbox.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.board.service.BoardServiceImpl;
import ourbox.board.service.IBoardService;
import ourbox.common.vo.BoardVO;

@WebServlet("/SearchBoardController")
public class SearchBoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// parameter 가져오기
    	String searchOption = request.getParameter("searchOption");
    	String searchKeyWord = request.getParameter("searchKeyWord");
    	
    	// service 객체 가져오기
    	IBoardService boardService = BoardServiceImpl.getInstance();
    	
    	// board객체에 정보 저장하기
    	BoardVO board = new BoardVO();
    			
    	if (searchOption.equals("글제목만")) {
    		board.setBoard_title(searchKeyWord);
    	}else if (searchOption.equals("글내용만")) {
    		board.setBoard_content(searchKeyWord);
    	}else if (searchOption.equals("글제목 + 내용")) {
    		board.setBoard_title(searchKeyWord);
    		board.setBoard_content(searchKeyWord);
    	}else if (searchOption.equals("작성자")) {
			board.setMem_id(searchKeyWord);
		}
    			
    	// service 메소드 사용하기
    	List<BoardVO> boardList = boardService.searchBoard(board);
    	
    	// request에 저장하기
    	request.setAttribute("boardList", boardList);
    	
    	// jsp로 forward한다.
    	request.getRequestDispatcher("view/board/searchBoard.jsp").forward(request, response);
	
	
	}


}
