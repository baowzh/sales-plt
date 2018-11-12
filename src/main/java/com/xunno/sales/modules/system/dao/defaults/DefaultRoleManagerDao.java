package com.xunno.sales.modules.system.dao.defaults;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.xunno.sales.modules.system.dao.RoleManagerDao;
import com.xunno.sales.modules.system.model.Role;

@Repository
public class DefaultRoleManagerDao implements RoleManagerDao {

	@Autowired
	private DBSqlSession sqlSession;
	
	@Override
	public Role getByName(Role role) {
		return sqlSession.getDataSingle(Role.class, role);
	}

	@Override
	public Role getByEnname(Role role) {
		return sqlSession.getDataSingle(Role.class, role);
	}

	@Override
	public int deleteRoleMenu(Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertRoleMenu(Role role) {
		return 0;
	}

	@Override
	public int deleteRoleOffice(Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertRoleOffice(Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Role> findRole(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sqlSession.getData(Role.class, param);
	}

	@Override
	public void insertRole(Role role) {
		sqlSession.insert(Role.class, role);
	}

	@Override
	public void updateRole(Role role) {
		sqlSession.update(Role.class, role);
		
	}

	@Override
	public void deleteRole(Role role) {
		sqlSession.del(Role.class, role);
	}

	@Override
	public DBPageValue<Role> findRole(DBPagingPrams page) {
		return sqlSession.pagingqueryData(Role.class, page);
	}

	@Override
	public String getMaxRoleCode() {
		return sqlSession.getDataSingle("getMaxRoleCode", new Object());
	}

}
