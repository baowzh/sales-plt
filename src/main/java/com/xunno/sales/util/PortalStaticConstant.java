package com.xunno.sales.util;

public class PortalStaticConstant {
	public interface SystemType {
		public String SYS_TYPE_CEN1 = "CEN1";
		public String SYS_TYPE_CRM = "CRM";
	}

	public static final String DEFAULT_SUPER_DEPARTID = "0000";
	public static final Integer MAIN_PAGE_CAT_NEWS_COUNT = 6;
	public static final Integer LIST_PAGE_CAT_NEWS_COUNT = 6;

	public interface DepartType {
		public static final Integer DEPART_TYPE_SUPER = 0;
		public static final Integer DEPART_TYPE_DEPART = 1;
		public static final Integer DEPART_TYPE_TOWN = 2;
		public static final Integer DEPART_TYPE_HAMLET = 3;
	}

}
