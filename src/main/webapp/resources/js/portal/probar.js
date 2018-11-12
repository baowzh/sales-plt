<!--
function WebProcessBar(value, max, width, height){
	this.value		= value 	|| 0;
	this.max		= max		|| 100;
	this.width		= width		|| '100%';
	this.height		= height	|| '12px';
	
	this.bgColor	= '#FFFFCC';
	this.fgColor	= '#99CC00';
	this.border		= '1px Solid black';

	this.fontSize	= '9px';
	this.fontColor	= 'black';
	this.isShowText	= true;
	this.getObject	= null;
}

WebProcessBar.prototype.value
WebProcessBar.prototype.max

WebProcessBar.prototype.width
WebProcessBar.prototype.height

WebProcessBar.prototype.Percent = function(){
	return (parseInt(this.value,10)/parseInt(this.max,10)*100);
}

WebProcessBar.prototype.bgColor
WebProcessBar.prototype.fgColor
WebProcessBar.prototype.border

WebProcessBar.prototype.fontSize
WebProcessBar.prototype.fontColor

WebProcessBar.prototype.isShowText

WebProcessBar.prototype.newCreate	= function(e){
	var sHtml = '<div style="'+
				'width: '+this.width+'; '+
				'height: '+this.height+'; '+
				'border: '+this.border+'; '+
				'background-color: '+this.bgColor+';">'+
				'<table border="0" cellspacing="0" cellpadding="0" style="position: absolute; width: 100%; height: 100%;">'+
				'<tr><td align="center" valign="middle" style="'+
				'font-size: '+this.fontSize+'; '+
				'color: '+this.fontColor+';">'+
				(this.isShowText ? this.value+'/'+this.max : '')+'</td></tr></table>'+
				'<div style="'+
				'width: '+this.Percent()+'%; '+
				'height: 100%; '+
				'background-color: '+this.fgColor+';"><span></span></div></div>';
	e.innerHTML = sHtml;
	this.getObject = e.children(0);
}

WebProcessBar.prototype.toString 	= function(){
	if (this.getObject == null){
		return ''; 
	}else{
		return this.getObject.outerHTML;
	}
}

WebProcessBar.prototype.refresh 	= function(){
	if (this.getObject == null) return;
	this.getObject.innerHTML = 	'<table border="0" cellspacing="0" cellpadding="0" style="position: absolute; width: 100%; height: 100%;">'+
								'<tr><td align="center" valign="middle" style="'+
								'font-size: '+this.fontSize+'; '+
								'color: '+this.fontColor+';">'+
								(this.isShowText ? this.value+'/'+this.max : '')+'</td></tr></table>'+
								'<div style="'+
								'width: '+this.Percent()+'%; '+
								'height: 100%; '+
								'background-color: '+this.fgColor+';"><span></span></div>';
}


var oPopup 		= window.createPopup();
var oWpb		= new WebProcessBar();

var oTimeHandle = null;
var iIntelval	= null;

function openPopProcessBar(){
	window.clearTimeout(oTimeHandle);
	iIntelval	= 100;
	
	var srceenW = screen.availWidth;
	var srceenH = screen.availHeight;
	
	var iPopW	= 579;
	var iPopH	= 200;
	var iPopL	= parseInt((srceenW-iPopW)/2, 10);
	var iPopT	= parseInt((srceenH-iPopH)/2, 10);
	
	oWpb.value		= 0;
	oWpb.max		= 100;
	oWpb.height		= '100%';
	oWpb.fontSize	= '12px';
	oWpb.border		= '0px none black';
	oWpb.bgColor	= '#EEEEEE';
	
	var oPopupBody	= oPopup.document.body;
	oPopupBody.innerHTML ='<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="black">'
						+'<tr>'
							+'<td height="100%" bgcolor="#ffffff" align=center><img src="component/images/load.gif"><br><font size=2><b>正在操作，稍作等待......</b></font><br><font size=1>LinkAge Integrate Develop Platform &copy;2005-2008</font></td>'
						+'</tr><tr>'
							+'<td bgcolor="#6699CC"></td>'
						+'</tr>'
					+'</table>';

	oWpb.newCreate(oPopupBody.all(0).rows(1).cells(0));
	oPopup.show(iPopL, iPopT, iPopW, iPopH);	

	runProcessBar();
}

function runProcessBar(){
	if (oWpb.value >= 100){
		oPopup.hide();
	}else{
		oWpb.value 	+= 1;
		iIntelval	+= 5;
		oWpb.refresh();
		oTimeHandle = window.setTimeout('javascript:runProcessBar();', iIntelval);
	}
}
function hiddenPopup(){
	oPopup.hide();
}
//-->