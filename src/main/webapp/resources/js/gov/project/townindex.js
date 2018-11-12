$(document).ready(function() {

	dg111 = $('#dg').datagrid({
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
			title : '填报时间',
			field : 'inputDate',
			align : 'left',
			halign : 'center',
			width : '120',
			rowspan : 1,
			colspan : 1
			,
			formatter : function(value,
					row, index) {
				var date = new Date(
						value);
				return date
						.format('yyyy-MM-dd');
			}
		},

		{
			title : '部门名称',
			field : 'orgName',
			align : 'left',
			halign : 'center',
			width : '120',
			rowspan : 1,
			colspan : 1
		}
		,

		{
			title : '嘎查村名称',
			field : 'hamletName',
			align : 'left',
			halign : 'center',
			width : '120',
			rowspan : 1,
			colspan : 1
		}
		,

		{
			title : '项目名称',
			field : 'name',
			align : 'left',
			halign : 'center',
			width : '120',
			rowspan : 1,
			colspan : 1
		},

		{
			title : '项目金额(万元)',
			field : 'amount',
			align : 'left',
			halign : 'center',
			width : '120',
			rowspan : 1,
			colspan : 1
		},

		{
			title : '发放时间',
			field : 'releaseTime',
			align : 'left',
			halign : 'center',
			width : '120',
			rowspan : 1,
			colspan : 1
			,
			formatter : function(value,
					row, index) {
				var date = new Date(
						value);
				return date
						.format('yyyy-MM-dd');
			}
		},

		{
			title : '备注',
			field : 'comm',
			align : 'left',
			halign : 'center',
			width : '120',
			rowspan : 1,
			colspan : 1
		},

		{
			title : '批次',
			field : 'batch',
			align : 'left',
			halign : 'center',
			width : '120',
			rowspan : 1,
			colspan : 1
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

var doSearch = function() {
	$('#dg').datagrid('load', {
		name : $('#name').val(),
		comm : $('#comm').val()
	});

}

var grid_height = function() {
	var realHeight = $(window).height() - 28;
	return realHeight;
}
