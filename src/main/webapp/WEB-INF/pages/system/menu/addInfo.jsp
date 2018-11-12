<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>菜单新增</title>
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
function submitForm(){
	
	$('#addMenu').form({
		onSubmit : function() {
			var t = $('#parentTitle').combotree('tree');
			var n = t.tree('getSelected');
			if (n != null && n.id != null) {
				$('#parentMenuId').val(n.id);
			}

		},
		success : function(data) {
			var data = eval('(' + data + ')'); // change the JSON string to javascript object
			alert(data.message)
			if (data.success) {
				opener.reloadMenuTree();

				window.close()
			}
		}
	});
	
	$('#addMenu').form('submit');
}
</script>

</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel"  style="width: 100%; height: 100%;"  title="菜单信息新增">
		<form id="addMenu" action="saveInfo.jhtml" method="post">
			<table align="center" style="width: 90%;">
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.menuTitle" style="width: 90%"
						data-options="label:'菜单标题： ',required:true,labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.menuTitle}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.menuText" style="width: 90%"
						data-options="label:'菜单说明： ',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.menuText}"></td>
				</tr>

				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.subsysCode" style="width: 90%"
						data-options="label:'子系统编码：',required:true,labelWidth:110,labelAlign:'right'"
						validType="length[3,3]" invalidMessage="子系统编码必须为3位" value="${systemMenuBean.systemGuiMenu.subsysCode}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><select id="parentTitle"
						data-options="label:'上级菜单： ' , value:'${systemMenuBean.parentTitle}' ,labelWidth:110,labelAlign:'right',
						iconWidth:22,icons:[{
						iconCls:'icon-clear',
						handler: function(e){
							$(e.data.target).combotree('clear');
							$('#parentMenuId').val('');
						}
						}]"
						class="easyui-combotree" url="menuTree.jhtml" name="parentTitle"
						style="width: 90%"  /></td>
				</tr>
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.displayMode" style="width: 90%"
						data-options="label:'显示方式：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.displayMode}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-numberbox"
						name="systemGuiMenu.imageIndex" style="width: 90%"
						data-options="label:'图片索引  ：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.imageIndex}"></td>

				</tr>
				<tr>
					<td align="right" width="40%"><input class="easyui-numberbox"
						name="systemGuiMenu.showOrder" style="width: 90%"
						data-options="label:'显示顺序：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.showOrder}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-numberbox"
						name="systemGuiMenu.toolbarIndex" style="width: 90%"
						data-options="label:'工作条索引：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.toolbarIndex}"></td>

				</tr>
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.devideType" style="width: 90%"
						data-options="label:'分隔类别：',labelWidth:110,labelAlign:'right',validType:'length[0,1]'"
						invalidMessage="分隔类别最大长度为1" value="${systemMenuBean.systemGuiMenu.devideType}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-numberbox"
						name="systemGuiMenu.classLevel" style="width: 90%"
						data-options="label:'级别：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.classLevel}"></td>

				</tr>
				<tr>

					<td align="right" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.shortKey" style="width: 90%"
						data-options="label:'快捷键：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.shortKey}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.remark" style="width: 90%"
						data-options="label:'备注：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.remark}"></td>


				</tr>

				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.menuType" style="width: 90%"
						data-options="label:'菜单类型：',labelWidth:110,labelAlign:'right'"
						invalidMessage="分隔类别最大长度为1" validType="length[0,1]"
						value="${systemMenuBean.systemGuiMenu.menuType}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.imageUrl" style="width: 90%"
						data-options="label:'图片地址：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.imageUrl}"></td>

				</tr>
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="systemGuiMenu.menuStatus" style="width: 90%"
						data-options="label:'菜单状态：',labelWidth:110,labelAlign:'right'"
						invalidMessage="菜单状态最大长度为1" validType="length[0,1]"
						value="${systemMenuBean.systemGuiMenu.menuStatus}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-numberbox"
						name="systemGuiMenu.imageId" style="width: 90%"
						data-options="label:'图片Id：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.systemGuiMenu.imageId}"></td>

				</tr>
				<tr>


					<td align="right" width="40%"><input class="easyui-textbox"
						name="modFile.modName" style="width: 90%"
						data-options="label:'模块名称：',labelWidth:110,labelAlign:'right'"
						value="${systemMenuBean.modFile.modName}"></td>

					<td width="20%"></td>
					<td align="left" width="40%"><select class="easyui-combobox"
						name="modFile.modType"
						data-options="label:'菜单类型：',labelWidth:110,labelAlign:'right'"
						style="width: 90%;">
							<c:if test="${systemMenuBean.modFile.modType == '0'}">

								<option value="0" selected>DLL文件</option>
								<option value="1">WEB地址</option>
							</c:if>

							<c:if test="${systemMenuBean.modFile.modType != '0'}">

								<option value="0">DLL文件</option>
								<option value="1" selected>WEB地址</option>
							</c:if>
					</select></td>

				</tr>


			</table>
			<input type="hidden" id = "parentMenuId" name="systemGuiMenu.parentMenuId" value="${systemMenuBean.systemGuiMenu.parentMenuId}">
			
			<div align="center"
				style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc">
				<a href="javascript:void(0)" onclick="submitForm()" class="easyui-linkbutton" iconCls="icon-save"  >保存</a>
				<a	href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:window.close()">取消</a>
			</div>


		</form>
	</div>

</body>
</html>