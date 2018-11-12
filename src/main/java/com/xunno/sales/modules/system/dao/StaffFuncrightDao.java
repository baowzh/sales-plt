package com.xunno.sales.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.xunno.sales.modules.system.model.StaffFuncright;

public interface StaffFuncrightDao {
	
	public int insertStaffFuncright(StaffFuncright staffFuncright);

	public int delStaffFuncright(Map<String, Object> param);
	
	public int[] batchInsertStaffFuncright(List<StaffFuncright> staffFuncrights);
	
	public List<StaffFuncright> findStaffFuncrights(Map<String, Object> param);
	
	
}
