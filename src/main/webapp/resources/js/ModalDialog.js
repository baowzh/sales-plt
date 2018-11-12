//=================================ModalDialog=============================
// simulation modal dialog
// @version		1.0
// @author		liuchang
// @create		20071018
// @rely		dialog.css
// @rely		prototype framework
// @modify		20080407
//===============================/ModalDialog==============================
var ModalDialog = function(box, conf) {
	this.background;
	this.dialog;
	this.header;
	this.footer;
	this.width = conf.width ? conf.width : 600;
	this.height = conf.height ? conf.height : 400;
	this.title = conf.title ? conf.title : '';
	this.content = $(box);

	this.setup();
};
	
ModalDialog.prototype.show = function() {
	this.background.style.display = 'block';
	this.dialog.style.display = 'block';
	ModalDialog.addClass(document.body, 'x-body-masked');
	this.dialog.style.left = (parseInt(document.body.offsetWidth) - parseInt(this.dialog.style.width)) / 2 + 'px';
	this.dialog.style.top = (parseInt(document.body.offsetHeight) - parseInt(this.dialog.style.height)) / 2 + 'px';
};

ModalDialog.prototype.hide = function() {
	this.background.style.display = 'none';
	this.dialog.style.display = 'none';
	ModalDialog.removeClass(document.body, 'x-body-masked');
};

ModalDialog.prototype.addButton = function(text, handler, scope) {
	if (!this.btnCon) {
		var btnCon = document.createElement('DIV');
		ModalDialog.addClass(btnCon, 'x-btn-container');
		this.footer.appendChild(btnCon);
		this.btnCon = btnCon;
	}
	this.removeButton(text);
	var button = document.createElement('INPUT');
	ModalDialog.addClass(button, 'x-btn');
	button.type = 'button';
	button.value = text;
	button.onclick = function() { handler.apply(scope); };
	this.btnCon.appendChild(button);
}

ModalDialog.prototype.removeButton = function(text) {
	if (!this.btnCon) {
		return;
	}
	var buttons = this.btnCon.getElementsByTagName('INPUT');
	for (var i = 0, l = buttons.length; i < l; i++) {
		if (buttons[i].type == 'button' && buttons[i].value == text) {
			buttons[i].parentNode.removeChild(buttons[i]);
			return;
		}
	}
}

ModalDialog.prototype.setup = function() {
	var bg = document.createElement('DIV');
	ModalDialog.addClass(bg, 'x-dlg-mask');
	bg.style.height = document.body.scrollHeight < document.body.clientHeight ? document.body.clientHeight : document.body.scrollHeight;
	bg.oncontextmenu = function() { return false; };
	document.body.appendChild(bg);
	this.background = bg;
	
	var win = document.createElement('DIV');
	ModalDialog.addClass(win, 'x-dlg');
	win.style.background = 'white'; 
	win.style.display = 'none';
	var w = this.width;
	var h = this.height;
	win.style.width = w;
	win.style.height = h;
	win.style.left = (parseInt(document.body.clientWidth) - w) / 2;
	win.style.top = (parseInt(document.body.clientHeight) - h) / 2;
	win.style.textAlign = 'center';
	win.style.lineHeight = '25px';
	document.body.appendChild(win);
	this.dialog = win;
	
	var hd = document.createElement('DIV');
	ModalDialog.addClass(hd, 'x-dlg-hd');
	hd.unselectable = 'on';
	hd.innerHTML = this.title;
	win.appendChild(hd);
	this.header = hd;
	
	var toolbox = document.createElement('DIV');
	ModalDialog.addClass(toolbox, 'x-dlg-toolbox');
	hd.appendChild(toolbox);
	var close = document.createElement('DIV');
	ModalDialog.addClass(close, 'x-dlg-close');
	toolbox.appendChild(close);
	var self = this;
	var hide = this.hide;
	close.onclick = function() { hide.apply(self); };
	
	var contentDiv = document.createElement('DIV');
	ModalDialog.addClass(contentDiv, 'x-dlg-bd');
	contentDiv.innerHTML = this.content.innerHTML;
	win.appendChild(contentDiv);
	if (this.content.parentNode) {
		this.content.parentNode.removeChild(this.content);
	}

	var ft = document.createElement('DIV');
	ModalDialog.addClass(ft, 'x-dlg-ft');
	win.appendChild(ft);
	this.footer = ft;
};
ModalDialog.removeClass = function(el, className) {
	if (!(el && el.className)) {
		return;
	}
	var cls = el.className.split(" ");
	var ar = new Array();
	for (var i = cls.length; i > 0;) {
		if (cls[--i] != className) {
			ar[ar.length] = cls[i];
		}
	}
	el.className = ar.join(" ");
};

ModalDialog.addClass = function(el, className) {
	ModalDialog.removeClass(el, className);
	el.className += " " + className;
};