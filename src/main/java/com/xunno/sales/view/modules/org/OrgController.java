package com.xunno.sales.view.modules.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.asiainfo.ewebframe.ui.form.model.SelectResult;
import com.xunno.sales.modules.depart.model.Depart;
import com.xunno.sales.modules.depart.repository.DepartRepository;
import com.xunno.sales.modules.depart.service.OrgService;
import com.xunno.sales.view.modules.FileUploadControler;
import com.xunno.sales.view.modules.system.bean.BasicTree;

import net.sourceforge.pinyin4j.PinyinHelper;

@Controller
@RequestMapping(value = "org")
public class OrgController extends FileUploadControler {
	@Autowired
	private OrgService orgService;
	@Autowired
	private DepartRepository departRepository;
	@Autowired
	private DBSqlSession sqlSession;	

	@RequestMapping("index")
	public ModelAndView index(ModelMap modelMap) {
		return new ModelAndView("gov/org/index", modelMap);
	}

	@RequestMapping("info")
	public ModelAndView info(ModelMap modelMap, String id) {
		Depart depart = this.orgService.getDepart(id);
		modelMap.put("depart", depart);
		return new ModelAndView("gov/org/info", modelMap);
	}

	@ResponseBody
	@RequestMapping("del")
	public Map<String, Object> del(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.orgService.del(id);
			map.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", false);
			map.put("mess", ex.getMessage());
		}
		return map;
	}

	@RequestMapping("edit")
	public ModelAndView add(ModelMap modelMap, String id) {
		Depart depart = this.orgService.getDepart(id);
		List<SelectResult> results = new ArrayList<SelectResult>();
		if (depart != null && depart.getType() == 3) {
			List<Depart> departs = orgService.getDeparts(2);
			for (int i = 0; i < departs.size(); i++) {
				Depart departi = departs.get(i);
				SelectResult result = new SelectResult();
				result.setId(departi.getId());
				result.setText(departi.getName());
				results.add(result);
			}

		}
		if (depart != null && depart.getParentId() != null) {
			Depart parent = this.orgService.getDepart(depart.getParentId());
			modelMap.put("parent", parent);
		}

		modelMap.put("parents", results);

		modelMap.put("depart", depart);
		return new ModelAndView("gov/org/edit", modelMap);
	}

	@RequestMapping("save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {

		try {
			String name = request.getParameter("name");
			String code = request.getParameter("code");
			String id = request.getParameter("id");
			String parentId = request.getParameter("parentId");
			String type = request.getParameter("type");
			Depart depart = new Depart();
			depart.setCode(code);
			depart.setName(name);
			depart.setType(Integer.parseInt(type));
			depart.setParentId(parentId);
			if (parentId == null) {
				depart.setLevel(0);
			}
			depart.setId(id);
			String saveUrl = this.wiredFile(request);
			depart.setImg(saveUrl);
			this.orgService.save(depart);
			modelMap.put("mess", "保存数据成功。");
		} catch (Exception ex) {
			ex.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("mess", ex.getMessage());
			return new ModelAndView("gov/org/index", modelMap);
		}
		return new ModelAndView("gov/org/index", modelMap);
	}

	@RequestMapping("treeInfo")
	@ResponseBody
	public List<BasicTree> menuTree(String departId, ModelMap modelMap) {
		List<Depart> departs = this.orgService.getDeparts(departId);
		List<BasicTree> basicTrees = new ArrayList<BasicTree>();
		List<BasicTree> toDel = new ArrayList<BasicTree>();
		for (int i = 0; i < departs.size(); i++) {
			Depart depart = departs.get(i);
			List<Depart> childs = this.getChilds(depart, departs);
			BasicTree tree = new BasicTree();
			tree.setId(depart.getId());
			tree.setText(depart.getName());
			tree.setSelected(false);
			basicTrees.add(tree);
			List<BasicTree> allChilds = new ArrayList<BasicTree>();
			for (int j = 0; j < childs.size(); j++) {
				Depart departj = childs.get(j);
				BasicTree treei = new BasicTree();
				treei.setId(departj.getId());
				treei.setText(departj.getName());
				treei.setSelected(false);
				allChilds.add(treei);
			}
			tree.setChildren(allChilds);
			toDel.addAll(allChilds);
		}
		List<BasicTree> delEle = new ArrayList<BasicTree>();
		for (int i = 0; i < toDel.size(); i++) {
			BasicTree treei = toDel.get(i);
			for (int j = 0; j < basicTrees.size(); j++) {
				if (treei.getId().equalsIgnoreCase(basicTrees.get(j).getId())) {
					delEle.add(basicTrees.get(j));
				}
			}
		}
		for (int i = 0; i < delEle.size(); i++) {
			basicTrees.remove(delEle.get(i));
		}
		return basicTrees;

	}

	private List<Depart> getChilds(Depart depart, List<Depart> departs) {
		List<Depart> childs = new ArrayList<Depart>();
		for (int i = 0; i < departs.size(); i++) {
			Depart departi = departs.get(i);
			if (depart.getId().equalsIgnoreCase(departi.getParentId())) {
				childs.add(departi);
			}

		}
		return childs;
	}

	@RequestMapping("importHamlet")
	@ResponseBody
	public Map<String, Object> importHamlet() {

		Map<String, Object> infomap = new HashMap<String, Object>();
		try {
			List<Depart> departs = this.getHamls();
			List<String> staffIds = new ArrayList<String>();
			for (int i = 0; i < departs.size(); i++) {
				Depart depart = departs.get(i);
				// 生成组织机构
				this.departRepository.insert(depart);
				//
				UserInfo userInfo = new UserInfo();
				if (!staffIds.contains(depart.getAlias() + "01")) {
					staffIds.add(depart.getAlias() + "01");
					userInfo.setStaffid(depart.getAlias() + "01");
				} else {
					staffIds.add(depart.getAlias() + "02");
					userInfo.setStaffid(depart.getAlias() + "02");
				}
				Integer count= sqlSession.getDataSingle("statistic.checkuserId", userInfo.getStaffid());
				if(count>0){
					userInfo.setStaffid(depart.getAlias()+"02");
				}
				
				userInfo.setDepartid(depart.getId());
				userInfo.setStaffname(depart.getName() + "用户");
				sqlSession.insert("createOrg.insertStaff", userInfo);
				Map<String, Object> functionIfo = new HashMap<String, Object>();
				functionIfo.put("staff_id", userInfo.getStaffid());
				functionIfo.put("right_code", "004");
				functionIfo.put("depart_id", userInfo.getDepartid());
				sqlSession.insert("createOrg.insertfuncright", functionIfo);
				Map<String, Object> passwordMap = new HashMap<String, Object>();
				passwordMap.put("staff_id", userInfo.getStaffid());
				passwordMap.put("staff_passwd", "q8GYiw");
				passwordMap.put("update_time", new Date());
				sqlSession.insert("createOrg.insertStaffPass", passwordMap);

			}
			infomap.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			infomap.put("success", false);
			infomap.put("mess", ex.getMessage());
		}
		return infomap;
	}

	@RequestMapping("importTown")
	@ResponseBody
	public Map<String, Object> importTown() {

		Map<String, Object> infomap = new HashMap<String, Object>();
		try {
			List<Depart> departs = this.getTowns();

			for (int i = 0; i < departs.size(); i++) {
				Depart depart = departs.get(i);
				// 生成组织机构
				this.departRepository.insert(depart);
				//
				UserInfo userInfo = new UserInfo();
				userInfo.setStaffid(depart.getId());
				userInfo.setDepartid(depart.getId());
				userInfo.setStaffname(depart.getName() + "用户");
				sqlSession.insert("createOrg.insertStaff", userInfo);
				Map<String, Object> functionIfo = new HashMap<String, Object>();
				functionIfo.put("staff_id", userInfo.getStaffid());
				functionIfo.put("right_code", "003");
				functionIfo.put("depart_id", userInfo.getDepartid());
				sqlSession.insert("createOrg.insertfuncright", functionIfo);
				Map<String, Object> passwordMap = new HashMap<String, Object>();
				passwordMap.put("staff_id", userInfo.getStaffid());
				passwordMap.put("staff_passwd", "q8GYiw");
				passwordMap.put("update_time", new Date());
				sqlSession.insert("createOrg.insertStaffPass", passwordMap);

			}
			infomap.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			infomap.put("success", false);
			infomap.put("mess", ex.getMessage());
		}
		return infomap;
	}

	@RequestMapping("importDepart")
	@ResponseBody
	public Map<String, Object> importDepart() {

		Map<String, Object> infomap = new HashMap<String, Object>();
		try {
			List<Depart> departs = this.getDeparts();

			for (int i = 0; i < departs.size(); i++) {
				Depart depart = departs.get(i);
				// 生成组织机构
				this.departRepository.insert(depart);
				//
				UserInfo userInfo = new UserInfo();
				userInfo.setStaffid(depart.getId());
				userInfo.setDepartid(depart.getId());
				userInfo.setStaffname(depart.getName() + "用户");
				sqlSession.insert("createOrg.insertStaff", userInfo);
				Map<String, Object> functionIfo = new HashMap<String, Object>();
				functionIfo.put("staff_id", userInfo.getStaffid());
				functionIfo.put("right_code", "002");
				functionIfo.put("depart_id", userInfo.getDepartid());
				sqlSession.insert("createOrg.insertfuncright", functionIfo);
				Map<String, Object> passwordMap = new HashMap<String, Object>();
				passwordMap.put("staff_id", userInfo.getStaffid());
				passwordMap.put("staff_passwd", "q8GYiw");
				passwordMap.put("update_time", new Date());
				sqlSession.insert("createOrg.insertStaffPass", passwordMap);

			}
			infomap.put("success", true);
		} catch (Exception ex) {
			infomap.put("success", false);
			infomap.put("mess", ex.getMessage());
		}
		return infomap;
	}

	private List<Depart> getHamls() throws Exception {
		// InputStream stream =
		// super.getClass().getClassLoader().getResourceAsStream("town-data.xlsx");
		File departFile = new File("d:/嘎查村2003.xls");
		InputStream stream = new FileInputStream(departFile);
		Workbook book = WorkbookFactory.create(stream);
		Sheet sheet = book.getSheetAt(0);
		int i = 0;
		List<Depart> projects = new ArrayList<Depart>();
		List<String> ids = new ArrayList<String>();
		while (true) {
			Row row = sheet.getRow(i);
			if (!this.isEmptyRow(row)) {
				Cell parentNameCell = row.getCell(0);
				String parentName = parentNameCell.getStringCellValue();
				String parentId = getPinYinHeadChar(parentName) + "01";
				String allNames = row.getCell(1).getStringCellValue();
				String names[] = allNames.split("、");
				String aliesNames = row.getCell(2).getStringCellValue();
				String alies[] = aliesNames.split("、");
				for (int j = 0; j < names.length; j++) {
					Depart depart = new Depart();
					depart.setName(names[j]);
					depart.setType(3);
					depart.setAlias(names[j]);
					String id = getPinYinHeadChar(names[j]) + "01";
					if (!ids.contains(id)) {
						ids.add(id);
					} else {
						id = id + "02";
					}
					Depart departi = this.orgService.getDepart(id);
					if (departi != null) {
						id = id + "03";
					}
					depart.setId(id);
					depart.setAlias(getPinYinHeadChar(alies[j]));
					depart.setParentId(parentId);
					projects.add(depart);

				}

			} else {
				break;
			}
			i++;
		}
		stream.close();
		return projects;
	}

	private List<Depart> getTowns() throws Exception {
		// InputStream stream =
		// super.getClass().getClassLoader().getResourceAsStream("town-data.xlsx");
		File departFile = new File("d:/苏木镇场2003.xls");
		InputStream stream = new FileInputStream(departFile);
		Workbook book = WorkbookFactory.create(stream);
		Sheet sheet = book.getSheetAt(0);
		int i = 1;
		List<Depart> projects = new ArrayList<Depart>();
		List<String> ids = new ArrayList<String>();
		while (true) {
			Row row = sheet.getRow(i);
			if (!this.isEmptyRow(row)) {
				Cell nameCell = row.getCell(0);
				String departname = nameCell.getStringCellValue();
				Depart depart = new Depart();
				depart.setName(departname);
				depart.setType(2);
				depart.setAlias(departname);
				String id = getPinYinHeadChar(departname) + "01";
				if (!ids.contains(id)) {
					ids.add(id);
				} else {
					id = id + "02";
				}
				depart.setId(id);
				projects.add(depart);
			} else {
				break;
			}
			i++;
		}
		stream.close();
		return projects;
	}

	private List<Depart> getDeparts() throws Exception {
		// InputStream stream =
		// super.getClass().getClassLoader().getResourceAsStream("depart-data.xls");
		// HSSFWorkbook book = new HSSFWorkbook(stream);
		File departFile = new File("d:/data.xlsx");
		InputStream stream = new FileInputStream(departFile);
		Workbook book = WorkbookFactory.create(stream);
		Sheet sheet = book.getSheetAt(0);
		int i = 0;
		List<Depart> projects = new ArrayList<Depart>();
		List<String> ids = new ArrayList<String>();
		while (true) {
			Row row = sheet.getRow(i);
			if (!this.isEmptyRow(row)) {
				Cell nameCell = row.getCell(0);
				String departname = nameCell.getStringCellValue();
				Depart depart = new Depart();
				depart.setName(departname);
				depart.setType(1);
				depart.setAlias(departname);
				String id = getPinYinHeadChar(departname) + "01";
				if (!ids.contains(id)) {
					ids.add(id);
				} else {
					id = id + "02";
				}
				depart.setId(id);
				projects.add(depart);
			} else {
				break;
			}
			i++;
		}
		stream.close();
		return projects;
	}

	public static void main(String args[]) {
		System.out.println(getPinYinHeadChar("朝鲁图镇"));
	}

	private static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	private boolean isEmptyRow(Row row) {
		int c = 0;
		if (row == null) {
			return true;
		}
		boolean nonBlankRowFound = false;
		for (c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && row.getCell(c).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
				nonBlankRowFound = true;
			}
		}
		return !nonBlankRowFound;
	}


}
