package com.xunno.sales.modules.system.service;

import java.util.List;

import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.xunno.sales.modules.system.model.Menu;
import com.xunno.sales.modules.system.model.Role;
import com.xunno.sales.modules.system.model.User;
import com.xunno.sales.view.modules.system.bean.UserRoleBeen;

public interface SystemService {

	public User getUser(String userId);

	public User getUserByLoginName(String loginName);

	public DBPageValue<User> findUser(DBPagingPrams page, User user);

	public List<User> findUser(User user);

	/**
	 * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByOfficeId(String officeId);

	public void saveUser(User user);

	public void updateUserInfo(User user);

	public void deleteUser(User user);

	public void updatePasswordById(String id, String loginName, String newPassword);

	public void updateUserLoginInfo(User user);

	public Role getRole(String id);

	public Role getRoleByName(String name);

	public Role getRoleByEnname(String enname);

	public List<Role> findRole(Role role, String staffid);

	public DBPageValue<Role> findRole(DBPagingPrams page);

	public List<Role> findAllRole();

	public void saveRole(Role role);

	public void deleteRole(Role role);

	public void forcDeleteRole(Role role);

	public Boolean outUserInRole(Role role, User user);

	public User assignUserToRole(Role role, User user);

	public void saveUserRole(UserRoleBeen userRoleBeen, UserInfo userInfo);

	public Menu getMenu(String id);

	/**
	 * 获取当前用户的所有菜单
	 * 
	 * @return
	 */
	public List<Menu> findAllMenu();

	public void saveMenu(Menu menu);

	public void updateMenuSort(Menu menu);

	public void deleteMenu(Menu menu);

	public void updatePass(String oldPass, String newPass) throws Exception;

	public String resetPassword(String staffId) throws Exception;

}
