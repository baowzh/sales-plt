var asiainfoViewGrid = $.widget("ui.asiainfoViewGrid", jQuery.ui.formComponent, {
	version : "1.0.0-beta.1",
	defaultElement : "<div>",
	delay : 300,
	options : {
		id : '#grid1',
		listeners : [],// 当自己改变时，要通知刷新的列表
		filterfields : [], // 当自己刷新时，要传入的参数 {selector:'#test1',type:'select'}
		url : window.contextroot + '/form/viewGrid/config.jhtml'
	},
	_create : function() {
		var gridConfig = this._getGridConfig();
		var tabHeads = new Array();
		for (var i = 0; i < gridConfig.heads.length; i++) {
			var coldefs = new Array();
			for (var j = 0; j < gridConfig.heads[i].length; j++) {
				var coldef = {
					title : gridConfig.heads[i][j].title
				};
				if (gridConfig.heads[i][j].colmeta != null
						&& gridConfig.heads[i][j].colmeta.field != null
						&& gridConfig.heads[i][j].colmeta.field != '') {
					coldef = $.extend(coldef, {
						field : gridConfig.heads[i][j].colmeta.field
					});
				}
				if (gridConfig.heads[i][j].colmeta != null
						&& gridConfig.heads[i][j].colmeta.align != null
						&& gridConfig.heads[i][j].colmeta.align != '') {
					coldef = $.extend(coldef, {
						align : gridConfig.heads[i][j].colmeta.align
					});
				}
				if (gridConfig.heads[i][j].colmeta != null
						&& gridConfig.heads[i][j].colmeta.width != null
						&& gridConfig.heads[i][j].colmeta.width != '') {
					coldef = $.extend(coldef, {
						width : gridConfig.heads[i][j].colmeta.width
					});
				}
				if (gridConfig.heads[i][j].rowspan != null
						&& gridConfig.heads[i][j].rowspan != '') {
					coldef = $.extend(coldef, {
						rowspan : gridConfig.heads[i][j].rowspan
					});
				}
				if (gridConfig.heads[i][j].colspan != null
						&& gridConfig.heads[i][j].colspan != '') {
					coldef = $.extend(coldef, {
						colspan : gridConfig.heads[i][j].colspan
					});
				}
				coldefs.push(coldef);

			}
			tabHeads.push(coldefs);
		}
		//

		var freeZoneTabHeads = new Array();
		for (var i = 0; i < gridConfig.frozenheads.length; i++) {
			var coldefs = new Array();
			for (var j = 0; j < gridConfig.frozenheads[i].length; j++) {
				var coldef = {
					title : gridConfig.frozenheads[i][j].title
				};
				if (gridConfig.frozenheads[i][j].colmeta != null
						&& gridConfig.frozenheads[i][j].colmeta.field != null
						&& gridConfig.frozenheads[i][j].colmeta.field != '') {
					coldef = $.extend(coldef, {
						field : gridConfig.frozenheads[i][j].colmeta.field
					});
				}
				if (gridConfig.frozenheads[i][j].colmeta != null
						&& gridConfig.frozenheads[i][j].colmeta.align != null
						&& gridConfig.frozenheads[i][j].colmeta.align != '') {
					coldef = $.extend(coldef, {
						align : gridConfig.frozenheads[i][j].colmeta.align
					});
				}
				if (gridConfig.frozenheads[i][j].colmeta != null
						&& gridConfig.frozenheads[i][j].colmeta.width != null
						&& gridConfig.frozenheads[i][j].colmeta.width != '') {
					coldef = $.extend(coldef, {
						width : gridConfig.frozenheads[i][j].colmeta.width
					});
				}
				if (gridConfig.frozenheads[i][j].rowspan != null
						&& gridConfig.frozenheads[i][j].rowspan != '') {
					coldef = $.extend(coldef, {
						rowspan : gridConfig.frozenheads[i][j].rowspan
					});
				}
				if (gridConfig.frozenheads[i][j].colspan != null
						&& gridConfig.frozenheads[i][j].colspan != '') {
					coldef = $.extend(coldef, {
						colspan : gridConfig.frozenheads[i][j].colspan
					});
				}
				coldefs.push(coldef);

			}
			freeZoneTabHeads.push(coldefs);
		}
		var fillCount = tabHeads.length - freeZoneTabHeads.length;
		if (fillCount > 0) {
			for (var i = 0; i < fillCount; i++) {

				freeZoneTabHeads.push(new Array());
			}
		}
		if (fillCount < 0) {
			for (var i = 0; i < (0 - fillCount); i++) {
				tabHeads.push(new Array());
			}
		}

		var options = {
			url : '',
			loadMsg : '数据加载中...',
			sortOrder : 'asc',
			singleSelect : true,
			fit : false,
			width : '100%',
			height : gridConfig.height,
			showFooter : true,
			openAnimation : 'slide',
			columns : tabHeads,
			frozenColumns : freeZoneTabHeads,
			autoRowHeight : true,
			striped : true,
			rownumbers : true,
			pagination : gridConfig.paging,
			pageSize : 10,
			pageList : [ 10, 30, 50, 70, 100 ],
			remoteFilter : false
		};
		console.log(options);
		$(this.element).datagrid(options);
		$(this.element).datagrid({
	        url:gridConfig.dataSrc,
			queryParams: this.options.filterfields,
			pageNumber:1
		});


	},

	_getGridConfig : function() {
		//
		var girdConfig = null;
		$.ajax({
			type : "POST",
			url : this.options.url,
			data : {
				gridId : this.options.id
			},
			async : false,
			dataType : "json",
			success : function(data) {
				girdConfig = data;

			},
			error : function(info) {
				console.log("获取grid 定义失败，请仔细确认。")
			}
		});
		return girdConfig;

	},

	value : function() {
		return $(this.options.id).find("option:selected").val();
	},
	refreshdata : function() {

	}

});