package ourbox.atchfile.service;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import ourbox.atchfile.dao.AtchFileDaoImpl;
import ourbox.atchfile.dao.IAtchFileDao;
import ourbox.common.util.FileUploadRequestWrapper;
import ourbox.common.vo.AtchFileVO;

public class AtchFileServiceImpl implements IAtchFileService {

	private static IAtchFileService atchFileService;
	private IAtchFileDao atchFileDao;
	
	private AtchFileServiceImpl() {
		atchFileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(atchFileService == null) {
			atchFileService = new AtchFileServiceImpl();
		}
		
		return atchFileService;
	}
	
	@Override
	public AtchFileVO saveAtchFile(FileItem item) throws Exception {
		
		// 전체경로를 제외한 파일명만 추출
		String orignFileName = new File(item.getName()).getName();
		long fileSize = item.getSize(); // 파일 사이즈
		
		String saveFileName = UUID.randomUUID().toString().replace("-", "");
		
		File upload = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		
		if(!upload.exists()) {
			upload.mkdirs();
		}
		String filePath = 
				FileUploadRequestWrapper.UPLOAD_DIRECTORY + File.separator + saveFileName;
		
		File saveFile = new File(filePath);
		
		item.write(saveFile); // 업로드 파일 저장
		
		// 파일 저장 서비스 호출.
		AtchFileVO atchFile = new AtchFileVO();
		atchFile.setAtch_file_name(orignFileName);
		atchFile.setAtch_file_path(filePath);
		
		atchFileDao.insertAtchFile(atchFile); // 첨부파일 정보 저장
		
		item.delete();// 임시 업로드 파일 삭제
		
		return atchFile;
	}

	@Override
	public AtchFileVO select(int atch_file_seq) throws SQLException {
		
		return atchFileDao.selectAtchFile(atch_file_seq);
	}

}
