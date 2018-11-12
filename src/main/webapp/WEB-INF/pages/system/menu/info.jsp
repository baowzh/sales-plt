<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>Basic Tree - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/easyUI/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/easyUI/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/easyUI/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/easyUI/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

	
	
	
</script>

</head>
<body>
	<div class="easyui-panel" style="width: 100%;" title="${systemMenuBean.systemGuiMenu.menuTitle}">
		<table style="width: 100%; font-size:12px;color:'#333'">
			<tr>
				<td  style="width: 15%;" align="right">菜单标识：</td>
				<td style="width: 35%;" align="left">${systemMenuBean.systemGuiMenu.menuId}</td>
				<td style="width: 15%;" align="right">菜单标题：</td>
				<td style="width: 35%;" align="left">${systemMenuBean.systemGuiMenu.menuTitle}</td>
			</tr>

			<tr>
				<td align="right">菜单说明：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.menuText}</td>
				<td align="right">上级菜单标题：</td>
				<td align="left">${systemMenuBean.parentTitle}</td>
			</tr>
			<tr>
				<td align="right">子系统编码：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.subsysCode}</td>
				<td align="right">图片索引：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.imageIndex}</td>
			</tr>
			<tr>
				<td align="right">显示顺序：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.showOrder}</td>
				<td align="right">工作条索引：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.toolbarIndex}</td>
			</tr>
			</tr>
			</tr>
			<tr>
				<td align="right">分隔类别：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.devideType}</td>
				<td align="right">级别：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.classLevel}</td>
			</tr>
			</tr>
			<tr>
				<td align="right">显示方式：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.displayMode}</td>
				<td align="right">权限编码：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.rightCode}</td>
			</tr>
			</tr>
			<tr>
				<td align="right">快捷键：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.shortKey}</td>
				<td align="right">备注：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.remark}</td>
			</tr>
			<tr>
				<td align="right">更新时间：</td>
				<td align="left"><fmt:formatDate value="${systemMenuBean.systemGuiMenu.updateTime}" type="both"/></td>
				<td align="right">更新员工：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.updateStaffId}</td>
			</tr>
			<tr>
				<td align="right">更新部门：</td>
				<td align="left">
				<asiainfo:select displaytext="true" emid="depart" name="updateStaffId" id="updateStaffId" value="${systemMenuBean.systemGuiMenu.updateDepartId}"></asiainfo:select>
				</td>
				<td align="right">图片地址：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.imageUrl}</td>
			</tr>
			<tr>
				<td align="right">菜单状态：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.menuStatus}</td>
				<td align="right">图片编码：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.imageId}</td>
			</tr>
			<tr>
				<td align="right">菜单类型：</td>
				<td align="left">${systemMenuBean.systemGuiMenu.menuType}</td>
				<td align="right">模块名称/Url：</td>
				<td align="left">${systemMenuBean.modFile.modName}</td>
			</tr>
			</tr>
			<tr>
				<td align="right">库文件类型：</td>
				<td align="left"><c:if
						test="${systemMenuBean.modFile.modType == '0'}">DLL文件</c:if> <c:if
						test="${systemMenuBean.modFile.modType == '1'}">WEB地址</c:if>
				<td align="right"></td>
				<td align="left"></td>
			</tr>


		</table>
		<form id="removeMenu"
			action="removeMenuInfo.jhtml?menuId=${systemMenuBean.systemGuiMenu.menuId}"
			method="post">
			<div align="center"
				style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc">
				
				<a		href="javascript:void(0)"
					class="easyui-linkbutton"  onclick="addSubMenu()" iconCls="icon-add">新增子菜单</a>
				<a	href="javascript:void(0)" class="easyui-linkbutton"  onclick="updateMenu()"  iconCls="icon-edit">修改菜单</a>
				<a href="javascript:void(0)" onclick="removeMenu()"
					class="easyui-linkbutton" iconCls="icon-cancel">删除菜单</a>
			</div>
		</form>



	</div>

</body>
</html>