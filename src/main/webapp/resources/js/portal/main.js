var  $j = jQuery.noConflict();
 $j(function(){
	//【重要】JSON和JQUERY冲突，因为JSON中定义了Object的toJSONString属性，目前删掉Object的toJSONString属性之后，暂时能解决冲突
	delete(Object.prototype.toJSONString);
})

$j(document).ready(function(){

	//导航
	$j(".sy1_ul .sy1_li").hover(


		function(){

			var index = $j('.sy1_ul .sy1_li').index(this);
			
			var left = $j(this).offset().left;	
			var nvgLevel2 = $j(this).addClass("sy1_li_bg").find(".sy2_ul");
			var nvgLastX = ($j(".sy1_ul .sy1_li:last").offset().left + $j(".sy1_ul .sy1_li:last").width())-17+400;
			if(left+nvgLevel2.width()>nvgLastX){
				left = nvgLastX - nvgLevel2.width();
			}
			
			nvgLevel2.css("left",left).stop(true,true).fadeIn(350);


			//select隐藏（i6下select始终在最上层的问题）


			$j(".searchselect").hide();


		},


		function(){


			var index = $j('.sy1_ul .sy1_li').index(this);


			$j(this).removeClass("sy1_li_bg").find(".sy2_ul").hide();


			//select 恢复显示


			$j(".searchselect").show();


		}


	)


	 $j(".sy2_li").hover(


			function(){


				var index=$j(this).children("ul").length;


				if(index>0){$j(this).addClass("sy2_li_h")}


				$j(this).find("ul:first").show()	},


			function(){$j(this).removeClass("sy2_li_h").find("ul:first").hide()}


		);


	//导航下拉位置样式控制


	$j(".sy2_ul .sy2_li").addClass("bod_1");


});