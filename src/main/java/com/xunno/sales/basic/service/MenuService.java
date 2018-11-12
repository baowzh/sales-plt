package com.xunno.sales.basic.service;

import java.util.List;

import com.asiainfo.eframe.model.UserMenuValue;

public interface MenuService {
	/**
	 * 获取菜单层级
	 * @return
	 */
	public Integer getMaxLevel();
    /**
     * 获取用户所有有权限的菜单
     * @return
     */
	public List<UserMenuValue> getUserMenus();


}
