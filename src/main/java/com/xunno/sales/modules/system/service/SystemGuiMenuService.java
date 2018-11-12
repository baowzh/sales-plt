package com.xunno.sales.modules.system.service;

import java.util.List;

import com.asiainfo.eframe.security.model.UserInfo;
import com.xunno.sales.modules.system.model.SystemGuiMenu;
import com.xunno.sales.view.modules.system.bean.SystemMenuBean;

public interface SystemGuiMenuService {

	public void saveInfo(SystemMenuBean systemGuiMenu,UserInfo userInfo);

	public void removeInfo(String menuId);

	public SystemGuiMenu getInfoByMenuId(String menuId);

	public SystemMenuBean getBeanByMenuId(String menuId);

	public List<SystemGuiMenu> getAllMenu();

	public List<SystemGuiMenu> getMenuByParentId(String parentMenuId);

}
