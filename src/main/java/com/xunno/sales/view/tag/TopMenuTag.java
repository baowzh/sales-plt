package com.xunno.sales.view.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.asiainfo.eframe.component.ApplicationContextUtil;
import com.asiainfo.eframe.model.UserMenuValue;
import com.asiainfo.ewebframe.uitl.ContextHolderUtils;
import com.xunno.sales.basic.service.MenuService;

public class TopMenuTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String userid;
	private String parentId;
	private MenuService menuService;

	@Override
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public StringBuffer end() {
		menuService = ApplicationContextUtil.getBean(MenuService.class);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", userid);
		params.put("departid", ContextHolderUtils.getSessionUserInfo().getDepartid());
		if (this.getParentId() == null || this.getParentId().equalsIgnoreCase("")) {
			params.put("istop", 1);
		} else {
			params.put("parentmenuid", this.getParentId());
		}

		List<UserMenuValue> menus = menuService.getUserMenus();
		StringBuffer sb = new StringBuffer();
		//

		sb.append("<div class=\"nav\">\n");
		sb.append("<ul class=\"sy1_ul\">\n");
		sb.append("<div class=\"sy2_inside\">\n");

		// 添加功能菜单
		sb.append(
				"<li id=\"ctrl-btn-1\" class=\"home01 closed\"><a href=\"javascript:void(0)\" class = \"_left_off_icon2\" onclick = \"switchLeftMenu()\"></a></li>\n");

		// 添加首页标签
		sb.append(
				"<li id=\"fpage\" class=\"home02 current\"><a onclick=\"return switchframe('div_firstpage',this,false);\" href=\"javascript:void(0)\"><span style=\"font-size: 15px;\">工作台</span></a></li>\n");

		for (int i = 0; i < menus.size(); i++) {
			UserMenuValue item = menus.get(i);

			sb.append("<li class=\"sy1_li navli" + (i + 1)
					+ "\" onclick=\"switchframe('div_contentframe',this,false);  return window.frames['siderbar'].setTreeMenu(this);\">\n");

			sb.append("<div class=\"sy1_tit\">\n");
			sb.append("" + item.getMenuname() + "\n");
			sb.append("</div>\n");

			sb.append("<div class=\"treeDiv\" flag=\"" + i + "\">");
			writerMenuBySecond(sb, item);
			sb.append("</div>");

			sb.append("</li>\n");
		}

		sb.append("</ul>\n");
		sb.append("</div>\n");
		//

		return sb;
	}

	protected void writerMenuBySecond(StringBuffer sb, UserMenuValue item) {
		List<UserMenuValue> items = item.getChildren();
		for (int i = 0; i < items.size(); i++) {
			UserMenuValue itemi = items.get(i);
			sb.append(" <h3><a href=\"javascript:void(0);\"> " + itemi.getMenuname() + "</a></h3>");
			sb.append("<div class=\"treeMenu\">");
			writerTree(sb, itemi);
			sb.append("</div>");
		}
	}

	protected void writerTree(StringBuffer sb, UserMenuValue treeItem) {

		List<UserMenuValue> items = treeItem.getChildren();
		sb.append("<ul>");
		for (int i = 0; i < items.size(); i++) {
			UserMenuValue item = items.get(i);
			boolean hasChild = this.hasChild(item);
			if (hasChild) {
				sb.append("<li>" + item.getMenuname());
			} else {
				String href = item.getMenuurl();
				sb.append("<li><a class=\"text menu_link\" id=\"" + item.getMenuid() + "\" menuid=\"" + item.getMenuid()
						+ "\" href=\"javascript:void(0)\" title=" + item.getMenuname() + " caption=" + item.getMenuurl()
						+ " onclick=\"clickMenuItem(this);" + "openmenu('" + href + "',this);" + "\">"
						+ item.getMenuname() + "</a>");
			}

			if (hasChild) {
				writerTree(sb, item);
			}
			sb.append("</li>");
		}
		sb.append("</ul>");

	}

	private boolean hasChild(UserMenuValue treeItem) {
		if (treeItem.getChildren() != null && !treeItem.getChildren().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
