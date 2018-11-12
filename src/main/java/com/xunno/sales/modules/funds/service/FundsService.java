package com.xunno.sales.modules.funds.service;

import java.util.List;
import java.util.Map;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.xunno.sales.basic.model.FileContent;
import com.xunno.sales.form.FoundsPagingForm;
import com.xunno.sales.modules.funds.mdoel.Funds;
import com.xunno.sales.modules.news.model.News;

public interface FundsService {
	public List<Funds> list(Map<String, Object> params);

	public DBPageValue<Funds> paging(FoundsPagingForm pagingForm);

	public void save(Funds founds) throws Exception;

	public void del(String batchId)throws Exception;

	public News get(Integer id);

	public void upload(FileContent excelContent) throws Exception;

	public void townUpload(FileContent excelContent) throws Exception;

	public List<String> getBathIds() ;
}
