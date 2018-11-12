package com.xunno.sales.modules.news.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.NameSpace;
import com.xunno.sales.modules.news.model.News;
import com.xunno.sales.modules.news.model.NewsPost;

@DaoBean
public interface NewsPostRepository {
	@NameSpace(namespaceClass = NewsPost.class)
	public void insert(NewsPost newsPost);

	@NameSpace(namespaceClass = NewsPost.class)
	public void del(@Param(value = "departId") String departId, @Param(value = "id") Integer Id);

	@NameSpace(namespaceClass = NewsPost.class)
	public List<News> getPostedNews(@Param(value = "departId") String departId, @Param(value = "count") Integer count);
}
