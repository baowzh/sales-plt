<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>功能修改</title>
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

		$('#updateFunc').form({
			onSubmit : function() {
			},
			success : function(data) {
				var data = eval('(' + data + ')'); // change the JSON string to javascript object
				alert(data.message);
				if (data.success) {
					 window.parent.opener.$('#dg').datagrid('reload');
					window.close()
				}
			}
		});
		
		$('#updateFunc').form('submit');
	}

	
</script>

</head>
<body>
	<div class="easyui-panel" height=280px width=790px title="功能信息修改">
		<form id="updateFunc" action="saveInfo.jhtml" method="post">
			<table align="center" style="width: 90%;">
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox" id="menuId"
						name="rightCode" style="width: 90%"
						data-options="label:'权限编码： ',editable:false,required:true,labelWidth:100,labelAlign:'right'"
						value="${funcright.rightCode}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-textbox"
						name="rightName" style="width: 90%"
						data-options="label:'权限名称： ',editable:true,labelWidth:100,labelAlign:'right'"
						value="${funcright.rightName}"></td>
				</tr>

				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="classCode" style="width: 90%"
						data-options="label:'分类编码： ',editable:false,labelWidth:100,labelAlign:'right'"
						value="${funcright.classCode}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-textbox"
						name="rightAttr" style="width: 90%"
						data-options="label:'权限属性：',editable:false,required:true,labelWidth:100,labelAlign:'right'"
						value="${funcright.rightAttr}"></td>
				</tr>
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="modCode" style="width: 90%"
						data-options="label:'模块编码：',editable:false,required:true,labelWidth:100,labelAlign:'right'"
						value="${funcright.modCode}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-numberbox"
						name="helpIndex" style="width: 90%"
						data-options="label:'帮助索引  ：',labelWidth:100,labelAlign:'right'"
						value="${funcright.helpIndex}"></td>

				</tr>
				<tr>
					<td   colspan="3" align="left"><input class="easyui-textbox"
						name="remark" style="width: 90%"
						data-options="label:'备注：',labelWidth:100,labelAlign:'right'"
						value="${funcright.remark}"></td>
		

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