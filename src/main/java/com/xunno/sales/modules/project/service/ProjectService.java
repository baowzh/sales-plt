package com.xunno.sales.modules.project.service;

import java.util.List;
import java.util.Map;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.xunno.sales.basic.model.FileContent;
import com.xunno.sales.form.ProjectPagingForm;
import com.xunno.sales.modules.news.model.News;
import com.xunno.sales.modules.project.model.Project;

public interface ProjectService {
	public List<Project> list(Map<String, Object> params);

	public DBPageValue<Project> paging(ProjectPagingForm pagingForm);

	public void save(Project project) throws Exception;

	public void del(String batchId)throws Exception;

	public News get(Integer id);

	public void upload(FileContent excelContent) throws Exception;

	public void townUpload(FileContent excelContent) throws Exception;

	public List<String> getBathIds() ;
}
