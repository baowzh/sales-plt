<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/easyUI/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/easyUI/themes/icon.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/easyUI/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/easyUI/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	
	function editUserRole() {
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
			var child = window.open("assignationRole.jhtml?staffid="
					+ row.staffid, "修改用户角色",
					"height=500,width=850,status=yes,toolbar=no,menubar=no,location=no,top="
							+ top + ",left=" + left);
		} else {
			alert("请选择需要授权的用户")
		}

	}

	function getRowIndex(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}

	function formatOper(val, row, index) {
		return '<a href="#" class="easyui-linkbutton"  iconCls="icon-edit" text="修改" plain="true" onclick="editFunc(this)">修改</a>';
	}

	function sexformatOper(val, row, index) {
		if (val == '0') {
			return '男';
		} else if (val == '1') {
			return '女';
		} else {
			return val;
		}
	}

	function dismissionformatOper(val, row, index) {
		if (val == '0') {
			return '有效';
		} else if (val == '1') {
			return '失效';
		} else {
			return val;
		}
	}
	function custManagerformatOper(val, row, index) {
		if (val == '0') {
			return '客户经理';
		} else if (val == '1') {
			return '商家客户经理';
		} else if (val == '2') {
			return '集团客户经理';
		} else {
			return val;
		}
	}

	function doSearch(value, name) {
		$('#dg').datagrid('load', {
			'staffname' : value
		});

	}
	
	
	var resetPass=function(){
	 var row = $('#dg').datagrid('getSelected');
	 if (row) {
		 $.ajax({
	      "dataType": 'json',
	      "type": "POST",
	      "url": 'resetPassword.jhtml',
	      "data": {
	      staffId:row.staffid
	      
	      },
	      "success": function(json) {
		       if(json.success){
		         alert('重置密码成功,新密码为'+json.newPass);
		       }else{
		        alert(json.message);
		       }
	      },
	     "error": function(XMLHttpRequest, textStatus, errorThrown) {
		     
	     }
       });
		}else{
		
		 alert('请选择需要重置密码的用户。');
		}
	}

	
</script>

</head>
<body>
	<div style="margin: 20px 0;"></div>

	<table id="dg" class="easyui-datagrid" title="员工列表"
		style="width: 100%; height: 450px" toolbar="#tb"
		data-options="rownumbers:true,method:'post',singleSelect:true,
			url:'infoList.jhtml',
				pagination:true,
				autoRowHeight:true,
				pageSize:10	">

		<thead>
			<tr id="dg-tr">
				<th field="staffid" width="100" halign="center" align="left">员工编码</th>
				<th field="staffname" width="140" halign="center" align="left">员工姓名</th>
				<th field="departName" width="100" halign="center" align="left">部门名称</th>
				<th field="email" width="100" halign="center" align="left">电子邮件</th>
				<th field="linkPhone" width="60" halign="center" align="left">联系电话</th>

			</tr>

		</thead>
	</table>
	<div id="tb" style="height: auto">

		<input id="ss" class="easyui-searchbox" style="width: 300px"
			data-options="searcher:doSearch, prompt:'请输入需要查找用户名',menu:'#mm'"></input>
		<div id="mm" style="width: 120px">
			<div data-options="name:'staffname'">员工名称</div>
		</div>

		<a href="javascript:void(0)" onclick="editUserRole()"
			class="easyui-linkbutton" style="float: right" iconCls="icon-more">分配用户角色</a>
		<a href="javascript:void(0)" onclick="resetPass();"
			class="easyui-linkbutton" style="float: right" iconCls="icon-more">重置密码</a>

	</div>


</body>

</html>