$.fn.dataTable.Api.register('refreshtable()', function() {
	var sTableId = this.settings()[0].sTableId;
	$('#' + sTableId).DataTable().draw();
});
$.fn.dataTable.Api.register('processing()', function(show) {
	return this.iterator('table', function(ctx) {
		ctx.oApi._fnProcessingDisplay(ctx, show);
	});
});
$.fn.dataTable.Api.register('getSelections()',
		function() {
	var sTableId = this.settings()[0].sTableId;
			var str = "";
			for (var i = 0; i < $("#"+sTableId+" input[name='checklist']")
					.size(); i++) {
				if ($($("#"+sTableId+" input[name='checklist']")[i]).prop(
						"checked")) {
					str += i + ","
				}
			}
			str.split(",");
			return str;
		});
$.fn.dataTable.Api.register('installCheckbox()', function() {
	var sTableId = this.settings()[0].sTableId;
	$('#' + sTableId + '_wrapper input.tableflat').iCheck({
		checkboxClass : 'icheckbox_flat-green',
		radioClass : 'iradio_flat-green'
	});
	$('#' + sTableId + '_input_checkall').on('ifChanged', function(e) {
		var arrChk = $('#' + sTableId + " input[class='tableflat']");
		if (e.target.checked) {
			$(arrChk).each(function() {
				$(this).iCheck('check')
			});
		} else {
			$(arrChk).each(function() {
				$(this).iCheck('uncheck')
			});
		}
		;
	});
	$('#' + sTableId + '_wrapper input.tableflat').on('ifChecked', function(e) {
		$(this).parent().parent().parent().addClass('selected');
	});
	$('#' + sTableId + '_wrapper input.tableflat').on('ifUnchecked',
			function(e) {
				$(this).parent().parent().parent().removeClass('selected');
			});
});