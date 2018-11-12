var formComponent = $.widget("ui.formComponent", {
	version : "1.0.0-beta.1",
	defaultElement : "<div>",
	delay : 300,
	options : {

	},
	/**
	 * 构造方法
	 */
	_create : function() {

	},

	/**
	 * 获取当前组件值
	 */
	getValue : function() {

	},

	_getParams : function() {
		var list = this.options.filterfields;
		var rs = '{';
		var size = list.length - 1;
		for (var i = 0; i < list.length; i++) {
			rs += '"' + list[i].sqlfieldname;
			rs += '":"';
			rs += $(list[i].filedname).val() + '"';
			if (size != i) {
				rs += ',';
			}
		}
		rs += '}';
		return rs;

	},
	/**
	 * 当值发生变化时候出发其他组件
	 */
	_fireListener : function() {
		for (var i = 0; i < this.options.listeners.length; i++) {
			var id = this.options.listeners[i].selector;
			var type = this.options.listeners[i].type;
			if(type=='tree'){
				id=id+'tree';
			}
			var pluginName = "asiainfo" + type.replace(/(\w)/, function(v) {
				return v.toUpperCase()
			});
			var func = "$('" + id + "')." + pluginName + "(\"refreshdata\")";
			eval(func);
		}
	},
	/**
	 * 设置此元素为必填项
	 */
	_setRequired : function(v_element) {
		v_element.validatebox({
			required : true,
			validateOnCreate : false,
			missingMessage : this.options.tooltipmess,
			invalidMessage : this.options.tooltipmess,
			prompt : this.options.tooltipmess
		});
		v_element.addClass('easyui-validatebox');
	}

});
