package com.xunno.sales.modules.system.model;

import java.util.Date;

public class Funcright {
	private static final long serialVersionUID = 1L;
	private String rightCode;
	private String rightName;
	private String classCode;
	private String rightAttr;
	private String modCode;
	private String helpIndex;
	private String remark;
	private Date updateTime;
	private String updateStaffId;
	private String updateDepartId;
	private String parentMenuCode;
	private String menuTitle;
	private String departName;
	
	
	
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getParentMenuCode() {
		return parentMenuCode;
	}
	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
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
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getRightAttr() {
		return rightAttr;
	}
	public void setRightAttr(String rightAttr) {
		this.rightAttr = rightAttr;
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
		result = prime * result + ((classCode == null) ? 0 : classCode.hashCode());
		result = prime * result + ((helpIndex == null) ? 0 : helpIndex.hashCode());
		result = prime * result + ((modCode == null) ? 0 : modCode.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((rightAttr == null) ? 0 : rightAttr.hashCode());
		result = prime * result + ((rightCode == null) ? 0 : rightCode.hashCode());
		result = prime * result + ((rightName == null) ? 0 : rightName.hashCode());
		result = prime * result + ((updateDepartId == null) ? 0 : updateDepartId.hashCode());
		result = prime * result + ((updateStaffId == null) ? 0 : updateStaffId.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
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
		Funcright other = (Funcright) obj;
		if (classCode == null) {
			if (other.classCode != null)
				return false;
		} else if (!classCode.equals(other.classCode))
			return false;
		if (helpIndex == null) {
			if (other.helpIndex != null)
				return false;
		} else if (!helpIndex.equals(other.helpIndex))
			return false;
		if (modCode == null) {
			if (other.modCode != null)
				return false;
		} else if (!modCode.equals(other.modCode))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (rightAttr == null) {
			if (other.rightAttr != null)
				return false;
		} else if (!rightAttr.equals(other.rightAttr))
			return false;
		if (rightCode == null) {
			if (other.rightCode != null)
				return false;
		} else if (!rightCode.equals(other.rightCode))
			return false;
		if (rightName == null) {
			if (other.rightName != null)
				return false;
		} else if (!rightName.equals(other.rightName))
			return false;
		if (updateDepartId == null) {
			if (other.updateDepartId != null)
				return false;
		} else if (!updateDepartId.equals(other.updateDepartId))
			return false;
		if (updateStaffId == null) {
			if (other.updateStaffId != null)
				return false;
		} else if (!updateStaffId.equals(other.updateStaffId))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	
	
	
}
