package com.xunno.sales.modules.system.dao.defaults;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.xunno.sales.modules.system.dao.RoleFuncrightDao;
import com.xunno.sales.modules.system.model.RoleFuncright;

@Repository
public class DefaultRoleFuncrightDao implements RoleFuncrightDao {

	@Autowired
	private DBSqlSession sqlSession;

	public DBSqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(DBSqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertRoleFuncright(RoleFuncright roleFuncright) {
		sqlSession.insert(RoleFuncright.class, roleFuncright);

	}

	@Override
	public void updateRoleFuncright(RoleFuncright roleFuncright) {
		sqlSession.update(RoleFuncright.class, roleFuncright);

	}

	@Override
	public void deleteRoleFuncright(Map<String, Object> params) {
		sqlSession.del(RoleFuncright.class, params);

	}

	@Override
	public List<RoleFuncright> findRoleFuncright(Map<String, Object> params) {
		return sqlSession.getData(RoleFuncright.class, params);
	}

	@Override
	public RoleFuncright findRoleFuncrightByCode(Map<String, Object> params) {
		return sqlSession.getDataSingle(RoleFuncright.class, params);
	}

	@Override
	public int[] batchInsertRoleFuncright(List<RoleFuncright> roleFuncrights) {
		StringBuffer batchInsertSql = new StringBuffer();
		
		batchInsertSql.append(" insert into TF_M_ROLEFUNCRIGHT ").append(RoleFuncright.getSqlTypeString()).append(" values")
		.append(RoleFuncright.getSqlValString());
		
		return sqlSession.batchInsert(batchInsertSql.toString(), roleFuncrights);
	}

}
