 $j(document).ready(function(){

	 $j("#nicemenu img.arrow").click(function(){ 
								
		 $j("span.head_menu").removeClass('active');
		
		submenu =  $j(this).parent().parent().find("div.sub_menu");
		
		if(submenu.css('display')=="block"){
			 $j(this).parent().removeClass("active"); 	
			submenu.hide(); 		
			 $j(this).attr('src',window.contextroot+'/resources/images/triagle_or.png');									
		}else{
			 $j(this).parent().addClass("active"); 	
			submenu.fadeIn(); 		
			 $j(this).attr('src',window.contextroot+'/resources/images/triagle_or.png');	
		}
		
		 $j("div.sub_menu:visible").not(submenu).hide();
		 $j("#nicemenu img.arrow").not(this).attr('src',window.contextroot+'/resources/images/triagle.png');
						
	})
	.mouseover(function(){  $j(this).attr('src',window.contextroot+'/resources/images/triagle_or.png'); })
	.mouseout(function(){ 
		if( $j(this).parent().parent().find("div.sub_menu").css('display')!="block"){
			 $j(this).attr('src',window.contextroot+'/resources/images/triagle.png');
		}else{
			 $j(this).attr('src',window.contextroot+'/resources/images/triagle_or.png');
		}
	});

	 $j("#nicemenu span.head_menu").mouseover(function(){  $j(this).addClass('over')})
								 .mouseout(function(){  $j(this).removeClass('over') });
	
	 $j("#nicemenu div.sub_menu").mouseover(function(){  $j(this).fadeIn(); })
							   .blur(function(){ 
							   		 $j(this).hide();
									 $j("span.head_menu").removeClass('active');
								});		
								
	 $j(document).click(function(event){ 		
			var target =  $j(event.target);
			if (target.parents("#nicemenu").length == 0) {				
				 $j("#nicemenu span.head_menu").removeClass('active');
				 $j("#nicemenu div.sub_menu").hide();
				 $j("#nicemenu img.arrow").attr('src',window.contextroot+'/resources/images/triagle.png');
			}
	});			   
							   
								   
});