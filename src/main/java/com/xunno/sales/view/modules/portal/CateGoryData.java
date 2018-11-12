package com.xunno.sales.view.modules.portal;

import java.util.List;

import com.xunno.sales.modules.category.model.Category;
import com.xunno.sales.modules.news.model.News;

public class CateGoryData {

	private Category category;
	List<News> news;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

}
