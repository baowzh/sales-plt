<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单管理</title>
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
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dateFormatter.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

	});

	function roleFuncManager() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			var top = 0;
			var left = 0
			if (screen.height > 500) {
				top = (screen.height - 500) / 2
			}
			if (screen.width > 850) {
				left = (screen.width - 850) / 2
			}

			//var row = $('#dg').datagrid('getSelected');
			var child = window.open("roleFuncManager.jhtml?rightCode="
					+ row.rightcode, "角色权限管理",
					"height=500,width=850,status=yes,toolbar=no,menubar=no,location=no,top="
							+ top + ",left=" + left);
		} else {
			alert("请选择需要修改的功能")
		}

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

	function editFunc(target) {

		var row = $('#dg').datagrid('getRows')[getRowIndex(target)];
		if (row) {
			var top = 0;
			var left = 0
			if (screen.height > 300) {
				top = (screen.height - 300) / 2
			}
			if (screen.width > 800) {
				left = (screen.width - 800) / 2
			}

			//var row = $('#dg').datagrid('getSelected');
			var child = window.open("update.jhtml?rightCode=" + row.rightCode,
					"修改功能",
					"height=300,width=800,status=yes,toolbar=no,menubar=no,location=no,top="
							+ top + ",left=" + left);
		} else {
			alert("请选择需要修改的功能")
		}

	}
	function getRowIndex(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}

	function formatOper(val, row, index) {
		return '<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-edit" text="修改" plain="true" onclick="editFunc(this)">修改</a>';
	}

	function formatData(val, row, index) {
		if (val != null && val != "") {
			var date = new Date(val);
			return date.format("yyyy-MM-dd hh:mm:ss ");
		} else {
			return val;
		}

	}

	function doSearch(value, name) {
		$('#dg').datagrid('load', {
			name : name,
			value : value
		});

	}

	function qq(value, name) {
		alert(value + ":" + name)
	}
</script>

</head'
<body>
	<div style="margin: 20px 0;">
	
	</div>
	
	<table id="dg" class="easyui-datagrid" title="功能列表"
		style="width: 100%; height: 400px" toolbar="#tb"
		data-options="rownumbers:true,method:'get',singleSelect:true,
			url:'infoPageList.jhtml',
				pagination:true,
				autoRowHeight:true,
				pageSize:10	">
			
		<thead>
			<tr id="dg-tr">
				<th field="rightCode" width="100" halign="center" align="left" >权限编码</th>
				<th field="rightName" width="140" halign="center" align="left" >权限名称</th>
				<th field="classCode" width="60" halign="center" align="left" >分类编码</th>
				<th field="rightAttr" width="60"  halign="center" align="left" formatter=rightAttrformatOper >权限属性</th>
				<th field="modCode" width="80"  halign="center" align="left">模块编码</th>
				<th field="helpIndex" width="80"  halign="center" align="left">帮助索引</th>
				<th field="remark" width="140" halign="center" align="left">备注</th>
				<th field="updateTime" formatter=formatData width="120" halign="center" align="left" >更新时间</th>
				<th field="updateStaffId" width="120" halign="center" align="left" >更新员工</th>
				<th field="departName" width="120" halign="center" align="left">更新部门</th>
				<th field="_operate" formatter=formatOper width="60" align="center" >操作</th>
			</tr>

		</thead>
	</table>
	<div id="tb" style="height:auto">
	
	<input id="ss" class="easyui-searchbox" style="width:300px"
    data-options="searcher:doSearch, prompt:'请输入需要查找关键字',menu:'#mm'"></input>
	<div id="mm" style="width:120px">
    	<div data-options="name:'rightCode'">权限编码</div>
        <div data-options="name:'rightName'">权限名称</div>
    	<div data-options="name:'classCode'">分类编码</div>
	</div>
	
	
	</div>
	
</body>






</html>