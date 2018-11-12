package com.xunno.sales.modules.funds.service.defaults;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.eframe.component.UserSessionHolderService;
import com.asiainfo.eframe.security.model.UserInfo;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.util.StringUtil;
import com.xunno.sales.basic.model.FileContent;
import com.xunno.sales.form.FoundsPagingForm;
import com.xunno.sales.modules.depart.model.Depart;
import com.xunno.sales.modules.depart.repository.DepartRepository;
import com.xunno.sales.modules.funds.mdoel.Funds;
import com.xunno.sales.modules.funds.repository.FundsRepository;
import com.xunno.sales.modules.funds.service.FundsService;
import com.xunno.sales.modules.news.model.News;

@Service("foundsService")
public class DefaultFundsService implements FundsService {
	@Autowired
	private FundsRepository fundsRepository;
	@Autowired
	private UserSessionHolderService userSessionHolderService;
	@Autowired
	private DepartRepository departRepository;

	@Override
	public List<Funds> list(Map<String, Object> params) {

		return null;
	}

	@Override
	public DBPageValue<Funds> paging(FoundsPagingForm pagingForm) {
		pagingForm.setDepartId(userSessionHolderService.getSessionUserInfo().getDepartid());
		DBPageValue<Funds> page = this.fundsRepository.paging(pagingForm);
		return page;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void save(Funds founds) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(rollbackFor = Exception.class)
	public void innerSave(Funds founds) throws Exception {
		if (StringUtil.isEmpty(founds.getDepartId())) {
			founds.setDepartId(userSessionHolderService.getSessionUserInfo().getDepartid());
		}
		Calendar calendar = java.util.Calendar.getInstance();
		founds.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
		founds.setInputDate(new Date());
		this.fundsRepository.insert(founds);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void del(String batchId) throws Exception {
		String departId = this.userSessionHolderService.getSessionUserInfo().getDepartid();
		List<Depart> departs = this.departRepository.list(departId);
		for (int i = 0; i < departs.size(); i++) {
			Depart depart = departs.get(i);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("departId", depart.getId());
			params.put("batch", batchId);
			this.fundsRepository.del(params);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departId", departId);
		params.put("batch", batchId);
		this.fundsRepository.del(params);

	}

	@Override
	public News get(Integer id) {

		return null;
	}

	@Transactional(rollbackFor = Exception.class)
	public void townUpload(FileContent excelContent) throws Exception {
		ByteArrayInputStream stream = new ByteArrayInputStream(excelContent.getContnet());
		Workbook book = WorkbookFactory.create(stream);
		Sheet sheet = book.getSheetAt(0);
		int i = 1;
		List<Funds> projects = new ArrayList<Funds>();
		while (true) {
			Row row = sheet.getRow(i);
			if (!this.isEmptyRow(row)) {
				Cell nameCell = row.getCell(0);
				//Cell areaCell = row.getCell(1);
				Cell orgCell = row.getCell(1);
				Cell amountCell = row.getCell(2);
				Cell releaseTimeCell = row.getCell(3);
				Cell commCell = row.getCell(4);
				Funds project = new Funds();
				Object obj = this.getCellValue(nameCell);
				if (obj == null) {
					throw new Exception("请填写第" + i + "行的项目名称。");
				}
				String name = String.valueOf(obj);
				project.setName(name);

//				obj = this.getCellValue(areaCell);
//
//				if (obj == null) {
//					throw new Exception("请填写第" + i + "行的区域信息。");
//				}
//				String areaName = String.valueOf(obj);
//				project.setAreaName(areaName);

				//
				obj = this.getCellValue(orgCell);

				if (obj == null) {
					throw new Exception("请填写第" + i + "行的嘎查村名称。");
				}
				String orgName = String.valueOf(obj);
				project.setOrgName(orgName);
				//

				obj = this.getCellValue(amountCell);

				if (obj == null) {
					throw new Exception("请填写第" + i + "行的项目金额。");
				}
				project.setAmount(Double.valueOf("" + this.getCellValue(amountCell)));

				obj = this.getCellValue(releaseTimeCell);

				if (obj == null) {
					throw new Exception("请填写第" + i + "行的发放时间。");
				} else {
					Date reaTIme = releaseTimeCell.getDateCellValue();
					project.setReleaseTime(reaTIme);
				}
				obj = this.getCellValue(commCell);
				if (obj != null) {
					project.setComm(String.valueOf(obj));
				}
				projects.add(project);

			} else {
				break;
			}

			i++;
		}
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String batchId = dataFormat.format(new Date()) + "01";
		for (int j = 0; j < projects.size(); j++) {
			UserInfo userInfo = userSessionHolderService.getSessionUserInfo();
			projects.get(j).setBatch(batchId);
			projects.get(j).setDepartId(userInfo.getDepartid());
		}
		this.batchSave(projects);

	}

	private void batchSave(List<Funds> projects) throws Exception {
		// 获取所有下级单位的列表并跟表格中的村名进行对比，如果有误则不让保存
		UserInfo userInfo = this.userSessionHolderService.getSessionUserInfo();
		List<Depart> departs = departRepository.list(userInfo.getDepartid());
		List<Funds> hamletprojects = new ArrayList<Funds>();
		for (int i = 0; i < projects.size(); i++) {
			Funds project = projects.get(i);
			Depart depart = this.getDepartInfo(departs, project.getOrgName());
			if (depart == null) {
				throw new Exception("第" + (i + 1) + "行数据，嘎查村名字不正确。");
			}
			// 拷贝一份放到 村一级数据里面
			project.setInputDate(new Date());
			Funds hamletproject = new Funds();
			project.setDepartId(userInfo.getDepartid());
			BeanUtils.copyProperties(hamletproject, project);
			hamletproject.setDepartId(depart.getId());
			hamletprojects.add(hamletproject);
		}
		projects.addAll(hamletprojects);
		for (int i = 0; i < projects.size(); i++) {
			Funds project = projects.get(i);
			Calendar calendar = java.util.Calendar.getInstance();
			project.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
			project.setInputDate(new Date());
			this.fundsRepository.insert(project);
		}
		//

	}

	private Depart getDepartInfo(List<Depart> departs, String departName) {
		for (int i = 0; i < departs.size(); i++) {
			Depart depart = departs.get(i);
			if (depart.getName().equalsIgnoreCase(departName) || departName.equalsIgnoreCase(depart.getAlias())) {
				return depart;
			}
		}
		return null;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void upload(FileContent excelContent) throws Exception {
		ByteArrayInputStream stream = new ByteArrayInputStream(excelContent.getContnet());
		Workbook book = WorkbookFactory.create(stream);
		Sheet sheet = book.getSheetAt(0);
		int i = 1;
		List<Funds> projects = new ArrayList<Funds>();
		while (true) {
			Row row = sheet.getRow(i);
			if (!this.isEmptyRow(row)) {
				Cell nameCell = row.getCell(0);
				Cell areaCell = row.getCell(1);
				Cell amountCell = row.getCell(2);
				Cell releaseTimeCell = row.getCell(3);
				Cell commCell = row.getCell(4);
				Funds project = new Funds();
				Object obj = this.getCellValue(nameCell);
				if (obj == null) {
					throw new Exception("请填写第" + i + "行的项目名称。");
				}
				String name = String.valueOf(obj);
				project.setName(name);

				obj = this.getCellValue(areaCell);

				if (obj == null) {
					throw new Exception("请填写第" + i + "行的区域信息。");
				}
				String area = String.valueOf(obj);
				project.setAreaName(area);

				obj = this.getCellValue(amountCell);

				if (obj == null) {
					throw new Exception("请填写第" + i + "行的项目金额。");
				}
				project.setAmount(Double.valueOf("" + this.getCellValue(amountCell)));

				obj = this.getCellValue(releaseTimeCell);

				if (obj == null) {
					throw new Exception("请填写第" + i + "行的发放时间。");
				} else {
					Date reaTIme = releaseTimeCell.getDateCellValue();
					project.setReleaseTime(reaTIme);
				}
				obj = this.getCellValue(commCell);
				if (obj != null) {
					project.setComm(String.valueOf(obj));
				}
				projects.add(project);

			} else {
				break;
			}

			i++;
		}
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String batchId = dataFormat.format(new Date()) + "01";
		for (int j = 0; j < projects.size(); j++) {
			UserInfo userInfo = userSessionHolderService.getSessionUserInfo();
			projects.get(j).setOrgName(userInfo.getDepartname());
			projects.get(j).setBatch(batchId);
			this.innerSave(projects.get(j));
		}
	}

	private Object getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return cell.getNumericCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return null;
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return cell.getCellFormula();
		} else {
			return null;
		}

	}

	private boolean isEmptyRow(Row row) {
		int c = 0;
		boolean nonBlankRowFound = false;
		for (c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && row.getCell(c).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
				nonBlankRowFound = true;
			}
		}
		return !nonBlankRowFound;
	}

	@Override
	public List<String> getBathIds() {
		String departId = this.userSessionHolderService.getSessionUserInfo().getDepartid();
		String year = String.valueOf(java.util.Calendar.getInstance().get(Calendar.YEAR));
		return this.fundsRepository.getBatchIds(departId, year);
	}

}
