<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Nav</title>
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
<link href="${ctx}/resources/js/portal/nav/new_common.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/js/portal/nav/sub_right.css"
	type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/js/portal/main.js"></script>
<script src="${ctx}/resources/js/portal/shouye_tab.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/portal/sub_right.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/portal/jquery-latest.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/portal/left.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/portal/switchframe.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/portal/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/portal/bootstrap/custom.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/portal/bootstrap/icheck.min.js"></script>
<link rel="stylesheet"
	href="${ctx}/resources/js/portal/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${ctx}/resources/js/portal/bootstrap/css/icheck/flat/green.css" />

<style type="text/css">
.treeDiv {
	display: none;
}

.width300 {
	width: 300px;
}

.login-role-box {
	padding: 10px 20px;
}

.login-role-box .radio {
	line-height: 30px;
	color: #333333;
}

.iradio_flat-green {
	margin-right: 10px;
}

.font-orange {
	color: #f46f2a;
	font-size: 18px;
}

.modal-footer {
	border-top: 0px solid #e5e5e5 !important;
}
</style>

<script>
	$j(document).ready(
			function() {
				$j(".tc").find(".tz_box").hide(0);
				$j(".tc").hover(
						function() {
							$j(this).css("z-index", "20").find(".tz_box").css(
									"z-index", "50").stop(true, true).animate({
								opacity : "show"
							});
						},
						function() {
							$j(this).css("z-index", "1").find(".tz_box").css(
									"z-index", "1").stop(true, true).animate({
								opacity : "hide"
							});
						});
				$j(".search_text").focus(function() {
					$j(this).addClass("focus");
				}).blur(function() {
					$j(this).removeClass("focus");
				});
				$j(".sy1_ul li.home02 a").css("background-color", "#fffced");
				$j(".sy1_ul li.home02 a").css("color", "#333");
				initNvgMenu();
				showSidebar();
				
			})
			
    function showSidebar(){
       switchframe('div_contentframe',this,false); 
       // return window.frames['siderbar'].setTreeMenu(this);
     
    }

	function select() {
		var departNames = document.getElementById('departNames').value;
		var departIds = document.getElementById('departIds').value;
		var departNameList = departNames.split(";");
		var departIdList = departIds.split(";");

		var select = document.getElementById("departId");
		var blogin = document.getElementById("blogin");
		//首先清空所有的下拉值
		select.options.length = 0;
		var option;
		for (var i = 0; i < departNameList.length; i++) {
			select.options.add(new Option(departNameList[i], departIdList[i]))
		}
		select.style.display = "block";
		blogin.style.display = "block";
	}

	function openFirstPage() {
		var firstpage = document.getElementById('firstpage').value;
		var subSysCode = document.getElementById('subSysCode').value;
		var staffId = document.getElementById('staffId1').value;
		var LOGIN_CHECK_CODE = document.getElementById('LOGIN_CHECK_CODE').value;
		var url = firstpage + '&staffId=' + staffId + '&subSysCode='
				+ subSysCode + '&LOGIN_CHECK_CODE=' + LOGIN_CHECK_CODE;
		$("#contentframe").attr("src", url);
	}

	function getOpenUrl(page, listener, params) {
		var url = getContextName() + "?service=page/" + page;
		if (listener != null)
			url += "&listener=" + listener;
		if (params != null)
			url += params;
		return url;
	}
	/**
	 * 由于在portal 中调用了sysmanm中的page，所以单独写了一个getUrl 方法
	 * @param page
	 * @param listener
	 * @param params
	 * @returns {string}
	 */
	function getSysOpenUrl(page, listener, params) {
		var url = '/sysmanm/sysmanm' + "?service=page/" + page;
		if (listener != null)
			url += "&listener=" + listener;
		if (params != null)
			url += params;
		return url;
	}

	function openmenu(url) {

		url = url.replace("jhtml&", "jhtml?");
		if (getFrame("contentframe").menuframe == null) {
			url = "&url=" + url;
			var linkobj = getElementByTag(getElementBySrc(), "a");
			if (linkobj != null) {
				url = "&title=" + linkobj.innerHTML + url;
			} else {
				url = "&title=" + $j("#searchcontent").val() + url;
			}
			url = getSysAddr(url);
			getFrame("contentframe").location.href = getContextName()
					+ "?service=page/Navigation&listener=opencontent" + url;
		} else {
			openmenuNew(url);
		}
	}

	function openmenuNew(url) {
		if (!url)
			return false;
		if (url.lastIndexOf("&url=") != -1) {
			url = url.substr(url.lastIndexOf("&url=") + 5);
		}
		redirectToNavByUrl(url, "contentframe");
	}

	function logout() {
		if (!window.confirm('确定要退出吗?')) {
			return false;
		}
		window.location.href = '${ctx}/login.jsp';
		window.sessionStorage.clear();
	}

	function changeStaffRole() {
		var rolesModel = document.getElementById('RolesChangeModal');
		$(rolesModel).modal('show');
		window.sessionStorage.clear();
	}

	

	function selectStaffRole(obj) {
		var roleId = $(obj).attr("roleCode");
		$("#selectRoleId").val(roleId);
		var firstpage = document.getElementById('firstpage').value;
		var subSysCode = document.getElementById('subSysCode').value;
		var staffId = document.getElementById('staffId1').value;
		var LOGIN_CHECK_CODE = document.getElementById('LOGIN_CHECK_CODE').value;
		var url = firstpage + '&staffId=' + staffId + '&subSysCode='
				+ subSysCode + '&LOGIN_CHECK_CODE=' + LOGIN_CHECK_CODE
				+ '&LOGIN_ROLE_ID=' + roleId;
		$("#firstpage").attr("src", url);

		$("#chanegRoleLogin").click();

	}
	function openUpdPassWin() {
		$j('#updPassDiv').show();
		$j('#fade').show();
	}
	function updatePass() {
		// 修改密码
		$j.ajax({
		type : "POST",
		url : '${ctx}/userpass/update.jhtml',
		data : {
		 oldPassWord:$j('#oldPass').val(),
		 newPassWord:$j('#newPass').val()
		},
		dataType : "json",
		success : function(data) {
			if(data.success){
				alert('修改成功，请重新登陆。');
				window.location.href='${ctx}/login.jsp';
			}else{
				alert(data.mess);
			}
		},
		error : function(info) {
			console.log("连接异常，请检查！")
		}
	});
		

	}
	function closeUpdPassWin() {
		$j('#updPassDiv').hide();
		$j('#fade').hide();
	}
</script>



</head>
<body onresize="setWidth();">
	<form method="post" action="\portal">
		<div class="clr"></div>
		<div class="top">
			<div class="title" style="padding-left: 400px;">
				<%-- 				<img align="absmiddle" src="${ctx}/resources/images/unicom.png" --%>
				<!-- 					width="228" /> -->

				${sysConfig.syscname}
			</div>

			<div id="nicemenu">
				<ul>
					<li><span class="head_menu"> <img
							src="${ctx}/resources/images/mail.png" width="20" height="20" />
							<a href="javascript:void(0)"> 0 </a> <img
							src="${ctx}/resources/images/login/triagle.png" width="14"
							height="14" align="top" class="arrow" /></span>
						<div class="sub_menu"></div></li>
					<li><span class="head_menu"><img
							src="${ctx}/resources/images/user.png" width="20" height="20" />
							<a href="javascript:void(0)"> ${userInfo.staffname} </a><img
							src="${ctx}/resources/images/login/triagle.png" width="14"
							height="14" align="top" class="arrow" /></span>
						<div class="sub_menu" id="sta">
							<a href="javascript:void(0)">欢迎您，${userInfo.staffname}！ </a> <a
								target="_top" onclick="logout();isCloseWindow = false;"
								class="item_line tc" href="javascript:void(0)"
								style="z-index: 1;">安全退出</a> <a
								href="javascript:openUpdPassWin();" class="item_line tc">修改密码</a>

						</div>
						
						</li>
				</ul>
			</div>
		</div>
		<div id="RolesChangeModal" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-backdrop fade in" style="height: 100%;"></div>
			<div class="modal-dialog width300" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title font-orange">请选择角色</h4>
					</div>
					<div class="modal-body login-role-box">
						<div id="rolesmodal" style="padding: 5px 20px;">
							<div id="rolesListInfo" class="form-group" style="float: left;">
							</div>
						</div>
					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>

		<div id="menu-1" class="_left_nav_menu">
			<div class="_left_menu_wap">

				<div class="left_title">功能菜单检索</div>
				<div id="search_box">

					<input type="text" id="searchcontent" value="" class="swap_value s"
						autocomplete="off" style="outline: none;" /> <span
						class="search-box"><img
						src="${ctx}/resources/images/login/zoom_06.png" width="19"
						height="19" class="go" alt="搜索" /></span>

				</div>
				<hr class="hr_line" />

				<span id="FavoriteMenusPart" optimize="true">
					<ul class="useful" id="favMenus">


					</ul>
				</span>

				<div class="left-ico">
					<a href="javascript:void(0);"
						onclick="switchframe('div_contentframe',this,true);openmenu(getOpenUrl('brower.detail','detail',''));closeLeftMenu();"
						caption="浏览器下载"> <img
						src="${ctx}/resources/images/login/browser.png" /> <br />浏览器下载
					</a>
				</div>
			</div>

		</div>
		<!-- 
		<div class="nav">
			<ul class="sy1_ul">
				<div class="sy2_inside">
					<li id="ctrl-btn-1" class="home01 closed"><a
						href="javascript:void(0)" class="_left_off_icon2"
						onclick="switchLeftMenu()"> </a></li>
					<asiainfo:topMenu userid=""></asiainfo:topMenu>
				</div>
			</ul>
		</div>
		 -->
		
		<div id="div_firstpage" style="display: block; height: 84%;">
			<iframe id="firstpage" name="firstpage" style="margin-top: 0px;"
				width="100%" height="100%" frameborder="no" scrolling="auto"
				noresize="noresize" src="${sysConfig.firstPage}"> </iframe>
		</div>
		<div id="div_contentframe" style="display: none; height: 100%;">
			<div id="in_siderbar"
				style="width: 177px; height: 100%; float: left; border-right: 1px solid #ccc;">
				<div style="height: 100%;">
					<iframe id="siderbar" name="siderbar" style="margin-top: 0px;"
						width="100%" height="84%" frameborder="no" scrolling="no"
						noresize="noresize" src="${ctx}/sidebar/index.jhtml"> </iframe>
				</div>
				<div class="f_slip">
					<a class="sideOff" id="tipon" href="javascript:void(0)"
						onclick="shrinkSiderbar(true)"></a> <a class="sideOn" id="tipoff"
						href="javascript:void(0)" style="display: none"
						onclick="shrinkSiderbar(false)"></a>
				</div>
			</div>
			<div id="in_contentframe"
				style="width: calc(100% - 179px); float: left; height: 100%;">
				<iframe id="contentframe" name="contentframe"
					style="margin-top: 0px;" width="100%" height="84%" frameborder="no"
					scrolling="auto" noresize="noresize"
					src="${ctx}/navigation/index.jhtml"> </iframe>
			</div>
		</div>




		<iframe id="printframe" name="printframe" style="margin-top: 0px;"
			width="100%" height="0px" frameborder="no" src="" scrolling="auto"
			noresize="noresize"> </iframe>
	</form>
	<div
		style="display: none; background-color: #fff; z-index: 1001; width: 300px; height: 160px; position: fixed; top: 150px; left: calc(50% - 200px); border: 1px solid #d10707; padding: 5px;"
		id="updPassDiv">
		<table style="width: 100%; height: 100%;">
			<tr>
				<td>当前密码：</td>
				<td><input type="password" name="oldPass" id="oldPass" /></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" name="newPass" id="newPass" /></td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left: 90px;"><a
					style="display: inline-block; float: left; margin-right: 20px;"
					href="javascript:updatePass();">修改</a> <a
					style="display: inline-block; float: left"
					href="javascript:closeUpdPassWin();"> 关闭</a></td>
			</tr>
		</table>
	</div>

	<div id="fade" class="black_overlay"
		style="display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: #00000087; z-index: 1000; -moz-opacity: 0.2; opacity: 0.20;"></div>

</body>
</html>