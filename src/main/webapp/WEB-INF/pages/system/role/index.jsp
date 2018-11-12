<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dateFormatter.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});

	function formatData(val, row, index) {
		if (val != null && val != "") {
			var date = new Date(val);
			return date.format("yyyy-MM-dd hh:mm:ss ");
		} else {
			return val;
		}

	}	

	function updateRoleInfo() {
		var row = $('#dg').datagrid('getSelected');
		if(row) {
  	 		var url = "updateInfo.jhtml?rightCode=" + row.rightcode;

			var top = 0;
			var left = 0;
			if (screen.height > 230) {
				top = (screen.height - 230) / 2;
			}
			if (screen.width > 800) {
				left = (screen.width - 800) / 2;
			}
			//var row = $('#dg').datagrid('getSelected');
			var child = window.open(url, "角色信息修改",
					"height=230,width=800,status=yes,toolbar=no,menubar=no,location=no,top="
							+ top + ",left=" + left);
		}else {
			alert("请选择需要修改的角色");
		}
		

	}
	

	function addRoleInfo() {
		
	
			var top = 0;
			var left = 0
			if (screen.height > 230) {
				top = (screen.height - 230) / 2;
			}
			if (screen.width > 800) {
				left = (screen.width - 800) / 2;
			}

			//var row = $('#dg').datagrid('getSelected');
			var child = window.open("addInfo.jhtml", "新增角色",
					"height=230,width=800,status=yes,toolbar=no,menubar=no,location=no,top="
					+ top + ",left=" + left);

	}
	
	function forcDeleteRoleInfo(row){
		
		var confirmInfo = "是否强制删除角色("+row.rightcode+"-"+row.rightname+")?";
	 　	if(confirm(confirmInfo)) {
	 　		$.ajax({  
      	 		url :"forcDeleteInfo.jhtml?rightCode=" + row.rightcode,
     	    	type:"post",
    	    	async:false,
    	    	dataType:"json",   
          		error: function(){  
          			alert("服务器没有返回数据，可能服务器忙，请重试");  
          		},
          		
          		success: function(data){
					alert(data.message);
					if (data.success) {
						$("#dg").datagrid("reload");
 					}
           		}   
			}); 
	 　	}
	}
	 　	
	
	function deleteRoleInfo(){
		var row = $('#dg').datagrid('getSelected');
		if(row) {
			var confirmInfo = "确定删除角色("+row.rightcode+"-"+row.rightname+")?";
		 　	if(confirm(confirmInfo)) {
		 　		$.ajax({  
          	 		url :"deleteInfo.jhtml?rightCode=" + row.rightcode,
         	    	type:"post",
        	    	async:false,
        	    	dataType:"json",   
              		error: function(){  
              			alert("服务器没有返回数据，可能服务器忙，请重试");  
              		},
              		
              		success: function(data){
						alert(data.message);
						if(data.success) {
							$("#dg").datagrid("reload");
     					}else if(data.errorCode == "1"){
     						forcDeleteRoleInfo(row);
     					}
	           		}   
				});  
		 　	}
		}else {
			alert("请选择需要删除的角色");
		}
		
	}
	
	
	
	function roleFuncManager() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			
			
			var top = 0;
			var left = 0;
			if (screen.height > 500) {
				top = (screen.height - 500) / 2;
			}
			if (screen.width > 850) {
				left = (screen.width - 850) / 2;
			}

			//var row = $('#dg').datagrid('getSelected');
			var child = window.open("roleFuncManager.jhtml?rightCode="
					+ row.rightcode, "角色权限管理",
					"height=500,width=850,status=yes,toolbar=no,menubar=no,location=no,top="
							+ top + ",left=" + left);
		} else {
			alert("请选择需要修改权限的角色");
		}

	}
	
	function getRowIndex(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}

	function formatOper(val, row, index) {
		return '<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-edit" text="修改" plain="true" onclick="editFunc(this)">修改</a>';
	}


	function roleAttrformatOper(val, row, index) {
		if(val == '0'){
			return '功能权限角色';
		}else if(val == '1'){
			return '数据权限角色';
		}else{
			return val;
		}
	}
	
	function validformatOper(val, row, index) {
		if(val == '0'){
			return '有效';
		}else if(val == '1'){
			return '失效';
		}else{
			return val;
		}
	}

	function doSearch(value, name) {
		$('#dg').datagrid('load', {
			name: name,
			value: value
		});

	}
	
	function dateformatOper(val, row, index){
		if (val != null && val != "") {
			var date = new Date(val);
			return date.format("yyyy-MM-dd hh:mm:ss ");
		} else {
			return val;
		}
		
	}
	
	function qq(value, name) {
		alert(value + ":" + name)
	}
</script>

</head>
<body>
	<div style="margin: 20px 0;"></div>

	<table id="dg" class="easyui-datagrid" title="角色列表"
		style="width: 100%; height: 450px" toolbar="#tb"
		data-options="rownumbers:true,method:'get',singleSelect:true,
			url:'infoList.jhtml',
				pagination:true,
				autoRowHeight:true,
				pageSize:10	">

		<thead>
			<tr id="dg-tr">
				<th field="rightcode" width="100" halign="center" align="left">角色编码</th>
				<th field="rightname" width="160" halign="center" align="left">角色名称</th>
				<th field="roleAttr" width="100" halign="center" align="left"
					formatter=roleAttrformatOper>角色属性</th>
				<th field="remark" width="200" halign="center" align="left">备注</th>
				<th field="updateTime" width="130" halign="center" align="left"
					formatter=dateformatOper>更新时间</th>
				<th field="updateStaffId" width="100" halign="center" align="left">更新员工</th>
				<th field="departName" width="100" halign="center" align="left">更新部门</th>
				<th field="validFlag" width="60" halign="center" align="left"
					formatter=validformatOper>有效标志</th>
			</tr>

		</thead>
	</table>
	<div id="tb" style="height: auto">


		<div style="width: 100%;">

			<a href="javascript:void(0)" onclick="roleFuncManager()"
				class="easyui-linkbutton" style="float: right" iconCls="icon-more">角色权限管理</a>

			<div style="float: right; width: 5px">&nbsp;</div>
			<a href="javascript:void(0)" onclick="deleteRoleInfo()"
				id="removeRole" class="easyui-linkbutton" style="float: right"
				iconCls="icon-remove">删除角色</a>
			<div style="float: right; width: 5px">&nbsp;</div>

			<a href="javascript:void(0)" onclick="updateRoleInfo()"
				class="easyui-linkbutton" style="float: right" iconCls="icon-edit">角色修改</a>

			<div style="float: right; width: 5px">&nbsp;</div>
			<a href="javascript:void(0)" onclick="addRoleInfo()"
				class="easyui-linkbutton" style="float: right" iconCls="icon-add">角色新增</a>

		</div>

		<div id="ss">
			<input class="easyui-searchbox" style="width: 300px"
				data-options="searcher:doSearch, prompt:'请输入需要查找角色名',menu:'#mm'"></input>
			<div id="mm" style="width: 120px">
				<div data-options="name:'rightCode'">角色编码</div>
				<div data-options="name:'rightname'">角色名称</div>
			</div>
		</div>



	</div>

</body>





</html>