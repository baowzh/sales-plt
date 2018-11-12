package com.xunno.sales.modules.system.dao.defaults;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.xunno.sales.modules.system.dao.StaffFuncrightDao;
import com.xunno.sales.modules.system.model.StaffFuncright;

@Repository
public class DefaultStaffFuncrightDao implements StaffFuncrightDao {

	@Autowired
	private DBSqlSession sqlSession;

	@Override
	public int insertStaffFuncright(StaffFuncright staffFuncright) {
		return sqlSession.insert(StaffFuncright.class, staffFuncright);
	}

	@Override
	public int delStaffFuncright(Map<String, Object> param) {
		if (StringUtils.isBlank((String) param.get("rightAttr"))) {
			throw new RuntimeException("删除失败：参数缺失！");
		}
		if (StringUtils.isBlank((String) param.get("staffId"))
				&& StringUtils.isBlank((String) param.get("rightCode"))) {
			throw new RuntimeException("删除失败：参数缺失！");

		}

		return sqlSession.del(StaffFuncright.class, param);
	}

	@Override
	public int[] batchInsertStaffFuncright(List<StaffFuncright> staffFuncrights) {
		StringBuffer insertSql = new StringBuffer();

		insertSql.append(" insert into TF_M_STAFFFUNCRIGHT").append(StaffFuncright.getSqlTypeString()).append(" values")
				.append(StaffFuncright.getSqlValString());

		return sqlSession.batchInsert(insertSql.toString(), staffFuncrights);
	}

	@Override
	public List<StaffFuncright> findStaffFuncrights(Map<String, Object> param) {
		return sqlSession.getData(StaffFuncright.class, param);
	}

}
