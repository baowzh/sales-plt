package com.xunno.sales.view.modules.system.bean;

import com.xunno.sales.modules.system.model.ModFile;
import com.xunno.sales.modules.system.model.SystemGuiMenu;

public class SystemMenuBean {
	private SystemGuiMenu systemGuiMenu;
	private ModFile modFile;
	private String parentTitle;
	
	
	public SystemGuiMenu getSystemGuiMenu() {
		return systemGuiMenu;
	}
	public void setSystemGuiMenu(SystemGuiMenu systemGuiMenu) {
		this.systemGuiMenu = systemGuiMenu;
	}
	public ModFile getModFile() {
		return modFile;
	}
	public void setModFile(ModFile modFile) {
		this.modFile = modFile;
	}
	@Override
	public String toString() {
		return "SystemMenuBean [systemGuiMenu=" + systemGuiMenu.toString() + ", modFile=" + modFile.toString() + "]";
	}
	public String getParentTitle() {
		return parentTitle;
	}
	public void setParentTitle(String parentName) {
		this.parentTitle = parentName;
	}
	
	
	

}
