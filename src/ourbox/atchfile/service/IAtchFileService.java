package ourbox.atchfile.service;

import java.sql.SQLException;

import org.apache.commons.fileupload.FileItem;

import ourbox.common.vo.AtchFileVO;

public interface IAtchFileService {
	
	/**
	 * 첨부파일을 저장하기 위한 메서드
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public AtchFileVO saveAtchFile(FileItem item) throws Exception;
	
	/**
	 * 첨부파일 아이디로 첨부파일 정보 조회하기
	 * @param atchFileId
	 * @return
	 * @throws SQLException
	 */
	public AtchFileVO select(int atch_file_seq) throws SQLException;
	
	
	
}
