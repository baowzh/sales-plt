package com.xunno.sales.modules.depart.service.defaults;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.eframe.util.StringUtil;
import com.asiainfo.eframe.util.UUIDGenerator;
import com.xunno.sales.modules.depart.model.Depart;
import com.xunno.sales.modules.depart.repository.DepartRepository;
import com.xunno.sales.modules.depart.service.OrgService;
import com.xunno.sales.modules.news.repository.NewsRepository;

@Service("orgService")
public class DefaultOrgService implements OrgService {
	@Autowired
	private DepartRepository departRepository;
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public void save(Depart depart) throws Exception {
		if (StringUtil.isEmpty(depart.getName())) {
			throw new Exception("请填写组织机构名称。");
		}
		if (depart.getType() == null) {
			throw new Exception("请选择组织结构类型。");
		}
		if (StringUtil.isEmpty(depart.getId())) {
			depart.setId(UUIDGenerator.generate());
			departRepository.insert(depart);
		} else {
			departRepository.update(depart);
		}
	}

	@Override
	public List<Depart> getDeparts(String departid) {

		return this.departRepository.list(departid);
	}

	@Override
	public List<Depart> getDeparts(Integer type) {

		return this.departRepository.getDeparts(type);
	}

	@Override
	public Depart getDepart(String departId) {

		return this.departRepository.get(departId);
	}

	@Override
	public void del(String departId) throws Exception {
		Integer count = this.departRepository.childCount(departId);
		if (count > 0) {
			throw new Exception("存在下级组织结构不能删除。");
		}
		count = this.newsRepository.getDepartNewsCount(departId);
		if (count > 0) {
			throw new Exception("存在发布的信息不能删除。");
		}
		this.departRepository.del(departId);

	}

}
