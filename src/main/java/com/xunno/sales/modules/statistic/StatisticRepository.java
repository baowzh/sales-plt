package com.xunno.sales.modules.statistic;

import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.asiainfo.eframe.sqlsession.proxy.annotations.DaoBean;
import com.asiainfo.eframe.sqlsession.proxy.annotations.NameSpace;
import com.asiainfo.eframe.sqlsession.proxy.annotations.Paging;

@DaoBean
public interface StatisticRepository {
    @Paging
	@NameSpace( namespace = "statistic")
	public DBPageValue<java.util.Map<String, Object>> pagingCunwgkStatistic(DBPagingPrams pagingParam);
    @Paging
	@NameSpace( namespace = "statistic")
	public DBPageValue<java.util.Map<String, Object>> pagingDwgkStatistic(DBPagingPrams pagingParam);
    @Paging
	@NameSpace( namespace = "statistic")
	public DBPageValue<java.util.Map<String, Object>> pagingZwgkStatistic(DBPagingPrams pagingParam);

}
