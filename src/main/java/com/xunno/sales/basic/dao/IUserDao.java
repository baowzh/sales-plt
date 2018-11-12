package com.xunno.sales.basic.dao;

import java.util.Map;

import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.NameSpace;
import com.asiainfo.eframe.sqlsession.proxy.annotations.ParamType;

/**
 * 
 * @author baowzh
 *
 */
@DaoBean
public interface IUserDao {
	/**
	 * 获取用户名
	 * 
	 * @return
	 */
	@NameSpace( namespaceClass = String.class, namespace = "user")
	public String getUserName( Map<String, Object> params);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@NameSpace( namespaceClass = String.class, namespace = "user")
	public UserInfo getUserByName( String userName);
}
