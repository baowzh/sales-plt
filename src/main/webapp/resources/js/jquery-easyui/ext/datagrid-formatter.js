$
		.extend(
				$.fn.datagrid.methods,
				{
					customformater : function(target, params) {
						var fieldname = params.fieldName;
						var format = params.format;
						var value = params.value;
						var index = params.index;
						var row = params.row;
						var dg = $(target);
						var options = dg.datagrid('options');
						var colmetadata = options.colmetadata;
						if (colmetadata != null) {
							var datatype = colmetadata[fieldname].typeName;
							return datagridCellFormat(datatype, format, value,
									row, index);
						}
						return value;
					},
					customHref : function(target, params) {
						var fieldname = params.fieldName;
						var href = params.href;
						var value = params.value;
						var index = params.index;
						var row = params.row;
						if (typeof href == 'string'
								&& href.indexOf('{', 0) == -1) {
							return href;
						} else if (typeof href == 'string'&&href.indexOf('{', 0)!=-1) {
							var hrefDef = this._parseHref(href);
							var reportId = hrefDef.reportId;
							var paramDefs = hrefDef.paramDefs;
							var returnHref = reportId + '.jhtml?_a=_a';
							for (var j=0;j< paramDefs.length;j++) {
								if (paramDefs[j].type == 'form') {
									returnHref = returnHref
											+ '&'
											+ paramDefs[j].name
											+ '='
											+ $('#' + paramDefs[j].valueFile)
													.val();
								} else {
									returnHref = returnHref + '&'
											+ paramDefs[j].name + '='
											+ row[paramDefs[j].valueFile];
								}
							}
							if (window.systemType == 'portal') {
								var title = this.reportName;
								var url = returnHref
								returnHref = "javascript:parent.parent.addNavFrame('contentframe','"
										+ url + "','" + title + "');void(0);";
							} else {
								returnHref = window.contaxtrot + '/report/'
										+ returnHref;
								returnHref = "javascript:window.open('"
										+ returnHref + "');void(0);";
							}
							return returnHref;
						}else if(typeof href == 'function'){
							return href.call(this,row);//此处返回的有可能是字符串也可能是一个函数
						}else{
							return href;
						}
						
					},
					_parseHref : function(hrefStr) {
						var reportId = hrefStr.split('?')[0];
						var otherParamDefs = hrefStr.split('?')[1];
						var otherParamDefArray = otherParamDefs.split('&');
						var reportParams = new Array();
						for (var i=0;i<otherParamDefArray.length;i++) {
							var nameDefPair = {
								name : '',
								valueFile : '',
								type : ''
							};
							var paramDef = otherParamDefArray[i];
							paramDef = paramDef.replace(/(^\s*)|(\s*$)/g, '');
							var name = paramDef.split('=')[0];
							name = name.replace(/(^\s*)|(\s*$)/g, '');
							var valueDef = paramDef.split('=')[1];
							valueDef = valueDef.replace(/(^\s*)|(\s*$)/g, '');
							var type = valueDef.substr(0, 1);
							if (type == 'r') {
								type = 'row';
							} else if (type == 'f') {
								type = 'form';
							} else {
								type = 'row';
							}
							var endIndex = valueDef.indexOf('}');
							var beginIndex = valueDef.indexOf('{');
							var length = endIndex - beginIndex;
							var valueFile = valueDef.substr(beginIndex + 1,
									length - 1);
							nameDefPair.name = name;
							nameDefPair.valueFile = valueFile;
							nameDefPair.type = type;
							reportParams.push(nameDefPair);

						}
						return {
							reportId : reportId,
							paramDefs : reportParams
						};
					}
				});

var isFooter = function(row) {
	if (row._footer) {
		return true;
	}

	return false;
}
