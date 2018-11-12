package com.xunno.sales.modules.system.service;

import java.util.List;

import com.asiainfo.eframe.security.model.UserInfo;
import com.xunno.sales.modules.system.model.RoleFuncright;


public interface RoleFuncrightService {

	public void saveRoleFuncright(RoleFuncright roleFuncright);
	
	public void saveRoleFuncright(String rolecode,String funcCodes,UserInfo userInfo);


	public void removeRoleFuncright(RoleFuncright roleFuncright);

	public RoleFuncright getRFByCode(String roleCode,String rightCode);

	public List<RoleFuncright> getRFByRoleCode(String roleCode);

	public List<RoleFuncright> getAllRoleFuncright();
	
	public void removeRFByRoleCode(String roleCode);
	
	public void removeRFByRightCode(String rightCode);
	
	public void removeRFByCode(String roleCode,String rightCode);
	
}
