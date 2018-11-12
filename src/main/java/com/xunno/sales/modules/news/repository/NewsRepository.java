package com.xunno.sales.modules.news.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.Paging;
import com.xunno.sales.form.NewsPagingForm;
import com.xunno.sales.modules.news.model.News;

@DaoBean
public interface NewsRepository {

	public List<News> list(Map<String, Object> params);

	@Paging

	public DBPageValue<News> paging(NewsPagingForm pagingForm);

	public void insert(News news) throws Exception;

	public void update(News news) throws Exception;

	public void del(Integer id);

	public News get(Integer id);

	public Integer getDepartNewsCount(String departId);

	/**
	 * 
	 * @param cannelId
	 * @param count
	 * @return
	 */

	public List<News> topNewsByChannel(@Param(value = "departId") String departId,
			@Param(value = "cannelId") Integer cannelId, @Param(value = "count") Integer count);

	public List<News> hotNewsByChannel(@Param(value = "departId") String departId,
			@Param(value = "count") Integer count);

	public void updateTop(@Param(value = "top") Integer top, @Param(value = "id") Integer id);

}
