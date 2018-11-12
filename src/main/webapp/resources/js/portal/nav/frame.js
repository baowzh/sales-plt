/** shrink sidebar */

function shrinkSiderbar(result) {
			var tipon = getElement("tipon");
			var tipoff = getElement("tipoff");
			if (result) {
				$j("#in_siderbar").animate({
					width : "0%"
				});
				$j("#in_contentframe").animate({
					width : "99%"
				});
				$j(".f_slip a").animate({
					left : "0px"
				});
				hidden(tipon, true);
				hidden(tipoff, false);
			} else {
				$j("#in_siderbar").animate({
					width : "177px"
				});
				//$j("#in_contentframe").css("width","86.7%");
				var newWidth = $j("#div_contentframe").width() - 179 + "px";
				$j("#in_contentframe").animate({
					width : newWidth
				});
				//$j("#in_contentframe").animate({width:"86.7%"});
				$j(".f_slip a").animate({
					left : "177px"
				});
				hidden(tipon, false);
				hidden(tipoff, true);
			}
		}
		function setWidth() {
			var newWidth = $(document.body).width()
					- $j("#in_siderbar").width() - 5;
			$j("#in_contentframe").animate({
				width : newWidth
			});
		}

function shrinkHeader(result) {
	var aset = parent.document.getElementById("aset");
	if (result) {
		aset.rows = "0,48,*";
	} else {
		aset.rows = "56,48,*";
	}
}
/** open subsys menus */
function openSubsysMenus(srcelement) {
	if(srcelement.value == "OSS")
	{
		popupWindow('OSSChoose', 'querySystems', '', 'OSS系统选锟斤拷');
		return;
	}
	
	var openedcode = "OPENED_SUBSYS_CODE";	
	var openedobj = getElement(openedcode);
	if (openedobj != null) {
		openedobj.id = null;
		openedobj.className = null;
	}
	srcelement.parentElement.id = openedcode;
	srcelement.parentElement.className = "on";
	
	redirectTo('Nav', 'queryMenuData', '&STAFF_ID=' + pagevisit.getAttribute('staffId') + '&SUBSYS_CODE=' + srcelement.getAttribute('code'), 'navframe');
}

/** open second menus */
function openSecondMenus(srcelement) {
	window.parent.document.getElementById('aset').rows = "30,31,0,*";
	document.getElementById('fpage').className = "li";
	
	var selfirname = "SELECTED_FIRST_MENU_ITEM";
	var selfirelement = getElement(selfirname);
	if (selfirelement != null) {
		selfirelement.id = null;
		selfirelement.className = "li";
	}
	var parelement = srcelement.parentElement;
	parelement.id = selfirname;
	parelement.className = "li on";
	
	var selsecname = "SELECTED_SECOND_MENU_ITEM";
	var selsecelement = getElement(selsecname);
	if (selsecelement != null) {
		selsecelement.id = null;
		hidden(selsecelement.parentElement, true);
	}
	
	var lichilds = srcelement.parentElement.children;
	if (lichilds.length == 2) {
		lichilds[1].children[0].id = selsecname;
		hidden(lichilds[1], false);
	}
	return true;
}
/** open tree menu */
/** open tree menu */
function openTreeMenu(srcelement) {
	window.parent.slipframe.document.getElementById('tipoff').click();
	var selname = "SELECTED_TREE_MENU_ITEM";
	var selelement = getElement(selname);
	if (selelement != null) {
		selelement.id = null;
		selelement.className = null;
	}
	var parelement = srcelement.parentElement;
	var url = srcelement.url;
	//modified  by Heshiquan 
	if (url){ 
	   url=url.substring(url.indexOf("openmenu('") + 10,url.indexOf("&RIGHT_CODE"));
	   //url = "&url=" + url;
	   url = getSysAddr(url);
	   var contextName=getContextName();
	   if (url.indexOf("http") == -1){
	     url = contextName.substring(0,contextName.lastIndexOf("/")) + url;	
	    }
	   //alert(url);   
	}
	//end modi
	parelement.id = selname;
	parelement.className = "on";

	var lichilds = srcelement.parentElement.children;
	if (lichilds.length == 2) {
		var menuTree = getFrame("sidebarframe").document.getElementById("MenuTree");
		//modified by Heshiquan 
		if (menuTree != null && !url) { 
		   menuTree.innerHTML = lichilds[1].innerHTML;
		 }else if (menuTree != null &&url){
		   menuTree.innerHTML = "<iframe id='mytreemenu' name='mytreemenu' src='"+url+"' height='450px' width='100%' frameborder='0' style='width:350px;position:relative;overflow:auto'>"+"</iframe>";
		 }
		//end modi
	}
	return true;
}
/** selected shortcut */
function selectedShortcut(srcelement) {
	var selname = "SELECTED_SHORTCUT_ITEM";
	var selelement = getElement(selname);
	if (selelement != null) {
		selelement.id = null;
		selelement.className = "text";
	}
	var txtelement = srcelement.parentElement;
	txtelement.id = selname;
	txtelement.className = "on";
}
/** click menu node */
function clickMenuNode(srcelement) {
	var liobj = srcelement.parentElement;
	var lichilds = liobj.children;
	var ulobj = lichilds[lichilds.length - 1];
	if (ulobj.style.display == "") {
		liobj.className = "fold"
		hidden(ulobj, true);
	} else {
		liobj.className = "unfold"
		hidden(ulobj, false);
	}
}
/** click menu item */
function clickMenuItem(srcelement) {
    
	var id = srcelement.id;
    var url1= document.getElementById(id).getAttribute("onclick");
    var a= url1.indexOf("');");
	    url1 =url1.substring(31,a);
    var url2=srcelement.baseURI;
    var URL = url2+url1;
  
    var menu = srcelement.innerHTML;
    menu = "menu="+menu;
    URL ="URL="+URL;
    var date=menu+"&"+URL;
   // ajaxDirect(this, "functionCount",date);

    var selname = "SELECTED_TREE_MENU_TEXT";
	var selelement = getElement(selname);
	if (selelement != null) {
		selelement.id = null;
		selelement.className = "text";
	}
	var txtelement = srcelement;
	txtelement.id = selname;
	txtelement.className = "text focus";
}
/** coll menu item */
function collMenuItem(obj) {
	var selelement = getElement("SELECTED_TREE_MENU_TEXT");
	if (selelement == null) {
		alert("锟斤拷选锟斤拷要锟秸藏的菜碉拷锟筋！");
		return false;
	}
	var menuid = selelement.getAttribute("menuid");
	if (menuid == null) {
		alert("锟矫菜碉拷锟斤拷锟窖撅拷锟秸诧拷锟剿ｏ拷锟斤拷锟斤拷锟劫达拷锟秸藏ｏ拷");
		return false;
	}
	if (!confirmInfo(obj, "确锟斤拷锟秸藏该菜碉拷锟斤拷锟斤拷")) return false;
	ajaxDirect(this, "createFavoriteMenu", "&STAFF_ID=" + pagevisit.getAttribute("staffId") + "&DEPART_ID=" + pagevisit.getAttribute("deptId") + "&CITY_CODE=" + pagevisit.getAttribute("cityId") + "&EPARCHY_CODE=" + pagevisit.getAttribute("epachyId")+ "&MENU_ID=" + menuid, "MenuPart");
	return true;
}

/** popup new window */
function popupWindow(page, listener, params, title, width, height, subsyscode, subsysaddr) {
	if (title == null) title = "锟斤拷锟斤拷锟斤拷锟斤拷";
	if (width == null) width = 800;
	if (height == null) height = 640;
	
	var url = getContextName() + "?service=page/" + page;
	if (listener != null) url += "&listener=" + listener;
	if (params != null) url += params;
	url = getSysAddr(url, subsyscode, subsysaddr);
	if (url.indexOf("&%72andom=") == -1) url += "&random=" + getRandomParam();
	
	openWindow(url, title, width, height);
}

function openWindow(url, title, width, height)
{
	window.open(url, title, "scrollbars=0, width=" + width + ", height=" + height + ", toolbar =yes, menubar=yes, scrollbars=yes, resizable=yes, location=yes, status=yes");
}