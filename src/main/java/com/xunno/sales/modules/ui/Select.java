package com.xunno.sales.modules.ui;

import java.util.List;

import com.asiainfo.ewebframe.ui.form.model.SelectResult;

public interface Select {
	public List<SelectResult> getCategorys();

	public List<SelectResult> getSubCategorys(Integer categoryId);

	public List<SelectResult> getTowns();

	public List<SelectResult> gethamlets(String townId);

	public List<SelectResult> getDeparts();
}
