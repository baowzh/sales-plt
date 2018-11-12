var asiainfoEmail = $.widget("ui.asiainfoEmail",jQuery.ui.formComponent,  {
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
		 * 由系统统一验证则设置为false
		 */
		valid : false,
		defaultvalue:'',
		readonly:false,
		tooltipmess : '',
		messcontainer : '.errormessage'
	},
	/**
	 * 构造方法
	 */
	_create : function() {
		this.element.data({'type':'email'});
		if(this.options.defaultvalue!=''){
			this.element.val(defaultvalue);
		}
		if (this.options.required) {
			/*
			this.element.attr({
				'data-options' : 'required:true'
			});*/
			this._setRequired(this.element);
		}
		if (this.options.readonly) {
			this.element.attr({
				'readonly' : 'readonly'
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
		// 给组件添加监听方法
		this._on($(this.element), {
			"change" : function(event) {
				// 校验邮件格式是否正确
				var Regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				var TextVal = this.element.val();
				if (Regex.test(TextVal)) {
					if (this.options.valid) {
						$('#' + this.element.context.name + '_mess').remove();
					}
					this._fireListener();
				} else {
					// 提示错误信息
					if (this.options.valid) {
						this.element.after($('<span>').text('地址输入有误').attr(
								'id', this.element.context.name + '_mess'));
					}

				}

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
