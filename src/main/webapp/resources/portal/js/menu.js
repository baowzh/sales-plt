$(document).ready(function() {
	$('.lt-p-header').bind('click',function(){
		if($(this).hasClass('lt-p-header-close')){
			$('.headnav').hide();
			$(this).removeClass('lt-p-header-close');
		}else{
			$('.headnav').show();
			$(this).addClass('lt-p-header-close');
		}
	})

});
