package com.xunno.sales.modules.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.NameSpace;
import com.xunno.sales.modules.category.model.Category;

@DaoBean
public interface CategoryRepository {
	@NameSpace(namespaceClass = Category.class)
	public List<Category> getCategorys(@Param(value = "group") Integer group);

	@NameSpace(namespaceClass = Category.class)
	public List<Category> getSubCategorys(@Param(value = "id") Integer id, @Param(value = "group") Integer group);

	@NameSpace(namespaceClass = Category.class)
	public Category get(Integer id);
}
