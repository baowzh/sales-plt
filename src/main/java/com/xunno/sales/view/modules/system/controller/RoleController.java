package com.xunno.sales.view.modules.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.xunno.sales.exception.DeleteRoleException;
import com.xunno.sales.modules.system.model.Funcright;
import com.xunno.sales.modules.system.model.Role;
import com.xunno.sales.modules.system.model.RoleFuncright;
import com.xunno.sales.modules.system.service.FuncrightService;
import com.xunno.sales.modules.system.service.RoleFuncrightService;
import com.xunno.sales.modules.system.service.SystemService;
import com.xunno.sales.view.modules.system.bean.BasicTree;
import com.xunno.sales.view.modules.system.bean.RoleDBPagingPrams;
import com.xunno.sales.view.modules.system.bean.SubmitRest;

@Controller
@RequestMapping(value = "system/role")
public class RoleController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserSessionHolderService contextHolderService;

	@Autowired
	private RoleFuncrightService rolefuncrightService;

	@Autowired
	private FuncrightService funcrightService;

	@RequestMapping("index")
	public ModelAndView index(ModelMap modelMap) {
		return new ModelAndView("system/role/index", modelMap);
	}

	@RequestMapping("updateInfo")
	public ModelAndView updateRole(String rightCode, ModelMap modelMap) {
		Role role = this.systemService.getRole(rightCode);

		modelMap.put("role", role);

		return new ModelAndView("system/role/updateInfo", modelMap);
	}

	@RequestMapping("addInfo")
	public ModelAndView add(ModelMap modelMap) {
		return new ModelAndView("system/role/addInfo", modelMap);
	}

	@RequestMapping("saveRole")
	@ResponseBody
	public SubmitRest saveRole(Role role, ModelMap modelMap) {

		UserInfo userInfo = contextHolderService.getSessionUserInfo();
		SubmitRest saveRoleRest = new SubmitRest();

		try {

			role.setUpdateStaffId(userInfo.getStaffid());
			role.setUpdateDepartId(userInfo.getDepartid());
			role.setUpdateTime(new Date());

			this.systemService.saveRole(role);

			saveRoleRest.setSuccess(true);
			saveRoleRest.setMessage("角色保存成功");
		} catch (RuntimeException e) {
			saveRoleRest.setSuccess(false);
			saveRoleRest.setMessage("角色保存失败!!!：\n" + e.getMessage());
			logger.error("saveRole error ", e);
		}

		return saveRoleRest;

	}

	@RequestMapping("forcDeleteInfo")
	@ResponseBody
	public SubmitRest forcDeleteInfo(String rightCode, ModelMap modelMap) {
		SubmitRest delteRoleRest = new SubmitRest();

		try {
			Role role = this.systemService.getRole(rightCode);

			this.systemService.forcDeleteRole(role);

			delteRoleRest.setSuccess(true);
			delteRoleRest.setMessage("角色删除成功");
		} catch (RuntimeException e) {
			delteRoleRest.setSuccess(false);
			delteRoleRest.setMessage("角色删除失败!!!：\n" + e.getMessage());
			logger.error("forcDeleteInfo error ", e);
		}

		return delteRoleRest;

	}

	@RequestMapping("deleteInfo")
	@ResponseBody
	public SubmitRest deleteInfo(String rightCode, ModelMap modelMap) {

		SubmitRest delteRoleRest = new SubmitRest();

		try {
			Role role = this.systemService.getRole(rightCode);

			this.systemService.deleteRole(role);

			delteRoleRest.setSuccess(true);
			delteRoleRest.setMessage("角色删除成功");
		} catch (DeleteRoleException e) {
			delteRoleRest.setSuccess(false);
			delteRoleRest.setErrorCode(1);
			delteRoleRest.setMessage("角色删除失败!!!\n" + e.getMessage());
		} catch (RuntimeException e) {
			delteRoleRest.setSuccess(false);
			delteRoleRest.setMessage("角色删除失败!!!\n" + e.getMessage());
			logger.error("deleteInfo error ", e);
		}

		return delteRoleRest;

	}

	@RequestMapping("roleFuncManager")
	public ModelAndView roleFuncManager(String rightCode, ModelMap modelMap) {

		Role role = systemService.getRole(rightCode);
		if (role == null) {
			throw new RuntimeException("用户信息查询失败");
		}
		modelMap.put("roleCode", role.getRightcode());

		return new ModelAndView("system/role/roleFuncManager", modelMap);
	}

	@RequestMapping("updateRoleFunc")
	public ModelAndView updateRoleFunc(String rightCode, ModelMap modelMap) {

		Role role = systemService.getRole(rightCode);
		if (role == null) {
			throw new RuntimeException("用户信息查询失败");
		}
		StringBuffer funcCodes = new StringBuffer();
		List<RoleFuncright> roleFuncrights = rolefuncrightService.getRFByRoleCode(rightCode);
		if (roleFuncrights != null) {
			for (RoleFuncright roleFuncright : roleFuncrights) {
				funcCodes.append(roleFuncright.getRightCode()).append(",");
			}
		}

		modelMap.put("role", role);
		modelMap.put("funcCodes", funcCodes.toString().replaceAll(",$", ""));
		return new ModelAndView("system/role/updateRoleFunc", modelMap);
	}

	@RequestMapping("saveRoleFunc")
	@ResponseBody
	public SubmitRest saveRoleFunc(String rightcode, String funcCodes, ModelMap modelMap) {

		UserInfo userInfo = contextHolderService.getSessionUserInfo();
		SubmitRest saveRoleFuncRest = new SubmitRest();
		if ("--".equals(funcCodes)) {
			saveRoleFuncRest.setMessage("没有任何修改，无需进行保存");
			saveRoleFuncRest.setSuccess(false);
			return saveRoleFuncRest;
		}

		try {
			rolefuncrightService.saveRoleFuncright(rightcode, funcCodes, userInfo);
			saveRoleFuncRest.setSuccess(true);
			saveRoleFuncRest.setMessage("权限分配成功！");
		} catch (RuntimeException e) {
			logger.error("saveRoleFunc error:", e);
			saveRoleFuncRest.setSuccess(false);
			saveRoleFuncRest.setMessage("权限分配失败！\n" + e.getMessage());
		}

		return saveRoleFuncRest;
	}

	@RequestMapping("infoList")
	@ResponseBody
	public RoleList4Page infoList(String name, String value, Integer rows, Integer page, ModelMap modelMap) {

		RoleList4Page roleList4Page = new RoleList4Page();

		RoleDBPagingPrams dbPagingPrams = new RoleDBPagingPrams();

		if ("rightname".equals(name)) {
			dbPagingPrams.setRightname(value);
		} else if ("rightCode".equals(name)) {
			dbPagingPrams.setRightcode(value);
		}

		dbPagingPrams.setRows(rows);
		dbPagingPrams.setPage(page);

		DBPageValue<Role> pageUsers = systemService.findRole(dbPagingPrams);

		roleList4Page.setTotal(Long.valueOf(pageUsers.getTotalrowcount()));
		roleList4Page.setRows(pageUsers.getModels());
		return roleList4Page;
	}

	@RequestMapping("roleFuncList")
	@ResponseBody
	public List<FuncTree> roleFuncList(String roleCode, String checkVal, String searchText, ModelMap modelMap) {

		List<RoleFuncright> roleFuncrights = null;
		List<Funcright> funcrights = null;

		if (StringUtils.isNotBlank(checkVal)) {
			String[] funcCodes = checkVal.split(",");
			roleFuncrights = new ArrayList<RoleFuncright>();
			for (String funcCode : funcCodes) {
				RoleFuncright roleFuncright = new RoleFuncright();
				roleFuncright.setRightCode(funcCode);
				roleFuncrights.add(roleFuncright);
			}
		} else {
			roleFuncrights = rolefuncrightService.getRFByRoleCode(roleCode);
		}

		if (StringUtils.isNotBlank(searchText) && !searchText.trim().isEmpty()) {
			funcrights = funcrightService.getFuncsChilds(searchText);
		} else {
			funcrights = funcrightService.getAllFuncright();
		}

		List<FuncTree> menuFuncTrees = this.menuFuncToBasicTree(funcrights, roleFuncrights);
		List<FuncTree> menuFuncTopTrees = BasicTree.getTopTree(menuFuncTrees,searchText);

		List<FuncTree> topTress = new ArrayList<FuncTree>();

		FuncTree menuFuncTree = new FuncTree();
		menuFuncTree.setChildren(menuFuncTopTrees);
		menuFuncTree.setId("0");
		menuFuncTree.setState("close");
		menuFuncTree.setText("页面权限");
		topTress.add(menuFuncTree);

		return topTress;

	}

	private List<FuncTree> menuFuncToBasicTree(List<Funcright> funcrights, List<RoleFuncright> roleFuncrights) {
		List<FuncTree> funcTrees = new CopyOnWriteArrayList<FuncTree>();
		if (funcrights != null) {

			for (Funcright funcright : funcrights) {
				FuncTree funcTree = new FuncTree(funcright);

				funcTree.setId(funcright.getModCode());
				funcTree.setText(StringUtils.isNotBlank(funcright.getRightName()) ? funcright.getRightName()
						: funcright.getMenuTitle());
				funcTree.setParentId(funcright.getParentMenuCode());
				
				if (roleFuncrights != null) {
					for (int i = roleFuncrights.size() - 1; i >= 0; i--) {
						RoleFuncright roleFuncright = roleFuncrights.get(i);
						if (roleFuncright.getRightCode().equals(funcright.getRightCode())) {
							funcTree.setChecked(true);
							roleFuncrights.remove(i);
							break;
						}
					}
				}
				funcTrees.add(funcTree);
			}
		}
		return funcTrees;

	}

	public static class FuncTree extends BasicTree {
		private String rightCode;
		private String rightName;
		private String rightAttr;
		private String classCode;
		private String modCode;
		private String helpIndex;
		private String remark;
		private String menuTitle;

		public FuncTree() {
			super();
		}

		public FuncTree(Funcright funcright) {
			super();
			this.rightCode = funcright.getRightCode();
			this.rightName = funcright.getRightName();
			this.rightAttr = funcright.getRightAttr();
			this.classCode = funcright.getClassCode();
			this.modCode = funcright.getModCode();
			this.helpIndex = funcright.getHelpIndex();
			this.remark = funcright.getRemark();
			this.menuTitle = funcright.getMenuTitle();

			this.setId(funcright.getModCode());
			this.setText(StringUtils.isNotBlank(funcright.getRightName()) ? funcright.getRightName()
					: funcright.getRightCode());
			this.setParentId(funcright.getParentMenuCode());

		}

		public String getMenuTitle() {
			return menuTitle;
		}

		public void setMenuTitle(String menuTitle) {
			this.menuTitle = menuTitle;
		}

		public String getClassCode() {
			return classCode;
		}

		public void setClassCode(String classCode) {
			this.classCode = classCode;
		}

		public String getModCode() {
			return modCode;
		}

		public void setModCode(String modCode) {
			this.modCode = modCode;
		}

		public String getHelpIndex() {
			return helpIndex;
		}

		public void setHelpIndex(String helpIndex) {
			this.helpIndex = helpIndex;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getRightCode() {
			return rightCode;
		}

		public void setRightCode(String rightCode) {
			this.rightCode = rightCode;
		}

		public String getRightName() {
			return rightName;
		}

		public void setRightName(String rightName) {
			this.rightName = rightName;
		}

		public String getRightAttr() {
			return rightAttr;
		}

		public void setRightAttr(String rightAttr) {
			this.rightAttr = rightAttr;
		}

	}

	public static class RoleList4Page {
		private List<Role> rows;
		private Long total = 0L;

		public List<Role> getRows() {
			return rows;
		}

		public void setRows(List<Role> rows) {
			this.rows = rows;
		}

		public Long getTotal() {
			return total;
		}

		public void setTotal(Long total) {
			this.total = total;
		}

	}

}
