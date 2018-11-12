/**
 * click homepage or menu switch different frame to keep the menu has opened and do not close
 */
var homepagediv = "div_contentframe";
var menudiv = "div_firstpage";
var homepageframe = "contentframe";
var menuframe = "firstpage";
var siderbarframe = "siderbar";

function switchframe(id,obj,flag){
	if(id == homepagediv){
		if($j("#"+homepagediv).css("display") == "none"){
			if(flag){
				$j(".sy1_ul li.home02 a").css("background-color","");
				$j(".sy1_ul li.home02 a").css("color","#fff");
				$j("#"+menudiv).hide();
				$j("#"+homepagediv).show();
				
				shrinkSiderbar(true);
			}
			else{
				$j(".sy1_ul li.home02 a").css("background-color","");
				$j(".sy1_ul li.home02 a").css("color","#fff");
				$j(obj).addClass("sy1_li_bg");
				
				$j("#"+menudiv).hide();
				$j("#"+homepagediv).show();
				
				shrinkSiderbar(false);
			}
			
		}
		else{
			if(flag){
				shrinkSiderbar(true);
			}
			else{
				if($j(obj).hasClass("sy1_li_bg")){
					if(parseInt($j(".f_slip a").css("left")) > 0){
						shrinkSiderbar(true);
					}
					else{
						shrinkSiderbar(false);
					}
				}
				else{
					$j(".sy1_ul li.sy1_li").removeClass("sy1_li_bg");
					$j(obj).addClass("sy1_li_bg");
					shrinkSiderbar(false);
				}
			}
		}
	}
	else if(id == menudiv){
		if($j("#"+ menudiv).css("display") == "none"){
			$j(".sy1_ul li.home02 a").css("background-color","#fffced");
			$j(".sy1_ul li.home02 a").css("color","#333");
			$j(".sy1_ul li.sy1_li").removeClass("sy1_li_bg");
			
			$j("#"+homepagediv).hide();
			$j("#"+menudiv).show();
		}
		if(!!window.ActiveXObject || "ActiveXObject" in window){  
			shrinkSiderbar(true);
		}
	}
}

