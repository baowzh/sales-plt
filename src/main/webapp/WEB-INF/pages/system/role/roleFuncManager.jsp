<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>角色拥有的权限</title>
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
	$(function() {
	});

	function updateRoleFunc() {
		var top = 0;
		var left = 0
		if (screen.height > 500) {
			top = (screen.height - 500) / 2
		}
		if (screen.width > 800) {
			left = (screen.width - 850) / 2
		}

		//var row = $('#dg').datagrid('getSelected');
		var child = window.open("updateRoleFunc.jhtml?rightCode=${roleCode}",
				"角色权限管理",
				"height=500,width=850,status=yes,toolbar=no,menubar=no,location=no,top="
						+ top + ",left=" + left);

	}
	
	function checkAdult(node){
		
		if(node.children != null && node.children.length > 0){
			node.children = node.children.filter(checkAdult);
		}
		
		return node.checked || (node.children != null && node.children.length > 0);
		
	}
	
	function rightAttrformatOper(val, row, index) {
		if (val == '0') {
			return '菜单权限';
		} else if (val == '1') {
			return '界面控制权限';
		} else {
			return val;
		}
	}
	
	function submitForm() {

		$('#updateUserRole').form({
			onSubmit : function() {
				var funcCodesVal = $('#funcCodes').val();

				var t = $('#funcModCodes').combotree('combotree');
				var n = t.tree('getSelected');
				if (n != null && n.id != null) {
					$('#parentMenuId').val(n.id);
				}
			},
			success : function(data) {
				var data = eval('(' + data + ')'); // change the JSON string to javascript object
				alert(data.message);
				if (data.success) {
					window.close()
				}
			}
		});

		$('#updateUserRole').form('submit');
	}
</script>

</head>
<body>
	<div class="easyui-panel" height=480px width=820px>
		<table title="角色拥有的权限" url="roleFuncList.jhtml?roleCode=${roleCode}"
			toolbar="#tb" class="easyui-treegrid"
			style="width: 100%; height: 400px"
			data-options="
				rownumbers: true,
				idField: 'id',
				treeField: 'text',
				loadFilter: function(nodes){
						if(nodes != null && nodes.length > 0){
							return nodes.filter(checkAdult);
						}else{
							return nodes;
						}
					}
			">
			<thead>

				<tr id="dg-tr">
					<th field="text"  align="left"  width="200">权限名称</th>
					<th field="rightCode" align="center"  width="100">权限编码</th>
					<th field="classCode" align="center"  width="60">分类编码</th>
					<th field="rightAttr" width="80"  align="center" formatter=rightAttrformatOper >权限属性</th>
					<th field="modCode" width="100"  align="center" >模块编码</th>
					<th field="helpIndex" width="100"  align="center" >帮助索引</th>
					<th field="remark" width="150" align="center" >备注</th>
				</tr>
			</thead>
		</table>
		<input type="hidden" id="rolecode" name="roleCode" value="${roleCode}">
		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" onclick="updateRoleFunc()"
				class="easyui-linkbutton" style="float: right" iconCls="icon-edit">修改角色权限</a>
		</div>
		
		<div align="center"
				style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc">
				 <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:window.close();">关闭</a>
			</div>
		

	</div>

</body>
</html>