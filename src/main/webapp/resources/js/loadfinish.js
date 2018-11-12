/**
 * 在portal中，页面加载完成后，调用该方法，去除加载状态
 */

window.onload = function() {
	if(window.frameElement!=null&&window.frameElement.id!=null){
		var frameid = window.frameElement.id;
		var id = frameid + "_loading";
		if(parent.frames["menuframe"]!=null){
			if(parent.frames["menuframe"].document!=null&&parent.frames["menuframe"].document!=undefined){
				var span = parent.frames["menuframe"].document.getElementById(id);
				if(span!=null){
					span.className = '';		
				}
				
			}
			
		}	
	}
			
};
