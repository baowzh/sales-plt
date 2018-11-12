package com.xunno.sales.basic.dao;

import java.util.List;
import java.util.Map;

import com.asiainfo.eframe.security.model.RoleInfo;
import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.NameSpace;
@DaoBean
public interface IRoleDao {
	@NameSpace( namespaceClass = String.class, namespace = "role")
	public List<RoleInfo> getRoleInfosByUsername(Map<String, Object> params);
}
