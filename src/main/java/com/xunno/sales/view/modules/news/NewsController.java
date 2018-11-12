package com.xunno.sales.view.modules.news;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.util.StringUtil;
import com.xunno.sales.form.NewsPagingForm;
import com.xunno.sales.modules.news.model.News;
import com.xunno.sales.modules.news.service.NewsService;
import com.xunno.sales.view.modules.FileUploadControler;

@Controller
@RequestMapping(value = "news")
public class NewsController extends FileUploadControler {
	@Autowired
	private NewsService newsService;
	private static Logger logger = LoggerFactory.getLogger(NewsController.class);

	@RequestMapping("index")
	public ModelAndView index(ModelMap modelMap) {
		return new ModelAndView("gov/news/index", modelMap);
	}
	
	@RequestMapping("welcome")
	public ModelAndView welcome(ModelMap modelMap) {
		return new ModelAndView("gov/news/index", modelMap);
	}

	@RequestMapping("edit")
	public ModelAndView add(ModelMap modelMap, Integer id) {
		News news = this.newsService.get(id);
		modelMap.put("news", news);
		return new ModelAndView("gov/news/edit", modelMap);
	}

	@ResponseBody
	@RequestMapping("del")
	public Map<String, Object> del(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.newsService.del(id);
			map.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", false);
			map.put("mess", ex.getMessage());
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("toTop")
	public Map<String, Object> toTop(Integer id, Integer top) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.newsService.updateTop(id, top);
			map.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", false);
			map.put("mess", ex.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "paging")
	@ResponseBody
	public DBPageValue<News> paging(NewsPagingForm pagingForm) {
		return this.newsService.paging(pagingForm);
	}

	@RequestMapping("save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> returnInfo = new HashMap<String, Object>();
		try {
			News news = new News();
			String catId = request.getParameter("catId");
			news.setCatId(Integer.valueOf(catId));
			String typeId = request.getParameter("typeId");
			if (!StringUtil.isEmpty(typeId)) {
				news.setTypeId(Integer.valueOf(typeId));
			}
			String title = request.getParameter("title");
			news.setTitle(title);
			String keywords = request.getParameter("keywords");
			news.setKeywords(keywords);
			String description = request.getParameter("description");
			news.setDescription(description);
			String content = request.getParameter("content");
			String id = request.getParameter("id");
			if(!StringUtil.isEmpty(id)){
				news.setId(Integer.parseInt(id));
			}
			news.setContent(content);
			String imgURl = this.wiredFile(request);
			news.setThumb(imgURl);
			newsService.save(news);
			returnInfo.put("success", true);
			modelMap.put("mess", "保存成功。");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存新闻数据出现异常:{}", e);
			modelMap.put("success", false);
			modelMap.put("mess", e.getMessage());
		}
		return new ModelAndView("gov/news/index", modelMap);
	}

}
