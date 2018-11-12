$(function() {
	$(".fa-collapse")
			.each(
					function() {
						var t = $(this).attr("collapse");

						$(this)
								.on(
										'click',
										function() {
											$(this)
													.toggleClass(
															'fa-angle-double-up')
													.toggleClass(
															'fa-angle-double-down');
											$("#" + t).slideToggle("slow");
											var adjustHeight = $('#search-form')[0].clientHeight;
											if ($(this).hasClass(
													'fa-angle-double-down')) {
												// 当收缩时候
												if (window.adjustGridHeight) {
													adjustGridHeight(
															adjustHeight, true);
												}
											} else {
												if (window.adjustGridHeight) {
													adjustGridHeight(
															adjustHeight, false);
												}
											}
										});

					});

});
/**
 * 随着表单的收缩和展开调整页面上所有表格的高度
 */
var adjustGridHeight = function(formHeight, collapse) {
	for (dg in reportGridIds) {
		if (reportGridIds[dg].type == 'grid') {
			var options = $('#' + reportGridIds[dg].id).datagrid('options');
			var height = options.height;
			if (collapse) {
				height = height + formHeight;
			} else {
				height = height - formHeight;
			}
			$('#' + reportGridIds[dg].id).datagrid('resize', {
				width : options.width,
				height : height
			});
		} else {
			var options = $('#' + reportGridIds[dg].id).treegrid('options');
			var height = options.height;
			if (collapse) {
				height = height + formHeight;
			} else {
				height = height - formHeight;
			}
			$('#' + reportGridIds[dg].id).treegrid('resize', {
				width : options.width,
				height : height
			});
		}

	}
}