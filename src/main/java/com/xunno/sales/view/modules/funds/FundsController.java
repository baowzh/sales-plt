package com.xunno.sales.view.modules.funds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.xunno.sales.basic.model.FileContent;
import com.xunno.sales.form.FoundsPagingForm;
import com.xunno.sales.modules.funds.mdoel.Funds;
import com.xunno.sales.modules.funds.service.FundsService;
import com.xunno.sales.view.modules.FileUploadControler;

@Controller
@RequestMapping(value = "funds")
public class FundsController extends FileUploadControler {
	@Autowired
	private FundsService fundsService;

	@RequestMapping("depart/index")
	public ModelAndView departIndex(ModelMap modelMap) {
		return new ModelAndView("gov/funds/departIndex", modelMap);
	}

	@RequestMapping("town/index")
	public ModelAndView townIndex(ModelMap modelMap) {
		return new ModelAndView("gov/funds/townIndex", modelMap);
	}

	@RequestMapping("add")
	public ModelAndView add(ModelMap modelMap) {
		return new ModelAndView("gov/funds/add", modelMap);
	}

	@RequestMapping("del")
	public ModelAndView del(ModelMap modelMap, String departType) {
		List<String> bathIds = this.fundsService.getBathIds();
		modelMap.put("bathIds", bathIds);
		modelMap.put("departType", departType);
		return new ModelAndView("gov/funds/del", modelMap);
	}

	@ResponseBody
	@RequestMapping("delData")
	public Map<String, Object> delData(String bathId) {
		Map<String, Object> info = new HashMap<String, Object>();
		try {
			this.fundsService.del(bathId);
			info.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			info.put("success", false);
			info.put("mess", ex.getMessage());
		}
		return info;
	}

	@RequestMapping("depart/import")
	public ModelAndView departImport(ModelMap modelMap) {
		return new ModelAndView("gov/funds/departimport", modelMap);
	}

	@RequestMapping("town/import")
	public ModelAndView importExcel(ModelMap modelMap) {
		return new ModelAndView("gov/funds/townimport", modelMap);
	}

	@RequestMapping("depart/upload")
	public ModelAndView departUpload(HttpServletRequest request, ModelMap modelMap) {
		try {
			FileContent excelContent = this.getFileContent(request);
			fundsService.upload(excelContent);
			modelMap.put("mess", "导入成功。");
			modelMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("mess", e.getMessage());
			modelMap.put("success", false);

		}
		return new ModelAndView("gov/funds/departimport", modelMap);
	}

	@RequestMapping("town/upload")
	public ModelAndView townUpload(HttpServletRequest request, ModelMap modelMap) {
		try {
			FileContent excelContent = this.getFileContent(request);
			fundsService.townUpload(excelContent);
			modelMap.put("mess", "导入成功。");
			modelMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("mess", e.getMessage());
			modelMap.put("success", false);

		}
		return new ModelAndView("gov/funds/townimport", modelMap);
	}

	@RequestMapping(value = "depart/paging")
	@ResponseBody
	public DBPageValue<Funds> departPaging(FoundsPagingForm pagingForm) {
		return this.fundsService.paging(pagingForm);
	}

	@RequestMapping(value = "town/paging")
	@ResponseBody
	public DBPageValue<Funds> townPaging(FoundsPagingForm pagingForm) {
		return this.fundsService.paging(pagingForm);
	}

}
