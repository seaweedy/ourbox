package ourbox.atchfile.dao;

import java.sql.SQLException;

import ourbox.common.vo.AtchFileVO;

public interface IAtchFileDao {
	
	public int insertAtchFile(AtchFileVO atchFile) throws SQLException;
	
	public AtchFileVO selectAtchFile(int atch_file_seq) throws SQLException;
	
}
