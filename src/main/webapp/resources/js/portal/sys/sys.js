function  checkUserRegister() {

	var password = getElementValue("STAFF_PASSWD");
	var password2 = getElementValue("STAFF_PASSWD_REP");
	var question = getElementValue("PASSWD_QUESTION");
	var answer = getElementValue("PASSWD_ANSWER");
	
	if(password != password2) {
		alert("�ظ����������벻��ͬ���������������룡");
		getElement("STAFF_PASSWD").value = "";
		getElement("STAFF_PASSWD_REP").value = "";
		
		return false;
	}
	if(question == answer) {
		alert("��ʾ����𰸲�����������ʾ������ͬ��");
		getElement("PASSWD_ANSWER").value = "";
		
		return false;
	}
	
	return true;
}