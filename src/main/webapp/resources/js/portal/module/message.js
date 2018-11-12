var isPopupPage = false;
var isMsgPopup = false;
var isDirectPageFlow = false;
var isSubmitPageFlow = false;
/** init */
function init() {
	isPopupPage = isSameDomain(top) && top.window.document.getElementById("PopupAgent") != null;
	isMsgPopup = isSameDomain(parent) && parent.wade_sbtframe != null && parent.wade_sbtframe.location.href != "about:blank";
	isDirectPageFlow = isSameDomain(parent) && parent.document.getElementById("flowbody") != null;
	isSubmitPageFlow = isMsgPopup && isSameDomain(parent.parent) && parent.parent.document.getElementById("flowbody") != null;
	
	var buttonbar = getElement("buttonbar");
	if (buttonbar.childNodes.length == 0) {
		var iscurrent = isSameDomain(parent) && parent.document.getElementById("navframeset") != null;
		var isparent = isMsgPopup && isSameDomain(parent.parent) && parent.parent.document.getElementById("navframeset") != null;
		
		var button = document.createElement("INPUT");
		button.id = "BUTTON_1";
		button.name = "BUTTON_1";
		button.type = "button";
		button.className = "button";
		button.value = "¹Ø±Õ";
		getElement("buttonbar").appendChild(button);
		
		var btevent = isMsgPopup ? "closeMsgPopup" : null;
		if (isPopupPage || iscurrent || isparent || isDirectPageFlow ||isSubmitPageFlow) {
			btevent = "cancel";
			if (iscurrent || isparent || isDirectPageFlow || isSubmitPageFlow) {
				btevent = "closeNavFrameByLocation";
				if (isparent || isDirectPageFlow) {
					btevent = "parent." + btevent;
				} else if (isSubmitPageFlow) {
					btevent = "parent.parent." + btevent;
				}
			}
		}
		addObjEventListener("document.getElementById('BUTTON_1')", "click", btevent);
	}
	if (buttonbar.childNodes.length != 0) {
		focus(buttonbar.childNodes[0]);
	}
	
	if (isMsgPopup) {
		var wade_sbtframe = parent.document.getElementById("wade_sbtframe");
		parent.endPageLoading();
		parent.beginPageOverlay();
		addHandle(getElement("titlebar"), window);
		hidden(wade_sbtframe, false);
		focus(document.body);
	}
	getElement("anchorTrigger").click();
}
/** redirect to by message */
function redirectToByMessage(obj) {
	obj.disabled = true;
	var target = obj.getAttribute("target");
	if (isMsgPopup || isDirectPageFlow) {
		if (target == null || target == "") {
			target = "parent";
		} else if (target == "parent" || startsWith(target, "parent.")) {
			target += ".parent";
		}
	}
	if (isSubmitPageFlow) target += ".parent";
	redirectTo(obj.getAttribute("path"), obj.getAttribute("event"), obj.getAttribute("parameters"), target);
}
/** close msg popup */
function closeMsgPopup() {
	parent.endPageOverlay();
	parent.document.getElementById("wade_sbtframe").style.display = "none";
	parent.wade_sbtframe.location.href = "about:blank";
}