package com.xunno.sales.modules.system.service;

import java.util.List;

import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.xunno.sales.modules.system.model.Funcright;

public interface FuncrightService {
	
	public void saveFunc(Funcright funcright,UserInfo userInfo);

	public void removeFuncright(Funcright funcright);

	public Funcright getFuncrightByrightCode(String rightCode);

	public List<Funcright> getAllFuncright();
	
	public List<Funcright> getAllMenuFuncright();


	public List<Funcright> getFuncByClassCode(String classCode);
	
	public List<Funcright> getFuncsChilds(String rightName);
	
	public void removeFuncByModeCode(String classCode,String modeCode);
	
	public DBPageValue<Funcright> findFuncright(DBPagingPrams page, Funcright funcright);


}
