package com.xunno.sales.modules.depart.service;

import java.util.List;

import com.xunno.sales.modules.depart.model.Depart;

public interface OrgService {
	public void save(Depart depart) throws Exception;

	public List<Depart> getDeparts(String departid);

	public List<Depart> getDeparts(Integer type);

	public Depart getDepart(String departId);
	
	public void del(String departId) throws Exception;
}
