<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>配置角色权限</title>
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
	var checkVals = '';
	$(document).ready(function() {
		checkVals = "${funcCodes}"
	});

	function doSearch(e) {
		funcModCodes = $('#funcModCodes');
		var searchText = $("#searchText").val();
		var checkVal = checkVals;
		var rightcode = "${role.rightcode}";
		var params = {
			'checkVal' : checkVal,
			'searchText' : searchText

		};
		funcModCodes.tree("options").queryParams = params;
		funcModCodes.tree('reload');
	
	}
	
	function setcheckVals(node,checked){
		if (checked) {
			if (checkVals == '') {
				checkVals = node.rightCode;
			} else if(checkVals.indexOf(node.rightCode)<0){
				checkVals = checkVals + ',' + node.rightCode;
			}

		} else {
			var reg = new RegExp(node.rightCode , "g" );
			checkVals = checkVals.replace(reg, '');
			var re = /,,+/g;
			checkVals = checkVals.replace(re, ',');
			re = /,$/;
			checkVals = checkVals.replace(re, '');
			re = /^,/;
			checkVals = checkVals.replace(re, '');

		}
		
		if(node.children != null && node.children.length > 0){
			setCheckVal4Child(node.children,checked)
		}
		
	}

	function setCheckVal4Child(childs, checked) {
		
		if(childs == null || childs.length < 1){
			return;
		}
		
		for(var i = 0 ; i< childs.length;i++ ){
			setcheckVals(childs[i],checked);
		}
	}

	function getCheckVal() {
		var checkVal = '';
		var nodes = $('#funcModCodes').tree('getChecked',
				[ 'checked', 'indeterminate' ]);
		if (nodes != null && nodes.length > 0) {
			var beginIndex = 0;
			if (nodes[0].id == '0') {
				beginIndex++;
			}
			checkVal = nodes[beginIndex].rightCode;
			for (var i = beginIndex + 1; i < nodes.length; i++) {

				if (nodes[i].id != '0') {
					checkVal = checkVal + "," + nodes[i].rightCode;
				}

			}
		}
		return checkVal;
	}

	function submitForm() {

		$('#updateUserRole').form({
			onSubmit : function() {
				$('#funcCodes').val(checkVals);
			},
			success : function(data) {
				var data = eval('(' + data + ')'); // change the JSON string to javascript object
				alert(data.message);
				if (data.success) {
					window.history.back();
				}
			}
		});

		$('#updateUserRole').form('submit');
	}
</script>

</head>
<body>
	<div class="easyui-panel" height=480px width=820px title="配置角色权限">
		<form id="updateUserRole" action="saveRoleFunc.jhtml" method="post">
			<table align="center" style="width: 70%;">
				<tr>
					<td align="right" width="40%"><input class="easyui-textbox"
						id="rightcode" name="rightcode" style="width: 100%"
						data-options="label:'角色编码： ',editable:false,required:true,labelWidth:100,labelAlign:'right'"
						value="${role.rightcode}"></td>
					<td width="20%"></td>
					<td align="left" width="40%"><input class="easyui-textbox"
						name="rightname" style="width: 100%;"
						data-options="label:'角色名称： ',editable:false,labelWidth:100,labelAlign:'right'"
						value="${role.rightname}"></td>
				</tr>
				<tr>


					<td colspan="3" align="right">
						<div class="easyui-layout" style="width: 100%; height: 350px;">
							<div class="easyui-panel" region="west" split="false"
								align="right"
								style="width: 15%; height: 350px; padding: 0px; font-size: 12px; border: 0px; color: #000;">
								分配权限:</div>
								<div class="easyui-panel"
								style="padding: 0px; width: 83%; height: 100%">
							<div class="easyui-layout" style="width: 0px; width:, 100%;   height: 100%;">		
								<div data-options="region:'north'"
					style="padding: 4px; background: #fafafa; width: 100px; height: 40px;border: 1px solid #ccc">
									<input id="searchText" class="easyui-textbox"
										style="width: 200px"
										data-options="label:'搜索:',labelWidth:40,
													icons: [{
															iconCls:'icon-search',
															handler:doSearch
													}]
					
					"></input>
								</div>
					<div data-options="region:'center',split:true"
					style="height: 310px;">
								<ul id="funcModCodes" class="easyui-tree" name="funcModCodes"
									url="roleFuncList.jhtml?roleCode=${role.rightcode}"
									data-options="labelPosition:'left',animate:true,checkbox:true,
							 formatter:function(node){
							 
					          		if(node.id == '0'){
										return '页面';
					          		}		          		
					          		return node.text;
								},
								onCheck:setcheckVals
					 "></ul>
							</div>
							</div>
							</div>
						</div>
					</td>


				</tr>



			</table>
			<input type="hidden" id="funcCodes" name="funcCodes"
				value="${funcCodes}">

			<div align="center"
				style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc">
				<a href="javascript:void(0)" onclick="submitForm()"
					class="easyui-linkbutton" iconCls="icon-save">保存</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel" onclick="javascript:window.history.back();">返回</a>
			</div>


		</form>
	</div>

</body>
</html>