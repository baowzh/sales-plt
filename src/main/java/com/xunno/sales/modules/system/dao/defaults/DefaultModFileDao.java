package com.xunno.sales.modules.system.dao.defaults;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.xunno.sales.modules.system.dao.ModFileDao;
import com.xunno.sales.modules.system.model.ModFile;

@Repository
public class DefaultModFileDao implements ModFileDao {

	@Autowired
	private DBSqlSession sqlSession;

	@Override
	public void insertModFile(ModFile modFile) {
		sqlSession.insert(ModFile.class, modFile);
	}

	@Override
	public void updateModFile(ModFile modFile) {
		sqlSession.update(ModFile.class, modFile);
	}

	@Override
	public void deleteModFile(ModFile modFile) {
		sqlSession.del(ModFile.class, modFile);
	}

	@Override
	public List<ModFile> findModFile(Map<String, Object> params) {
		return sqlSession.getData(ModFile.class, params);
	}

	@Override
	public ModFile findModFileByModCode(String modCode) {
		return sqlSession.getDataSingle(ModFile.class, modCode);
	}

	public DBSqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(DBSqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
