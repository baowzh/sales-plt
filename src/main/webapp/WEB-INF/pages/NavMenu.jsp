<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Nav Menu</title>
<script language="JavaScript" src="${ctx}/resources/js/public.js"></script>
<link href="${ctx}/resources/style/public.css" rel="stylesheet"
	type="text/css" media="screen"/>
<link href="${ctx}/resources/styles/tabset.css" rel="stylesheet" type="text/css" media="screen">
<link href="${ctx}/resources/styles/editor.css" rel="stylesheet" type="text/css" media="screen">
<link href="${ctx}/resources/styles/combobox.css" rel="stylesheet" type="text/css" media="screen">
<script language="JavaScript" src="${ctx}/resources/js/tabset.js"></script>
<script language="JavaScript" src="${ctx}/resources/js/tableedit.js"></script>
<script language="JavaScript" src="${ctx}/resources/js/print.js"></script>
<script language="JavaScript" src="${ctx}/resources/js/file.js"></script>
<script language="JavaScript" src="${ctx}/resources/js/portal/module/navmenu.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Nav Menu</title>
<link href="${ctx}/resources/styles/styles_all_bss.css" rel="stylesheet" type="text/css" media="screen">

<script language="JavaScript" type="text/JavaScript">
var NAV_MENU_CURSOR = 0;
</script>
<script language="JavaScript" type="text/JavaScript">
	function onKeyPressEventByDefault() {onKeyPressEvent(null);}
	addObjEventListener("document", "keypress", onKeyPressEventByDefault);
	var pagevisit = getPageVisit();
	completePageLoad();</script></head>
<body ondrag="return false" oncontextmenu="window.event.returnValue = false" onselectstart="return false;">

<iframe id="wade_sbtframe" name="wade_sbtframe" class="c_sbtframe" frameborder="no" scrolling="no" style="display:none"></iframe>
<form method="post" name="Form0" action="/portal">
<input type="hidden" id="service" name="service" value="direct/1/NavMenu/$Form">
<input type="hidden" id="sp" name="sp" value="S0">
<input type="hidden" id="Form0" name="Form0" value="">

<div class="c_tabPage clear">
	<div class="tabTurner clear">
		<ul>
			<li><a id="turnLeft" href="javascript:void(0)" title="左移标签组" class="left"></a></li>
			<li><a href="javascript:void(0)" onclick="resetNavMenus();" title="复位标签组" class="default"></a></li>
			<li><a id="turnRight" href="javascript:void(0)" title="右移标签组" class="right"></a></li>
		</ul>
	</div>

	<div id="navnotify" style="display:none">
		<div class="placard">
			<div class="wrapper2 clear">


<input value="5" id="notifyFrequency" type="hidden" name="notifyFrequency">	<script>touchNotifyByTimeout();</script>
	</div>
		</div>
	</div>

	<div class="tab clear">
		<div class="maxWidth">
		    <!--  
			<ul id="navmenus" class="clear">
			<li class="open" id="navmenu_0" relaframe="navframe_0">
			<a href="javascript:void(0)" class="li" title="欢迎" onclick="switchNavFrame(parent, 'navmenu_0');" ondblclick="return closeNavFrame(parent, 'navmenu_0', 'closeOnEsc');"><span class="text2"><span id="navframe_0_loading" class="">欢迎</span></span></a><a href="javascript:void(0)" class="closeTab" id="navframe_0_close" onclick="return closeNavFrame(parent, 'navmenu_0', 'closeOnEsc');"></a>
			</li></ul>
			-->
			<ul id="navmenus" class="clear"></ul>
		</div>
	</div>
	<div class="line"></div>
</div>
<script>
parent.openFirstFrame();
</script>
</form>

</body></html>