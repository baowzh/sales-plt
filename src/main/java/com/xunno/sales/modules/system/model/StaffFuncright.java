package com.xunno.sales.modules.system.model;

import java.util.Date;

public class StaffFuncright {
	private String staffId;
	private String rightCode;
	private String rightAttr;
	private String extendValue1;
	private String extendValue2;
	private String rightTag;
	private String rsvalue1;
	private String rsvalue2;
	private String remark;
	private Date accreditTime;
	private String accreditStaffId;
	private String departId;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public String getRightAttr() {
		return rightAttr;
	}

	public void setRightAttr(String rightAttr) {
		this.rightAttr = rightAttr;
	}

	public String getExtendValue1() {
		return extendValue1;
	}

	public void setExtendValue1(String extendValue1) {
		this.extendValue1 = extendValue1;
	}

	public String getExtendValue2() {
		return extendValue2;
	}

	public void setExtendValue2(String extendValue2) {
		this.extendValue2 = extendValue2;
	}

	public String getRightTag() {
		return rightTag;
	}

	public void setRightTag(String rightTag) {
		this.rightTag = rightTag;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAccreditTime() {
		return accreditTime;
	}

	public void setAccreditTime(Date accreditTime) {
		this.accreditTime = accreditTime;
	}

	public String getAccreditStaffId() {
		return accreditStaffId;
	}

	public void setAccreditStaffId(String accreditStaffId) {
		this.accreditStaffId = accreditStaffId;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	@Override
	public String toString() {
		return "StaffFuncright [staffId=" + staffId + ", rightCode=" + rightCode + ", rightAttr=" + rightAttr
				+ ", extendValue1=" + extendValue1 + ", extendValue2=" + extendValue2 + ", rightTag=" + rightTag
				+ ", rsvalue1=" + rsvalue1 + ", rsvalue2=" + rsvalue2 + ", remark=" + remark + ", accreditTime="
				+ accreditTime + ", accreditStaffId=" + accreditStaffId + ", departId=" + departId + "]";
	}

	public static String getSqlTypeString() {
		return "(STAFF_ID, RIGHT_CODE, RIGHT_ATTR, EXTEND_VALUE1, EXTEND_VALUE2, RIGHT_TAG, RSVALUE1, RSVALUE2, REMARK, ACCREDIT_TIME, ACCREDIT_STAFF_ID, DEPART_ID)";
	}

	public static String getSqlValString() {
		return "(:staffId, :rightCode, :rightAttr,:extendValue1, :extendValue2, :rightTag, :rsvalue1, :rsvalue2, :remark, :accreditTime, :accreditStaffId, :departId)";
	}

}
