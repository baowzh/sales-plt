package com.xunno.sales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.asiainfo.ewebframe.config.WebSysConfig;
@Primary
@Component("protalConfig")
public class ProtalConfig extends WebSysConfig {
	@Value("${portal.firstPage}")
	private String firstPage;
	@Value("${cas.serverUrlPrefix}")
	private String logOutUrl;

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public String getLogOutUrl() {
		return logOutUrl;
	}

	public void setLogOutUrl(String logOutUrl) {
		this.logOutUrl = logOutUrl;
	}
	

}
