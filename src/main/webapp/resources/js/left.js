/**
	打开左侧导航条。

*/
function openLeftMenu(){
	 $j("#menu-1").css("opacity",'1').show(0,function(){
			 $j(this).animate({"width":"270px"},240);
	})
}

function closeLeftMenu(){
	 $j("#menu-1").animate({"width":"0px"},240,function(){
		 $j(this).hide();
	})
		
}