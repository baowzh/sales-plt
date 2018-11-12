package com.xunno.sales.modules.news.service;

import java.util.List;
import java.util.Map;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.xunno.sales.form.NewsPagingForm;
import com.xunno.sales.modules.news.model.News;

public interface NewsService {
	/**
	 * 
	 * @param cannelId
	 * @return
	 */
	public List<News> topNewsByChannel(String departId, Integer cannelId, Integer count);

	public List<News> hotNewsByChannel(String departId, Integer count);

	public List<News> list(Map<String, Object> params);

	public DBPageValue<News> paging(NewsPagingForm pagingForm);
	
	public DBPageValue<News> portalPaging(NewsPagingForm pagingForm);

	public void save(News news) throws Exception;

	public void del(Integer id);

	public News get(Integer id);

	public void updateTop(Integer id, Integer top) throws Exception;

}
