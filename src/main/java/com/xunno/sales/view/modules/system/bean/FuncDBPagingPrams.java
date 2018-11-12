package com.xunno.sales.view.modules.system.bean;

import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;

public class FuncDBPagingPrams extends DBPagingPrams {
	private String rightName;
	private String rightCode;
	private String modCode;

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public String getModCode() {
		return modCode;
	}

	public void setModCode(String modCode) {
		this.modCode = modCode;
	}

}
