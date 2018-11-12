package com.xunno.sales.view.modules.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.xunno.sales.modules.system.model.Funcright;
import com.xunno.sales.modules.system.service.FuncrightService;
import com.xunno.sales.view.modules.system.bean.FuncDBPagingPrams;
import com.xunno.sales.view.modules.system.bean.SubmitRest;

@Controller
@RequestMapping(value = "system/func")
public class FuncrightController {

	@Autowired
	private UserSessionHolderService contextHolderService;

	@Autowired
	private FuncrightService funcrightService;


	@RequestMapping("index")
	public ModelAndView index(ModelMap modelMap) {

		return new ModelAndView("system/func/index", modelMap);
	}

	@RequestMapping("infoList")
	@ResponseBody
	public FuncInfoList4Page infoList(ModelMap modelMap) {
		FuncInfoList4Page funcInfoList4Page = new FuncInfoList4Page();

		List<Funcright> funcrights = funcrightService.getAllFuncright();
		funcInfoList4Page.setRows(funcrights);
		return funcInfoList4Page;
	}

	@RequestMapping("infoPageList")
	@ResponseBody
	public FuncInfoList4Page infoPageList(String name, String value, Integer rows, Integer page,
			ModelMap modelMap) {
		FuncInfoList4Page funcInfoList4Page = new FuncInfoList4Page();

		FuncDBPagingPrams dbPagingPrams = new FuncDBPagingPrams();

		if ("rightName".equals(name)) {
			dbPagingPrams.setRightName(value);
		} else if ("rightCode".equals(name)) {
			dbPagingPrams.setRightCode(value);
		} else if ("modCode".equals(name)) {
			dbPagingPrams.setModCode(value);
		}

		dbPagingPrams.setRows(rows);
		dbPagingPrams.setPage(page);

		DBPageValue<Funcright> funcrights = funcrightService.findFuncright(dbPagingPrams, new Funcright());

		funcInfoList4Page.setTotal(Long.valueOf(funcrights.getTotalrowcount()));
		funcInfoList4Page.setRows(funcrights.getModels());
		return funcInfoList4Page;
	}

	@RequestMapping("update")
	public ModelAndView update(String rightCode, ModelMap modelMap) {
		Funcright funcright = funcrightService.getFuncrightByrightCode(rightCode);
		modelMap.put("funcright", funcright);
		return new ModelAndView("system/func/updateInfo", modelMap);
	}

	@RequestMapping("saveInfo")
	@ResponseBody
	public SubmitRest saveInfo(Funcright funcright, ModelMap modelMap) {

		SubmitRest saveFuncuRest = new SubmitRest();
		UserInfo userInfo = contextHolderService.getSessionUserInfo();

		try {
			funcrightService.saveFunc(funcright,userInfo);
			saveFuncuRest.setSuccess(true);
			saveFuncuRest.setMessage("修改成功");

		} catch (RuntimeException e) {
			saveFuncuRest.setSuccess(false);
			saveFuncuRest.setMessage("修改失败!!!：\n" + e.getMessage());

		}

		return saveFuncuRest;
	}

	public static class FuncInfoList4Page {
		private List<Funcright> rows;
		private Long total = 0L;

		public List<Funcright> getRows() {
			return rows;
		}

		public void setRows(List<Funcright> rows) {
			this.rows = rows;
		}

		public Long getTotal() {
			return total;
		}

		public void setTotal(Long total) {
			this.total = total;
		}

	}

}
