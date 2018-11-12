package com.xunno.sales.modules.system.service.defaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.security.model.RoleInfo;
import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.asiainfo.eframe.util.Encryptor;
import com.asiainfo.ewebframe.security.EncryptAndDecrypt;
import com.xunno.sales.basic.dao.IRoleDao;
import com.xunno.sales.exception.DeleteRoleException;
import com.xunno.sales.modules.system.dao.MenuManagerDao;
import com.xunno.sales.modules.system.dao.RoleFuncrightDao;
import com.xunno.sales.modules.system.dao.RoleManagerDao;
import com.xunno.sales.modules.system.dao.StaffFuncrightDao;
import com.xunno.sales.modules.system.dao.UserManagerDao;
import com.xunno.sales.modules.system.model.Menu;
import com.xunno.sales.modules.system.model.Role;
import com.xunno.sales.modules.system.model.StaffFuncright;
import com.xunno.sales.modules.system.model.User;
import com.xunno.sales.modules.system.service.SystemService;
import com.xunno.sales.view.modules.system.bean.UserRoleBeen;

import jodd.util.StringUtil;

@Service("systemService")
public class DefaultSystemService implements SystemService {
	@Autowired
	private UserManagerDao userDao;
	@Autowired
	private MenuManagerDao menuManagerDao;
	@Autowired
	private RoleManagerDao roleManagerDao;
	@Autowired
	private EncryptAndDecrypt encryptAndDecrypt;
	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private RoleFuncrightDao roleFuncrightDao;

	@Autowired
	private StaffFuncrightDao staffFuncrightDao;
	@Autowired
	private UserSessionHolderService userSessionHolderService;

	@Override
	public User getUser(String userId) {
		return this.getUserDao().findUserByStaffid(userId);
	}

	@Override
	public User getUserByLoginName(String loginName) {
		User info = new User();
		info.setStaffid(loginName);
		return this.getUserDao().getByLoginName(info);
	}

	@Override
	public DBPageValue<User> findUser(DBPagingPrams page, User user) {

		return this.getUserDao().findUser(page);
	}

	@Override
	public List<User> findUser(User user) {

		return this.getUserDao().getUsers(user);
	}

	@Override
	public List<User> findUserByOfficeId(String officeId) {
		User user = new User();
		user.setDepartid(officeId);
		List<User> users = this.getUserDao().findUserByOfficeId(user);
		return users;
	}

	@Override
	public void saveUser(User user) {

		if (StringUtils.isBlank(user.getId())) {
			// 新建用户之前做一些工作
			this.getUserDao().insertUserRole(user);

		} else {
			User oldUser = this.getUserDao().getByLoginName(user);
			if (oldUser != null) {
				this.getUserDao().updateUserInfo(user);
			}

		}
		if (StringUtils.isNotBlank(user.getId())) {
			this.getUserDao().deleteUserRole(user);
			if (user.getRoleInfos() != null && user.getRoleInfos().size() > 0) {
				this.getUserDao().insertUserRole(user);
			} else {
				throw new RuntimeException(user.getStaffid() + "没有设置角色！");
			}
		}

	}

	@Override
	public void saveUserRole(UserRoleBeen userRoleBeen, UserInfo userInfo) {

		User user;
		if (StringUtils.isBlank(userRoleBeen.getStaffid())) {
			throw new RuntimeException("参数错误，用户编码不能为空");

		} else {
			user = this.getUserByLoginName(userRoleBeen.getStaffid());
			if (user == null) {
				throw new RuntimeException("数据错误，用户数据查询失败");

			}

		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("staffId", userRoleBeen.getStaffid());
		param.put("rightAttr", "1");
		this.staffFuncrightDao.delStaffFuncright(param);
		List<StaffFuncright> staffFuncrights = new ArrayList<StaffFuncright>();
		Date date = new Date();
		List<String> roleCodeList = userRoleBeen.getRoleCodeList();
		if (roleCodeList != null && roleCodeList.size() > 0) {

			Set<String> roleCodeSet = new HashSet<String>(roleCodeList);

			for (String roleCode : roleCodeSet) {

				if (StringUtils.isBlank(roleCode)) {
					continue;
				}

				StaffFuncright staffFuncright = new StaffFuncright();

				staffFuncright.setStaffId(userRoleBeen.getStaffid());
				staffFuncright.setRightCode(roleCode);
				staffFuncright.setRightAttr("1");
				staffFuncright.setRightTag("1");
				staffFuncright.setDepartId(user.getDepartid());
				staffFuncright.setAccreditStaffId(userInfo.getStaffid());
				staffFuncright.setAccreditTime(date);
				staffFuncrights.add(staffFuncright);

			}

			this.staffFuncrightDao.batchInsertStaffFuncright(staffFuncrights);
		}
	}

	@Override
	public void updateUserInfo(User user) {
		// 不需要写业务逻辑？
		this.getUserDao().updateUserInfo(user);
	}

	@Override
	public void deleteUser(User user) {
		this.getUserDao().deleteUser(user);
	}

	@Override
	public void updatePasswordById(String id, String loginName, String newPassword) {
		User user = new User();
		user.setStaffid(loginName);
		try {
			user.setStaffpasswd(this.getEncryptAndDecrypt().encrypt(newPassword));
		} catch (Exception e) {
			e.printStackTrace();
		}
		userDao.updatePasswordById(user);
	}

	@Override
	public void updateUserLoginInfo(User user) {

	}

	@Override
	public Role getRole(String id) {
		Role role = new Role();
		role.setRightcode(id);
		return this.getRoleManagerDao().getByEnname(role);
	}

	@Override
	public Role getRoleByName(String name) {

		Role roleInfo = new Role();
		roleInfo.setRightcode(name);
		return this.getRoleManagerDao().getByName(roleInfo);
	}

	@Override
	public Role getRoleByEnname(String enname) {

		Role roleInfo = new Role();
		roleInfo.setRightcode(enname);
		return this.getRoleManagerDao().getByEnname(roleInfo);
	}

	@Override
	public List<Role> findRole(Role role, String staffid) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (role != null) {
			param.put("rightcode", role.getRightcode());
			param.put("rightname", role.getRightname());
		}
		if (StringUtils.isNotBlank(staffid)) {
			param.put("staffid", staffid);

		}
		return this.getRoleManagerDao().findRole(param);
	}

	@Override
	public List<Role> findAllRole() {
		// 获取当前用的所有角色
		/// 获取当前用户
		// UserInfo userinfo = contextHolderService.getSessionUserInfo();
		// HashMap<String, Object> map = new HashMap<String, Object>();
		// map.put("username", userinfo.getStaffid());
		// return this.getRoleDao().getRoleInfosByUsername(map);
		return roleManagerDao.findRole(new HashMap<String, Object>());
	}

	@Override
	public void saveRole(Role role) {

		if (role == null) {
			throw new RuntimeException("保存失败，传入角色数据为空");
		}

		if (StringUtils.isBlank(role.getRightcode())) {

			setRoleCode4NewRole(role);

			this.getRoleManagerDao().insertRole(role);
		} else {
			this.getRoleManagerDao().updateRole(role);
		}
		this.getRoleManagerDao().deleteRoleMenu(role);
		if (role.getMenuList().size() > 0) {
			this.getRoleManagerDao().insertRoleMenu(role);
		}
	}

	@Override
	public void forcDeleteRole(Role role) {

		Map<String, Object> params = new HashMap<String, Object>();

		Map<String, Object> deleteRoleParam = new HashMap<String, Object>();
		deleteRoleParam.put("roleCode", role.getRightcode());
		roleFuncrightDao.deleteRoleFuncright(deleteRoleParam);

		params = new HashMap<String, Object>();
		params.put("rightCode", role.getRightcode());
		params.put("rightAttr", "1");
		this.staffFuncrightDao.delStaffFuncright(params);

		this.getRoleManagerDao().deleteRole(role);
	}

	@Override
	public void deleteRole(Role role) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("rightAttr", "1");
		params.put("roleCode", role.getRightcode());
		List<StaffFuncright> staffFuncrights = this.staffFuncrightDao.findStaffFuncrights(params);
		if (staffFuncrights != null && !staffFuncrights.isEmpty()) {
			throw new DeleteRoleException("删除失败：已有员工分配了该角色！！");
		}

		Map<String, Object> deleteRoleParam = new HashMap<String, Object>();
		deleteRoleParam.put("roleCode", role.getRightcode());
		roleFuncrightDao.deleteRoleFuncright(deleteRoleParam);

		// params = new HashMap<String, Object>();
		// params.put("rightCode", role.getRightcode());
		// params.put("rightAttr", "1");
		// this.staffFuncrightDao.delStaffFuncright(params);
		//

		this.getRoleManagerDao().deleteRole(role);
	}

	@Override
	public Boolean outUserInRole(Role role, User user) {

		List<RoleInfo> roles = user.getRoleInfos();
		for (RoleInfo e : roles) {
			if (e.getRightcode().equals(role.getRightcode())) {
				roles.remove(e);
				saveUser(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public User assignUserToRole(Role role, User user) {

		return null;
	}

	@Override
	public Menu getMenu(String id) {

		return this.getMenuManagerDao().getMenuById(id);
	}

	@Override
	public List<Menu> findAllMenu() {

		return null;
	}

	@Override
	public void saveMenu(Menu menu) {
		if (StringUtils.isBlank(menu.getMenuid())) {
			this.getMenuManagerDao().insertMenu(menu);
		} else {
			this.getMenuManagerDao().updateMenu(menu);
		}
	}

	@Override
	public void updateMenuSort(Menu menu) {

	}

	@Override
	public void deleteMenu(Menu menu) {
		this.getMenuManagerDao().deleteMenu(menu);
	}

	public UserManagerDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserManagerDao userDao) {
		this.userDao = userDao;
	}

	public MenuManagerDao getMenuManagerDao() {
		return menuManagerDao;
	}

	public void setMenuManagerDao(MenuManagerDao menuManagerDao) {
		this.menuManagerDao = menuManagerDao;
	}

	public RoleManagerDao getRoleManagerDao() {
		return roleManagerDao;
	}

	public void setRoleManagerDao(RoleManagerDao roleManagerDao) {
		this.roleManagerDao = roleManagerDao;
	}

	public EncryptAndDecrypt getEncryptAndDecrypt() {
		return encryptAndDecrypt;
	}

	public void setEncryptAndDecrypt(EncryptAndDecrypt encryptAndDecrypt) {
		this.encryptAndDecrypt = encryptAndDecrypt;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public DBPageValue<Role> findRole(DBPagingPrams page) {
		return this.roleManagerDao.findRole(page);
	}

	private void setRoleCode4NewRole(Role role) {
		String maxRoleCode = this.roleManagerDao.getMaxRoleCode();

		if (StringUtils.isBlank(maxRoleCode)) {
			role.setRightcode("00000001");
			return;
		}

		if (maxRoleCode.toUpperCase(Locale.US).compareTo("ZZZZZZZZ") >= 0) {
			throw new RuntimeException("新增失败:角色编码数量达到上限");
		}

		Long newMaxRoleCodeFlag = null;
		try {
			newMaxRoleCodeFlag = Long.valueOf(maxRoleCode) + 1;
			if (newMaxRoleCodeFlag < 100000000) {
				role.setRightcode(String.format("%08d", newMaxRoleCodeFlag));

			} else if (newMaxRoleCodeFlag == 100000000) {
				role.setRightcode("AAAAAAAA");
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			char[] maxRoleCodeChar = maxRoleCode.toUpperCase(Locale.US).toCharArray();
			int maxRoleCodeCharLen = maxRoleCodeChar.length;

			for (int i = maxRoleCodeCharLen - 1; i >= 0; i--) {
				if (maxRoleCodeChar[i] < 'Z') {
					maxRoleCodeChar[i] = (char) (maxRoleCodeChar[i] + 1);
					break;
				} else {
					maxRoleCodeChar[i] = 'A';
				}
			}

			role.setRightcode(String.valueOf(maxRoleCodeChar));

		}

	}

	@Override
	public void updatePass(String oldPass, String newPass) throws Exception {
		String staffId = userSessionHolderService.getSessionUserInfo().getStaffid();
		String oldPassword = userSessionHolderService.getSessionUserInfo().getStaffpasswd();
		String enold = Encryptor.fnEncrypt(oldPass, "00linkage");
		String ennew = Encryptor.fnEncrypt(newPass, "00linkage");
		if (StringUtil.isEmpty(oldPass) || StringUtil.isEmpty(newPass)) {
			throw new Exception("请填写当前密码和新的密码。");
		}
		if (oldPassword.equalsIgnoreCase(oldPass)) {
			throw new Exception("当前密码不正确。");
		}
		User user = new User();
		user.setStaffid(staffId);
		user.setStaffpasswd(ennew);
		userDao.updatePasswordById(user);
	}

	@Override
	public String resetPassword(String staffId) throws Exception {
		Integer baseElement[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		String randomPass = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			randomPass = randomPass + baseElement[random.nextInt(9)];
		}
		String ennew = Encryptor.fnEncrypt(randomPass, "00linkage");
		User user = new User();
		user.setStaffid(staffId);
		user.setStaffpasswd(ennew);
		userDao.updatePasswordById(user);
		return randomPass;
	}

}
