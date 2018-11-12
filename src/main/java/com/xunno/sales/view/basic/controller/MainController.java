package com.xunno.sales.view.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.security.model.UserInfo;
import com.xunno.sales.config.ProtalConfig;

@Controller
@RequestMapping("main")
public class MainController {
	@Autowired
	private UserSessionHolderService contextHolderService;
	
	@Autowired
	private ProtalConfig sysConfig;


	@RequestMapping("index")
	public ModelAndView index(ModelMap modelMap) {
		UserInfo userInfo = contextHolderService.getSessionUserInfo();
		modelMap.put("userInfo", userInfo);
		modelMap.put("sysname", sysConfig.getSyscname());
		return new ModelAndView("Main", modelMap);
	}
	
	
}
