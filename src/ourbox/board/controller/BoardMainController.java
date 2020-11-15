package ourbox.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BoardMainController")
public class BoardMainController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// URL 변수 받아오기
		String mem_id = request.getParameter("memId");
		int room_seq = Integer.parseInt(request.getParameter("roomSeq"));
	
		// request객체에 저장하기
		request.setAttribute("mem_id", mem_id);
		request.setAttribute("room_seq", room_seq);
	
		// jsp로 전송하기
		request.getRequestDispatcher("view/board/boardMain.jsp").forward(request, response);
	
	}

	


}
