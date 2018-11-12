package com.xunno.sales.view.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.model.UserMenuValue;
import com.asiainfo.eframe.security.model.UserInfo;
import com.xunno.sales.basic.service.MenuService;
import com.xunno.sales.config.ProtalConfig;

@Controller
@RequestMapping("nav")
public class NavController {
	@Autowired
	private UserSessionHolderService contextHolderService;
	@Autowired
	private ProtalConfig sysConfig;
	@Autowired
	private MenuService menuService;
	@RequestMapping("index")
	public ModelAndView index(ModelMap modelMap) {
		UserInfo userInfo = contextHolderService.getSessionUserInfo();
		modelMap.put("userInfo", userInfo);
		modelMap.put("sysConfig", sysConfig);
		Integer maxMenuLevel = menuService.getMaxLevel();
		List<UserMenuValue> topMenus = menuService.getUserMenus();
		modelMap.put("maxMenuLevel", maxMenuLevel);
		modelMap.put("topMenus", topMenus);
		return new ModelAndView("Nav", modelMap);
	}
}
