package com.xunno.sales.modules.project.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.NameSpace;
import com.asiainfo.eframe.sqlsession.proxy.annotations.Paging;
import com.xunno.sales.form.ProjectPagingForm;
import com.xunno.sales.modules.project.model.Project;

@DaoBean
public interface ProjectRepository {
	@NameSpace( namespaceClass = Project.class)
	public void insert(Project project) throws Exception;

	@NameSpace( namespaceClass = Project.class)
	public void update(Project project) throws Exception;

	@NameSpace( namespaceClass = Project.class)
	public void del(Map<String, Object> params);

	@NameSpace( namespaceClass = Project.class)
	public Project get(String id);

	@NameSpace( namespaceClass = Project.class)
	public Integer departProjectCount(String departId);
    @Paging
	@NameSpace( namespaceClass = Project.class)
	public DBPageValue<Project> paging(ProjectPagingForm pagingForm);

    @NameSpace( namespaceClass = Project.class)
	public List<String> getBatchIds(@Param(value = "departId") String departId,
			@Param(value = "year") String year);
}
