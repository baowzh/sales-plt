package com.xunno.sales.modules.system.dao.defaults;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.xunno.sales.modules.system.dao.FuncrightDao;
import com.xunno.sales.modules.system.model.Funcright;

@Repository
public class DefaultFuncrightDao implements FuncrightDao {

	@Autowired
	private DBSqlSession sqlSession;

	public DBSqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(DBSqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void inserFuncright(Funcright funcright) {
		sqlSession.insert(Funcright.class, funcright);

	}

	@Override
	public void updateFuncright(Funcright funcright) {
		sqlSession.update(Funcright.class, funcright);

	}

	@Override
	public void deleteFuncright(Funcright funcright) {
		sqlSession.del(Funcright.class, funcright);

	}

	@Override
	public List<Funcright> findFuncright(Map<String, Object> params) {
		return sqlSession.getData(Funcright.class, params);
	}

	@Override
	public Funcright findModFuncrightByRightCode(String rightCode) {
		return sqlSession.getDataSingle(Funcright.class, rightCode);
	}

	@Override
	public DBPageValue<Funcright> findFuncright(DBPagingPrams page) {
		return sqlSession.pagingqueryData(Funcright.class, page);
		
	}

	@Override
	public List<Funcright> findFuncChilds(Map<String, Object> params) {
		return sqlSession.getData("findFuncrightChilds", params);
	}
}
