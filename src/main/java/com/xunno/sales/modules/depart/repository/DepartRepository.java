package com.xunno.sales.modules.depart.repository;

import java.util.List;

import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.NameSpace;
import com.xunno.sales.modules.depart.model.Depart;

@DaoBean
public interface DepartRepository {
	@NameSpace( namespaceClass = Depart.class)
	public void insert(Depart depart) throws Exception;

	@NameSpace( namespaceClass = Depart.class)
	public void update(Depart depart) throws Exception;

	@NameSpace( namespaceClass = Depart.class)
	public void del(String id);

	@NameSpace( namespaceClass = Depart.class)
	public Depart get(String id);

	@NameSpace( namespaceClass = Depart.class)
	public List<Depart> list(String parentId);

	@NameSpace( namespaceClass = Depart.class)
	public List<Depart> getDeparts(Integer type);
	@NameSpace( namespaceClass = Depart.class)
	public Integer childCount(String parentId);

}
