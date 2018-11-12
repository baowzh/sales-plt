<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysname}</title>
<style>
* {
	font-family: "\5FAE\8F6F\96C5\9ED1", Helvetica;
	text-decoration: none;
}

#msgLink a:hover {
	color: red;
}
</style>
<script language="JavaScript" src="${ctx}/resources/js/public.js"></script>
<link href="${ctx}/resources/style/public.css" rel="stylesheet"
	type="text/css" media="screen">
<link href="${ctx}/resources/style/notice.css" rel="stylesheet"
	type="text/css" />
<script language="JavaScript"
	src="${ctx}/resources/js/portal/bootstrap/jquery.min.js"></script>
<style>
html, body {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
	overflow: hidden;
}
</style>
</head>
<body>
	<div class="mask" id="mask" style="display: none">
		<div class="wrap">
			<button class="btn_n" type="button" id="close"
				onclick="document.getElementById('mask').style.display= 'None'">关闭</button>
		</div>
	</div>

	<iframe name="frame_all" width="100%" height="100%"
		src="${ctx}/frame/index.jhtml" scrolling="no" noresize="noresize"></iframe>

</body>
</html>