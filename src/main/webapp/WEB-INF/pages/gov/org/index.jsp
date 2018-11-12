<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>菜单管理</title>
<asiainfo:resource needFallBack="false"
	type="Bootstrap,easyui,jquery,dynamicform,validate"></asiainfo:resource>
<script type="text/javascript">
	var index = 0;
	
	function reloadOrgTree() {
		$('#tree').tree('reload');
	}


	function edit(id) {
		var url = 'edit.jhtml?id=' + id
		$('#pp').panel('open').panel('refresh', url);
		// 绑定插件
		$('#parentId').asiainfoSelect({
			id : '#parentId',
			url : '${ctx}/ui/getTowns.jhtml'
		});

	}

	
	var submitForm = function() {
	    // 加校验
	    var type=$('#type').val();
	    if(type==null||type==''){
	      alert('请选择组织机构类别。');
	      return ;
	    }
	    
	     var name=$('#name').val();
	    if(name==null||name==''){
	      alert('请填写组织机构名称。');
	      return ;
	    }
	    var parentId=$('#parentId').val();
	    if(type==3&&(parentId==null||parentId=='')){
	        alert('请选择乡镇。');
	         return ;
	    }
		$('#editOrg').submit();

	}
	var loadTowns = function() {

		$.ajax({
			type : "POST",
			url : '${ctx}/ui/getTowns.jhtml',
			dataType : "json",
			success : function(data) {
				$('#parentId').empty();
				var defaultOption = $('<option>').attr({
					value : ''
				}).text('请选择');
				$('#parentId').append(defaultOption);
				for (var i = 0; i < data.length; i++) {
					var option = $('<option>').attr({
						value : data[i].id
					}).text(data[i].text);
					$('#parentId').append(option);
				}
			},
			error : function(info) {
				console.log("连接异常，请检查！")
			}
		});

	}
	
 var del=function(id){
 
  $.ajax({
			type : "POST",
			url : '${ctx}/org/del.jhtml',
			data:{
			id:id
			},
			dataType : "json",
			success : function(data) {
				 if(data.success){
				   alert('删除成功。');		
				   window.location.href='${ctx}/org/index.jhtml';		   
				 }else{
				  alert(data.mess);
				 }
			},
			error : function(info) {
				console.log("连接异常，请检查！")
			}
		});
 
 
 }
 
 var mess='${mess}';
 if(mess!=''){
   alert(mess);
 }
	
</script>

</head>
<body style="background: #F7F7F7;">
	<div style="margin: 20px 0;"></div>
	<div class="easyui-layout" style="width: 100%; height: 95%">
		<div region="west" split="true" style="width: 20%; height: 100%;">
			<div class="easyui-layout" style="width: 100%; height: 100%">
				<div data-options="region:'north'"
					style="padding: 4px; background: #fafafa; width: 100px; height: 40px; border: 1px solid #ccc">
					<a href="javascript:void(0)" target="_blank"
						class="btn btn-success" onclick="edit()">添加</a> <a
						href="javascript:void(0)" class="btn btn-success"
						iconCls="icon-reload" onclick="reloadOrgTree()">刷新</a>
				</div>
				<div data-options="region:'center',split:true" style="height: 100%;">
					<ul id="tree" class="easyui-tree"
						data-options="url:'treeInfo.jhtml',
				onSelect: function( node){
					var title = node.text;
					var url = 'info.jhtml?id=' + node.id;
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