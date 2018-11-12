package com.xunno.sales.modules.system.dao.defaults;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xunno.sales.modules.system.dao.MenuManagerDao;
import com.xunno.sales.modules.system.model.Menu;

@Repository
public class DefaultMenuManagerDao implements MenuManagerDao {

	@Override
	public List<Menu> findByParentIdsLike(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findByUserId(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateParentIds(Menu menu) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSort(Menu menu) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Menu getMenuById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

}
