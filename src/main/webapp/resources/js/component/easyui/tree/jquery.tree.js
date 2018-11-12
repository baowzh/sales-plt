var asiainfoTree = $
		.widget(
				"ui.asiainfoTree",
				 jQuery.ui.formComponent,
				{
					version : "1.0.0-beta.1",
					defaultElement : "<div>",
					delay : 300,
					options : {
						/**
						 * 组件id
						 */
						treeid : 'tree1view',
						/**
						 * 对应输入域的名称
						 */
						fieldname : 'treevalue',
						/**
						 * 放大镜图片的位置
						 */
						zoomimgsrc : '/resources/images/icon/direarr.jpg',
						/**
						 * 是否多选
						 */
						multiSelect : false,
						/**
						 * 必须选择叶子节点
						 */
						selectLeaf : false,
						/**
						 * 显示树
						 */
						showtree : false,
						/**
						 * 树类型
						 */
						treetype : 1,
						/**
						 * 弹出对话框标题
						 */
						title : '请选择部门',
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
						 * 获取树节点的服务地址
						 */
						serverurl : window.contextroot + '/form/tree/data.jhtml',
						defaultvalue : {
							text : '',
							value : ''
						},
						required : false,
						readonly : false,
						tooltipmess : '',
						messcontainer : '.errormessage'
					},
					/**
					 * 构造方法
					 */
					_create : function() {
						var $this = this;
						this.container;
						// 构造popuptree 界面
						this.element.css({
							//position : 'relative'
						});
						var inputdiv = $('<div>').addClass('popuptree')
						var popup_filed = $('<input>').attr({
							type : 'text',
							name : this.options.fieldname + "_pop",
							id : this.options.fieldname + "_pop",
							readonly : 'readonly'
						}).css({
							float : 'left',
							width : '90%'
						}).val(this.options.defaultvalue.text).addClass(
								'form-control col-md-11');
						if (this.options.required) {
							/*
							popup_filed.attr({
								'data-options' : 'required:true'
							});*/
							 this._setRequired(popup_filed);
							
							/*
							popup_filed.validatebox({
								required : true,
								validateOnCreate: false,
								missingMessage: this.options.tooltipmess,
								invalidMessage:this.options.tooltipmess,
								prompt:this.options.tooltipmess
							});
							popup_filed.addClass('easyui-validatebox');*/
							
							
							
						}

						popup_filed
								.attr({
									'data-parsley-errors-container' : this.options.messcontainer
								});

						if (this.options.tooltipmess != '') {
							popup_filed
									.attr({
										'data-parsley-error-message' : this.options.tooltipmess
									});
						}

						var hiddenfield = $('<input>').attr({
							type : 'hidden',
							name : this.options.fieldname,
							id : this.options.fieldname
						}).val(this.options.defaultvalue.value);
						inputdiv.append(hiddenfield);
						inputdiv.append(popup_filed);
						var serveraddr = $this.options.serverurl;
						serveraddr = serveraddr.split("/")[0];
						var zoomimg = $('<div>').attr({
							'class' : 'direarr',
							'id' : $this.options.fieldname + '_img'
						});
						if (!this.options.readonly) {
							zoomimg.bind('click', function() {
								if (!$this.options.showtree) {
									$this._showtree();
									$this.options.showtree = true;
								} else {
									$this._hidetree();
									$this.options.showtree = false;
								}
							});
						}else{
							this._fireListener();
						}
						var imgdiv = $('<div>').css({
							float : 'left',
							width : '8%',
							height : '16px'
						});
						imgdiv.append(zoomimg);
						inputdiv.append(imgdiv)
						this.element.append(inputdiv);
						var treecontainerdiv = $('<div>').attr('id',
								this.options.fieldname + "_treecontainer")
								.addClass('asiainfoTree');
						var treediv = $('<div>').attr('id',
								this.options.fieldname + "_tree").css({
							'z-index' : 1000,
							display : 'block',
							'overflow-y' : 'scroll',
							'overflow-x' : 'hidden',
							'max-height' : '315px',
							'height' : '315px',
							width:'100%'
						});
						//
						treecontainerdiv.append($('<div>').text(
								this.options.title).addClass('title'));
						treecontainerdiv.append(treediv);
						var buttondiv = $('<div>').attr('id',
								this.options.fieldname + "_button").addClass(
								'zoombuton');
						var okbutton = $('<input>')
								.attr({
									type : 'button',
									name : 'treeok',
									value : '确定'
								})
								.addClass('btn btn-success btn-xs')
								.bind(
										'click',
										function() {
											$this._hidetree();
											$this._onSelectNode();
										});
						buttondiv.append(okbutton);
						var clearbutton = $('<input>').attr({
							type : 'button',
							name : 'clear',
							value : '清除'
						}).bind('click', function() {
							$this._clearselection();
							$this._hidetree()
						}).addClass('btn btn-info btn-xs');
						buttondiv.append(clearbutton);
						treecontainerdiv.append(buttondiv);
						//this.element.append(treecontainerdiv);
						$('body').append(treecontainerdiv);
						// 内部初始化tree
						$('#' + this.options.fieldname + "_tree")
								.tree(
										{
											url : this.options.serverurl,
											method : 'post',
											checkbox:this.options.multiSelect,
											queryParams :$.extend(
													{treeid : this.options.treeid,
													id : '#'
													},$this.options.filtervals),
											onBeforeLoad:function(node, param){
												$.extend(
														param,$this.options.filtervals )	;// 重新加载时候把过滤参数加入进来
												return true;
											},
											onClick:function(node){
												if (node != null
														&& node.haschild
														&& $this.options.selectLeaf) {
													$this._setValue('', '');
													alert('请选择末级节点。');
													return;
												}
												$this._onSelectNode();
												
											},
											onCheck:function(node, checked){
												
											},
											loadFilter : function(data, parent) {
												if (parent == null
														|| parent == undefined) {// 跟节点的时候
													var retunArr = new Array();
													if (data.children.length > 0) {
														for ( var i in data.children) {
															if (data.children[i].haschild == 1) {
																$
																		.extend(
																				data.children[i],
																				{
																					state : 'closed'
																				});
															} else {
																$
																		.extend(
																				data.children[i],
																				{
																					state : 'open'
																				});
															}
														}

													}
													retunArr.push(data);
													return retunArr;
												} else {// 下级节点
													if (data.length > 0) {
														for ( var i in data) {
															if (data[i].haschild == 1) {
																$
																		.extend(
																				data[i],
																				{
																					state : 'closed'
																				});
															} else {
																$
																		.extend(
																				data[i],
																				{
																					state : 'open'
																				});
															}
														}

													}
													return data;
												}

											}
										}

								);

					},
					_onSelectNode:function(){
						var selectnodes=null;
						if(this.options.multiSelect){
							selectnodes=	$(
									'#'
									+ this.options.fieldname
									+ "_tree").tree(
						"getChecked");
						}else{
						var selectedNode = $(
									'#'
											+ this.options.fieldname
											+ "_tree").tree(
								"getSelected");
						selectnodes=new Array();
						selectnodes.push(selectedNode);
						}
						if (!this.options.multiSelect&&selectnodes[0].children
								&& this.options.selectLeaf) {
							this._setValue('', '');
							alert('请选择叶子节点。');
							return;
						}
						
						var selectedtext = "[";
						var selectedvalue = "";
						for (var i = 0; i < selectnodes.length; i++) {
							var text = selectnodes[i].text;
							if (this.options.multiSelect) {
								selectedvalue = selectedvalue
										+ selectnodes[i].id
										+ ",";
								selectedtext = selectedtext
										+ text + ",";
							} else {
								selectedvalue = selectnodes[i].id;
								selectedtext = selectedtext
										+ text + ",";
							}
						}
						if (selectedtext.length > 1) {
							selectedtext = selectedtext
									.substring(
											0,
											selectedtext.length - 1);
						}
						if (this.options.multiSelect
								&& selectedvalue.length > 0) {
							selectedvalue = selectedvalue
									.substring(
											0,
											selectedvalue.length - 1);
						}
						selectedtext = selectedtext + "]";
						this._setValue(selectedvalue,
								selectedtext);
						// 调用回调函数
						if (this.options.multiSelect
								&& this.options.afterselect != null
								&& this.options.afterselect != undefined) {
							this.options.afterselect.call(
									window, jsondata);
						}
						//	
						
					},
					/**
					 * 显示弹出树同时加鼠标点击事件
					 */
					_showtree : function() {
						this.options.showtree = true;
						//this.options.fieldname + "_treecontainer"
						var height = $(this.element).height();
						var width = $(this.element).width();
						var topOffset = this.element.offset().top+height ;
						var leftOffset = this.element.offset().left;
						
						$('#' + this.options.fieldname + "_treecontainer").css({
							POSITION:'absolute',
							'z-index': 100002,
							top : topOffset,
							left : leftOffset
							
						})
								.show();
						this
								._on(
										document,
										{
											"click" : function(event) {
												$('#' + this.options.fieldname
														+ "_tree");
												var targetid = event.target.id;
												var targets = new Array();

												targets = this
														._sameorginal(event.target);
												sameorginal = targets;
												if (!sameorginal) {
													if (event.target.className != null
															&& event.target.className
																	.indexOf("tree") >= 0) {
														sameorginal = true;
													}
												}
												if (!sameorginal
														&& this.options.showtree
														&& event.target.id != this.options.fieldname
																+ '_img') {
													this._hidetree();
												}
											}
										});

					},
					/**
					 * 隐藏弹出树
					 */
					_hidetree : function() {

						$('#' + this.options.fieldname + "_treecontainer")
								.hide();
						this.options.showtree = false;

					},
					/**
					 * 由别的组件调用刷新树组件
					 */
					refreshdata : function() {
						this._clearselection();
						this.options.filtervals = $
								.parseJSON(this._getParams());
						$
						$("#" + this.options.fieldname + "_tree").tree(
								"reload");// 出发树形组件数据重新加载

					},
					
					_setValue : function(selectedvalue, selectedtext) {
						$('#' + this.options.fieldname + "_pop").val(
								selectedtext);
						$('#' + this.options.fieldname).val(selectedvalue);
						// 出发其他组件
						this._fireListener();
					},
					/**
					 * 清除选择
					 */
					_clearselection : function() {
						this._setValue('', '');
					},
					/**
					 * 判断事件是否从同一div出现
					 */
					_sameorginal : function(object) {
						var parentelement = $(object).parent().hasClass(
								"asiainfoTree");
						if (parentelement == true) {
							return true;
						}
						if ($(object).parent()[0].nodeType == 1
								|| $(object).parent()[0] == undefined) {
							return false;
						} else {
							return this._sameorginal($(object).parent());

						}
					
					}
				});
