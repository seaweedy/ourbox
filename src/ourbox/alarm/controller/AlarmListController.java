package ourbox.alarm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.alarm.service.AlarmServiceImpl;
import ourbox.alarm.service.IAlarmService;
import ourbox.common.vo.AlarmVO;


@WebServlet("/AlarmListController")
public class AlarmListController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터값 가져오기
		String mem_id = request.getParameter("memId");
				
		// 서비스 객체 생성
		IAlarmService alarmService = AlarmServiceImpl.getInstance();
				
		// 메소드 호출 하기
		List<AlarmVO> alarmList = alarmService.alarmList(mem_id);
			
		// request에 저장하기
		request.setAttribute("alarmList", alarmList);
		request.setAttribute("mem_id", mem_id);
						
		// jsp로 전송하기
		request.getRequestDispatcher("view/alarm/alarmList.jsp").forward(request, response);
		
		
		
	}
}
