package ourbox.atchfile.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.atchfile.service.AtchFileServiceImpl;
import ourbox.atchfile.service.IAtchFileService;
import ourbox.common.vo.AtchFileVO;

@WebServlet("/FileDownloadController")
public class FileDownloadController extends HttpServlet {
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IAtchFileService atchFileService = AtchFileServiceImpl.getInstance();
		
		// 파일명(변수)
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		
		AtchFileVO atchFile = new AtchFileVO();
		
		try {
			
			atchFile = atchFileService.select(fileId);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} // 파일정보 조회
		
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(atchFile.getAtch_file_name(), "utf-8").replaceAll("\\+", "%20") + "\""); // 다운로드 받을때의 파일명
						// 헤더이름 					벨류값	추가정보
						// 파일을 첨부할것이다 라는 의미 = > 다운받을것이다
						// 실제 파일명은 다를 수도 있다 서버에 다른이름으로 저장해놓은것이 아니라 실제파일명을 가져와야함 DB에서
		
		BufferedInputStream  bis = new BufferedInputStream(new FileInputStream(atchFile.getAtch_file_path()));
		// 버퍼 등을 이용하여 속도를 향상시킬수 있다.

		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		int c;
		while ((c = bis.read()) != -1) {
			bos.write(c);
		}
		bis.close();
		bos.close();
	}


}
