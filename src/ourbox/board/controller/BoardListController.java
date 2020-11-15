package ourbox.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.board.service.BoardServiceImpl;
import ourbox.board.service.IBoardService;
import ourbox.common.vo.BoardVO;

@WebServlet("/BoardListController")
public class BoardListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 클라이언트 요청시 cpage값가져오기
		int cpage = Integer.parseInt(request.getParameter("page"));
		int room_seq = Integer.parseInt(request.getParameter("room_seq"));
				
		// 서비스 객체 얻어오기
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		int perlist = 10; // 페이지당 출력개수
				
		// 메소드 호출하기
		int totalcount = boardService.getTotalCount();
				
		// 전체 페이지수
		int totalpage = (int) (Math.ceil((double) totalcount / perlist)) ; // 올림공식

		int perblock = 5; // 한화면에 표현되는 페이지수
		int startpage = ((cpage-1) / perblock * perblock) + 1;
		int endpage = startpage + perblock -1;
		if(endpage > totalpage) endpage = totalpage;
				
		// map에 설정하기
		int start = (cpage - 1) * perlist + 1;
		int end = start + perlist - 1;
		if(end > totalcount) end = totalcount;
				
		Map<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("room_seq", room_seq);
				
		// 페이지별 리스트 메소드 호출하기
		List<BoardVO> list = boardService.selectPage(map);
				
		// request에 결과 저장하기
		request.setAttribute("listvalue", list);
		request.setAttribute("spage", startpage);
		request.setAttribute("epage", endpage);
		request.setAttribute("tpage", totalpage);
		request.setAttribute("cpage", cpage);
				
		// jsp로 전송하기
		request.getRequestDispatcher("view/board/boardList.jsp").forward(request, response);
		
	
	}

}
