package com.xunno.sales.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.xunno.sales.modules.system.model.Role;

public interface RoleManagerDao {
	public Role getByName(Role role);

	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * 
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);

	/**
	 * 维护角色与公司部门关系
	 * 
	 * @param role
	 * @return
	 */
	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);

	public List<Role> findRole(Map<String, Object> param);

	public void insertRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(Role role);

	public DBPageValue<Role> findRole(DBPagingPrams page);
	
	public String getMaxRoleCode();
	

}
