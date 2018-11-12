<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>角色新增</title>
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
	function submitForm() {

		$('#addRole').form({
			onSubmit : function() {
	

			},
			success : function(data) {
				var data = eval('(' + data + ')'); // change the JSON string to javascript object
				alert(data.message)
				if (data.success) {
					opener.$("#dg").datagrid("reload");
					window.close()
				}
			}
		});

		$('#addRole').form('submit');
	}
</script>

</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" style="width: 100%; height: 90%;" title="角色新增">
		<form id="addRole" action="saveRole.jhtml" method="post">
			<table align="center" style="width: 100%;">
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="rightname" style="width: 90%" 
						data-options="label:'角色名称： ',required:true,labelWidth:100,labelAlign:'right'"
						value="${rightname}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><select class="easyui-combobox"
						name="roleAttr" size="100"
						data-options="label:'角色属性：',required:true,labelWidth:100,labelAlign:'right',panelHeight:'auto'"
						style="width: 90%;">
							<option value="0" selected>功能权限角色</option>
							<option value="1">数据权限角色</option>
					</select></td>
				</tr>

				<tr>
					<td align="right" width="40%"><select class="easyui-combobox"
						name="validFlag" 
						data-options="label:'有效标注：',required:true,labelWidth:100,labelAlign:'right',panelHeight:'auto'"
						style="width: 90%;">
							<option value="0" selected>有效</option>
							<option value="1">失效</option>
					</select></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-numberbox"
						name="remark" style="width: 90%" 
						data-options="label:'备注：',labelWidth:100, labelAlign:'right'"
						value="${remark}"></td>
				</tr>

			</table>

			<div align="center"
				style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc">
				<a href="javascript:void(0)" onclick="submitForm()"
					class="easyui-linkbutton" iconCls="icon-save">保存</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:window.close()">取消</a>
			</div>


		</form>
	</div>

</body>
</html>