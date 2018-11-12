package com.xunno.sales.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.xunno.sales.modules.system.model.Funcright;


public interface FuncrightDao {

	public void inserFuncright(Funcright funcright);

	public void updateFuncright(Funcright funcright);

	public void deleteFuncright(Funcright funcright);

	public List<Funcright> findFuncright(Map<String, Object> params);
	
	public List<Funcright> findFuncChilds(Map<String, Object> params);

	
	public Funcright findModFuncrightByRightCode(String rightCode);
	
	public DBPageValue<Funcright> findFuncright(DBPagingPrams page);

	
	
}
