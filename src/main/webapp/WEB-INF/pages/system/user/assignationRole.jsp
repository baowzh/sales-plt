<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>分配用户角色</title>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript">

var nodes='';


	function submitForm() {

		$('#updateUserRole').form({
			onSubmit : function() {
				
				$('#roleCodes').combobox('setValues',nodes);
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
	<div class="easyui-panel" height=480px width=820px title="分配用户角色">
		<form id="updateUserRole" action="saveUserRole.jhtml" method="post">
			<table align="center" style="width: 90%;">
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox" id="menuId"
						name="staffid" style="width: 90%"
						data-options="label:'用户编码： ',editable:false,required:true,labelWidth:120,labelAlign:'right'"
						value="${user.staffid}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-textbox"
						name="staffname" style="width: 90%"
						data-options="label:'用户名称： ',editable:false,labelWidth:120,labelAlign:'right'"
						value="${user.staffname}"></td>
				</tr>

				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						name="departName" style="width: 90%"
						data-options="label:'部门： ',editable:false,labelWidth:120,labelAlign:'right'"
						value="${user.departName}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-textbox"
						name="eparchycode" style="width: 90%"
						data-options="label:'地市编码性：',editable:false,labelWidth:120,labelAlign:'right'"
						value="${user.eparchycode}"></td>
				</tr>
				<tr>
				
				
					<td align="center"  colspan="3"  style="width: 78%" >
					<select id="roleCodes" class="easyui-combobox" name="roleCodes" multiple="true" multiline="true" label="分配角色"  style="width:90%;height:100px;"
							url="userRoleList.jhtml?staffid=${user.staffid}" data-options="valueField: 'rightcode',
  									textField: 'rightname',
									iconWidth:22,icons:[{
									iconCls:'icon-clear',
									handler: function(e){
										$(e.data.target).combobox('clear');
									}
									}],
									formatter:function(node){
					          			return node.rightcode +'--'+ node.rightname ;
									},
									onSelect :function(record){
										if(nodes == ''){
											nodes = record.rightcode;
										}else{
											nodes = nodes + ',' + record.rightcode;
										}
									},
									onUnselect :function(record){
										if(nodes == ''){
											nodes = '';
										}else{
											nodes = nodes.replace(record.rightcode,'');
											var re = /,,+/g;
											nodes = nodes.replace(re,',');
											re = /,$/;
											nodes = nodes.replace(re,'');
											re = /^,/;
											nodes = nodes.replace(re,'');												}
									}
  									
  									"	>
					
					</select>
					</td>
					

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