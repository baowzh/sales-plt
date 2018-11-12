package com.xunno.sales.modules.funds.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.NameSpace;
import com.asiainfo.eframe.sqlsession.proxy.annotations.Paging;
import com.xunno.sales.form.FoundsPagingForm;
import com.xunno.sales.modules.funds.mdoel.Funds;

@DaoBean
public interface FundsRepository {
	@NameSpace(namespaceClass = Funds.class)
	public void insert(Funds founds) throws Exception;

	@NameSpace(namespaceClass = Funds.class)
	public void update(Funds Founds) throws Exception;

	@NameSpace(namespaceClass = Funds.class)
	public void del(Map<String, Object> params);

	@NameSpace(namespaceClass = Funds.class)
	public Funds get(String id);

	@NameSpace(namespaceClass = Funds.class)
	public Integer departFoundsCount(String departId);

	@Paging
	@NameSpace(namespaceClass = Funds.class)
	public DBPageValue<Funds> paging(FoundsPagingForm pagingForm);

	@NameSpace(namespaceClass = Funds.class)
	public List<String> getBatchIds(@Param(value = "departId") String departId, @Param(value = "year") String year);
}
