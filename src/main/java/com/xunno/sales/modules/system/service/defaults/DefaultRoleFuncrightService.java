package com.xunno.sales.modules.system.service.defaults;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.eframe.security.model.UserInfo;
import com.xunno.sales.modules.system.dao.RoleFuncrightDao;
import com.xunno.sales.modules.system.model.Funcright;
import com.xunno.sales.modules.system.model.RoleFuncright;
import com.xunno.sales.modules.system.service.FuncrightService;
import com.xunno.sales.modules.system.service.RoleFuncrightService;

@Service("RoleFuncrightService")
public class DefaultRoleFuncrightService implements RoleFuncrightService {

	@Autowired
	private RoleFuncrightDao roleFuncrightDao;

	private FuncrightService funcrightService;

	@Override
	public void saveRoleFuncright(RoleFuncright roleFuncright) {
		if (roleFuncright == null) {
			throw new RuntimeException("参数错误：参数不能为空");
		}
		String roleCode = roleFuncright.getRoleCode();
		String rightCode = roleFuncright.getRightCode();

		if (StringUtils.isBlank(roleCode) || StringUtils.isAllLowerCase(rightCode)) {
			throw new RuntimeException("参数错误：角色编码或权限编码不能为空");
		}

		Funcright funcright = this.getFuncrightService().getFuncrightByrightCode(rightCode);
		if (funcright == null) {
			throw new RuntimeException("保存错误：无法查找到对应的权限");
		}

		// TODO 校验对应的角色

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rightCode", rightCode);
		params.put("roleCode", roleCode);

		RoleFuncright oldRoleFuncright = this.getRoleFuncrightDao().findRoleFuncrightByCode(params);
		if (oldRoleFuncright == null) {
			this.getRoleFuncrightDao().updateRoleFuncright(roleFuncright);
		} else {
			this.getRoleFuncrightDao().insertRoleFuncright(roleFuncright);
		}

	}

	@Override
	public void removeRoleFuncright(RoleFuncright roleFuncright) {
		if (roleFuncright == null) {
			throw new RuntimeException("参数错误：参数不能为空");

		}
		String roleCode = roleFuncright.getRoleCode();
		String rightCode = roleFuncright.getRightCode();

		if (StringUtils.isBlank(roleCode) || StringUtils.isAllLowerCase(rightCode)) {
			throw new RuntimeException("参数错误：角色编码或权限编码不能为空");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rightCode", rightCode);
		params.put("roleCode", roleCode);

		this.getRoleFuncrightDao().deleteRoleFuncright(params);

	}

	@Override
	public RoleFuncright getRFByCode(String roleCode, String rightCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rightCode", rightCode);
		params.put("roleCode", roleCode);
		if (StringUtils.isBlank(roleCode) || StringUtils.isAllLowerCase(rightCode)) {
			throw new RuntimeException("参数错误：角色编码或权限编码不能为空");

		}

		return this.getRoleFuncrightDao().findRoleFuncrightByCode(params);
	}

	@Override
	public List<RoleFuncright> getRFByRoleCode(String roleCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleCode", roleCode);
		if (StringUtils.isBlank(roleCode)) {
			throw new RuntimeException("参数错误：角色编码不能为空");
		}

		return this.getRoleFuncrightDao().findRoleFuncright(params);
	}

	@Override
	public List<RoleFuncright> getAllRoleFuncright() {
		return this.getRoleFuncrightDao().findRoleFuncright(new HashMap<String, Object>());
	}

	@Override
	public void removeRFByRoleCode(String roleCode) {

		if (StringUtils.isBlank(roleCode)) {
			throw new RuntimeException("参数错误：角色编码不能为空");

		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleCode", roleCode);

		this.getRoleFuncrightDao().deleteRoleFuncright(params);

	}

	@Override
	public void removeRFByRightCode(String rightCode) {
		if (StringUtils.isBlank(rightCode)) {
			throw new RuntimeException("参数错误：权限编码不能为空");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rightCode", rightCode);

		this.getRoleFuncrightDao().deleteRoleFuncright(params);
	}

	@Override
	public void removeRFByCode(String roleCode, String rightCode) {
		if (StringUtils.isBlank(roleCode) || StringUtils.isAllLowerCase(rightCode)) {
			throw new RuntimeException("参数错误：角色编码或权限编码不能为空");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rightCode", rightCode);
		params.put("roleCode", roleCode);

		this.getRoleFuncrightDao().deleteRoleFuncright(params);
	}

	public RoleFuncrightDao getRoleFuncrightDao() {
		return roleFuncrightDao;
	}

	public void setRoleFuncrightDao(RoleFuncrightDao roleFuncrightDao) {
		this.roleFuncrightDao = roleFuncrightDao;
	}

	public FuncrightService getFuncrightService() {
		return funcrightService;
	}

	public void setFuncrightService(FuncrightService funcrightService) {
		this.funcrightService = funcrightService;
	}

	@Override
	public void saveRoleFuncright(String rolecode, String funcCodes,UserInfo userInfo) {

		if (rolecode == null) {
			throw new RuntimeException("参数错误：角色编码不能为空");
		}
		
		Map<String, Object> deleteRoleParam = new HashMap<String, Object>();
		deleteRoleParam.put("roleCode", rolecode);
		roleFuncrightDao.deleteRoleFuncright(deleteRoleParam);

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr =  format.format(date);
		
		if (funcCodes != null && !funcCodes.trim().isEmpty()) {
			funcCodes = funcCodes.trim();
			List<RoleFuncright> roleFuncrights = new ArrayList<RoleFuncright>();

			if (funcCodes.contains(",")) {
				List<String> funcCodelist = Arrays.asList(funcCodes.split(","));

				for (String funcCode : funcCodelist) {
					RoleFuncright roleFuncright = new RoleFuncright();
					roleFuncright.setRightCode(funcCode);
					roleFuncright.setRoleCode(rolecode);
//					roleFuncright.setExtendValue2(1231223L);

					roleFuncright.setUpdateTime(date); 
					roleFuncright.setUpdateStaffId(userInfo.getStaffid());
					roleFuncright.setUpdateDepartId(userInfo.getDepartid());
					
					roleFuncrights.add(roleFuncright);
				}
				roleFuncrightDao.batchInsertRoleFuncright(roleFuncrights);

			} else {
				RoleFuncright roleFuncright = new RoleFuncright();
				roleFuncright.setRoleCode(rolecode);
				roleFuncright.setUpdateTime(date);
				roleFuncright.setUpdateStaffId(userInfo.getStaffid());
				roleFuncright.setRightCode(funcCodes);
				roleFuncright.setRoleCode(rolecode);
				roleFuncrightDao.insertRoleFuncright(roleFuncright);
			}
			
		}

	}

}
