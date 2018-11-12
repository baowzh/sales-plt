package com.xunno.sales.basic.service.defaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.model.UserMenuValue;
import com.xunno.sales.basic.dao.IMenuDao;
import com.xunno.sales.basic.service.MenuService;
import com.xunno.sales.util.PortalStaticConstant;

import jodd.util.StringUtil;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private IMenuDao menuDao;
	@Autowired
	private UserSessionHolderService contextHolderService;

	@Override
	public Integer getMaxLevel() {

		List<UserMenuValue> allMenus = this.getAllMenu();
		int maxLevel = 0;
		for (UserMenuValue menuValuei : allMenus) {
			if (!StringUtil.isEmpty(menuValuei.getParentid())) {
				continue;
			}
			Integer level = 0;
			this.orgMenuTree(menuValuei, allMenus);
			level = this.getMaxLevel(menuValuei) + 1;
			if (level > maxLevel) {
				maxLevel = level;
			}

		}

		return maxLevel;
	}

	private void orgMenuTree(UserMenuValue menui, List<UserMenuValue> allMenus) {
		List<UserMenuValue> childs = new ArrayList<UserMenuValue>();

		for (UserMenuValue menuValuei : allMenus) {
			if (menuValuei.getParentid() != null && menuValuei.getParentid().equalsIgnoreCase(menui.getMenuid())) {
				childs.add(menuValuei);
				this.orgMenuTree(menuValuei, allMenus);
			}
		}
		menui.setChildren(childs);

	}

	private Integer getMaxLevel(UserMenuValue menuValuei) {
		int maxLevel = 0;
		for (UserMenuValue menui : menuValuei.getChildren()) {
			int level = this.getLevel(menui);
			if (level > maxLevel) {
				maxLevel = level;
			}
		}
		return maxLevel;
	}

	private Integer getLevel(UserMenuValue menuValuei) {
		if (menuValuei.getChildren() == null || menuValuei.getChildren().isEmpty()) {
			return 1;
		} else {
			return 1 + getMaxLevel(menuValuei);
		}

	}

	@Override
	public List<UserMenuValue> getUserMenus() {
		List<UserMenuValue> topMenus = new ArrayList<UserMenuValue>();
		List<UserMenuValue> allMenus = this.getAllMenu();
		for (UserMenuValue menuValuei : allMenus) {
			if (!StringUtil.isEmpty(menuValuei.getParentid())) {
				continue;
			}

			this.orgMenuTree(menuValuei, allMenus);
			topMenus.add(menuValuei);

		}
		return topMenus;
	}

	private List<UserMenuValue> getAllMenu() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", contextHolderService.getSessionUserInfo().getStaffid());
		params.put("departid", contextHolderService.getSessionUserInfo().getDepartid());
		List<UserMenuValue> rightMenus = this.menuDao.getUserMenuValues(PortalStaticConstant.SystemType.SYS_TYPE_CEN1,
				params);
		for (int i = 0; i < rightMenus.size(); i++) {
			List<UserMenuValue> parentMenus = new ArrayList<UserMenuValue>();
			this.getParentMenus(rightMenus.get(i), parentMenus);
			for (int j = 0; j < parentMenus.size(); j++) {
				if (!this.isContain(parentMenus.get(j), rightMenus)) {
					rightMenus.add(parentMenus.get(j));
				}
			}
		}

		return rightMenus;
	}

	private boolean isContain(UserMenuValue menu, List<UserMenuValue> rightMenus) {
		for (int i = 0; i < rightMenus.size(); i++) {
			UserMenuValue menui = rightMenus.get(i);
			if (menui.getMenuid().equalsIgnoreCase(menu.getMenuid())) {
				return true;
			}
		}
		return false;
	}

	private void getParentMenus(UserMenuValue menu, List<UserMenuValue> parentMenus) {
		String parentMenuId = menu.getParentid();
		UserMenuValue parentMenu = this.menuDao.getParentMenus(PortalStaticConstant.SystemType.SYS_TYPE_CEN1,
				parentMenuId);
		if(parentMenu!=null){
			parentMenus.add(parentMenu);	
		}
		if (parentMenu==null||parentMenu.getParentid() == null) {
			return;
		} else {
			this.getParentMenus(parentMenu, parentMenus);
		}

	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

}
