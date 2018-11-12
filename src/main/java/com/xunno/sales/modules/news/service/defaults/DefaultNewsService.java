package com.xunno.sales.modules.news.service.defaults;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.xunno.sales.form.NewsPagingForm;
import com.xunno.sales.modules.news.model.News;
import com.xunno.sales.modules.news.model.NewsData;
import com.xunno.sales.modules.news.model.NewsPost;
import com.xunno.sales.modules.news.repository.NewsDataRepository;
import com.xunno.sales.modules.news.repository.NewsPostRepository;
import com.xunno.sales.modules.news.repository.NewsRepository;
import com.xunno.sales.modules.news.service.NewsService;

@Service("newsService")
public class DefaultNewsService implements NewsService {
	@Autowired
	private NewsRepository newsRepository;
	@Autowired
	private NewsDataRepository newsDataRepository;
	@Autowired
	private UserSessionHolderService userSessionHolderService;
	@Autowired
	private NewsPostRepository newsPostRepository;
	private static Logger logger = LoggerFactory.getLogger(DefaultNewsService.class);

	@Override
	public List<News> list(Map<String, Object> params) {

		return newsRepository.list(params);
	}

	@Override
	public DBPageValue<News> paging(NewsPagingForm pagingForm) {

		pagingForm.setDepartId(userSessionHolderService.getSessionUserInfo().getDepartid());
		return newsRepository.paging(pagingForm);
	}

	@Override
	public void save(News news) throws Exception {
		// 校验正确性然后保存
		NewsData newsData = new NewsData();
		newsData.setContent(news.getContent());
		newsData.setId(news.getId());
		UserInfo userInfo = userSessionHolderService.getSessionUserInfo();
		if (news.getId() == null) {
			news.setInputtime(new Date());
			news.setDepartId(userInfo.getDepartid());
			news.setUsername(userInfo.getStaffid());
			this.newsRepository.insert(news);
			newsData.setId(news.getId());

			this.newsDataRepository.insert(newsData);
		} else {
			news.setUpdatetime(new Date());
			this.newsRepository.update(news);
			this.newsDataRepository.update(newsData);
		}
	}

	@Override
	public void del(Integer id) {
		this.newsRepository.del(id);
	}

	@Override
	public News get(Integer id) {
		News news = this.newsRepository.get(id);
		if (news != null) {
			NewsData data = this.newsDataRepository.get(id);
			news.setData(data);
		}
		return news;
	}

	@Override
	public List<News> topNewsByChannel(String departId, Integer cannelId, Integer count) {

		return this.newsRepository.topNewsByChannel(departId, cannelId, count);
	}

	@Override
	public List<News> hotNewsByChannel(String departId, Integer count) {
		return newsPostRepository.getPostedNews(departId, count);

	}

	@Override
	public void updateTop(Integer id, Integer top) throws Exception {
		NewsPost newsPost = new NewsPost();
		newsPost.setDepartId(userSessionHolderService.getSessionUserInfo().getDepartid());
		newsPost.setId(id);
		newsPost.setPostTime(new Date());
		if (top.intValue() == 1) {
			this.newsPostRepository.insert(newsPost);
		} else {
			this.newsPostRepository.del(newsPost.getDepartId(), id);
		}
		this.newsRepository.updateTop(top, id);
	}

	@Override
	public DBPageValue<News> portalPaging(NewsPagingForm pagingForm) {

		return this.newsRepository.paging(pagingForm);
	}

}
