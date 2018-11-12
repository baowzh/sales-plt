package com.xunno.sales.view.modules.system.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.security.model.UserInfo;
import com.xunno.sales.modules.system.model.ModFile;
import com.xunno.sales.modules.system.model.SystemGuiMenu;
import com.xunno.sales.modules.system.service.SystemGuiMenuService;
import com.xunno.sales.view.modules.system.bean.BasicTree;
import com.xunno.sales.view.modules.system.bean.SubmitRest;
import com.xunno.sales.view.modules.system.bean.SystemMenuBean;

import jodd.util.StringUtil;

@Controller
@RequestMapping(value = "system/menu")
public class MenuController {

	@Autowired
	private UserSessionHolderService contextHolderService;

	@Autowired
	private SystemGuiMenuService systemGuiMenuService;

	@RequestMapping("index")
	public ModelAndView systemMenu(ModelMap modelMap) {
		return new ModelAndView("system/menu/index", modelMap);
	}

	@RequestMapping("treeInfo")
	@ResponseBody
	public List<BasicTree> menuTree(String menuId, ModelMap modelMap) {
		List<SystemGuiMenu> systemGuiMenus = this.getSystemGuiMenuService().getAllMenu();

		List<BasicTree> basicTrees = this.guiMenuToMenuTree(systemGuiMenus, menuId);

		List<BasicTree> topMenus = BasicTree.getTopTree(basicTrees);
		

		return topMenus;

	}

	@RequestMapping("info")
	public ModelAndView menuInfo(String menuId, ModelMap modelMap) {
		UserInfo userInfo = contextHolderService.getSessionUserInfo();
		modelMap.put("userInfo", userInfo);
		SystemMenuBean systemMenuBean = this.getSystemGuiMenuService().getBeanByMenuId(menuId);

		if (systemMenuBean != null && systemMenuBean.getSystemGuiMenu() != null
				&& StringUtil.isNotBlank(systemMenuBean.getSystemGuiMenu().getParentMenuId())) {

			SystemGuiMenu parentSystemGuiMenu = this.getSystemGuiMenuService()
					.getInfoByMenuId(systemMenuBean.getSystemGuiMenu().getParentMenuId());
			if (parentSystemGuiMenu != null) {
				systemMenuBean.setParentTitle(parentSystemGuiMenu.getMenuTitle());
			}
		}

		modelMap.put("systemMenuBean", systemMenuBean);

		return new ModelAndView("system/menu/info", modelMap);
	}


	@RequestMapping("updateInfo")
	public ModelAndView updateInfo(String menuId, ModelMap modelMap) {
		if (StringUtil.isBlank(menuId)) {
			return null;
		}
		SystemMenuBean systemMenuBean = this.getSystemGuiMenuService().getBeanByMenuId(menuId);

		modelMap.put("systemMenuBean", systemMenuBean);
		if (systemMenuBean != null && systemMenuBean.getSystemGuiMenu() != null
				&& StringUtil.isNotBlank(systemMenuBean.getSystemGuiMenu().getParentMenuId())) {

			SystemGuiMenu parentSystemGuiMenu = this.getSystemGuiMenuService()
					.getInfoByMenuId(systemMenuBean.getSystemGuiMenu().getParentMenuId());
			if (parentSystemGuiMenu != null) {
				systemMenuBean.setParentTitle(parentSystemGuiMenu.getMenuTitle());
			}
		}
		return new ModelAndView("system/menu/updateInfo", modelMap);
	}

	@RequestMapping("addInfo")
	public ModelAndView addInfo(String parentMenuId, ModelMap modelMap) {
		SystemMenuBean systemMenuBean = new SystemMenuBean();

		systemMenuBean.setModFile(new ModFile());
		systemMenuBean.setSystemGuiMenu(new SystemGuiMenu());
		if (StringUtil.isNotBlank(parentMenuId)) {
			SystemGuiMenu parentSystemGuiMenu = this.getSystemGuiMenuService().getInfoByMenuId(parentMenuId);
			if (parentSystemGuiMenu != null) {
				systemMenuBean.setParentTitle(parentSystemGuiMenu.getMenuTitle());
				systemMenuBean.getSystemGuiMenu().setParentMenuId(parentMenuId);
			}
		}

		modelMap.put("systemMenuBean", systemMenuBean);
		return new ModelAndView("system/menu/addInfo", modelMap);
	}

	@RequestMapping("removeMenuInfo")
	@ResponseBody
	public SubmitRest removeMenuInfo(String menuId, ModelMap modelMap) {
		SubmitRest removeMenuRest = new SubmitRest();

		try {
			this.getSystemGuiMenuService().removeInfo(menuId);
			removeMenuRest.setSuccess(true);
			removeMenuRest.setMessage("菜单删除成功");

		} catch (RuntimeException e) {
			removeMenuRest.setSuccess(false);
			removeMenuRest.setMessage("菜单删除失败!!!：\n" + e.getMessage());

		}
		return removeMenuRest;

	}

	@RequestMapping("saveInfo")
	@ResponseBody
	public SubmitRest saveInfo(SystemMenuBean systemMenuBean, ModelMap modelMap) {

		UserInfo userInfo = contextHolderService.getSessionUserInfo();

		SubmitRest saveMenuRest = new SubmitRest();
		try {
			ModFile modFile = systemMenuBean.getModFile();
			if (modFile == null || StringUtil.isBlank(modFile.getModName())) {
				systemMenuBean.setModFile(null);
			}

			systemGuiMenuService.saveInfo(systemMenuBean, userInfo);
			saveMenuRest.setSuccess(true);
			saveMenuRest.setMessage("菜单保存成功");

		} catch (RuntimeException e) {
			saveMenuRest.setSuccess(false);
			saveMenuRest.setMessage("菜单保存失败!!!：\n" + e.getMessage());

		}

		return saveMenuRest;
	}

	private List<BasicTree> guiMenuToMenuTree(List<SystemGuiMenu> systemGuiMenus, String menuId) {
		List<BasicTree> basicTrees = new CopyOnWriteArrayList<BasicTree>();
		if (systemGuiMenus != null) {

			for (SystemGuiMenu systemGuiMenu : systemGuiMenus) {
				BasicTree basicTree = new BasicTree();
				basicTree.setId(systemGuiMenu.getMenuId());
				basicTree.setText(systemGuiMenu.getMenuTitle());
				basicTree.setParentId(systemGuiMenu.getParentMenuId());
				basicTrees.add(basicTree);
			}
		}
		return basicTrees;

	}

	public UserSessionHolderService getContextHolderService() {
		return contextHolderService;
	}

	public void setContextHolderService(UserSessionHolderService contextHolderService) {
		this.contextHolderService = contextHolderService;
	}

	public SystemGuiMenuService getSystemGuiMenuService() {
		return systemGuiMenuService;
	}

	public void setSystemGuiMenuService(SystemGuiMenuService systemGuiMenuService) {
		this.systemGuiMenuService = systemGuiMenuService;
	}

}
