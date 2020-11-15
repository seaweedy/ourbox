package ourbox.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import ourbox.atchfile.service.AtchFileServiceImpl;
import ourbox.atchfile.service.IAtchFileService;
import ourbox.board.service.BoardServiceImpl;
import ourbox.board.service.IBoardService;
import ourbox.common.util.FileUploadRequestWrapper;
import ourbox.common.vo.AtchFileVO;
import ourbox.common.vo.BoardVO;

@WebServlet("/UpdateBoardController")
public class UpdateBoardController extends HttpServlet {

	private static final String VIEW_PAGE = "view/board/updateBoardForm.jsp";
	
	private IAtchFileService atchFileService = AtchFileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// URL 변수 받아오기
		int board_seq = Integer.parseInt(request.getParameter("board_seq"));
		int room_seq = Integer.parseInt(request.getParameter("room_seq"));
		String mem_id = request.getParameter("mem_id");
		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");
		
		String logId = request.getParameter("logId");
		
		// request에 저장하기
		request.setAttribute("mem_id", mem_id);
		request.setAttribute("room_seq", room_seq);
		request.setAttribute("board_seq", board_seq);
		request.setAttribute("board_title", board_title);
		request.setAttribute("board_content", board_content);
		request.setAttribute("logId", logId);
		
		// jsp로 전송하기
		request.getRequestDispatcher(VIEW_PAGE).forward(request, response);
		
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		FileUploadRequestWrapper requestWrapper = null;
		try {
			requestWrapper = new FileUploadRequestWrapper(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		FileItem item = ((FileUploadRequestWrapper)requestWrapper).getFileItem("atchFile"); // FileItem 추출
		AtchFileVO atchFile = new AtchFileVO();
		 if(item != null && !item.getName().equals("")) {
			 try {
				 atchFile = atchFileService.saveAtchFile(item);
				 
			 }catch(Exception ex) {
				 ex.printStackTrace();
			 }
		 }
		System.out.println("첨부파일 아이디: " + atchFile.getAtch_file_seq());
		
		// 파라미터값 받아오기
		String board_title = requestWrapper.getParameter("board_title");
		String board_content = requestWrapper.getParameter("board_content");
		String mem_id = requestWrapper.getParameter("mem_id");
		int board_seq = Integer.parseInt(requestWrapper.getParameter("board_seq"));
		int room_seq = Integer.parseInt(requestWrapper.getParameter("room_seq"));
		
		// 서비스 객체 생성
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		BoardVO board = new BoardVO();
		
		board.setBoard_title(board_title);
		board.setBoard_content(board_content);
		board.setBoard_seq(board_seq);	
		
		if (atchFile != null) {
			board.setAtch_file_seq(atchFile.getAtch_file_seq());
		} else {
			board.setAtch_file_seq(0);
		}
		
		int count = boardService.updateBoard(board);
		
		String msg = "";
		if(count > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		// 목록 조회 화면으로 이동
		String redirectUrl = request.getContextPath() + "/BoardMainController?memId="+mem_id+"&roomSeq="+room_seq;
				
		response.sendRedirect(redirectUrl); // 목록 조회화면으로 리다이렉트 처리하기.
	
 
		
	
	}

}
