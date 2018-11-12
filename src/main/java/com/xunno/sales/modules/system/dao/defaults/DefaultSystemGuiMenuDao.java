package com.xunno.sales.modules.system.dao.defaults;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.xunno.sales.modules.system.dao.SystemGuiMenuDao;
import com.xunno.sales.modules.system.model.SystemGuiMenu;

@Repository
public class DefaultSystemGuiMenuDao implements SystemGuiMenuDao {

	@Autowired
	private DBSqlSession sqlSession;

	@Override
	public void insertSystemGuiMenu(SystemGuiMenu systemGuiMenu) {
		sqlSession.insert(SystemGuiMenu.class, systemGuiMenu);
	}

	@Override
	public void updateSystemGuiMenu(SystemGuiMenu systemGuiMenu) {
		sqlSession.update(SystemGuiMenu.class, systemGuiMenu);
	}

	@Override
	public void deleteSystemGuiMenu(SystemGuiMenu systemGuiMenu) {
		 sqlSession.del(SystemGuiMenu.class,systemGuiMenu);

	}

	@Override
	public List<SystemGuiMenu> findSystemGuiMenu(Map<String, Object> params) {
		List<SystemGuiMenu> SystemGuiMenus = sqlSession.getData(SystemGuiMenu.class,params);
		return SystemGuiMenus;
	}

	@Override
	public SystemGuiMenu findSystemGuiMenuByMenuId(String menuId) {
		return sqlSession.getDataSingle(SystemGuiMenu.class,menuId);
	}

	@Override
	public String findMaxMenuIdBySubsysCode(String subsysCode) {
		return sqlSession.getDataSingle("getMaxMenuIdBySubsysCode",subsysCode);
	}

	public DBSqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(DBSqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
