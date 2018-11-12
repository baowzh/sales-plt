function  checkUserRegister() {

	var password = getElementValue("STAFF_PASSWD");
	var password2 = getElementValue("STAFF_PASSWD_REP");
	var question = getElementValue("PASSWD_QUESTION");
	var answer = getElementValue("PASSWD_ANSWER");
	
	if(password != password2) {
		alert("重复密码与密码不相同，请重新输入密码！");
		getElement("STAFF_PASSWD").value = "";
		getElement("STAFF_PASSWD_REP").value = "";
		
		return false;
	}
	if(question == answer) {
		alert("提示问题答案不能与密码提示问题相同！");
		getElement("PASSWD_ANSWER").value = "";
		
		return false;
	}
	
	return true;
}