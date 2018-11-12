function initNvgMenu(){
	
		 $j("li._nvg_menu_li").hover(function(){
				 $j("li._nvg_menu_li").removeClass("hover02");
				 $j(this).addClass("hover02");
				var index =  $j("li._nvg_menu_li").index( $j(this));
				var menuCon =  $j("div._nvg_menu_con");
				menuCon.hide();
				menuCon.eq(index).show();
			})
	
	}