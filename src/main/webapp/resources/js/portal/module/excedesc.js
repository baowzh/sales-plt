var sameTopDomain = compareDomain(top);
var sameParentDomain = compareDomain(parent);
var sbtframe = sameParentDomain && parent.document.getElementById("wade_sbtframe");
var isPopupPage = sameTopDomain && top.window.document.getElementById("PopupAgent") != null;
var isMsgPopup = sbtframe && parent.wade_sbtframe.location.href != "about:blank";
var isPageRedirect = location.href.indexOf("?") != -1;
var isTopNavPage = isPageRedirect && sameParentDomain && parent.document.getElementById("navframeset") != null;
var isSimplePage = sbtframe && sbtframe.getAttribute("simplePage") == "true";
var isDirectPageFlow = sameParentDomain && parent.document.getElementById("flowbody") != null;
var isSubmitPageFlow = isMsgPopup && compareDomain(parent.parent) && parent.parent.document.getElementById("flowbody") != null;

if (!isSimplePage) {
	document.writeln("<script language=\"JavaScript\" src=\"component/scripts/public.js\"></script>");
	document.writeln("<script language=\"JavaScript\" src=\"component/scripts/validate.js\"></script>");
	document.writeln("<script language=\"JavaScript\" src=\"component/scripts/dragiframe.js\"></script>");
}

/** init */
function init() {
	if (self.name == "printframe") {
		alert("²Ù×÷·¢Éú´íÎó!\n´íÎóÔ­Òò£º" + getElement("ErrorMessage").innerText);
	} else {
		if (!isSimplePage) {
			completePageLoad();
		}
		if (isMsgPopup) {
			sbtframe.style.display = "";
			document.body.focus();
			if (!isSimplePage) {
				parent.endPageLoading();
				parent.beginPageOverlay();
				parent.setDisabledElements(parent.wadeDisabledElements, true);
				addHandle(document.getElementById("titlebar"), window);
			}
		}
		
		var breturn = document.getElementById("breturn");
		if (isPopupPage || isMsgPopup || isTopNavPage || isSubmitPageFlow) {
			breturn.value = "¹Ø±Õ";
		} else if (isPageRedirect && getFrameObjByUp(parent, "navframeset") != null) {
			breturn.style.display = "none";
		}
		
		if (breturn.style.display == "") {
			breturn.focus();
		}
	}
	document.getElementById("anchorTrigger").click();
}
/** click stack ctrl */
function clickStackCtrl() {
	var stackarea = document.getElementById("StackArea");
	var linkview = document.getElementById("linkview");
    if (stackarea.style.display == "") {
    	stackarea.style.display = "none";
    	linkview.innerText = "ÏÔÊ¾´íÎó";
    } else {
    	stackarea.style.display = "";
    	linkview.innerText = "Òþ²Ø´íÎó";
	}
}
/** cancle excedesc */
function cancelExcedesc() {
	if (isMsgPopup) {
		if (!isSimplePage) {
			parent.endPageOverlay();
		}
		sbtframe.style.display = "none";
		parent.wade_sbtframe.location.href = "about:blank";
	} else {
		if (isPopupPage) {
			window.close();
		} else {
			if (isTopNavPage) {
				closeNavFrameByLocation();
			} else if (isDirectPageFlow) {
				var pageflow = getFrame(["flowtab", parent]).pageflow;
				if (pageflow.alreadyFinish) {
					backHistoryPage();
					pageflow.endFlowOverlay();
					pageflow.alreadyFinish = false;
				} else {
					pageflow.backPageStep();
				}
			} else {
				backHistoryPage();
			}
		}
	}
	if (isSubmitPageFlow) {
		getFrame(["flowtab", parent.parent]).pageflow.endFlowOverlay();
	}
}
/** get frame obj by up */
function getFrameObjByUp(target, objname) {
	var obj = target.document.getElementById(objname);
	if (obj != null) return obj;
	if (top != target.window) {
		return getFrameObjByUp(target.parent, objname);
	}
	return null;
}
/** is compare domain */
function compareDomain(target) {
	try {
		return target.document.domain == document.domain;
	} catch (e) {
		return false;
	}
}
/** back history page */
function backHistoryPage() {
	history.back();
	history.back();
}