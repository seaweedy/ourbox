package ourbox.atchfile.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.AtchFileVO;

public class AtchFileDaoImpl implements IAtchFileDao {
		
	
	private static IAtchFileDao atchFileDao;
	private SqlMapClient smc;
	
	private AtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileDao getInstance() {
		if(atchFileDao == null) {
			atchFileDao = new AtchFileDaoImpl();
		}
		
		return atchFileDao;
	}

	@Override
	public int insertAtchFile(AtchFileVO atchFile) throws SQLException {
		
		Object obj = smc.insert("atchFile.insertAtchFile", atchFile);
		
		int cnt = 0;
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public AtchFileVO selectAtchFile(int atch_file_seq) throws SQLException {
		
		AtchFileVO atchFile = 
				(AtchFileVO) smc.queryForObject("atchFile.selectAtchFile", atch_file_seq);
		
		return atchFile;
	}
	
}
