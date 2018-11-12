/**
 * jQuery 用 Echarts 插件。
 * 作者 baowenshou
 *  2016.08.03
 */
 
 /**
 * 调用方法1 $("selector").echarts({echartOption:echartOption})
 * 调用方法2 $("selector").echarts({optionUrl:""})
 * 目前参数 echartOption 为 Echart原始配置格式的参数,optionUrl为异步获取echartOption的地址。。
 */
(function($){
	$.widget("ui.echarts", {
	    options: {
	        optionUrl:"",
	        echartOption:null,
	        myChart:null,
	        data:{},
	        containerClass:"echarts-container"
	    },
	    _create: function() {
	    	var e = this.element;
	    	this.options.myChart = echarts.init(this.element.get(0));
	    	$(window).resize(this.options.myChart,function(event){
				var instance =event.data;
				instance.resize();
			})
	    },
	    loadData:function(params){
	    	$.ajax({ 
        		url: this.options.optionUrl, 
        		context: this, 
        		type:'POST',
        		data:params,
        		dataType:"text",// echart 使用的数据一个js 对象可能携带js函数所以不能ajax解析，需要返回text 然后eval 转换。
        		error:function(XMLHttpRequest, textStatus, errorThrown){
        			alert("ajax-error:"+XMLHttpRequest.responseText);
        		},
        		success: function(result){
			        var myChart = echarts.getInstanceByDom(this.element.get(0));
			        result=eval('('+result+')');
					myChart.setOption(result);
				}
			});
	    	
	    	
	    },
		destroy:function(){
			var e = this.element;
		}
	});
})(jQuery);