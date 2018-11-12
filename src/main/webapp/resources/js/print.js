/********************************************************************************************
* HTML Print
* setNeedBorder:�Ƿ�����߿�Ĭ�ϰ����߿�
* printRow("table");��ӡָ�����ԭʼ����
* print("table"),��ӡ�����������
* print("table", 'CODE,TYPE'),��ӡ���ָ�����������ݣ�����Ҫ��ӡ����������ڶ�����������Ӧ��td id="col_CODE"�ȣ���Ҫ����col_��
* print("table", 'CODE,TYPE', '100,200'),��ӡ���ָ��������/ָ�����ݣ���ָ���п�
* print("table", 'CODE,TYPE', '100,200', true),��ӡ����У����в����ı�ʾ�Ƿ�ɾ��/�����У�Ϊtrue��ʾɾ��������ָ�����У�Ϊfalse��ʾֻ��ʾ������ָ�����У�Ĭ��Ϊfalse:������
* print("table", 'CODE,TYPE', '100,200', true, true),��ӡ�������/ָ������,���������ָ���Ƿ�ȥ����ͷ��Ĭ��Ϊ��ȥ
* print("table", 'CODE', '100,200', true, true, title),��ӡ�������/ָ������,����6�Ǽ��ϱ���
* print('table_list', 'TYPE', '100,200', true, true, '��ӡ����', 'SUPERUSR 2005-06-02 12:12:12'); ��ӡ�������/ָ�����ݣ�����7 ָ�������������Ϣ
* @����һ������
* @�������������������,�ŷֿ�
* @���������п���������Ӧ
* @�����ģ��в������ͣ�false|true��false��ʾ������ΪҪɾ����������true��ʾ������ΪҪ��ʾ��������Ĭ��Ϊ��ʾ����(false)
* @�����壺�Ƿ�ȥ����ͷ,Ĭ��false
* @�����壺�Ƿ�ȥ����ͷ,Ĭ��false
* @����������������
* @�����ߣ�ҳβ����
*/

/** html print */
function HtmlPrint() {
	this.hkey_root = "HKEY_CURRENT_USER";
	this.hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	this.need_border = true;
	this.printframe = getFrame("printframe");
	
	/** clear header and footer  */
	this.clearHeaderAndFooter = function() {
		this.setHeaderAndFooter("", "");
	}
	/** set default header and footer */
	this.setHeaderAndFooter = function() {
		this.setHeaderAndFooter("&w&bҳ�룬&p/&P", "&u&b&d");
	}
	/** set header and footer */
	this.setHeaderAndFooter = function(header, footer) {
		try {
			var regwsh = new ActiveXObject("WScript.Shell");
			var hkey_key = "header";    
			regwsh.RegWrite(this.hkey_root + this.hkey_path + hkey_key, header);
			hkey_key = "footer";
			regwsh.RegWrite(this.hkey_root + this.hkey_path + hkey_key, footer);
		} catch (e) {
		}
	}
	/** set need border */
	this.setNeedBorder = function (need_border) {
		this.need_border = need_border;
	}
	/** get print object */
	this.getPrintObject = function (objname) {
		this.clearHeaderAndFooter();
		
		this.printframe.document.body.innerHTML = "";
		this.printframe.document.body.insertAdjacentHTML("afterBegin", "");
		this.printframe.document.body.insertAdjacentHTML("beforeEnd", document.getElementById(objname).outerHTML);
	
		return this.printframe.document.getElementById(objname);
	}
	/** print raw */
	this.printRaw = function (objname) {
		if (window.confirm("ȷ����ӡ��")) {
			var newobj = this.getPrintObject(objname);
			newobj.style.display = "";
			if (this.need_border) {
				newobj.style.borderCollapse = "collapse";
				newobj.borderColor = "#000000";
			}
			newobj.bgColor = "#FFFFFF";
			newobj.style.fontSize = "12px";
						
			this.printframe.focus();
			this.printframe.print();
		}
	}
	/** 
	 * print table
	 * @param objname<table name>
	 * @param encode<table column>
	 * @param isdelete<is delete column(default false)>
	 * @param removehead<remove header>
	 * @param title
	 * @param info
	 * @param footer
	 */
	this.print = function (objname, encodehead, encodewidth, isdelete, removehead, title, info, footer) {
		if (window.confirm("ȷ����ӡ��")) {
			var newobj = this.getPrintObject(objname);
			newobj.style.display = "";
			if (this.need_border) {
				newobj.border = "1";
				newobj.style.borderCollapse = "collapse";
			}
			newobj.borderColor = "#000000";
			newobj.bgColor = "#FFFFFF";
			newobj.style.fontSize = "12px";
			
			var header = newobj.rows[0];
			header.style.textAlign = "center";
			
			var hcells = header.cells;
			var celllength = hcells.length;
			var headlength = encodehead && encodehead != "" ? encodehead.split(",").length : 0;
			var colspan = isdelete ? celllength - headlength : headlength != 0 ? headlength : celllength;
	
			if (encodehead && encodehead != "") {
				var rows = newobj.rows;
				var heads = encodehead.split(",");
				var widths = encodewidth && encodewidth != '' ? encodewidth.split(",") : null;
				for (var i=0; i<rows.length; i++) {
					var row = rows[i];
					if (isdelete) {
						for (var j=0; j<heads.length; j++) {
							var cells = row.cells;
							for (var k=0; k<cells.length; k++) {
								var cell = cells[k];
								cell.id = hcells[k].id;
								if (cell.id == "col_" + heads[j]) {
									cell.style.display = "none";
									break;
								}
							}
						}
					} else {
						var cells = row.cells;
						for (var j=0; j<cells.length; j++) {
							var cell = cells[j];
							cell.id = hcells[j].id;
							var width = cell.width;
							var isexist = false;
							for (var k=0; k<heads.length; k++) {
								if (cell.id == "col_" + heads[k]) {
									isexist = true;
									if (widths && widths[k] != "") width = widths[k];
									break;
								}
							}
							if (!isexist) {
								cell.style.display = "none";
							} else {
								cell.width = width;
							}
						}
					}
				}
			}
		
			if (removehead) newobj.deleteRow(0);
			if (info && info != '') {
				var inforow = newobj.insertRow(0);
				var infocell = inforow.insertCell(0);
				infocell.innerHTML = info;
				infocell.colSpan = colspan;
			}
			if (title && title != '') {
				var titlerow = newobj.insertRow(0);
				var titlecell = titlerow.insertCell(0);
				titlecell.innerHTML = title;
				titlecell.colSpan = colspan;
				titlecell.align = "center";
				titlecell.height = "20";
				titlecell.style.fontSize = "20px";
				titlecell.style.fontWeight = "bold";
			}
			
			if (footer) {
				var footerrow = newobj.insertRow(newobj.rows.length);
				var footercell = footerrow.insertCell(0);
				footercell.innerHTML = footer;
				footercell.colSpan = colspan;
				footercell.height = "100";
			}
			
			this.printframe.focus();
			this.printframe.print();
		}
	}
}
/** print raw */
function printRaw(objname) {
	new HtmlPrint().printRaw(objname);
}
/** print */
function print(objname, encodehead, encodewidth, isdelete, removehead, title, info, footer) {
	new HtmlPrint().print(objname, encodehead, encodewidth, isdelete, removehead, title, info, footer);
}