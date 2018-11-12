<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>菜单管理</title>
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
	var index = 0;
	function removeTab(title) {
		$('#menuInfo').tabs('close', title);
	}

	function addTab() {

		var index = $('#tree').tree('getSelected');
		var title = index.text;
		var url = "menuInfo.jhtml?menuId=" + index.id;
		var content = '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';

		if ($('#menuInfo').tabs('exists', title)) {

			$('#menuInfo').tabs('select', title);

		} else {

			$('#menuInfo').tabs('add', {
				title : title,
				content : content,
				closable : true
			});
		}
	}

	function refreshMenuInfo() {
		// onclick="refreshMenuInfo()"
		var index = $('#tree').tree('getSelected');
		var title = index.text;
		var url = "menuInfo.jhtml?menuId=" + index.id;
		$('#pp').panel('open').panel('refresh', url);

	}

	function reloadMenuTree() {

		$('#tree').tree('reload');

	}

	function testButton2() {
		$('#tree').tree("reload");
	}

	function removeMenu() {
		$('#removeMenu').form({
			onSubmit : function() {
			},
			success : function(data) {
				var data = eval('(' + data + ')'); // change the JSON string to javascript object
				alert(data.message)
				if (data.success) {
					reloadMenuTree();
				}
			}
		});

		$('#removeMenu').form('submit');
	}

	function addSubMenu() {

		var index = $('#tree').tree('getSelected');
		var top = 0;
		var left = 0

		if (screen.width > 900) {
			left = (screen.width - 900) / 2
		}
		if (screen.height > 450) {
			top = (screen.height - 450) / 2
		}

		//var row = $('#dg').datagrid('getSelected');
		var child = window.open("addInfo.jhtml?parentMenuId=" + index.id,
				"修改菜单信息",
				"height=450,width=900,status=yes,toolbar=no,menubar=no,location=no,top="
						+ top + ",left=" + left);

	}

	function addMenu() {

		var top = 0;
		var left = 0

		if (screen.width > 900) {
			left = (screen.width - 900) / 2
		}
		if (screen.height > 450) {
			top = (screen.height - 450) / 2
		}

		//var row = $('#dg').datagrid('getSelected');
		var child = window.open("addInfo.jhtml", "修改菜单信息",
				"height=450,width=900,status=yes,toolbar=no,menubar=no,location=no,top="
						+ top + ",left=" + left);

	}

	function updateMenu() {

		var index = $('#tree').tree('getSelected');
		var top = 0;
		var left = 0

		if (screen.width > 900) {
			left = (screen.width - 900) / 2
		}
		if (screen.height > 450) {
			top = (screen.height - 450) / 2
		}

		//var row = $('#dg').datagrid('getSelected');
		var child = window.open("updateInfo.jhtml?menuId=" + index.id,
				"修改菜单信息",
				"height=450,width=900,status=yes,toolbar=no,menubar=no,location=no,top="
						+ top + ",left=" + left);

	}
</script>

</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-layout" style="width: 100%; height: 95%">
		<div region="west" split="true" title="菜单列表"
			style="width: 20%; height: 100%;">
			<div class="easyui-layout" style="width: 100%; height: 100%">
				<div data-options="region:'north'"
					style="padding: 4px; background: #fafafa; width: 100px; height: 40px;border: 1px solid #ccc">
					<a href="javascript:void(0)" target="_blank"
						class="easyui-linkbutton" onclick="addMenu()" iconCls="icon-add">新增菜单</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-reload" onclick="reloadMenuTree()">刷新</a>
				</div>
				<div data-options="region:'center',split:true"
					style="height: 100%;">
					<ul id="tree" class="easyui-tree"
						data-options="url:'treeInfo.jhtml',
				onSelect: function( node){
					var title = node.text;
					var url = 'info.jhtml?menuId=' + node.id;
					$('#pp').panel('open').panel('refresh', url);
				}"></ul>
				</div>


			</div>
		</div>

		<div id="pp" class="easyui-panel" region="center"
			style="width: 100%; height: 100%;"></div>
	</div>
</body>
</html>