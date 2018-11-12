package com.xunno.sales.view.basic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunno.sales.modules.system.service.SystemService;

@Controller
@RequestMapping("userpass")
public class UserPasswordController {
	@Autowired
	private SystemService systemService;

	@ResponseBody
	@RequestMapping("update")
	public Map<String, Object> updpass(String oldPassWord, String newPassWord) {
		Map<String, Object> info = new HashMap<String, Object>();
		try {
			systemService.updatePass(oldPassWord, newPassWord);
			info.put("success", true);
		} catch (Exception e) {
			info.put("success", false);
			info.put("mess", e.getMessage());
			e.printStackTrace();
		}
		return info;
	}

}
