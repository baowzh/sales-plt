package com.xunno.sales.modules.system.model;

import java.util.Date;

public class RoleFuncright {
	private static final long serialVersionUID = 1L;
	private String roleCode;
	private String roleName;
	private String rightCode;
	private String rightname;
	private String extendValue1;
	private Long extendValue2;
	private String rsvalue1;
	private String rsvalue2;
	private Date updateTime;
	private String updateStaffId;
	private String updateDepartId;
	private Boolean selected;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRightname() {
		return rightname;
	}

	public void setRightname(String rightname) {
		this.rightname = rightname;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public String getExtendValue1() {
		return extendValue1;
	}

	public void setExtendValue1(String extendValue1) {
		this.extendValue1 = extendValue1;
	}

	public Long getExtendValue2() {
		return extendValue2;
	}

	public void setExtendValue2(Long extendValue2) {
		this.extendValue2 = extendValue2;
	}

	public String getRsvalue1() {
		return rsvalue1;
	}

	public void setRsvalue1(String rsvalue1) {
		this.rsvalue1 = rsvalue1;
	}

	public String getRsvalue2() {
		return rsvalue2;
	}

	public void setRsvalue2(String rsvalue2) {
		this.rsvalue2 = rsvalue2;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rightCode == null) ? 0 : rightCode.hashCode());
		result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleFuncright other = (RoleFuncright) obj;
		if (rightCode == null) {
			if (other.rightCode != null)
				return false;
		} else if (!rightCode.equals(other.rightCode))
			return false;
		if (roleCode == null) {
			if (other.roleCode != null)
				return false;
		} else if (!roleCode.equals(other.roleCode))
			return false;
		return true;
	}

	public static String getSqlTypeString() {
		return " (ROLE_CODE, RIGHT_CODE, EXTEND_VALUE1, EXTEND_VALUE2, RSVALUE1, RSVALUE2, UPDATE_TIME, UPDATE_STAFF_ID, UPDATE_DEPART_ID)";
	}

	public static String getSqlValString() {
		return " (:roleCode, :rightCode, :extendValue1, :extendValue2, :rsvalue1, :rsvalue2,:updateTime, :updateStaffId, :updateDepartId)";
	}

}
