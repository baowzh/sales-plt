/* set field nullable */
function setNullable(field, nullable) {
	var pop_field = getElement("POP_" + field.name);
	var lab_field = getElement("LAB_" + field.name);
	
	field.nullable = nullable ? "" : "no";
	
	if (pop_field != null) {
		pop_field.nullable = nullable ? "" : "no";
	}
	
	if (lab_field != null) {
		lab_field.className = nullable ? "" : "starlist";
	}
}

/* set field editable */
function setEditable(field, isEditable) {
	var pop_field = getElement("POP_" + field.name);
	var img_cal = getElement("IMG_CAL_" + field.name);
	var pop_img = getElement("POP_IMG_" + field.name);
	var upload_img = getElement("UPLOAD_IMG_" + field.name);
	
	if (field.type == "text" || field.tagName.toUpperCase() == "TEXTAREA") {
		field.readOnly = !isEditable;
	}
	if (field.type == 'radio' || field.type == 'checkbox' || field.tagName.toUpperCase() == "SELECT") {
		field.disabled = !isEditable;
	}
	
	if (img_cal != null) {
		hidden(img_cal, !isEditable);
	}
	
	if (pop_img != null) {
		hidden(pop_img, !isEditable);
	}
	
	if (upload_img != null) {
		hidden(upload_img, !isEditable);
	}
}

/* clear field value */
function clearValue(field) {
	var pop_field = getElement("POP_" + field.name);
	var upload_div = getElement("UPLOAD_DIV_" + field.name);
	
	field.value = "";
	
	if (pop_field != null) {
		pop_field.value = "";
	}
	
	if (upload_div != null) {
		upload_div.innerHTML = "";
	}
}

/* set editable and nullable */
function set(field, isEditable, isNullable, isClearValue) {
	if (isEditable != null) {
		setEditable(field, isEditable);
	}
	
	if (isNullable != null) {
		setNullable(field, isNullable);
	}
	
	if (isClearValue != null && isClearValue) {
		clearValue(field);
	}
}

/**
 * set form editable
 * @param theForm
 * @param isEditable
 * @param btnMode - an argument that indicate what to do on the buttons except 'back' within the form.
 *                  If it doesn't equal to 'hidden' or 'disabled', or is not assigned, nothing will be done on the buttons.
 */
function setFmEdit(theForm, isEditable, btnMode) {
	for (var i = 0; i < theForm.elements.length; i++) {
		var field = theForm.elements[i];
		
		if ((field.type == "button" && field.name != "bclose") || field.type == "submit" || field.type == "reset") {
			if (btnMode == 'hidden') {
				hidden(field, !isEditable);
			} 
			if (btnMode == 'disabled') {
				field.disabled = !isEditable;
			}
		} else {
			set(field, isEditable, null, null);
		}
	}
}

/** hide field */
function hideField(field, isHidden) {
	var lab_field = getElement('LAB_' + field.name);
	
	hidden(field, isHidden);
	if(field.nullable == 'no' || field._nullable == 'no') {
		field._nullable = 'no';
		setNullable(field, isHidden);
	}
	if (lab_field) {
		hidden(lab_field, isHidden);
	}
}

/** create labels for all form fields */
function createAllLabels() {
	var tables = document.getElementsByTagName('TABLE');
	for (var i = 0; i < tables.length; i++) {
		if (tables[i].parentNode.tagName.toUpperCase() == 'DIV' && tables[i].parentNode.className.toLowerCase().indexOf('find_inbox_list') != -1) {
			for(var j = 0; j < tables[i].rows.length; j++) {
				for (var k = 1; k < tables[i].rows[j].cells.length; k += 2) {
					var field = locateField(tables[i].rows[j].cells[k]);
					if (field) {
						var labelTD= tables[i].rows[j].cells[k-1];
						createLabel(labelTD, field);
					}
				}
			}
		} else if (tables[i].parentNode.tagName.toUpperCase() == 'DIV' && tables[i].parentNode.className.toLowerCase().indexOf('uilist') != -1) {
			for (var j = 1; j < tables[i].rows.length; j += 2) {
				for (var k = 0; k < tables[i].rows[j].cells.length; k++) {
					var field = locateField(tables[i].rows[j].cells[k]);
					if (field && k < tables[i].rows[j-1].cells.length) {
						var labelTD= tables[i].rows[j-1].cells[k];
						createLabel(labelTD, field);
					}
				}
			}
		} else {
		}
	}
}

/**
 * Judge if an element is a form field with specified type,
 * the 2nd arg is only for input. 
 */
function isField(element, type) {
	if(element.nodeType != 1) {
		return false;
	}
	var tag = element.tagName.toUpperCase();
	if (tag == 'INPUT' || tag == 'SELECT' || tag == 'TEXTAREA') {
		return (type ? (tag == 'INPUT' && element.type == type) : true);
	}
	return false;
}

/** locate fact field from td's childNodes */
function locateField(td, n) {
	var nodes = td.childNodes;
	var idx = n ? n : 0;
	
	if ((nodes.length - idx) <= 0) {
		return null;
	}
	if ((nodes.length - idx) == 1) {
		return isField(nodes[idx]) ? nodes[idx] : locateField(nodes[idx]);
	}
	var node = nodes[idx];
	var node1 = nodes[idx + 1];
	if (isField(node)) {
		if (!isField(node1)) {
			return node;
		} else {
			if (isField(node1, 'hidden') && node.name.toUpperCase() == ('POP_' + node1.name).toUpperCase()) {
				return node1;
			}
			return node;
		}
	} else {
		if (isField(node1, 'hidden') && node.id.toUpperCase() == ('UPLOAD_DIV_' + node1.name).toUpperCase()) {
			return node1;
		}
		if (node.tagName.toUpperCase() == 'SPAN') {
			var field = locateField(node);
			if (field) {
				return field;
			}
		}
		var res = locateField(td, idx + 1);
		if (res) {
			return res;
		}
	}
	return null;
}

/** create label */
function createLabel(td, field) {
	if (td.tagName.toUpperCase() != 'TD' || !document.createElement) {
		return;
	}
	var nodes = td.childNodes;
	if (nodes.length == 0) {
		return;
	}
	if (nodes.length == 1) {
		if (nodes[0].nodeType == 3) {
			var label = document.createElement('SPAN');
			label.innerText = nodes[0].nodeValue;
			label.id = 'LAB_' + field.name;
			td.insertBefore(label, nodes[0]);
			td.removeChild(td.lastChild);
		} else if (nodes[0].tagName.toUpperCase() == 'SPAN') {
			if (nodes[0].id) {
				return;
			} else {
				nodes[0].id = 'LAB_' + field.name;
				return;
			}
		} else {
			return;
		}
	}
	if (nodes.length == 2 && nodes[0].tagName.toUpperCase() == 'SPAN' && nodes[0].childNodes.length == 1 && nodes[0].childNodes[0].nodeType == 3 && nodes[1].nodeType == 3) {
		var label = document.createElement('SPAN');
		label.innerText = nodes[0].innerText + nodes[1].nodeValue;
		if (nodes[0].className) {
			label.className = nodes[0].className;
		}
		label.id = 'LAB_' + field.name;
		td.insertBefore(label, nodes[0]);
		while (td.childNodes.length > 1) {
			td.removeChild(td.lastChild);
		}
	}
}

/** create labels On load */
function initWithLabs() {
	var old = window.onload ? window.onload : function () {};
	window.onload = function () { 
		old(); 
		createAllLabels(); 
		if (init) {
			init();
		}
	}
}

/** get all visible form elements in the specified parent */
function getAllFields(parent) {
	if (typeof parent == 'string') {
		parent = getElement(parent);
	}
	if (!parent || parent.nodeType != 1) {
		return null;
	}
	
	var fields = new Array();
	if (parent.all) {
		for (var i = 0; i < parent.all.length; i++) {
			if (isField(parent.all(i)) && parent.all(i).type != 'hidden') {
				fields[fields.length] = parent.all(i);
			}
		}
	} else if (parent.getElementsByTagName) {
		var elems = parent.getElementsByTagName('*');
		for (var i = 0; i < elems.length; i++) {
			if (isField(elems[i]) && elems[i].type != 'hidden') {
				fields[fields.length] = elems[i];
			}
		}
	} else {
	}
	
	return fields;
}

/** enable area */
function enableArea(parent, enable) {
	var fields = getAllFields(parent);
	if (!fields) return;
	for (var i = 0; i < fields.length; i++) {
		fields[i].disabled = !enable;
	}
}

/** reset area */
function clearArea(parent) {
	var fields = getAllFields(parent);
	if (!fields) return;
	for (var i = 0; i < fields.length; i++) {
		fields[i].value = '';
	}
}