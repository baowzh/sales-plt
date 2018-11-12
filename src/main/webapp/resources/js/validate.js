/*==================== public begin ====================*/
var wadeDisabledElements = new Array;
var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
function encode64(input){
    var output = "";
    var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
    var i = 0;
    while (i < input.length) {
        chr1 = input.charCodeAt(i++);
        chr2 = input.charCodeAt(i++);
        chr3 = input.charCodeAt(i++);
        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;
        if (isNaN(chr2)) {
            enc3 = enc4 = 64;
        } else if (isNaN(chr3)) {
            enc4 = 64;
        }
        output = output +
            keyStr.charAt(enc1) + keyStr.charAt(enc2) +
            keyStr.charAt(enc3) + keyStr.charAt(enc4);
    }
    return output;
}
function encodePassword(){

    var encodeOper =  getElementValue("ENCODE_OPER");
    if("1" == encodeOper ){

        var encodePassword = encode64(getElementValue("LOGIN_PASSWORD"));
        getElement("LOGIN_PASSWORD").value = encodePassword;
        

    }


}
/* initial all */
function initialAll(obj) {
	for (var i=0; i<document.forms.length; i++) {
		var theForm = document.forms[i];
		if (theForm.enctype != null && theForm.enctype.toUpperCase() == "MULTIPART/FORM-DATA") {
			var formParams = getElement("_FormParams");
			if (formParams == null) {
				formParams = document.createElement("INPUT");
				formParams.id = "_FormParams";
				formParams.name = "_FormParams";
				formParams.type = "hidden";
				theForm.appendChild(formParams);
			}
			
			var params = "";
			var elements = theForm.elements;
			for (var i=0; i<elements.length; i++) {
				var theType = elements[i].type;
				if (theType != null && theType.toUpperCase() != "FILE") {
					params = params + elements[i].name + ",";
				}
			}
			formParams.value = params;
		}
	}
	return true;
}
/* verify field */
function verifyField(field) {
	if (field.tagName == "INPUT" && field.type.toUpperCase() == "TEXT" || field.tagName == "TEXTAREA") {
		field.value = field.value.trim();
	}
		
	if (field.getAttribute("nullable") == "no" && !checkText(field, field.getAttribute("desc"))) return false;
	
	if (getAttributeValue(field, "equsize", "") != "" && !checkLength(field, field.getAttribute("equsize"), field.getAttribute("desc"))) return false;
	if (getAttributeValue(field, "minsize", "") != "" && !checkMinLength(field, field.getAttribute("minsize"), field.getAttribute("desc"))) return false;
	if (getAttributeValue(field, "maxsize", "") != "" && !checkMaxLength(field, field.getAttribute("maxsize"), field.getAttribute("desc"))) return false;
	
	var error = true;
	switch (field.getAttribute("datatype")) {
		case "integer" : error = checkInteger(field, field.getAttribute("desc")); break;
		case "pinteger" : error = checkPInteger(field, field.getAttribute("desc")); break;
		case "ninteger" : error = checkNInteger(field, field.getAttribute("desc")); break;
		case "numeric" : error = checkNumeric(field, field.getAttribute("desc")); break;
		case "nature" : error = checkNature(field, field.getAttribute("desc")); break;
		case "date" : error = checkDate(field, field.getAttribute("format"), field.getAttribute("desc")); break;
		case "email" : error = checkEmail(field, field.getAttribute("desc")); break;
		case "file" : error = checkFile(field, field.getAttribute("desc")); break;
	}
	if (!error) return false;
	
	return true;
}
/** verify all */
function verifyAll(object) {
	var rscoll = getVerifyElements(object);
	var result = rscoll["result"];
	if (!result) return false;
	
	if (pagevisit.getAttribute("productMode") == "true") {
		var elements = rscoll["elements"];
		setDisabledElements(elements, false);
	}
	
	return result;
}
/** verify box */
function verifyBox(object, boxName, info) {
	if (getCheckedBoxNum(boxName) == 0) {
		if (info == null || info == "") info = "��ѡ��Ҫ���������ݣ�";
		alert(info);
		return false;
	}
	return true;
}
/** confirm oper */
function confirmOper(object) {
	disableAllElements(object, false);

	initialAll(object);
	submitLoading();
	setConfirmSubmit();
	
	return true;
}
/** confirm all */
function confirmAll(object) {
	var rscoll = getVerifyElements(object);
	if (!rscoll["result"]) return false;
	
	var elements = rscoll["elements"];
	setDisabledElements(elements, false);
	
	if (!initialAll(object)) return false;
	submitLoading();
	setConfirmSubmit();

	return true;
}
/** confirm info */
function confirmInfo(object, info) {
	if (!setConfirmInfo(object, info)) return false;
	
	disableAllElements(object, false);
	
	if (!initialAll(object)) return false;
	submitLoading();
	setConfirmSubmit();
	
	return true;
}
/** confirm form */
function confirmForm(object, info) {
	var rscoll = getVerifyElements(object);
	
	if (!rscoll["result"]) return false;
	if (!setConfirmInfo(object, info)) return false;
	
	var elements = rscoll["elements"];
	setDisabledElements(elements, false);
	
	if (!initialAll(object)) return false;
	submitLoading();
	setConfirmSubmit();
	
	return true;
}
/** confirm box */
function confirmBox(object, boxName, info) {
	if (!verifyBox(object, boxName)) return false;
	if (!setConfirmInfo(object, info)) return false;
	
	if (!initialAll(object)) return false;
	submitLoading();
	setConfirmSubmit();
	
	return true;
}
/** query oper */
function queryOper(object, info) {
	disableAllElements(object, false);
	
	if (!initialAll(object)) return false;
	submitLoading();
	
	return true;
}
/** query all */
function queryAll(object) {
	var rscoll = getVerifyElements(object);
	if (!rscoll["result"]) return false;
	
	var elements = rscoll["elements"];
	setDisabledElements(elements, false);
	
	if (!initialAll(object)) return false;
	submitLoading();
	
	return true;
}
/** query info */
function queryInfo(object, info) {
	if (!setConfirmInfo(object, info)) return false;
	
	disableAllElements(object, false);
	
	if (!initialAll(object)) return false;
	submitLoading();
	
	return true;
}
/** query form */
function queryForm(object, info) {
	var rscoll = getVerifyElements(object);
	
	if (!rscoll["result"]) return false;
	if (!setConfirmInfo(object, info)) return false;
	
	var elements = rscoll["elements"];
	setDisabledElements(elements, false);
	
	if (!initialAll(object)) return false;
	submitLoading();
	
	return true;
}
/** query box */
function queryBox(object, boxName, info) {
	if (!verifyBox(object, boxName)) return false;
	if (!setConfirmInfo(object, info)) return false;
	
	if (!initialAll(object)) return false;
	submitLoading();
	
	return true;
}
/*==================== public end ====================*/

/*==================== verify library begin ====================*/
/** check length */
function checkLength(field, length, desc) {
	if (field.value != "" && getLength(field.value) != length) {
		alert(desc + "���ȱ���Ϊ" + length + "��");
		focus(field);
		return false;
	}
	return true;
}
/** check min length */
function checkMinLength(field, length, desc) {
	if (field.value != "" && getLength(field.value) < length) {
		alert(desc + "��С���Ȳ��ܵ���" + length + "��");
		focus(field);
		return false;
	}
	return true;
}
/** check max length */
function checkMaxLength(field, length, desc) {
	if (field.value != "" && getLength(field.value) > length) {
		alert(desc + "��󳤶Ȳ��ܳ���" + length + "��");
		focus(field);
		return false;
	}
	return true;
}
/** check text */
function checkText(field, desc) {
	if (field.tagName == "INPUT" && field.type != null && (field.type.toUpperCase() == "CHECKBOX" || field.type.toUpperCase() == "RADIO")) {
		if (field.name != null) {
			if (getCheckedBoxNum(field.name) == 0) {
				alert(desc + "Ϊ��ѡ�");
				focus(field);
				return false;
			}
		}
	} else {
		if (field.value == "") {
			alert(desc + "����Ϊ�գ�");
			focus(field);
			return false;
		}
	}
	return true;
}
/** check integer */
function checkInteger(field, desc) {
	if (!/^(-|\+)?\d+$/.test(field.value) && field.value != "") {
		alert(desc + "����Ϊ������");
		focus(field);
		return false;
	}
	if (!checkNumberRange(field, desc)) return false;
	return true;
}
/** check positive integer */
function checkPInteger(field, desc) {
	if (!/^\d+$/.test(field.value) && field.value != "") {
		alert(desc + "����Ϊ���ڻ�������������");
		focus(field);
		return false;
	}
	if (!checkNumberRange(field, desc)) return false;
	return true;
}
/** check negative integer */
function checkNInteger(field, desc) {
	if (!/^-\d+$/.test(field.value) && field.value != "") {
		alert(desc + "����ΪС�����������");
		focus(field);
		return false;
	}
	if (!checkNumberRange(field, desc)) return false;
	return true;
}
/** check numeric */
function checkNumeric(field, desc) {
	if (!/^(-|\+)?\d+(\.\d+)?$/.test(field.value) && field.value != "") {
		alert(desc + "����Ϊ���֣�");
		focus(field);
		return false;
	}
	var format = getAttributeValue(field, "format", "");
	if (format != "" && format.indexOf(".") != -1 && field.value != "") {
		var fieldValue = parseFloat(field.value);
		if (fieldValue < 0) {
			fieldValue = -fieldValue;
		}
		fieldValue = fieldValue.toString();
		if (fieldValue.indexOf(".") != -1 && (format.length - format.indexOf(".") < fieldValue.length - fieldValue.indexOf("."))) {
			alert(desc + "����Ϊ���ָ�ʽ(" + format + ")");
			focus(field);
			return false;
		}
	}
	if (!checkNumberRange(field, desc)) return false;
	return true;
}
/** check nature */
function checkNature(field, desc) {
	if ((!/^\d+$/.test(field.value) || !/[^0]/.test(field.value)) && field.value != "") {
		alert(desc + "����Ϊ�������������");
		focus(field);
		return false;
	}
	if (!checkNumberRange(field, desc)) return false;
	return true;
}
/** check number range */
function checkNumberRange(field, desc) {
	if (getAttributeValue(field, "min", "") != "" && parseInt(field.value) < parseInt(field.getAttribute("min"))) {
		alert(desc + "����С��(" + field.getAttribute("min") + ")��");
		focus(field);
		return false;
	}
	if (getAttributeValue(field, "max", "") != "" && parseInt(field.value) > parseInt(field.getAttribute("max"))) {
		alert(desc + "���ܴ���(" + field.getAttribute("max") + ")��");
		focus(field);
		return false;
	}
	return true;
}
/** check date */
function checkDate(field, format, desc) {
	var expression;
	switch(format) {
		case "yyyy":
			expression = /^(\d{1,4})$/;
			break;
		case "yyyyMM":
			expression = /^(\d{1,4})(\d{1,2})$/;
			break;
		case "yyyy-MM":
			expression = /^(\d{1,4})(-|\/)(\d{1,2})$/;
			break;
		case "yyyy/MM":
			expression = /^(\d{1,4})(\/|\/)(\d{1,2})$/;
			break;
		case "yyyyMMdd":
			expression = /^(\d{1,4})(\d{1,2})(\d{1,2})$/;
			break;
		case "yyyy-MM-dd":
			expression = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
			break;
		case "yyyy/MM/dd":
			expression = /^(\d{1,4})(\/|\/)(\d{1,2})\2(\d{1,2})$/;
			break;
		case "HH":
			expression = /^(\d{1,2})$/;
			break;
		case "HHmm":
			expression = /^(\d{1,2})(\d{1,2})$/;
			break;
		case "HH:mm":
			expression = /^(\d{1,2})(:)?(\d{1,2})$/;
			break;
		case "HHmmss":
			expression = /^(\d{1,2})(\d{1,2})(\d{1,2})$/;
			break;
		case "HH:mm:ss":
			expression = /^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/;
			break;
		case "yyyyMMdd HH":
			expression = /^(\d{1,4})(\d{1,2})(\d{1,2}) (\d{1,2})$/;
			break;
		case "yyyy-MM-dd HH":
			expression = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2})$/;
			break;
		case "yyyy/MM/dd HH":
			expression = /^(\d{1,4})(\/|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2})$/;
			break;
		case "yyyyMMdd HHmm":
			expression = /^(\d{1,4})(\d{1,2})(\d{1,2}) (\d{1,2})(\d{1,2})$/;
			break;
		case "yyyy-MM-dd HH:mm":
			expression = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/;
			break;
		case "yyyy/MM/dd HH:mm":
			expression = /^(\d{1,4})(\/|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/;
			break;
		case "yyyyMMdd HHmmss":
			expression = /^(\d{1,4})(\d{1,2})(\d{1,2}) (\d{1,2})(\d{1,2})(\d{1,2})$/;
			break;
		case "yyyy-MM-dd HH:mm:ss":
			expression = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			break;
		case "yyyy/MM/dd HH:mm:ss":
			expression = /^(\d{1,4})(\/|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			break;
	}
	if (field.value != "") {
		if (field.value.length != format.length || !expression.test(field.value)) {
			alert(desc + "����Ϊʱ���ʽ(" + format + ")��");
			focus(field);
			return false;
		}
		if (getAttributeValue(field, "min", "") != "" && field.value < field.getAttribute("min")) {
			alert(desc + "����С��(" + field.getAttribute("min") + ")��");
			focus(field);
			return false;
		}
		if (getAttributeValue(field, "max", "") != "" && field.value > field.getAttribute("max")) {
			alert(desc + "���ܴ���(" + field.getAttribute("max") + ")��");
			focus(field);
			return false;
		}
		if (getAttributeValue(field, "minName", "") != "") {
			var minField = getElement(field.getAttribute("minName"));
		 	if (minField != null && minField.value != "" && minField.getAttribute("desc") != null && field.value < minField.value) {
				alert(desc + "����С��" + minField.getAttribute("desc") + "��");
				focus(field);
				return false;
			}
		}
		if (getAttributeValue(field, "maxName", "") != "") {
			var maxField = getElement(field.getAttribute("maxName"));
		 	if (maxField != null && maxField.value != "" && maxField.getAttribute("desc") != null && field.value > maxField.value) {
				alert(desc + "���ܴ���" + maxField.getAttribute("desc") + "��");
				focus(field);
				return false;
			}
		}
	}
	return true;
}
/** check email */
function checkEmail(field, desc) {
	if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(field.value) && field.value != "") {
		alert(desc + "����ΪEMail��ʽ��");
		focus(field);
		return false;
	}
	return true;
}
/** check file */
function checkFile(field, desc) {
	var extname = getAttributeValue(field, "extname", "");
	if (extname != "" && field.value) {
		var exts = extname.split(",");
		for (var i=0; i<exts.length; i++) {
			if (field.value.toUpperCase().endsWith(exts[i].toUpperCase())) return true;
		}
		
		alert(desc + "����Ϊָ����(" + extname + ")�ļ����ͣ�");
		focus(field);
		return false;
	}
	return true;
}
/*==================== verify library end ====================*/

/*==================== private begin ====================*/
/** set confirm info */
function setConfirmInfo(object, info) {
	var srcobj = getElementBySrc();
	if (info == null || info == "") {
		var srcvalue = trimEmpty(srcobj.value==null?srcobj.parentNode.value:srcobj.value);
		var srcdesc = srcobj.getAttribute("desc")==null?srcobj.parentNode.getAttribute("desc"):srcobj.getAttribute("desc");
		if (srcvalue == null) srcvalue = "";
		if (srcdesc == null) srcdesc = "";
		info = "ȷ��Ҫ" + srcvalue + srcdesc + "��";
	}
	if (!window.confirm(info)) return false;
	
	return true;
}
/** set disabled elements */
function setDisabledElements(elements, isdisabled) {
	if (elements != null) {
		for (var i=0; i<elements.length; i++) {
			elements[i].disabled = isdisabled;
		}
		if (isdisabled) {
			wadeDisabledElements.clear();
		} else {
			wadeDisabledElements = elements;
		}
	}
}
/** get verify elements */
function getVerifyElements(object, isopen) {
	var elements = new Array;
	var discount = 0;
	var childs = null;
	
	if (typeof(object) == "object") {
		if (object.form == null) {
			for (var i=0; i<document.forms.length; i++) {
				elements = elements.concat(document.forms[i].elements);
			}
		} else {
			childs = object.form.elements;
		}
	} else if (typeof(object) == "string") {
		var areaobj = getElement(object);
		if (areaobj == null) {
			alert(object + " not exist!");
			return false;
		}
		childs = getChildsByRecursion(areaobj, "desc");
	}
	if (childs == null) {
		alert(object + " define error!");
		return false;
	}
	
	for (var i=0; i<childs.length; i++) {
		var element = childs[i];
		if (element.disabled) {
			elements[discount] = element;
			discount++;
			continue;
		}
		if (isopen == null || isopen == false) {
			if (element.getAttribute("desc") == null) continue;
			if (!verifyField(element)) return { "result" : false };
		}
	}
	
	return { "result" : true, "elements" : elements };
}
/** set confirm submit */
function setConfirmSubmit() {
	if (getElement("wade_sbtframe") != null) {
		for (var i=0; i<document.forms.length; i++) {
			document.forms[i].target = "wade_sbtframe";
		}
	}
}
/** disable all elements */
function disableAllElements(object, isdisabled) {
	var disabled = isdisabled == null || isdisabled;
	var elements = null;
	
	if (typeof(object) == "object") {
		if (object.tagName == "FORM") {
			elements = object.elements;
		} else {
			if (object.form == null) {
				if (elements == null) elements = new Array;
				for (var i=0; i<document.forms.length; i++) {
					elements = elements.concat(document.forms[i].elements);
				}
			} else {
				elements = object.form.elements;
			}
		}
	} else if (typeof(object) == "string") {
		var areaobj = getElement(object);
		if (areaobj == null) {
			alert(object + " not exist!");
		} else {
			elements = getChildsByRecursion(areaobj, "desc");
		}
	}
	
	if (elements != null) {
		for (var i=0; i<elements.length; i++) {
			var element = elements[i];
			element.disabled = disabled;
		}
	}
}
/** trim empty */
function trimEmpty(param) {
	return param == null ? null : param.replace(/ /g, "");
}
/** submit loading */
function submitLoading() {
	var obj = getElementBySrc();
	if (obj.tagName == "INPUT" && obj.type.toUpperCase() == "SUBMIT" || obj.href != null && obj.href.indexOf("$LinkSubmit") != -1 || arguments[0]) {
		beginPageLoading();
	}
}
/** remove loading */
function removeLoading() {
	endPageLoading();
}
/*==================== private end ====================*/

/*==================== trash begin ====================*/
/** get verify all result */
function getVerifyAllResult(object) {
	warn("����getVerifyAllResult�Ѿ���������ʹ��verifyAll(object)����÷�����");
	return getVerifyElements(object);
}
/** get verify box result */
function getVerifyBoxResult(boxName) {
	warn("����getVerifyBoxResult�Ѿ���������ʹ��verifyBox(object, boxName)����÷�����");
	return verifyBox(null, boxName);
}
/** get form params */
function getFormParams(theForm) {
	warn("����getFormParams�Ѿ���������ʹ��initialAll()����÷�����");
	return initialAll();
}
/** lock form state */
function lockFormState(object) {
	var theForm = null;
	
	if (typeof(object) == "object") {
		if (object != null) theForm = object.form;
	} else if (typeof(object) == "string") {
		var areaobj = getElement(object);
		if (areaobj == null) {
			alert(object + " not exist!");
			return false;
		}
		theForm = areaobj.form;
	}
	
	if (theForm == null) theForm = document.forms[0];
	if (theForm.getAttribute("lock") == null || theForm.getAttribute("lock") == "false") {
		theForm.setAttribute("lock", "true");
		resetFormStateByTime();
		getFormParams(theForm);
	} else {
		alert("�������ύ�����Ժ�...");
		return false;
	}
	return true;
}
/** reset form state */
function resetFormState() {
	for (var i=0; i<document.forms.length; i++) {
		document.forms[i].setAttribute("lock", "false");
	}
}
/** reset form state by time */
function resetFormStateByTime() {
	setTimeout('resetFormState()', 500);
}
/*==================== trash end ====================*/