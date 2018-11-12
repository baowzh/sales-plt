package com.xunno.sales.modules.system.dao;

import java.util.List;

import com.xunno.sales.modules.system.model.Menu;

public interface MenuManagerDao {
	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);

	public int updateParentIds(Menu menu);

	public int updateSort(Menu menu);

	public Menu getMenuById(String id);

	public void insertMenu(Menu menu);

	public void updateMenu(Menu menu);

	public void deleteMenu(Menu menu);
}
