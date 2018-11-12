<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../public/head.jsp"%>
<script language="JavaScript" src="${ctx}/resources/js/public.js"></script>
<link href="${ctx}/resources/style/public.css" rel="stylesheet"
	type="text/css" media="screen">
<link href="${ctx}/resources/js/portal/style/frame_bss.css"
	rel="stylesheet" type="text/css" />
<script language="JavaScript"
	src="${ctx}/resources/js/portal/nav/frame.js"></script>
<script language="JavaScript"
	src="${ctx}/resources/js/portal/bootstrap/jquery.min.js"></script>
<script language="JavaScript"
	src="${ctx}/resources/js/portal/bootstrap/jquery-migrate-1.1.0.min.js"></script>
<link href="${ctx}/resources/js/portal/nav/nav.css" type="text/css"
	rel="stylesheet" />
<link  href="${ctx}/resources/styles/styles_all.css"  type="text/css" />
<script language="JavaScript" type="text/JavaScript">
	function openFirstFrame() {
			addNavFrame(self, "${ctx}/news/welcome.jhtml?c=c", "首页");
			holdFirstNavFrame("contentframe", true);
	}
</script>
</head>
<frameset rows="50,*,0,0,0,0,0" framespacing="0" frameborder="no" border="0" >
	<frame id="menuframe" name="menuframe" scrolling="no" noresize="noresize" src="menu.jhtml"/>
	<frameset id="navframeset" cols="*,0,0,0,0,0,0,0,0,0" framespacing="0" frameborder="no" border="0">
		<frame id="navframe_0" name="navframe_0" scrolling="auto"/>
		<frame id="navframe_1" name="navframe_1" scrolling="auto"/>
		<frame id="navframe_2" name="navframe_2" scrolling="auto"/>
		<frame id="navframe_3" name="navframe_3" scrolling="auto"/>
		<frame id="navframe_4" name="navframe_4" scrolling="auto"/>
		<frame id="navframe_5" name="navframe_5" scrolling="auto"/>
		<frame id="navframe_6" name="navframe_6" scrolling="auto"/>
		<frame id="navframe_7" name="navframe_7" scrolling="auto"/>
		<frame id="navframe_8" name="navframe_8" scrolling="auto"/>
		<frame id="navframe_9" name="navframe_9" scrolling="auto"/>
	</frameset>
	<frame id="printframe" name="printframe" scrolling="no"/>
	<frame id="customframe" name="customframe" scrolling="no"/>
</frameset>
<noframes>
	<body></body>
</noframes>

</html>