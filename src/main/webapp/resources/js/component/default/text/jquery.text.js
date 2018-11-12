var asiainfoText = $.widget("ui.asiainfoText", jQuery.ui.formComponent, {
	version : "1.0.0-beta.1",
	defaultElement : "<div>",
	delay : 300,
	options : {

		elmentid : 'elmentid',

		fieldname : '',
		/**
		 * 监听字段的其他字段列表
		 */
		listeners : [],
		/**
		 * 过滤字段
		 */
		filterfields : [],
		/**
		 * 初始化过滤参数和值列表
		 */
		filtervals : [],
		/**
		 * 默认值
		 */
		defaultvalue : '',

		required : false,
		/**
		 * 输入类型 text,email,number
		 */
		type : 'text',
		readonly : false,
		tooltipmess : '',
		messcontainer : '.errormessage'
	},
	/**
	 * 构造方法
	 */
	_create : function() {
		this.element.attr('type', 'text');
		if (this.options.readonly) {
			this.element.attr({
				readonly : 'readonly'
			});
		}
		this.element.attr({
			'data-parsley-errors-container' : this.options.messcontainer
		});
		if (this.options.tooltipmess != '') {
			this.element.attr({
				'data-parsley-error-message' : this.options.tooltipmess
			});
		}
		if (this.options.required==true) {
			   this._setRequired(this.element);
			}
		if (this.options.defaultvalue != '') {
			this.element.val(this.options.defaultvalue);
		}
		// 给组件添加监听方法
		this._on($(this.element), {
			"change" : function(event) {

				var TextVal = this.element.val();
				this._fireListener();

			}
		});

	},
	/**
	 * 由别的组件调用刷新树组件
	 */
	refreshdata : function() {
		// 邮件组件没有刷新功能
	}

});
