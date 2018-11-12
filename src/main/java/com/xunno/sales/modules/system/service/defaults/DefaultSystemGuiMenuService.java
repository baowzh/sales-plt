package com.xunno.sales.modules.system.service.defaults;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.eframe.security.model.UserInfo;
import com.xunno.sales.modules.system.dao.ModFileDao;
import com.xunno.sales.modules.system.dao.SystemGuiMenuDao;
import com.xunno.sales.modules.system.model.Funcright;
import com.xunno.sales.modules.system.model.ModFile;
import com.xunno.sales.modules.system.model.SystemGuiMenu;
import com.xunno.sales.modules.system.service.FuncrightService;
import com.xunno.sales.modules.system.service.SystemGuiMenuService;
import com.xunno.sales.view.modules.system.bean.SystemMenuBean;

import jodd.util.StringUtil;

@Service("systemGuiMenuService")
public class DefaultSystemGuiMenuService implements SystemGuiMenuService {

	@Autowired
	private SystemGuiMenuDao systemGuiMenuDao;

	@Autowired
	private ModFileDao modFileDao;

	@Autowired
	private FuncrightService funcrightService;

	private static final String SYSTEM_CLASS_CODE = "menu";

	private static Object getMenuIdLock = new Object();

	@Override
	public void saveInfo(SystemMenuBean systemMenuBean,UserInfo userInfo) {

		SystemGuiMenu systemGuiMenu = systemMenuBean.getSystemGuiMenu();
		
		 Date date = new Date();
		
		ModFile modFile = systemMenuBean.getModFile();
		if (systemGuiMenu == null) {
			throw new RuntimeException("参数错误：入参不能为空");
		}

		if (StringUtils.isBlank(systemGuiMenu.getSubsysCode())) {
			throw new RuntimeException("参数错误：子系统编码不可以为空");
		}
		systemGuiMenu.setSubsysCode(systemGuiMenu.getSubsysCode().toUpperCase(Locale.US));
		if (StringUtils.isBlank(systemGuiMenu.getMenuId())) {

			synchronized (getMenuIdLock) {
				setMenuId4NewSystemGuiMenu(systemGuiMenu);
				
				systemGuiMenu.setUpdateTime(date);
				systemGuiMenu.setUpdateStaffId(userInfo.getStaffid());
				systemGuiMenu.setUpdateDepartId(userInfo.getDepartid());
				
				this.getSystemGuiMenuDao().insertSystemGuiMenu(systemGuiMenu);
			}
			if (modFile != null && StringUtil.isNotBlank(modFile.getModName())) {
				modFile.setUpdateTime(date);
				modFile.setUpdateStaffId(userInfo.getStaffid());
				modFile.setUpdateDepartId(userInfo.getDepartid());
				
				
				modFile.setModCode(systemGuiMenu.getMenuId());
				this.modFileDao.insertModFile(modFile);
				saveFuncright4AddMenu(systemGuiMenu,userInfo);
			}

		} else {

			SystemGuiMenu oldSystemGuiMenu = this.getInfoByMenuId(systemGuiMenu.getMenuId());
			if (oldSystemGuiMenu == null) {
				throw new RuntimeException("修改失败：菜单原数据不存在");
			}

			if (!systemGuiMenu.getSubsysCode().equals(oldSystemGuiMenu.getSubsysCode())) {
				throw new RuntimeException("修改失败：子系统编码不可以修改");
			}

			if (!systemGuiMenu.equals(oldSystemGuiMenu)) {
				
				systemGuiMenu.setUpdateTime(date);
				systemGuiMenu.setUpdateStaffId(userInfo.getStaffid());
				systemGuiMenu.setUpdateDepartId(userInfo.getDepartid());
				
				this.getSystemGuiMenuDao().updateSystemGuiMenu(systemGuiMenu);
			}

			saveModFile4MenuChange(systemGuiMenu, modFile,userInfo);

		}

	}

	private void saveFuncright4AddMenu(SystemGuiMenu systemGuiMenu,UserInfo userInfo) {
		
		
		Funcright funcright = new Funcright();
		funcright.setClassCode(SYSTEM_CLASS_CODE);
		funcright.setRightName("菜单—" + systemGuiMenu.getMenuTitle());
		funcright.setRightAttr("0");
		funcright.setModCode(systemGuiMenu.getMenuId());
		
		
		this.getFuncrightService().saveFunc(funcright,userInfo);

	}

	private void saveModFile4MenuChange(SystemGuiMenu systemGuiMenu, ModFile modFile,UserInfo userInfo) {
		ModFile oldModFile = this.getModFileDao().findModFileByModCode(systemGuiMenu.getMenuId());
		

		 Date date = new Date();

		if (oldModFile == null && modFile == null) {
			return;
		} else if (oldModFile != null && modFile == null) {

			this.getModFileDao().deleteModFile(oldModFile);
			this.getFuncrightService().removeFuncByModeCode(SYSTEM_CLASS_CODE, oldModFile.getModCode());

		} else if (modFile != null && StringUtils.isNoneBlank(modFile.getModName()) &&  oldModFile == null ) {

			modFile.setModCode(systemGuiMenu.getMenuId());
			this.getModFileDao().insertModFile(modFile);
			saveFuncright4AddMenu(systemGuiMenu,userInfo);

		} else if (!modFile.equals(oldModFile)) {

			modFile.setUpdateTime(date);
			modFile.setUpdateStaffId(userInfo.getStaffid());
			modFile.setUpdateDepartId(userInfo.getDepartid());
			
			modFile.setModCode(systemGuiMenu.getMenuId());
			this.getModFileDao().updateModFile(modFile);

		}
	}

	@Override
	public void removeInfo(String menuId) {

		if (StringUtils.isBlank(menuId)) {
			throw new RuntimeException("删除失败：菜单编码不能为空");
		}

		SystemGuiMenu systemGuiMenu = this.getSystemGuiMenuDao().findSystemGuiMenuByMenuId(menuId);
		ModFile modFile = this.getModFileDao().findModFileByModCode(menuId);
		if (systemGuiMenu != null) {
			List<SystemGuiMenu> systemGuiMenus = this.getMenuByParentId(menuId);
			if (systemGuiMenus != null && !systemGuiMenus.isEmpty()) {
				throw new RuntimeException("删除失败：该菜单下还拥有子菜单，请先删除该菜单下所有子菜单");
			}

			this.getSystemGuiMenuDao().deleteSystemGuiMenu(systemGuiMenu);
		}

		if (modFile != null) {
			this.getModFileDao().deleteModFile(modFile);
			this.getFuncrightService().removeFuncByModeCode(SYSTEM_CLASS_CODE, menuId);
		}

	}

	@Override
	public SystemGuiMenu getInfoByMenuId(String menuId) {
		if (StringUtils.isBlank(menuId)) {
			throw new RuntimeException("查询失败：菜单编码不可以为空");

		}
		return this.systemGuiMenuDao.findSystemGuiMenuByMenuId(menuId);
	}

	@Override
	public List<SystemGuiMenu> getAllMenu() {
		return this.systemGuiMenuDao.findSystemGuiMenu(new HashMap<String, Object>());
	}

	@Override
	public SystemMenuBean getBeanByMenuId(String menuId) {
		if (StringUtils.isBlank(menuId)) {
			throw new RuntimeException("查询失败：页面编码不可以为空");

		}

		SystemGuiMenu systemGuiMenu = this.systemGuiMenuDao.findSystemGuiMenuByMenuId(menuId);
		ModFile modFile = this.getModFileDao().findModFileByModCode(menuId);
		SystemMenuBean systemMenuBean = new SystemMenuBean();
		systemMenuBean.setModFile(modFile);
		systemMenuBean.setSystemGuiMenu(systemGuiMenu);
		return systemMenuBean;
	}

	@Override
	public List<SystemGuiMenu> getMenuByParentId(String parentMenuId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentMenuId", parentMenuId);

		return this.systemGuiMenuDao.findSystemGuiMenu(params);
	}

	public SystemGuiMenuDao getSystemGuiMenuDao() {
		return systemGuiMenuDao;
	}

	public void setSystemGuiMenuDao(SystemGuiMenuDao systemGuiMenuDao) {
		this.systemGuiMenuDao = systemGuiMenuDao;
	}

	private void setMenuId4NewSystemGuiMenu(SystemGuiMenu systemGuiMenu) {
		String subsysCode = systemGuiMenu.getSubsysCode();
		String maxMenuId = this.systemGuiMenuDao.findMaxMenuIdBySubsysCode(subsysCode);
		if (StringUtils.isBlank(maxMenuId)) {
			systemGuiMenu.setMenuId(subsysCode + "0000");
			return;
		}

		String menuIdIndex = maxMenuId.substring(subsysCode.length()).trim();
		if (menuIdIndex.toLowerCase(Locale.US).compareTo("zzzz") >= 0 ) {
			throw new RuntimeException("新增失败:该子系统下页面数量达到上限");
		}

		Long newMenuIdIndexFlag = null;
		try {
			newMenuIdIndexFlag = Long.valueOf(menuIdIndex) + 1;
			if (newMenuIdIndexFlag < 10000) {
				systemGuiMenu.setMenuId(subsysCode + String.format("%04d",newMenuIdIndexFlag));
			} else if (newMenuIdIndexFlag == 1000) {
				systemGuiMenu.setMenuId(subsysCode + "aaaa");
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			char[] menuIdIndexChar = menuIdIndex.toLowerCase(Locale.US).toCharArray();
			int maxMenuIdCharLen = menuIdIndexChar.length;

			for (int i = maxMenuIdCharLen - 1; i >= 0; i--) {
				if (menuIdIndexChar[i] < 'z') {
					menuIdIndexChar[i] = (char) (menuIdIndexChar[i] + 1);
					break;
				} else {
					menuIdIndexChar[i] = 'a';
				}

			}

			systemGuiMenu.setMenuId(subsysCode + menuIdIndexChar.toString());

		}

	}

	public ModFileDao getModFileDao() {
		return modFileDao;
	}

	public void setModFileDao(ModFileDao modFileDao) {
		this.modFileDao = modFileDao;
	}

	public static Object getGetMenuIdLock() {
		return getMenuIdLock;
	}

	public static void setGetMenuIdLock(Object getMenuIdLock) {
		DefaultSystemGuiMenuService.getMenuIdLock = getMenuIdLock;
	}

	public FuncrightService getFuncrightService() {
		return funcrightService;
	}

	public void setFuncrightService(FuncrightService funcrightService) {
		this.funcrightService = funcrightService;
	}

}
