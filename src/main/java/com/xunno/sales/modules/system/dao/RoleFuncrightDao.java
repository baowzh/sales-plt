package com.xunno.sales.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.xunno.sales.modules.system.model.RoleFuncright;

public interface RoleFuncrightDao {
	public void insertRoleFuncright(RoleFuncright roleFuncright);

	public int[] batchInsertRoleFuncright(List<RoleFuncright> roleFuncrights);

	public void updateRoleFuncright(RoleFuncright roleFuncright);

	public void deleteRoleFuncright(Map<String, Object> params);

	public List<RoleFuncright> findRoleFuncright(Map<String, Object> params);

	public RoleFuncright findRoleFuncrightByCode(Map<String, Object> params);

}
