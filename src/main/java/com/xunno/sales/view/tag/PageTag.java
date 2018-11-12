package com.xunno.sales.view.tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {
	private static final long serialVersionUID = 6179314241821348095L;

	private int pageindex = 1;
	private int pagesize = 10;
	private int totalRecord;
	private String url;
	private boolean mobile;

	@Override
	public int doStartTag() throws JspException {
		if (pagesize <= 0) {
			pagesize = 10;
		}
		// 计算总的页数
		int pageCount = (totalRecord % pagesize == 0) ? (totalRecord / pagesize) : (totalRecord / pagesize + 1);
		// 设置最多能显示多少个页数按钮
		int maxShowButton = 5;

		StringBuilder sb = new StringBuilder();

		if (this.totalRecord == 0) {
			sb.append("<div style=\"width:100%;text-align:center;display:block;font-size:15px;\" >\r\n");
			sb.append("<Strong>没有可显示的项目</Strong>\r\n");
		} else {
			sb.append("<div class=\"pagination\" >\r\n");
			// 对页数进行越界处理
			if (pageindex > pageCount) {
				pageindex = pageCount;
			}
			if (pageindex < 1) {
				pageindex = 1;
			}

			sb.append("<form method=\"post\" action=\"").append(this.url).append("\" name=\"paramForm\">\r\n");

			// 获取当前页面request里的所有请求参数
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
			Enumeration<String> paramNames = request.getParameterNames();
			// 遍历枚举里面的参数，与分页有关的直接设置到属性上，其他参数放置到type为hidden的input中
			String name = null;
			String value = null;
			while (paramNames.hasMoreElements()) {
				name = paramNames.nextElement();
				value = request.getParameter(name);

				if ("pageindex".equals(name)) {
					if (value != null && !"".equals(value)) {
						this.pageindex = Integer.parseInt(value);
					}
				} else {
					sb.append("<input type=\"hidden\" name=\"").append(name).append("\" value=\"").append(value)
							.append("\" />\r\n");
				}
			}
			sb.append("<input type=\"hidden\" name=\"").append("pageindex").append("\" value=\"").append(this.pageindex)
					.append("\" />\r\n");

			sb.append("</form>\r\n");

			sb.append("<a href=\"javascript:turnOverPage(").append(1).append(")\">首页</a>\r\n");
			// 当前页面为第一页时不显示上一页
			if (pageindex == 1) {
				sb.append("<span class=\"disabled\">上一页</span>\r\n");
			} else {
				sb.append("<a href=\"javascript:turnOverPage(").append(this.pageindex - 1).append(")\" >上一页</a>\r\n");
			}

			// 设置显示按钮的数量
			int showButton = maxShowButton;
			// 当页数不够maxShowButton时
			if (pageCount < maxShowButton) {
				showButton = pageCount;
			}

			// 标识显示的按钮上的开始下标
			int startPageIndex = 1;
			// 当页数为第一页或第二页时
			if (this.pageindex == 1 || this.pageindex == 2) {
				startPageIndex = 1;
			} else {
				startPageIndex = this.pageindex - 2;
			}

			// 当页数为倒数第一或第二页时 6 7 8 [9] 10 6 7 8 9 [10]
			// 其他都是从当前页面的前2个开始，展示maxShowButton个.
			// 当总页数不足maxShowButton时也适用，因为此时showButton等于pageCount,开始下标一直为1.
			if (this.pageindex == pageCount || this.pageindex == pageCount - 1) {
				startPageIndex = pageCount - showButton + 1;
			}

			// 循环将按钮拼接到HTML上
			for (int i = 0; i < showButton; i++) {
				int pageIndex = startPageIndex++;
				// 如果是当前页，则改变样式，不可点击。
				if (pageIndex == this.pageindex) {
					sb.append("<span class=\"current\">").append(pageIndex).append("</span>\r\n");
				} else {
					sb.append("<a href=\"javascript:turnOverPage(").append(pageIndex).append(")\">").append(pageIndex)
							.append("</a>\r\n");
				}
			}
			// 如果到达了最后一页，则下一页按钮不可用
			if (this.pageindex == pageCount) {
				sb.append("<span class=\"disabled\">下一页</span>\r\n");
			} else {
				sb.append("<a href=\"javascript:turnOverPage(").append(this.pageindex + 1).append(")\" >下一页</a>\r\n");
			}

			sb.append("<a href=\"javascript:turnOverPage(").append(pageCount).append(")\">末页</a>\r\n");
			// 拼接总记录条数和总页数
			if (!this.isMobile()) {
				sb.append("共<strong>").append(this.totalRecord).append("</strong>条,").append("共<strong>")
						.append(pageCount).append("</strong>页");
			}

			sb.append("<script type=\"text/javascript\">\r\n");
			sb.append("function turnOverPage(no){\r\n");
			sb.append("if(no>").append(pageCount).append("){");
			sb.append("no=").append(pageCount).append(";}\r\n");
			sb.append("if(no<1){ no=1;}\r\n");
			sb.append("document.paramForm.pageindex.value=no;\r\n");
			sb.append("document.paramForm.submit();\r\n");
			sb.append("}\r\n");
			sb.append("</script>\r\n");
		}
		sb.append("</div>\r\n");

		try {
			this.pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return 0;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

}
