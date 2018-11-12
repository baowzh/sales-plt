package com.xunno.sales.view.modules.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.ewebframe.ui.form.model.SelectResult;
import com.xunno.sales.modules.depart.model.Depart;
import com.xunno.sales.modules.depart.service.OrgService;
import com.xunno.sales.modules.ui.Select;

@Controller
@RequestMapping(value = "ui")
public class UIController {
	@Autowired
	private Select select;
	@Autowired
	private OrgService orgService;

	@ResponseBody
	@RequestMapping("categorys")
	public List<SelectResult> getCategorys() {
		return select.getCategorys();
	}

	@ResponseBody
	@RequestMapping("subcategorys")
	public List<SelectResult> getSubCategorys(Integer catId) {
		return select.getSubCategorys(catId);
	}

	@ResponseBody
	@RequestMapping("getTowns")
	public List<SelectResult> getTowns() {

		List<Depart> departs = orgService.getDeparts(2);
		List<SelectResult> results = new ArrayList<SelectResult>();
		for (int i = 0; i < departs.size(); i++) {
			Depart departi = departs.get(i);
			SelectResult result = new SelectResult();
			result.setId(departi.getId());
			result.setText(departi.getName());
			results.add(result);
		}
		return results;

	}

	public List<SelectResult> gethamlets(String townId) {
		List<Depart> departs = orgService.getDeparts(townId);
		List<SelectResult> results = new ArrayList<SelectResult>();
		for (int i = 0; i < departs.size(); i++) {
			Depart departi = departs.get(i);
			SelectResult result = new SelectResult();
			result.setId(departi.getId());
			result.setText(departi.getName());
			results.add(result);
		}
		return results;
	}

	public List<SelectResult> getDeparts() {
		return null;
	}
}
