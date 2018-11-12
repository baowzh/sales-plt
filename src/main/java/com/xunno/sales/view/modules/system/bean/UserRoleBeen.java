package com.xunno.sales.view.modules.system.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class UserRoleBeen {
	private String staffid;
	private String roleCodes;
	private List<String> roleCodeList;

	public UserRoleBeen() {

	}

	public UserRoleBeen(String staffid, String roleCodes) {
		this.staffid = staffid;
		this.roleCodes = roleCodes;
		if (StringUtils.isNotBlank(roleCodes) && !roleCodes.trim().isEmpty()) {
			if (roleCodes.contains(",")) {
				roleCodeList = Arrays.asList((roleCodes.trim().split(",")));
			} else {
				roleCodeList = new ArrayList<String>();
				roleCodeList.add(roleCodes);
			}
		}
	}

	public List<String> getRoleCodeList() {
		return roleCodeList;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
		if (StringUtils.isNotBlank(roleCodes) && !roleCodes.trim().isEmpty()) {
			if (roleCodes.contains(",")) {
				roleCodeList = Arrays.asList((roleCodes.trim().split(",")));
			} else {
				roleCodeList = new ArrayList<String>();
				roleCodeList.add(roleCodes);
			}
		}
	}

}
