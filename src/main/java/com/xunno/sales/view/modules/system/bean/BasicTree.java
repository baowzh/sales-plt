package com.xunno.sales.view.modules.system.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jodd.util.StringUtil;

public class BasicTree {

	private String id;
	private String text;
	private List<? extends BasicTree> children;
	private String parentId;
	private String state;
	private Boolean selected;
	private Boolean checked;

	
	
	

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<? extends BasicTree> getChildren() {
		return children;
	}

	public void setChildren(List<? extends BasicTree> children) {
		this.children = children;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	public static  <T extends BasicTree>  List<T> getTopTree(List<T> basicTrees,String searchText) {
		List<T> topMenus = new ArrayList<T>();
		for (T basicTreeFlag : basicTrees) {
			if (!StringUtil.isEmpty(basicTreeFlag.getParentId())) {
				continue;
			}

			orgMenuTree(basicTreeFlag, basicTrees,searchText);
			topMenus.add(basicTreeFlag);

		}
		
		for (T basicTreeFlag : basicTrees) {
			topMenus.add(basicTreeFlag);
		}
		return topMenus;
	}
	
	public static  <T extends BasicTree>  List<T> getTopTree(List<T> basicTrees) {
		return getTopTree(basicTrees,null);
	}

	
	private static <T extends BasicTree> void orgMenuTree(T basicTree, List<? extends BasicTree> basicTrees,String searchText) {
		List<BasicTree> childs = new ArrayList<BasicTree>();

		String stateFlag = "closed";
		
		for (BasicTree systemMenuTreeFlag : basicTrees) {
			if (systemMenuTreeFlag.getParentId() != null
					&& systemMenuTreeFlag.getParentId().equalsIgnoreCase(basicTree.getId())) {
				childs.add(systemMenuTreeFlag);
				
				if(StringUtils.isNotBlank(searchText) && !searchText.trim().isEmpty()){
					if(systemMenuTreeFlag.getText().contains(searchText)){
						stateFlag = "open";
					}
				}
				
				orgMenuTree(systemMenuTreeFlag, basicTrees,searchText);
			}
		}
		if (childs.isEmpty()) {
			basicTree.setState("open");
		} else {
			basicTree.setChildren(childs);
			basicTree.setState(stateFlag);

		}
		basicTrees.remove(basicTree);

	}

}
