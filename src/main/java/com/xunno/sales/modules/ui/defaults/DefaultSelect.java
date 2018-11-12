package com.xunno.sales.modules.ui.defaults;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.ewebframe.ui.form.model.SelectResult;
import com.xunno.sales.modules.category.CategoryRepository;
import com.xunno.sales.modules.category.model.Category;
import com.xunno.sales.modules.depart.model.Depart;
import com.xunno.sales.modules.depart.service.OrgService;
import com.xunno.sales.modules.ui.Select;

@Service("select")
public class DefaultSelect implements Select {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserSessionHolderService userSessionHolderService;
	@Autowired
	private OrgService orgService;

	@Override
	public List<SelectResult> getCategorys() {
		String departId=userSessionHolderService.getSessionUserInfo().getDepartid();
		Depart depart=orgService.getDepart(departId);
		List<Category> categorys = categoryRepository.getCategorys(depart.getType());
		return this.convertoEnums(categorys);
	}

	@Override
	public List<SelectResult> getSubCategorys(Integer categoryId) {
		String departId=userSessionHolderService.getSessionUserInfo().getDepartid();
		Depart depart=orgService.getDepart(departId);
		List<Category> enumvalues = this.categoryRepository.getSubCategorys(categoryId,depart.getType());
		return this.convertoEnums(enumvalues);
	}

	private List<SelectResult> convertoEnums(List<Category> categorys) {
		List<SelectResult> enumes = new ArrayList<SelectResult>();
		for (int i = 0; i < categorys.size(); i++) {
			Category category = categorys.get(i);
			SelectResult enumeValue = new SelectResult();
			enumeValue.setId(String.valueOf(category.getId()));
			enumeValue.setText(category.getName());
			enumes.add(enumeValue);
		}
		return enumes;
	}

	@Override
	public List<SelectResult> getTowns() {

		return null;
	}

	@Override
	public List<SelectResult> gethamlets(String townId) {

		return null;
	}

	@Override
	public List<SelectResult> getDeparts() {

		return null;
	}

}
