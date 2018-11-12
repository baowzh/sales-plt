<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Sidebar</title>
<script language="JavaScript" src="${ctx}/resources/js/public.js"></script>
<link href="${ctx}/resources/style/public.css" rel="stylesheet"
	type="text/css" media="screen">
<link href="${ctx}/resources/js/portal/style/frame_bss.css"
	rel="stylesheet" type="text/css" />
<script language="JavaScript"
	src="${ctx}/resources/js/portal/nav/frame.js"></script>
<script language="JavaScript"
	src="${ctx}/resources/js/portal/bootstrap/jquery.min.js"></script>
<link rel="stylesheet" href="${ctx}/resources/js/portal/jquery.ui/themes/base/jquery.ui.all.css"/>
<link rel="stylesheet" href="${ctx}/resources/js/portal/jquery.treeview/jquery.treeview.css"/>
<link rel="stylesheet" href="${ctx}/resources/js/portal/jquery.ui/themes/ailkext/ailk.ui.accordion.css"/>
<style>
	a.menu_link:hover {font-weight:bold;color:#d53830;}
</style>
<script language="JavaScript" src="${ctx}/resources/js/portal/jquery.cookie.js"></script>
<script language="JavaScript" src="${ctx}/resources/js/portal/jquery.ui/jquery.ui.core.min.js"></script>
<script language="JavaScript" src="${ctx}/resources/js/portal/jquery.ui/jquery.ui.widget.min.js"></script>
<script language="JavaScript" src="${ctx}/resources/js/portal/jquery.ui/jquery.ui.accordion.min.js"></script>
<script language="JavaScript" src="${ctx}/resources/js/portal/jquery.treeview/jquery.treeview.js"></script>
<script>
	function openHomeNode(homeNode) {
		var node = getElement(homeNode);
		if (node != null) {
			var menuframe = getFrame("contentframe").menuframe;
			if (menuframe != null && menuframe.HOLD_FIRST_PAGE != null) {
				menuframe.HOLD_FIRST_PAGE = true;
				//node.click();
			} else {
				//setTimeout("openHomeNode('" + homeNode + "')", 100);
			}
		}
	}

	delete(Object.prototype.toJSONString);//解决与wade框架JSON.js冲突
	
	function setTreeMenu(e){
		var $div = $(e).find('.treeDiv');
		//如果当前sidebar中的树形菜单与被点击需要初始化的导航分类一致时，不需要再初始化了。
		if($div.attr('flag') != $('#accordion').attr('flag')){
			$('#accordion').attr('flag',$div.attr('flag'));//记录导航分类的ID
			$("#accordion").accordion('destroy').html($div.html());
			$('.treeMenu').treeview({
				animated: true
			});
			$("#accordion").accordion({
				autoHeight:false,
				fillSpace: true,
				navigation: true
			});
		}
	}
	
	$(function(){
		$('a.text').on('click',function(){
			//左侧菜单栏响应动作队列操作：闭合左侧栏
			//$(window.parent.document).find('#tipon').click();
		});
	});
	
	function openmenu(url,obj) {
		if(getFrame("contentframe").menuframe == null){
			url = "&url=" + url;
			var linkobj = getElementByTag(getElementBySrc(), "a");
			if (linkobj != null) {
				url = "&title=" + linkobj.innerHTML + url;
			}
			else{
				url = "&title=" + $j("#searchcontent").val() + url;
			}
			url = getSysAddr(url);
			getFrame("contentframe").location.href = '${ctx}/' + url;
		}else{
			addNavFrame("contentframe",url,$(obj).attr('title'));
		}
	}
	
	function openmenuNew(url) {
		if (!url) return false;
		if (url.lastIndexOf("&url=") != -1) {
			url = url.substr(url.lastIndexOf("&url=") + 5);
		}
		redirectToNavByUrl(url, "contentframe");
	}
</script>
</head>
<body >
 <div id="accordion" flag="0" class="ui-accordion ui-widget ui-helper-reset ui-accordion-icons" role="tablist"> <h3 class="ui-accordion-header ui-helper-reset ui-state-default ui-state-active ui-corner-top" role="tab" aria-expanded="true" aria-selected="true" tabindex="0"><span class="ico-down"></span><a href="javascript:void(0);"> 三务公开</a></h3><div class="treeMenu treeview ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active" role="tabpanel" style="height: 534px; overflow: auto;"><ul><li><a class="text menu_link" id="SYS0007" menuid="SYS0007" href="javascript:void(0)" title="信息公开" caption="/news/index.jhtml" onclick="clickMenuItem(this);openmenu('/news/index.jhtml',this);">信息公开</a></li><li><a class="text menu_link" id="SYS0008" menuid="SYS0008" href="javascript:void(0)" title="项目管理" caption="/project/depart/index.jhtml" onclick="clickMenuItem(this);openmenu('/project/depart/index.jhtml',this);">项目管理</a></li><li><a class="text menu_link" id="SYS0009" menuid="SYS0009" href="javascript:void(0)" title="组织机构管理" caption="/org/index.jhtml" onclick="clickMenuItem(this);openmenu('/org/index.jhtml',this);">组织机构管理</a></li><li class="last"><a class="text menu_link" id="SYS0013" menuid="SYS0013" href="javascript:void(0)" title="用户管理" caption="/system/user/index.jhtml?a=SYS0009" onclick="clickMenuItem(this);openmenu('/system/user/index.jhtml?a=SYS0009',this);">用户管理</a></li></ul></div></div>
<script>openHomeNode("homemenu");</script>
</body>
</html>