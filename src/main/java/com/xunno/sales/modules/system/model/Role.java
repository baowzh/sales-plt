package com.xunno.sales.modules.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asiainfo.eframe.security.model.RoleInfo;

public class Role extends RoleInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleAttr;
	private String remark;
	private Date updateTime;
	private String updateStaffId;
	private String updateDepartId;
	private String validFlag;
	private String departName;

	private Boolean selected;

	private List<Menu> menuList = new ArrayList<Menu>();

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getRoleAttr() {
		return roleAttr;
	}

	public void setRoleAttr(String roleAttr) {
		this.roleAttr = roleAttr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateStaffId() {
		return updateStaffId;
	}

	public void setUpdateStaffId(String updateStaffId) {
		this.updateStaffId = updateStaffId;
	}

	public String getUpdateDepartId() {
		return updateDepartId;
	}

	public void setUpdateDepartId(String updateDepartId) {
		this.updateDepartId = updateDepartId;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
