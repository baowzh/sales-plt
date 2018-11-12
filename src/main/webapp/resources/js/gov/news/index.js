$(document)
		.ready(
				function() {

					dg111 = $('#dg')
							.datagrid(
									{
										url : 'paging.jhtml',
										toolbar : "#tb",
										loadMsg : '数据加载中...',
										sortOrder : 'asc',
										singleSelect : true,
										fit : false,
										width : '100%',
										height : grid_height(),
										showFooter : true,
										openAnimation : 'slide',
										columns : [ [

												{
													title : 'id',
													field : 'id',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1
												},

												{
													title : '标题',
													field : 'title',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1
												},

												{
													title : '栏目',
													field : 'catName',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1
												},

												{
													title : '分类',
													field : 'subcat',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1
												},

												{
													title : '关键字',
													field : 'keywords',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1
												},

												{
													title : '摘要',
													field : 'description',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1
												},

												{
													title : '发布时间',
													field : 'inputtime',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1,
													formatter : function(value,
															row, index) {
														var date = new Date(
																value);
														return date
																.format('yyyy-MM-dd');
													}
												},

												{
													title : '发布部门',
													field : 'departName',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1
												},

												{
													title : '发布人',
													field : 'staffName',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1
												}

												,

												{
													title : '热门新闻',
													field : 'isTop',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1,
													formatter : function(value,
															row, index) {
														if (value == 1) {
															return '是';
														} else {
															return '否';
														}
													}

												},

												{
													title : '【操作】',
													field : 'GPD201610',
													align : 'left',
													halign : 'center',
													width : '120',
													rowspan : 1,
													colspan : 1,
													formatter : function(value,
															row, index) {
														return '<a href="javascript:upd(\''
																+ row.id
																+ '\');">修改</a>&nbsp;|&nbsp;<a href="javascript:del(\''
																+ row.id
																+ '\');">删除</a>';
													}

												}

										]

										],
										autoRowHeight : true,
										striped : true,
										rownumbers : true,
										pagination : true,
										pageSize : 10,
										pageList : [ 10, 30, 50, 70, 100 ],
										remoteFilter : false,
										loadFilter : function(data) {
											return {
												'rows' : data.models,
												'total' : data.totalrowcount
											};
										},
										onLoadError : function(data) {
											//BDialog.alert(data.responseText)
										}

									});

				});

var doSearch = function(value, name) {
	$('#dg').datagrid('load', {
		title : $('#title').val(),
		keyword : $('#keyword').val(),
		description : $('#description').val()
	});

}

var grid_height = function() {
	var realHeight = $(window).height() - 28;
	return realHeight;
};
var upd = function(id) {
	window.location.href = 'edit.jhtml?id=' + id;

};

var toTop=function(){
	var row = $('#dg').datagrid('getSelected');
	if (row == null) {
		alert('请选择待推送新闻。');
		return;
	}
	doTop(1);
};
var cancelTop=function(){
	var row = $('#dg').datagrid('getSelected');
	if (row == null) {
		alert('请选择待取消推送新闻。');
		return;
	}
	doTop(0);
};

var doTop = function(top) {
	var row = $('#dg').datagrid('getSelected');
	$.ajax({
		type : "POST",
		url : 'toTop.jhtml',
		data : {
			id : row.id,
			top : top
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				if(top==1){
					alert('推送成功。');
				}else{
					alert('取消推送成功。');
				}
				
				doSearch();
			} else {
				alert(data.mess);
			}
		},
		error : function(info) {
			console.log("连接异常，请检查！")
		}
	});

};

var del = function(id) {
	// 调用服务u直接删除	
	BDialog.confirm('确定要删除吗？', function() {
		if(arguments[0]){
			$.ajax({
				type : "POST",
				url : 'del.jhtml',
				data : {
					id : id
				},
				dataType : "json",
				success : function(data) {
					if (data.success) {
						alert('删除成功。');
						doSearch();
					} else {
						alert(data.mess);
					}
				},
				error : function(info) {
					console.log("连接异常，请检查！")
				}
			});
		}
		
	});

};
