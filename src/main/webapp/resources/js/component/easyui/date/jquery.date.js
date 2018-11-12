var widgetDate = $.widget("ui.asiainfoDate", jQuery.ui.formComponent, {
	version : "1.0.0-beta.1",
	defaultElement : "<div>",
	delay : 300,
	options : {
		id : '#date1',
		element_id : '12345679',
		required : false,
		timepicker : false,
		format : 'yyyy-MM-dd',
		listeners : [],// 当自己改变时，要通知刷新的列表
		filterfields : [], // 当自己刷新时，要传入的参数 {selector:'#test1',type:'select'}
		url : window.contaxtrot + '/date.jhtml',
		mask : {
			replace : function() {
			}
		},
		readonly : false,
		tooltipmess : '',
		messcontainer : '.errormessage'

	},

	_create : function() {
		if (this.options.required==true) {
		this._setRequired(this.element);
		}
		var _this=this;
		this.element.bind('click', function() {
			if (_this.options.format == 'yyyy-MM-dd HH:mm:ss') {
				WdatePicker({
					dateFmt : _this.options.format
				});
			} else if (_this.options.format == 'yyyy-MM-dd HH:mm') {
				WdatePicker({
					dateFmt : _this.options.format
				});
			} else {
				WdatePicker({
					dateFmt : 'yyyy-MM-dd'
				});
			}
			
		});
		if (this.options.readonly) {
			this.element.attr({"readonly": "readonly"})
		}
	    var value=this.options.value==null?this.options.defaultValue:this.options.value;
		if(value!=null&&value!=''){
			this.element.val(value);
		}
		// 给组件添加监听方法
		this._on($(this.element), {
			"change" : function(event) {
				this._fireListener();
			}
		});
	},
	refreshdata : function() {

	}

});