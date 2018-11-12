package com.xunno.sales.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.xunno.sales.modules.system.model.ModFile;


public interface ModFileDao {
	
	public void insertModFile(ModFile modFile);

	public void updateModFile(ModFile modFile);

	public void deleteModFile(ModFile modFile);

	public List<ModFile> findModFile(Map<String, Object> params);
	
	public ModFile findModFileByModCode(String modCode);

}
