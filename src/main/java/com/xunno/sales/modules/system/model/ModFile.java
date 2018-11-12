package com.xunno.sales.modules.system.model;

import java.util.Date;

public class ModFile {
	private static final long serialVersionUID = 1L;
	private String modCode;
	private String modName;
	private String modType;
	private String remark;
	private Date updateTime;
	private String updateStaffId;
	private String updateDepartId;
	private String targerShow;
	private String targetCode;
	private String menuName;
	private String parentModCode;
	private String imgCode;
	private String builder;
	private String crateTime;
	private String builderPart;
	private String state;
	private String imgUrl;
	
	
	public String getModCode() {
		return modCode;
	}
	public void setModCode(String modCode) {
		this.modCode = modCode;
	}
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getModType() {
		return modType;
	}
	public void setModType(String modType) {
		this.modType = modType;
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
	public String getTargerShow() {
		return targerShow;
	}
	public void setTargerShow(String targerShow) {
		this.targerShow = targerShow;
	}
	public String getTargetCode() {
		return targetCode;
	}
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getParentModCode() {
		return parentModCode;
	}
	public void setParentModCode(String parentModCode) {
		this.parentModCode = parentModCode;
	}
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
	public String getBuilder() {
		return builder;
	}
	public void setBuilder(String builder) {
		this.builder = builder;
	}
	public String getCrateTime() {
		return crateTime;
	}
	public void setCrateTime(String crateTime) {
		this.crateTime = crateTime;
	}
	public String getBuilderPart() {
		return builderPart;
	}
	public void setBuilderPart(String builderPart) {
		this.builderPart = builderPart;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((builder == null) ? 0 : builder.hashCode());
		result = prime * result + ((builderPart == null) ? 0 : builderPart.hashCode());
		result = prime * result + ((crateTime == null) ? 0 : crateTime.hashCode());
		result = prime * result + ((imgCode == null) ? 0 : imgCode.hashCode());
		result = prime * result + ((imgUrl == null) ? 0 : imgUrl.hashCode());
		result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + ((modCode == null) ? 0 : modCode.hashCode());
		result = prime * result + ((modName == null) ? 0 : modName.hashCode());
		result = prime * result + ((modType == null) ? 0 : modType.hashCode());
		result = prime * result + ((parentModCode == null) ? 0 : parentModCode.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((targerShow == null) ? 0 : targerShow.hashCode());
		result = prime * result + ((targetCode == null) ? 0 : targetCode.hashCode());
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
		ModFile other = (ModFile) obj;
		if (builder == null) {
			if (other.builder != null)
				return false;
		} else if (!builder.equals(other.builder))
			return false;
		if (builderPart == null) {
			if (other.builderPart != null)
				return false;
		} else if (!builderPart.equals(other.builderPart))
			return false;
		if (crateTime == null) {
			if (other.crateTime != null)
				return false;
		} else if (!crateTime.equals(other.crateTime))
			return false;
		if (imgCode == null) {
			if (other.imgCode != null)
				return false;
		} else if (!imgCode.equals(other.imgCode))
			return false;
		if (imgUrl == null) {
			if (other.imgUrl != null)
				return false;
		} else if (!imgUrl.equals(other.imgUrl))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (modCode == null) {
			if (other.modCode != null)
				return false;
		} else if (!modCode.equals(other.modCode))
			return false;
		if (modName == null) {
			if (other.modName != null)
				return false;
		} else if (!modName.equals(other.modName))
			return false;
		if (modType == null) {
			if (other.modType != null)
				return false;
		} else if (!modType.equals(other.modType))
			return false;
		if (parentModCode == null) {
			if (other.parentModCode != null)
				return false;
		} else if (!parentModCode.equals(other.parentModCode))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (targerShow == null) {
			if (other.targerShow != null)
				return false;
		} else if (!targerShow.equals(other.targerShow))
			return false;
		if (targetCode == null) {
			if (other.targetCode != null)
				return false;
		} else if (!targetCode.equals(other.targetCode))
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
	@Override
	public String toString() {
		return "ModFile [modCode=" + modCode + ", modName=" + modName + ", modType=" + modType + ", remark=" + remark
				+ ", updateTime=" + updateTime + ", updateStaffId=" + updateStaffId + ", updateDepartId="
				+ updateDepartId + ", targerShow=" + targerShow + ", targetCode=" + targetCode + ", menuName="
				+ menuName + ", parentModCode=" + parentModCode + ", imgCode=" + imgCode + ", builder=" + builder
				+ ", crateTime=" + crateTime + ", builderPart=" + builderPart + ", state=" + state + ", imgUrl="
				+ imgUrl + "]";
	}
	
	
	
}
