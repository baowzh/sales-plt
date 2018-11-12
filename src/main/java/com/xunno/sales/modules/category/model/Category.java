package com.xunno.sales.modules.category.model;

public class Category {
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer clasz;
	private String varName;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getClasz() {
		return clasz;
	}

	public void setClasz(Integer clasz) {
		this.clasz = clasz;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}


}
