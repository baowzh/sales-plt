package com.xunno.sales.view.modules.system.bean;

import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;

public class RoleDBPagingPrams extends DBPagingPrams {
	private String rightname;
	private String rightcode;

	public String getRightname() {
		return rightname;
	}

	public void setRightname(String rightname) {
		this.rightname = rightname;
	}

	public String getRightcode() {
		return rightcode;
	}

	public void setRightcode(String rightcode) {
		this.rightcode = rightcode;
	}

}
