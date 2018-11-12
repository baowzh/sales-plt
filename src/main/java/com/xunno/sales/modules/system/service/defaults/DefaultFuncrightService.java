package com.xunno.sales.modules.system.service.defaults;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.xunno.sales.modules.system.dao.FuncrightDao;
import com.xunno.sales.modules.system.model.Funcright;
import com.xunno.sales.modules.system.service.FuncrightService;
import com.xunno.sales.modules.system.service.RoleFuncrightService;

@Service("FuncrightService")
public class DefaultFuncrightService implements FuncrightService {

	@Autowired
	private FuncrightDao funcrightDao;

	@Autowired
	private RoleFuncrightService roleFuncrightService;

	@Override
	public void saveFunc(Funcright funcright,UserInfo userInfo) {

		if (funcright == null) {
			throw new RuntimeException("参数错误：入参不能为空");
		}

		String rightCode = funcright.getRightCode();
		String modCode = funcright.getModCode();

		if (StringUtils.isBlank(modCode)) {
			throw new RuntimeException("参数错误：模块编码不能为空");
		}
		if (StringUtils.isBlank(funcright.getClassCode())) {
			throw new RuntimeException("参数错误：分类编码不能为空");
		}
		
		 Date date = new Date();
		
		funcright.setUpdateTime(date);
		funcright.setUpdateStaffId(userInfo.getStaffid());
		funcright.setUpdateDepartId(userInfo.getDepartid());
		
		if (StringUtils.isBlank(rightCode)) {
			funcright.setRightCode(modCode + "_" + funcright.getClassCode());
			this.getFuncrightDao().inserFuncright(funcright);
		} else {
			Funcright oldFuncright = this.getFuncrightDao().findModFuncrightByRightCode(rightCode);
			if (oldFuncright == null) {
				throw new RuntimeException("修改失败：功能原数据不存在");
			}

			if (!oldFuncright.equals(funcright)) {
				this.getFuncrightDao().updateFuncright(funcright);
			}
		}

	}

	@Override
	public void removeFuncright(Funcright funcright) {
		if (funcright == null) {
			throw new RuntimeException("参数错误：入参不能为空");
		}

		this.getFuncrightDao().deleteFuncright(funcright);
	}

	@Override
	public Funcright getFuncrightByrightCode(String rightCode) {
		if (rightCode == null) {
			throw new RuntimeException("参数错误：功能编码不能为空");
		}

		return this.getFuncrightDao().findModFuncrightByRightCode(rightCode);
	}

	@Override
	public List<Funcright> getAllFuncright() {
		return this.getFuncrightDao().findFuncright(new HashMap<String, Object>());
	}
	
	@Override
	public List<Funcright> getAllMenuFuncright() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("rightAttr", "0");
		
		return this.getFuncrightDao().findFuncright(param);
	}

	@Override
	public List<Funcright> getFuncByClassCode(String classCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("classCode", classCode);
		return this.getFuncrightDao().findFuncright(params);
	}

	@Override
	public void removeFuncByModeCode(String classCode, String modeCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("classCode", classCode);
		params.put("modeCode", modeCode);
		List<Funcright> funcrights = this.getFuncrightDao().findFuncright(params);
		if (funcrights != null && !funcrights.isEmpty()) {
			Funcright funcright = funcrights.get(0);
			String rightCode = funcright.getRightCode();
			this.getFuncrightDao().deleteFuncright(funcrights.get(0));

			this.getRoleFuncrightService().removeRFByRightCode(rightCode);

		}

	}

	public FuncrightDao getFuncrightDao() {
		return funcrightDao;
	}

	public void setFuncrightDao(FuncrightDao funcrightDao) {
		this.funcrightDao = funcrightDao;
	}

	public RoleFuncrightService getRoleFuncrightService() {
		return roleFuncrightService;
	}

	public void setRoleFuncrightService(RoleFuncrightService roleFuncrightService) {
		this.roleFuncrightService = roleFuncrightService;
	}

	@Override
	public DBPageValue<Funcright> findFuncright(DBPagingPrams page, Funcright funcright) {
		return this.getFuncrightDao().findFuncright(page);
	}

	@Override
	public List<Funcright> getFuncsChilds(String rightName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rightName", rightName);
		return this.getFuncrightDao().findFuncChilds(params);
	}

}
