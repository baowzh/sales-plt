package com.xunno.sales.form;

import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;

public class ProjectPagingForm extends DBPagingPrams {

	private String name;
	private String comm;
	private String departId;
	private String year;

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
