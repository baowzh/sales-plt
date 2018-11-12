var submitForm=function() {
	if(valid()){
		
	}
	$('#editNews').submit();
	//var data=$('#editNews').serialize();
	/*
	$.ajax({
		type : "POST",
		url : 'save.jhtml',
		data : data,
		dataType : "json",
		success : function(data) {
			if(data.success){
				alert('保存成功。');
				window.location.href='index.jhtml';
			}else{
				alert(data.mess);
			}
		},
		error : function(info) {
			console.log("连接异常，请检查！")
		}
	});*/
	
	
};

var valid=function(){
	return true;
	
};

