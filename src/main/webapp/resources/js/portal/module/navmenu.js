//window.attachEvent("onload", pageLoad);

var NAV_MENU_CURSOR = 0;
var HOLD_FIRST_PAGE = false;
var dbctrlwin = null;

var turnLeft = 0;
var turnRight = 0;
var stepNum = 50;
var curNum = stepNum;
var loop = false;
var navmenus = null;
/** page load */
function pageLoad() {
	turnLeft = getElement("turnLeft");
	turnRight = getElement("turnRight");
	navmenus = getElement("navmenus");
    
	if (turnLeft != null && turnRight != null && navmenus != null) {
		turnLeft.attachEvent("onmousedown", turnLeftMouseDown);
		turnRight.attachEvent("onmousedown", turnRightMouseDown);
	}
}

/** turn right mouse down */
function turnRightMouseDown() {
	turnRegisterUp();
	curNum = 0 - stepNum;
	moveNavMenu();
}
/** turn left mouse down */
function turnLeftMouseDown() {
	turnRegisterUp();
	curNum = stepNum;
	moveNavMenu();
}
/** trurn mouse up */
function turnMouseUp() {
	loop = false;
	document.detachEvent("onmouseup", turnMouseUp);
}
/* turn register up */
function turnRegisterUp() {
	loop = true;
	document.attachEvent("onmouseup", turnMouseUp);
}
/** move nav menu */
function moveNavMenu(){
	if (loop == false) return;
	var marginLeft = navmenus.style.marginLeft;
	if (typeof(marginLeft) == 'undefined' || marginLeft == '') {
		marginLeft = "0px";
    }
    var exp = /(-)?(\d+)px/ig;
    exp = exp.exec(marginLeft);
    if (exp !=null && exp.length && exp.length == 3) {
        marginLeft = (parseInt(exp[1] + exp[2]) + curNum) + "px";
        navmenus.style.marginLeft = marginLeft;
    }
    window.setTimeout(moveNavMenu, 100);
}
/** reset navmenus */
function resetNavMenus() {
   navmenus.style.marginLeft = "0px"; 
}
/** touch notify */
function touchNotify() {
	
}
/** touch notify by timeout */
function touchNotifyByTimeout() {
	if (getElement("navmsgarea") != null) hidden(getElement("navnotify"), false);
	setTimeout("touchNotify()", getElementValue("notifyFrequency") * 60 * 1000);
}
/** get dbctrl win */
function getDbctrlWin() {
	if (dbctrlwin == null) return dbctrlwin;
	try {
		dbctrlwin.document;
	} catch (e) {
		return null;
	}
	return dbctrlwin;
}
/** submit as form */
function submitAsForm(url, target) {
	var submitForm = document.createElement("FORM");
	document.body.appendChild(submitForm);
	submitForm.method = "POST";
	submitForm.target=target;
	submitForm.action= url;
	submitForm.submit();
}
/** turn on dbdisplay */
function turnOnDbdisplay(initurl) {
	var padding = 25, width = 400, height = 200;
	var top = padding, left = screen.width - width - padding;
	
	var purl = parent.location.href;
	purl = purl.substring(0, purl.indexOf("?"));
	purl = purl.substring(0, purl.lastIndexOf("/"));
	
	var url = purl + "/" + getContextName() + "?service=page/component.dbdisplay.DbdisplayCtrl" + (initurl != null && initurl != '' ? '&INITURL=' + initurl.replace('&listener', '_listener') : '') + "&random=" + getRandomParam();
	dbctrlwin = window.open ("", "dbctrlwin", "width=" + width + ", height=" + height + ", top=" + top + ", left=" + left + ", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=yes"); 
	submitAsForm(getSysAddr(url), "dbctrlwin");
}