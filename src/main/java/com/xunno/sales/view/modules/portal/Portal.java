package com.xunno.sales.view.modules.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.asiainfo.eframe.util.StringUtil;
import com.xunno.sales.form.NewsPagingForm;
import com.xunno.sales.modules.category.CategoryRepository;
import com.xunno.sales.modules.category.PageCategoryRepository;
import com.xunno.sales.modules.category.model.Category;
import com.xunno.sales.modules.category.model.PageCateroty;
import com.xunno.sales.modules.depart.model.Depart;
import com.xunno.sales.modules.depart.service.OrgService;
import com.xunno.sales.modules.news.model.News;
import com.xunno.sales.modules.news.service.NewsService;
import com.xunno.sales.modules.statistic.StatisticRepository;
import com.xunno.sales.util.PortalStaticConstant;

@Controller
@RequestMapping(value = "portal")
public class Portal {
	@Autowired
	private OrgService orgService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private NewsService newsService;
	@Autowired
	private DBSqlSession sqlSession;
	@Autowired
	private PageCategoryRepository pageCategoryRepository;
	@Autowired
	private StatisticRepository statisticRepository;

	/**
	 * 网站首页
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView index(ModelMap modelMap) {
		List<Depart> departs = orgService.getDeparts(1);
		List<Depart> towns = orgService.getDeparts(2);
		modelMap.put("departs", departs);
		modelMap.put("towns", towns);
		return new ModelAndView("gov/portal/index", modelMap);
	}

	@RequestMapping("mszj")
	public ModelAndView mszj(ModelMap modelMap, String departId) {
		Depart depart = getDepart(departId);
		modelMap.put("depart", depart);
		String group = this.getTemplateGroup(depart);
		String pageName = "index" + group;
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_SUPER) {
			Long projectCount = sqlSession.getDataSingle("statistic.getProjectCountTop", new HashMap<String, Object>());
			modelMap.put("projectCount", projectCount);
			Double projectSum = sqlSession.getDataSingle("statistic.getProjectSumTop", new HashMap<String, Object>());
			modelMap.put("projectSum", projectSum);
			Double fundsSum = sqlSession.getDataSingle("statistic.getFundSumTop", new HashMap<String, Object>());
			modelMap.put("fundsSum", fundsSum);
		} else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_DEPART) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("departId", depart.getId());
			params.put("type", depart.getType());
			Long projectCount = sqlSession.getDataSingle("statistic.getDepartProjectCount", params);
			modelMap.put("projectCount", projectCount);
			Double projectSum = sqlSession.getDataSingle("statistic.getDepartProjectSum", params);
			modelMap.put("projectSum", projectSum);
			Double fundsSum = sqlSession.getDataSingle("statistic.getDepartFundSum", params);
			modelMap.put("fundsSum", fundsSum);

		} else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_TOWN) {

			// 获取嘎查村列表
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("departId", depart.getId());
			params.put("type", depart.getType());
			Long projectCount = sqlSession.getDataSingle("statistic.getDepartProjectCount", params);
			modelMap.put("projectCount", projectCount);
			Double projectSum = sqlSession.getDataSingle("statistic.getDepartProjectSum", params);
			modelMap.put("projectSum", projectSum);
			Double fundsSum = sqlSession.getDataSingle("statistic.getDepartFundSum", params);
			modelMap.put("fundsSum", fundsSum);

		} else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_HAMLET) {
			// 获取嘎查村列表
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("departId", depart.getId());
			params.put("type", depart.getType());
			Long projectCount = sqlSession.getDataSingle("statistic.getDepartProjectCount", params);
			modelMap.put("projectCount", projectCount);
			Double projectSum = sqlSession.getDataSingle("statistic.getDepartProjectSum", params);
			modelMap.put("projectSum", projectSum);
			Double fundsSum = sqlSession.getDataSingle("statistic.getDepartFundSum", params);
			modelMap.put("fundsSum", fundsSum);
		}
		return new ModelAndView("gov/portal/msxm/" + pageName, modelMap);
	}

	/**
	 * 各站点主页
	 * 
	 * @param modelMap
	 * @param departId
	 * @return
	 */
	@RequestMapping("main")
	public ModelAndView main(ModelMap modelMap, String departId) throws Exception {
		Depart depart = getDepart(departId);
		String group = this.getTemplateGroup(depart);
		String pageName = "main" + group;
		List<PageCateroty> pageCaterotys = pageCategoryRepository.getPageCategorys(pageName);
		// 获取各栏目下主页更新数据
		for (int i = 0; i < pageCaterotys.size(); i++) {
			PageCateroty pageCateroty = pageCaterotys.get(i);
			Category category = this.categoryRepository.get(pageCateroty.getCatId());
			List<News> news = newsService.topNewsByChannel(depart.getId(), category.getId(),
					PortalStaticConstant.MAIN_PAGE_CAT_NEWS_COUNT);
			CateGoryData cateGoryData = new CateGoryData();
			cateGoryData.setCategory(category);
			cateGoryData.setNews(news);
			modelMap.put(category.getVarName(), cateGoryData);
		}
		modelMap.put("depart", depart);

		// 获取民生项目 // 民生资金 // 到户资金 汇总数

		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_SUPER) {

			List<Map<String, Object>> dwgkStatistic = this.sqlSession.getData("statistic.dwgkStatistic",
					new HashMap<String, Object>());
			List<Map<String, Object>> zwgkStatistic = this.sqlSession.getData("statistic.zwgkStatistic",
					new HashMap<String, Object>());
			List<Map<String, Object>> cwgkStatistic = this.sqlSession.getData("statistic.cwgkStatistic",
					new HashMap<String, Object>());
			List<Map<String, Object>> cunwgkStatistic = this.sqlSession.getData("statistic.cunwgkStatistic",
					new HashMap<String, Object>());

			modelMap.put("dwgkStatistic", dwgkStatistic);
			modelMap.put("zwgkStatistic", zwgkStatistic);
			// 获取 党务排名、//政务排名 村务排名 财务排名
			modelMap.put("cwgkStatistic", cwgkStatistic);
			// 获取 党务排名、//政务排名 村务排名 财务排名
			// 获取 党务排名、//政务排名 村务排名 财务排名
			modelMap.put("cunwgkStatistic", cunwgkStatistic);

			Long projectCount = sqlSession.getDataSingle("statistic.getProjectCountTop", new HashMap<String, Object>());
			modelMap.put("projectCount", projectCount);
			Double projectSum = sqlSession.getDataSingle("statistic.getProjectSumTop", new HashMap<String, Object>());
			modelMap.put("projectSum", projectSum);
			Double fundsSum = sqlSession.getDataSingle("statistic.getFundSumTop", new HashMap<String, Object>());
			modelMap.put("fundsSum", fundsSum);

		} else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_DEPART) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("departId", depart.getId());
			params.put("type", depart.getType());
			Long projectCount = sqlSession.getDataSingle("statistic.getDepartProjectCount", params);
			modelMap.put("projectCount", projectCount);
			Double projectSum = sqlSession.getDataSingle("statistic.getDepartProjectSum", params);
			modelMap.put("projectSum", projectSum);
			Double fundsSum = sqlSession.getDataSingle("statistic.getDepartFundSum", params);
			modelMap.put("fundsSum", fundsSum);

		}

		else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_TOWN) {

			// 获取嘎查村列表
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("departId", depart.getId());
			params.put("type", depart.getType());
			Long projectCount = sqlSession.getDataSingle("statistic.getDepartProjectCount", params);
			modelMap.put("projectCount", projectCount);
			Double projectSum = sqlSession.getDataSingle("statistic.getDepartProjectSum", params);
			modelMap.put("projectSum", projectSum);
			Double fundsSum = sqlSession.getDataSingle("statistic.getDepartFundSum", params);
			modelMap.put("fundsSum", fundsSum);
			List<Depart> departs = this.orgService.getDeparts(depart.getId());
			modelMap.put("departs", departs);
		} else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_HAMLET) {
			// 获取嘎查村列表
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("departId", depart.getId());
			params.put("type", depart.getType());
			Long projectCount = sqlSession.getDataSingle("statistic.getDepartProjectCount", params);
			modelMap.put("projectCount", projectCount);
			Double projectSum = sqlSession.getDataSingle("statistic.getDepartProjectSum", params);
			modelMap.put("projectSum", projectSum);
			Double fundsSum = sqlSession.getDataSingle("statistic.getDepartFundSum", params);
			modelMap.put("fundsSum", fundsSum);
		}
		// 获取推送消息
		List<News> hotNews = this.newsService.hotNewsByChannel(depart.getId(), 5);
		modelMap.put("hotNews", hotNews);
		String typeName = this.getTemplateGroup(depart);
		String templateName = "main" + typeName;
		return new ModelAndView("gov/portal/main/" + templateName, modelMap);
	}

	private String getTemplateGroup(Depart depart) {
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_SUPER) {
			return "";
		} else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_DEPART) {
			return "-depart";
		} else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_TOWN) {
			return "-town";
		} else if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_HAMLET) {
			return "-hamlet";
		} else {
			return "";
		}
	}

	/**
	 * 新闻列表
	 * 
	 * @param modelMap
	 * @param departId
	 * @param catId
	 * @return
	 */
	@RequestMapping("news/list")
	public ModelAndView newsList(ModelMap modelMap, NewsPagingForm pagingForm) {
		pagingForm.setPagesize(10);
		Depart depart = getDepart(this.getDepartId(pagingForm));
		Category category = this.categoryRepository.get(pagingForm.getCatId());
		List<Category> childs = new ArrayList<Category>();
		if (category.getParentId() == null) { // 没有上级则
			childs = this.categoryRepository.getSubCategorys(pagingForm.getCatId(), depart.getType());
			modelMap.put("childs", childs);
		} else {
			childs = this.categoryRepository.getSubCategorys(category.getParentId(), depart.getType());
			modelMap.put("childs", childs);
		}
		Category currentCategory = this.getCurrentCateGory(category, depart);
		// if (pagingForm.getDepartId() == null) {
		pagingForm.setDepartId(depart.getId());
		// }
		pagingForm.setCatId(currentCategory.getId());
		DBPageValue<News> newsPage = this.newsService.portalPaging(pagingForm);
		modelMap.put("news", newsPage.getModels());
		modelMap.put("totalRecord", newsPage.getiTotalRecords());
		modelMap.put("pageindex", pagingForm.getPageindex());
		modelMap.put("depart", depart);
		modelMap.put("parentCateGory", this.getParentCateGory(category, depart));
		modelMap.put("currentCategory", currentCategory);
		// 如果是空则获取热门文章
		if (childs.isEmpty()) {
			modelMap.put("hotNews",
					this.newsService.hotNewsByChannel(depart.getId(), PortalStaticConstant.LIST_PAGE_CAT_NEWS_COUNT));
		}
		String group = this.getTemplateGroup(depart);
		String templatename = "newslist" + group;
		return new ModelAndView("gov/portal/newslist/" + templatename, modelMap);
	}

	private String getDepartId(NewsPagingForm pagingForm) {
		if (pagingForm.getDepartId() != null) {
			return pagingForm.getDepartId().split(",")[0];
		}
		return null;
	}

	/**
	 * 通过标签获取上一篇和下一篇
	 * 
	 * @param modelMap
	 * @param newId
	 * @return
	 */
	@RequestMapping("news/detail")
	public ModelAndView newsdetail(ModelMap modelMap, String departId, Integer id) {
		Depart depart = getDepart(departId);
		modelMap.put("depart", depart);
		News news = this.newsService.get(id);
		Integer cateId = news.getTypeId() == null ? news.getCatId() : news.getTypeId();
		Category category = this.categoryRepository.get(cateId);
		Category currentCategory = this.getCurrentCateGory(category, depart);
		modelMap.put("parentCateGory", this.getParentCateGory(currentCategory, depart));
		modelMap.put("currentCategory", currentCategory);
		modelMap.put("news", news);
		String group = this.getTemplateGroup(depart);
		String templatename = "detail" + group;
		return new ModelAndView("gov/portal/detail/" + templatename, modelMap);
	}

	/**
	 * 
	 * @param category
	 * @param depart
	 * @return
	 */
	private Category getParentCateGory(Category category, Depart depart) {
		if (category.getParentId() == null) {
			return category;
		} else {

			return this.categoryRepository.get(category.getParentId());

		}
	}

	private Category getCurrentCateGory(Category category, Depart depart) {

		Category currentCategory = new Category();
		List<Category> childs = new ArrayList<Category>();
		if (category.getParentId() == null) { // 没有上级则
			childs = this.categoryRepository.getSubCategorys(category.getId(), depart.getType());
			if (childs.isEmpty()) {
				currentCategory = category;
			} else {
				currentCategory = childs.get(0);
			}

		} else {
			currentCategory = category;
		}
		return currentCategory;

	}

	/**
	 * 项目列表-分组显示
	 * 
	 * @param modelMap
	 * @param departId
	 * @return
	 */
	@RequestMapping("projectGroup")
	public ModelAndView projectGroup(ModelMap modelMap, String departId) {
		Depart depart = this.getDepart(departId);
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_SUPER) {
			// 按照部门和 苏木乡镇 分组获取
			List<Map<String, Object>> projects = sqlSession.getData("statistic.getProjectCountByDepart",
					new HashMap<String, Object>());
			modelMap.put("projectsByDepart", projects);
			List<Map<String, Object>> townProjects = this.sqlSession.getData("statistic.getProjectCountByTown",
					new HashMap<String, Object>());
			modelMap.put("projectsByTown", townProjects);
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_DEPART) {

			List<Map<String, Object>> departProhectDetail = this.sqlSession.getData("statistic.getdepartProhectDetail",
					depart.getId());
			modelMap.put("departProhectDetail", departProhectDetail);
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_TOWN) {
			// 按照嘎查村分组
			// 按照部门和 苏木乡镇 分组获取
			List<Map<String, Object>> projects = sqlSession.getData("statistic.getProjectCountByTowmId", departId);
			modelMap.put("projectsByDepart", projects);

		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_HAMLET) {
			// 直接显示明细
			List<Map<String, Object>> departProhectDetail = this.sqlSession.getData("statistic.getdepartProhectDetail",
					depart.getId());
			modelMap.put("departProhectDetail", departProhectDetail);

		}
		modelMap.put("depart", depart);
		String group = this.getTemplateGroup(depart);
		String templatename = "projectcount" + group;
		return new ModelAndView("gov/portal/project/" + templatename, modelMap);
	}

	/**
	 * 项目列表
	 * 
	 * @param modelMap
	 * @param departId
	 * @return
	 */
	@RequestMapping("projectList")
	public ModelAndView projectList(ModelMap modelMap, String departId) {
		Depart depart = this.getDepart(departId);
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_SUPER) {
			// 按照部门和 苏木乡镇 分组获取
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_DEPART) {
			// 获取部门明细
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_TOWN) {
			// 按照嘎查村分组
		}

		return new ModelAndView("portal/index", modelMap);
	}

	/**
	 * 项目列表-分组显示
	 * 
	 * @param modelMap
	 * @param departId
	 * @return
	 */
	@RequestMapping("projectFundsGroup")
	public ModelAndView projectFundsGroup(ModelMap modelMap, String departId) {

		Depart depart = this.getDepart(departId);
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_SUPER) {
			// 按照部门和 苏木乡镇 分组获取
			List<Map<String, Object>> projects = sqlSession.getData("statistic.getProjectSumByDepart",
					new HashMap<String, Object>());
			modelMap.put("projectsByDepart", projects);
			List<Map<String, Object>> townProjects = this.sqlSession.getData("statistic.getProjectSumByTown",
					new HashMap<String, Object>());
			modelMap.put("projectsByTown", townProjects);
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_DEPART) {

			List<Map<String, Object>> departProhectDetail = this.sqlSession.getData("statistic.getdepartProhectDetail",
					depart.getId());
			modelMap.put("departProhectDetail", departProhectDetail);
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_TOWN) {
			// 按照嘎查村分组
			// 按照部门和 苏木乡镇 分组获取
			List<Map<String, Object>> projects = sqlSession.getData("statistic.getProjectSumByTowmId", departId);
			modelMap.put("projectsByDepart", projects);
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_HAMLET) {
			// 直接显示明细
			List<Map<String, Object>> departProhectDetail = this.sqlSession.getData("statistic.getdepartProhectDetail",
					depart.getId());
			modelMap.put("departProhectDetail", departProhectDetail);

		}
		modelMap.put("depart", depart);
		String group = this.getTemplateGroup(depart);
		String templatename = "projectfunds" + group;
		return new ModelAndView("gov/portal/projectfunds/" + templatename, modelMap);

	}

	/**
	 * 项目列表
	 * 
	 * @param modelMap
	 * @param departId
	 * @return
	 */
	@RequestMapping("projectFundsList")
	public ModelAndView projectFundsList(ModelMap modelMap, String departId) {
		Depart depart = this.getDepart(departId);
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_SUPER) {
			// 按照部门和 苏木乡镇 分组获取
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_DEPART) {
			// 获取部门明细
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_TOWN) {
			// 按照嘎查村分组
		}

		return new ModelAndView("portal/index", modelMap);
	}

	/**
	 * 项目列表
	 * 
	 * @param modelMap
	 * @param departId
	 * @return
	 */
	@RequestMapping("fundsGroup")
	public ModelAndView fundsList(ModelMap modelMap, String departId) {
		Depart depart = this.getDepart(departId);
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_SUPER) {
			// 按照部门和 苏木乡镇 分组获取
			List<Map<String, Object>> projects = sqlSession.getData("statistic.getFundByDepart",
					new HashMap<String, Object>());
			modelMap.put("projectsByDepart", projects);
			List<Map<String, Object>> townProjects = this.sqlSession.getData("statistic.getFundByTown",
					new HashMap<String, Object>());
			modelMap.put("projectsByTown", townProjects);
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_DEPART) {

			List<Map<String, Object>> departProhectDetail = this.sqlSession.getData("statistic.getdepartFundDetail",
					depart.getId());
			modelMap.put("departProhectDetail", departProhectDetail);
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_TOWN) {
			// 按照嘎查村分组
			// 按照部门和 苏木乡镇 分组获取
			List<Map<String, Object>> projects = sqlSession.getData("statistic.getFundsSumByTowmId", departId);
			modelMap.put("projectsByDepart", projects);
		}
		if (depart.getType() == PortalStaticConstant.DepartType.DEPART_TYPE_HAMLET) {
			// 直接显示明细
			List<Map<String, Object>> departProhectDetail = this.sqlSession.getData("statistic.getdepartFundDetail",
					depart.getId());
			modelMap.put("departProhectDetail", departProhectDetail);

		}
		modelMap.put("depart", depart);
		String group = this.getTemplateGroup(depart);
		String templatename = "funds" + group;
		return new ModelAndView("gov/portal/funds/" + templatename, modelMap);
	}

	private Depart getDepart(String departId) {
		if (StringUtil.isEmpty(departId)) {
			departId = PortalStaticConstant.DEFAULT_SUPER_DEPARTID;
		}
		Depart depart = orgService.getDepart(departId);
		return depart;
	}

	@RequestMapping("pagingCunwgkStatistic")
	public ModelAndView pagingCunwgkStatistic(ModelMap modelMap, DBPagingPrams pagingParam) {
		if(pagingParam.getPagesize()==20||pagingParam.getPagesize()==null){
			pagingParam.setPagesize(10);
		}
		DBPageValue<java.util.Map<String, Object>> page = this.statisticRepository.pagingCunwgkStatistic(pagingParam);
		modelMap.put("page", page);
		modelMap.put("dest", "pagingCunwgkStatistic.jhtml");
		return new ModelAndView("gov/portal/statistic/statistic", modelMap);
	}

	@RequestMapping("pagingDwgkStatistic")
	public ModelAndView pagingDwgkStatistic(ModelMap modelMap, DBPagingPrams pagingParam) {
		if(pagingParam.getPagesize()==20||pagingParam.getPagesize()==null){
			pagingParam.setPagesize(10);
		}
		DBPageValue<java.util.Map<String, Object>> page = this.statisticRepository.pagingDwgkStatistic(pagingParam);
		modelMap.put("page", page);
		modelMap.put("dest", "pagingDwgkStatistic.jhtml");
		return new ModelAndView("gov/portal/statistic/statistic", modelMap);
	}

	@RequestMapping("pagingZwgkStatistic")
	public ModelAndView pagingZwgkStatistic(ModelMap modelMap, DBPagingPrams pagingParam) {
		if(pagingParam.getPagesize()==20||pagingParam.getPagesize()==null){
			pagingParam.setPagesize(10);
		}
		DBPageValue<java.util.Map<String, Object>> page = this.statisticRepository.pagingZwgkStatistic(pagingParam);
		modelMap.put("page", page);
		modelMap.put("dest", "pagingZwgkStatistic.jhtml");
		return new ModelAndView("gov/portal/statistic/statistic", modelMap);
	}

}
