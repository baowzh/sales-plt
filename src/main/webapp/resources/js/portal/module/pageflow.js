/** page flow */
function PageFlow(flow) {
	this.flow = flow;
	this.selectedTabName = "SELECTED_PAGETAB_SPAN";
	this.hasPrevPage = false;
	this.hasNextPage = false;
	this.alreadyFinish = false;
	this.currstep = "begin";
	this.beforestep = this.currstep;
	this.fsbtframe = getFrame(["flowsubmit", parent]);
	this.fsbtsteps = new Array(); //Array List
	this.monisteps = new Array(); //Map
	this.blackset = {"listener" : "listener", "service" : "service", "sp" : "sp", "Form0" : "Form0" };
	
	var pageflowHandlers = {
			onCreate:function(){
				self.afterAction = "";
			},
			onComplete:function(){
				eval(self.afterAction);
			}
		};
	Ajax.Responders.unregister(myGlobalHandlers);
	Ajax.Responders.register(pageflowHandlers);
	
	/** get flow */
	this.getFlow = function() {
		this.flow;
	}
	/** get attribute */ 
	this.getAttribute = function(obj, attrname, defval) {
		var attrvalue = obj[attrname];
		if (attrvalue == null) attrvalue = defval;
		return attrvalue;
	}
	/** get flow attribute */
	this.getFlowAttribute = function(attrname, defval) {
		return this.getAttribute(this.flow, attrname, defval);
	}
	/** get steps */
	this.getSteps = function() {
		return this.flow["steps"];
	}
	/** get step */
	this.getStep = function(stepname) {
		var steps = this.getSteps();
		return steps[stepname];
	}
	/** get step attribute */
	this.getStepAttribute = function(stepname, attrname, defval) {
		var step = this.getStep(stepname);
		if (step == null) return null;
		return this.getAttribute(step, attrname, defval);
	}
	/** get cases */
	this.getCases = function(stepname) {
		var step = this.getStep(stepname);
		return step["cases"];
	}
	/** get case */
	this.getCase = function(stepname, caseval) {
		var cases = this.getCases(stepname);
		if (cases == null) return null;
		return cases[caseval];
	}
	/** get case attribute */
	this.getCaseAttribute = function(stepname, caseval, attrname, defval) {
		var cas = this.getCase(stepname, caseval);
		if (cas == null) return null;
		return this.getAttribute(cas, attrname, defval);
	}
	
	/** init page flow */
	this.initPageFlow = function() {
		this.firstPageStep();
	}
	/** finish page flow */
	this.finishPageFlow = function() {
		if (!this.verifyStepPage(this.currstep)) return;
		if (!this.monitorFlowModify(true)) return;
		var nextbutton = this.getStepAttribute(this.currstep, "nextbutton");
		if (nextbutton == null) {
			alert("the step " + this.currstep + " bing next button does not exist!");
			return;
		}
		if (!this.trigerBindSubmit(this.currstep, "nextbutton")) return;
		this.fillSubmitData();
		this.clickBindSubmit(this.currstep, "nextbutton");
		this.cleanupSubmitData();
		this.beginFlowOverlay();
		this.alreadyFinish = true;
	}
	/** cancel page flow */
	this.cancelPageFlow = function() {
		if (window.confirm("确定要取消所有操作并关闭页面吗？")) {
			if (parent.parent.document.getElementById("navframeset") != null) {
				parent.closeNavFrameByLocation();
			} else if (top.document.getElementById("PopupAgent") != null) {
				top.close();
			} else {
				history.back();
			}
		}
	}
	/** reset page flow */
	this.resetPageFlow = function() {
		var tabnodes = getElement("pagetabs").childNodes;
		var activetab = getElement(this.selectedTabName);
		if (activetab != null) {
			this.currstep = activetab.parentElement.parentElement.parentElement.id;
		} else {
			this.currstep = "begin";
		}
		this.beforestep = this.currstep;
		this.hasPrevPage = pagetabs.childNodes.length > 1;
		var nextstep = this.getStepAttribute(this.currstep, "nextstep");
		this.hasNextPage = nextstep != null && nextstep != "end";
		
		var nfsbtsteps = new Array();
		var nmonisteps = new Array();
		for (var i=0; i<tabnodes.length; i++) {
			var stepname = tabnodes[i].id;
			nfsbtsteps[i] = stepname;
			
			var monistep = this.monisteps[tabnodes[i].id];
			if (monistep != null) nmonisteps[stepname] = monistep;
		}
		this.fsbtsteps = nfsbtsteps;
		this.monisteps = nmonisteps;
	}
	/** first page step */
	this.firstPageStep = function() {
		this.fsbtframe = getFrame(["flowsubmit", parent]);
		if (this.fsbtframe != null && this.fsbtframe.document.getElementById("bback") != null) {
			this.nextPageStep();
		} else {
			setTimeout("firstPageStep()", 100);
		}
	}
	/** next page step */
	this.nextPageStep = function() {
		var currtagname = this.getStepAttribute(this.currstep, "tagname");
		if (currtagname != "switch") {
			if (!this.verifyStepPage(this.currstep)) return;
			if (!this.monitorFlowModify(!this.isEndActiveTab())) return;
			if (!this.trigerBindObject(this.currstep, "nextbutton")) return;
			
			this.beforestep = this.currstep;
			this.currstep = this.getStepAttribute(this.currstep, "nextstep");
			
			this.beginFlowOverlay();
		}
		
		var tagname = this.getStepAttribute(this.currstep, "tagname");
		if (tagname == "switch") {
			var stepframe = getFrame([this.beforestep, parent]);
			var expression = this.getStepAttribute(this.currstep, "expression");
			var params = "&expression=" + expression;
			var stepform = this.getStepForm(stepframe);
			if (stepform != null) {
				params += "&" + stepframe.Form.serialize(stepform);
				this.disabledRepeatTransfer(params);
			}
			ajaxSubmit(self, "getExpressionValue", params);
			self.afterAction = "pageflow.execPageSwitch()"; //callback use pageflow
			this.resumeRepeatTransfer();
		} else {
			this.execPageStep(this.currstep);
		}
	}
	/** back page step */
	this.backPageStep = function() {
		if (!this.monitorFlowModify(true)) return;
		
		var stepframe = getFrame([this.currstep, parent]);
		if (!this.trigerBindObject(this.currstep, "backbutton")) return;
		
		var sbtstepidx = this.fsbtsteps.length - 1;
		this.fsbtsteps = this.fsbtsteps.slice(0, sbtstepidx).concat(this.fsbtsteps.slice(sbtstepidx + 1, this.fsbtsteps.length));
		
		var pagetabs = document.getElementById("pagetabs");
		var tabnodes = pagetabs.childNodes;
		if (tabnodes.length > 1) {
			tabnodes[tabnodes.length - 2].className = "open";
			this.currstep = tabnodes[tabnodes.length - 2].id;
		}
		if (tabnodes.length > 0) {
			tabnodes[tabnodes.length - 1].removeNode(true);
		}
		
		this.hasPrevPage = pagetabs.childNodes.length > 1;
		this.hasNextPage = true;
		
		this.switchPageStep(this.currstep, false);
		this.displayStepButton();
	}
	/** exec page switch */
	this.execPageSwitch = function() {
		var switchValue = self.ajaxData[0].SWITCH_VALUE;
		if (switchValue == null || switchValue == "") switchValue = this.getStepAttribute(this.currstep, "default");
		var nextstep = this.getCaseAttribute(this.currstep, switchValue, "nextstep");
		var tagname = this.getStepAttribute(nextstep, "tagname");
		if (tagname == "switch") {
			this.currstep = nextstep;
			this.nextPageStep();
		} else {
			this.execPageStep(nextstep);
		}
	}
	/** exec page step */
	this.execPageStep = function(execstep) {
		var sbtstepidx = this.fsbtsteps.length;
		this.fsbtsteps[this.fsbtsteps.length] = execstep;
		
		this.currstep = execstep;
		var stepname = this.getStepAttribute(this.currstep, "name");
		var stepdesc = this.getStepAttribute(this.currstep, "desc");
		var nextstep = this.getStepAttribute(this.currstep, "nextstep");
		var steppage = this.getStepAttribute(this.currstep, "page");
		var steplistener = this.getStepAttribute(this.currstep, "listener");
		var stepparams = this.getStepAttribute(this.currstep, "params", "");
		
		var pagetabs = document.getElementById("pagetabs");
		var tabnodes = pagetabs.childNodes;
		if (tabnodes.length > 0) {
			tabnodes[tabnodes.length - 1].className = "finish";
		}
		
		var isreload = !this.isLoadedStep(stepname);
		var pagetabcont = "<span class=\"li\" title=\"" + stepdesc + "\" onclick=\"pageflow.switchPageStep('" + stepname + "', true);\"><span class=\"left\"></span><span class=\"text\"><span class=\"active\"><span id=\"" + (isreload ? "PAGEFLOW_LOADING" : "") + "\" class=\"" + (isreload ? "loading" : "") + "\">" + getPartStr(stepdesc, 20) + "</span></span></span><span class=\"right\"></span></span>";
		var pagetab = document.createElement("LI");
		pagetab.id = stepname;
		pagetab.className = "open";
		pagetab.innerHTML = pagetabcont;
		pagetabs.appendChild(pagetab);
		
		this.recodeFlowModify();
		this.switchPageStep(stepname, false);
		if (isreload) {
			var beforeframe = getFrame([this.beforestep, parent]);
			if (beforeframe != null) {
				var beforeform = this.getStepForm(beforeframe);
				if (beforeform != null) {
					var stepsbtarea = beforeframe.document.createElement("div");
					stepsbtarea.id = "stepSubmitArea";
					stepsbtarea.style.display = "none";
					beforeform.appendChild(stepsbtarea);
					
					var svcobj = beforeframe.document.getElementById("service");
					var svcoldval = svcobj == null ? null : svcobj.value;
					if (svcobj == null) {
						svcobj = beforeframe.document.createElement("input");
						svcobj.type = "hidden";
						svcobj.id = "service";
						svcobj.name = "service";
						stepsbtarea.appendChild(svcobj);
					}
					svcobj.value = "page/" + steppage;
					
					var lsnrobj = beforeframe.document.getElementById("listener");
					var lsnroldval = lsnrobj == null ? null : lsnrobj.value;
					if (lsnrobj == null) {
						lsnrobj = beforeframe.document.createElement("input");
						lsnrobj.type = "hidden";
						lsnrobj.id = "listener";
						lsnrobj.name = "listener";
						stepsbtarea.appendChild(lsnrobj);
					}
					lsnrobj.value = steplistener;
					
					if (stepparams != "") {
						var paramary = stepparams.split(";");
						for (var i=0; i<paramary.length; i++) {
							var stepparam = paramary[i];
							var paramid = stepparam.split(":")[0];
							var paramval = stepparam.split(":")[1];
							var paramobj = beforeframe.document.createElement("input");
							paramobj.type = "hidden";
							paramobj.id = paramid;
							paramobj.name = paramid;
							paramobj.value = paramval;
							stepsbtarea.appendChild(paramobj);
						}
					}
					
					this.setTransferParam(beforeframe, stepsbtarea);
					
					beforeform.target = stepname;
					beforeform.submit();
					
					beforeframe.setDisabledElements(beforeframe.wadeDisabledElements, true);
					
					if (svcoldval != null) svcobj.value = svcoldval;
					if (lsnroldval != null) lsnrobj.value = lsnroldval;
					
					stepsbtarea.removeNode(true);
				}
			} else {
				if (stepparams != "") {
					var paramstr = "";
					var paramary = stepparams.split(";");
					for (var i=0; i<paramary.length; i++) {
						var stepparam = paramary[i];
						paramstr += "&" + stepparam.split(":")[0] + "=" + stepparam.split(":")[1];
					}
					stepparams = paramstr;
				}
				stepparams += this.getTransferUrl();
				redirectTo(steppage, steplistener, stepparams, [stepname, parent]);
			}
			
			this.setLoadedStep(stepname, "true");
		} else {
			this.endFlowOverlay();
		}
		
		this.hasPrevPage = pagetabs.childNodes.length > 1;
		this.hasNextPage = nextstep != null && nextstep != "end";
		
		this.displayStepButton();
	}
	/** switch page step */
	this.switchPageStep = function(stepname, needmonitor) {
		if (needmonitor == true && !this.monitorFlowModify(true)) return;
		var preActiveSpan = getElement(this.selectedTabName);
		if (preActiveSpan != null) {
			preActiveSpan.id = null;
			preActiveSpan.className = null;
		}
		var activeSpan = getElement(stepname).childNodes[0].childNodes[1].childNodes[0];
		activeSpan.id = this.selectedTabName;
		activeSpan.className = "active";
		this.displayStepFrame(stepname);
	}
	/** fill submit data */
	this.fillSubmitData = function() {
		var sbtframe = getFrame([this.currstep, parent]);
		var sbtform = this.getStepForm(sbtframe);
		var div = sbtframe.document.createElement("DIV");
		div.id = "pageFlowArea";
		div.style.display = "none";
		sbtform.appendChild(div);
		
		this.setTransferParam(sbtframe, div);
		
		var sbtframeflds = new Array();
		var elements = sbtform.elements;
		for (var i=0; i<elements.length; i++) {
			if (elements[i].name != null) {
				sbtframeflds[elements[i].name] = sbtframe.name;
			}
		}
		
		for (var i=0; i<this.fsbtsteps.length; i++) {
			if (this.fsbtsteps[i] == this.currstep) continue;
			var stepframe = getFrame([this.fsbtsteps[i], parent]);
			var stepform = this.getStepForm(stepframe);
			if (stepform == null) continue;
			var elements = stepform.elements;
			for (var j=0; j<elements.length; j++) {
				if (elements[j].tagName == "INPUT" || elements[j].tagName == "SELECT" || elements[j].tagName == "TEXTAREA") {
					if (elements[j].name == null) continue;
					if (this.blackset[elements[j].name] != null) continue;
					if (elements[j].tagName == "INPUT" && (elements[j].type.toUpperCase() == "BUTTON" || elements[j].type.toUpperCase() == "SUBMIT")) continue;
					if (sbtframeflds[elements[j].name] != null && sbtframeflds[elements[j].name] != stepframe.name) continue;
					
					var newelm = sbtframe.document.createElement(elements[j].outerHTML);
					if (elements[j].tagName == "SELECT") {
					 	if (elements[j].options.length > 0) continue;
						var selectedIndex = elements[k].selectedIndex;;
						if (selectedIndex == -1) continue;
						var selectedOption = elements[j].options[selectedIndex];
						newelm.add(new Option(selectedOption.text, selectedOption.value));
					} else if (elements[j].tagName == "TEXTAREA") {
						newelm.innerHTML = elements[j].innerHTML;
					}
					newelm.className = null;
					newelm.setAttribute("desc", null);
					div.appendChild(newelm);
					
					sbtframeflds[elements[j].name] = stepframe.name;
				}
			}
		}
	}
	/** cleanup submit data */
	this.cleanupSubmitData = function() {
		var sbtframe = getFrame([this.currstep, parent]);
		var pageflowarea = sbtframe.document.getElementById("pageFlowArea");
		if (pageflowarea != null) pageflowarea.removeNode(true);
	}
	/** triger bind object */
	this.trigerBindObject = function(stepname, bindname) {
		var bindbtname = this.getStepAttribute(stepname, bindname);
		if (bindbtname != null) {
			return this.fireClickEvent(getFrame([stepname, parent]), bindbtname); 
		}
		return true;
	}
	/** click bind object */
	this.clickBindObject = function(stepname, bindname) {
		var bindbtname = this.getStepAttribute(stepname, bindname);
		if (bindbtname != null) {
			var bindbutton = getFrame([stepname, parent]).document.getElementById(bindbtname);
			if (bindbutton != null) {
				bindbutton.click();
			}
		}
	}
	/** triger bind submit */
	this.trigerBindSubmit = function(stepname, bindname) {
		var bindbtname = this.getStepAttribute(stepname, bindname);
		if (bindbtname != null) {
			var stepframe = getFrame([stepname, parent]);
			var bindbutton = stepframe.document.getElementById(bindbtname);
			if (bindbutton != null) {
				var stepform = this.getStepForm(stepframe);
				var proxybtname = (bindbutton.id != null ? bindbutton.id : bindbutton.name) + "_proxy";
				var proxybutton = stepframe.document.getElementById(proxybtname);
				if (proxybutton == null) {
					proxybutton = stepframe.document.createElement(bindbutton.outerHTML);
					proxybutton.id = proxybtname;
					proxybutton.name = proxybtname;
					stepform.appendChild(proxybutton);
				}
				return this.fireClickEvent(stepframe, proxybtname);
			}
		}
		return true;
	}
	/** click bind submit */
	this.clickBindSubmit = function(stepname, bindname) {
		var bindbtname = this.getStepAttribute(stepname, bindname);
		if (bindbtname != null) {
			var bindbutton = getFrame([stepname, parent]).document.getElementById(bindbtname);
			if (bindbutton != null) {
				bindbutton.onclick = null;
				bindbutton.click();
			}
		}
	}
	/** display step button */
	this.displayStepButton = function() {
		var bback = this.fsbtframe.document.getElementById("bback");
		var bnext = this.fsbtframe.document.getElementById("bnext");
		var bfinish = this.fsbtframe.document.getElementById("bfinish");
		var nexttab = getElement("nexttab");
		var overtab = getElement("overtab");
		bback.disabled = !this.hasPrevPage;
		hidden(bnext, !this.hasNextPage);
		hidden(bfinish, this.hasNextPage);
		hidden(nexttab, !this.hasNextPage);
		hidden(overtab, this.hasNextPage);
	}
	/** display step frame */
	this.displayStepFrame = function(stepname) {
		var flowbody = parent.document.getElementById("flowbody");
		var stepfrm = parent.document.getElementById(stepname);
		var stepfrmidx = parseInt(stepfrm.getAttribute("index"));
		var prefix = flowbody.cols.substring(0, 2);
		var fbdcols = flowbody.cols.substring(2, flowbody.cols.length - 2)
		var suffix = flowbody.cols.substring(flowbody.cols.length - 2, flowbody.cols.length);
		fbdcols = fbdcols.replaceAll("\\*", "0");
		fbdcols = fbdcols.substring(0, stepfrmidx * 2) + "*" + fbdcols.substring(stepfrmidx * 2 + 1);
		flowbody.cols = prefix + fbdcols + suffix;
	}
	/** verify flow action */
	this.verifyFlowAction = function() {
		if (getElement("PAGEFLOW_LOADING") != null) {
			alert("页面未载入完成，您的操作不能继续。\n请等页面载入完成后再操作！");
			return false;
		}
		return true;
	}
	/** verify step page */
	this.verifyStepPage = function(stepname) {
		var stepframe = getFrame([stepname, parent]);
		if (stepframe != null && stepframe.document.getElementById("pagecontext") == null) {
			alert("流程步骤[" + this.getStepAttribute(stepname, "desc") + "]已产生错误。\n导致接续步骤无法继续正确执行。\n请先处理完错误后再执行接续步骤！");
			return false;
		}
		return true;
	}
	/** recode flow modify */
	this.recodeFlowModify = function() {
		if (this.monisteps[this.beforestep] == null) {
			var beforeframe = getFrame([this.beforestep, parent]);
			var beforemonitor = this.getStepAttribute(this.beforestep, "monitor");
			if (beforeframe != null && beforemonitor != null) {
				var bfmonifieldnms = beforemonitor.split(",");
				var monistep = new Array();
				for (var i=0; i<bfmonifieldnms.length; i++) {
					var bfmonifieldval = this.getMonitorFieldValue(beforeframe, bfmonifieldnms[i]);
					if (bfmonifieldval != null) {
						monistep[bfmonifieldnms[i]] = bfmonifieldval;
					}
				}
				this.monisteps[this.beforestep] = monistep;
			}
		}
	}
	/** monitor flow modify */
	this.monitorFlowModify = function(needconfirm) {
		var activetab = getElement(this.selectedTabName);
		if (activetab != null) {
			var activestep = activetab.parentElement.parentElement.parentElement.id;
			var monistep = this.monisteps[activestep];
			if (monistep != null) {
				var activeframe = getFrame([activestep, parent]);
				var activemonitor = this.getStepAttribute(activestep, "monitor");
				if (activeframe != null && activemonitor != null) {
					var acmonifieldnms = activemonitor.split(",");
					for (var i=0; i<acmonifieldnms.length; i++) {
						var acmonifieldval = this.getMonitorFieldValue(activeframe, acmonifieldnms[i]);
						var acmonistepfldval = monistep[acmonifieldnms[i]];
						if (acmonifieldval != null && acmonistepfldval != null) {
							if (acmonifieldval != acmonistepfldval) {
								var acmonifielddesc = activeframe.document.getElementById(acmonifieldnms[i]).desc;
								if (acmonifielddesc == null || acmonifielddesc == "") acmonifielddesc = acmonifieldnms[i];
								var needcancel = needconfirm == false || needconfirm == true && window.confirm("字段[" + acmonifielddesc + "]的值已改变。\n该步骤之后的所有已执行的接续步骤将全部撤销。\n确认撤销吗？");
								if (!needcancel) return false;
								this.monisteps[activestep] = null;
								this.cancelNextSteps(activestep);
								if (needconfirm) return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	/** get monitor field value */
	this.getMonitorFieldValue = function(target, fieldname) {
		var fields = target.document.getElementsByName(fieldname);
		if (fields.length == 0) return null;
		var fieldvalues = "";
		for (var i=0; i<fields.length; i++) {
			var field = fields[i];
			var fieldvalue = "";
			if (field.tagName == "INPUT" && (field.type.toUpperCase() == "CHECKBOX" || field.type.toUpperCase() == "RADIO")) {
				if (field.checked) {
					fieldvalue = field.value;
				}
			} else {
				fieldvalue = field.value;
			}
			if (fieldvalue != "") {
				fieldvalues += (fieldvalues == "" ? "" : ",") + fieldvalue;
			}
		}
		return fieldvalues;
	}
	/** cancel next steps */
	this.cancelNextSteps = function(stepname) {
		var tabnodes = getElement("pagetabs").childNodes;
		for (var i=tabnodes.length - 1; i>=0; i--) {
			var tabnode = tabnodes[i];
			if (tabnode.id == stepname) {
				tabnode.className = "open";
				break;
			}
			tabnode.removeNode(true);
		}
		
		var reservedsteps = new Array();
		tabnodes = getElement("pagetabs").childNodes;
		for (var i=0; i<tabnodes.length; i++) {
			reservedsteps[tabnodes[i].id] = tabnodes[i].id;
		}
		
		var stepframes = parent.document.getElementById("flowbody").childNodes;
		for (var i=0; i<stepframes.length; i++) {
			var stepfrmname = stepframes[i].name;
			if (stepfrmname == "flowleft" || stepfrmname == "flowright") continue;
			if (reservedsteps[stepfrmname] == null) {
				this.setLoadedStep(stepfrmname, "false");
				getFrame([stepfrmname, parent]).location.href = "about:blank";
			}
		}
		
		this.resetPageFlow();
		this.displayStepButton();
	}
	/** disabled repeat transfer */
	this.disabledRepeatTransfer = function(url) {
		var transferNodes = getElement("transferArea").childNodes;
		for (var i=0; i<transferNodes.length; i++) {
			var transferNode = transferNodes[i];
			if (transferNode.tagName == "INPUT") {
				if (url.indexOf("&" + transferNode.name + "=") != -1) {
					transferNode.disabled = true;
				}
			}
		}
	}
	/** resume repeat transfer */
	this.resumeRepeatTransfer = function() {
		var transferNodes = getElement("transferArea").childNodes;
		for (var i=0; i<transferNodes.length; i++) {
			var transferNode = transferNodes[i];
			if (transferNode.tagName == "INPUT") {
				transferNode.disabled = false;
			}
		}
	}
	/** get transfer url */
	this.getTransferUrl = function() {
		var transferUrl = "";
		var transferNodes = getElement("transferArea").childNodes;
		for (var i=0; i<transferNodes.length; i++) {
			var transferNode = transferNodes[i];
			if (transferNode.tagName == "INPUT") {
				transferUrl += "&" + transferNode.name + "=" + transferNode.value;
			}
		}
		return transferUrl;
	}
	/** set transfer param */
	this.setTransferParam = function(target, areaobj) {
		var transferNodes = getElement("transferArea").childNodes;
		for (var i=0; i<transferNodes.length; i++) {
			var transferNode = transferNodes[i];
			if (target.document.getElementsByName(transferNode.name).length == 0) {
				areaobj.appendChild(target.document.createElement(transferNode.outerHTML));
			}
		}
	}
	/** begin flow overlay */
	this.beginFlowOverlay = function() {
		getFrame(["flowtab", parent]).beginPageOverlay();
		getFrame(["flowsubmit", parent]).beginPageOverlay();
		getFrame(["flowleft", parent]).beginPageOverlay();
		getFrame(["flowright", parent]).beginPageOverlay();
	}
	/** end flow overlay */
	this.endFlowOverlay = function() {
		getFrame(["flowtab", parent]).endPageOverlay();
		getFrame(["flowsubmit", parent]).endPageOverlay();
		getFrame(["flowleft", parent]).endPageOverlay();
		getFrame(["flowright", parent]).endPageOverlay();
	}
	/** end flow loading */
	this.endFlowLoading = function() {
		var pageload = getElement("PAGEFLOW_LOADING");
		if (pageload != null) {
			var stepname = pageload.parentElement.parentElement.parentElement.parentElement.id
			var backbtname = this.getStepAttribute(stepname, "backbutton");
			var nextbtname = this.getStepAttribute(stepname, "nextbutton");
			if (backbtname != null) {
				var backbtobj = getFrame([stepname, parent]).document.getElementById(backbtname);
				if (backbtobj != null) backbtobj.style.display = "none";
			}
			if (nextbtname != null) {
				var nextbtobj = getFrame([stepname, parent]).document.getElementById(nextbtname);
				if (nextbtobj != null) nextbtobj.style.display = "none";
			}
			
			pageload.id = null;
			pageload.className = null;
			this.endFlowOverlay();
		}
	}
	/** is end active tab */
	this.isEndActiveTab = function() {
		var activetab = getElement(this.selectedTabName);
		if (activetab != null) {
			var activestep = activetab.parentElement.parentElement.parentElement.id;
			var tabnodes = getElement("pagetabs").childNodes;
			if (tabnodes.length > 1) {
				return activestep == tabnodes[tabnodes.length - 1].id;
			}
		}
		return true;
	}
	/** fire click event */
  	this.fireClickEvent = function(target, fieldname) {
  		var obj = target.document.getElementById(fieldname);
  		if (obj == null) return true;
  		if (document.all) {
  			return obj.fireEvent("onclick");
		} else {
			var evt = target.document.createEvent("MouseEvent");   
			evt.initEvent("click", false, true);
			return obj.dispatchEvent(e);
  		}
  	}
  	/** get step form */
  	this.getStepForm = function(stepframe) {
		if (stepframe.document.forms.length != 1) {
			alert(stepname  + " form must unique!");
			return null;
		}
		return stepframe.document.forms[0];
  	}
  	/** is loaded step */
  	this.isLoadedStep = function(stepname) {
		var stepfrmobj = parent.document.getElementById(stepname);
		return stepfrmobj != null && stepfrmobj.getAttribute("loaded") == "true";
  	}
  	/** set loaded step */
  	this.setLoadedStep = function(stepname, result) {
		var stepfrmobj = parent.document.getElementById(stepname);
		if (stepfrmobj != null) stepfrmobj.setAttribute("loaded", result);
  	}
}

window["UserAgent"]={};
(function(){
    if(UserAgent.platform) return;
    var ua = window.navigator.userAgent;
    UserAgent.platform = window.navigator.platform;

    UserAgent.firefox = ua.indexOf("Firefox")>0;
    UserAgent.opera = typeof(window.opera)=="object";
    UserAgent.ie = !UserAgent.opera && ua.indexOf("MSIE")>0;
    UserAgent.mozilla = window.navigator.product == "Gecko";
    UserAgent.netscape= window.navigator.vendor=="Netscape";
    UserAgent.safari  = ua.indexOf("Safari")>-1;

    if(UserAgent.firefox) var re = /Firefox(\s|\/)(\d+(\.\d+)?)/;
    else if(UserAgent.ie) var re = /MSIE( )(\d+(\.\d+)?)/;
    else if(UserAgent.opera) var re = /Opera(\s|\/)(\d+(\.\d+)?)/;
    else if(UserAgent.netscape) var re = /Netscape(\s|\/)(\d+(\.\d+)?)/;
    else if(UserAgent.safari) var re = /Version(\/)(\d+(\.\d+)?)/;
    else if(UserAgent.mozilla) var re = /rv(\:)(\d+(\.\d+)?)/;
    
    if("undefined"!=typeof(re)&&re.test(ua))
        UserAgent.version = parseFloat(RegExp.$2);   
})();
/** add event listener */
function addEventListener(obj,e,handler){
	if('object'!=typeof(obj) || 'string'!=typeof(e) || 'function'!=typeof(handler)) return;
	if(UserAgent.ie) obj.attachEvent('on' + e,handler);
	else if(UserAgent.firefox) obj.addEventListener(e,handler,false);	
}
/** remove event listener */
function removeEventListener(obj,e,handler){
	if('object'!=typeof(obj) || 'string'!=typeof(e) || 'function'!=typeof(handler)) return;
	if(UserAgent.ie) obj.detachEvent('on' + e,handler);
	else if(UserAgent.firefox) obj.removeEventListener(e,handler,true);	
}
window.onload=window.load=page_Load;

var turnLeft,turnRight,tabBar;
var stepNum=50,curNum=stepNum,loop=false;
/** page load */
function page_Load(){
    turnLeft=document.getElementById('turnLeft');
    turnRight=document.getElementById('turnRight');
    tabBar=document.getElementById('tabBar');
    
    if(turnLeft && turnRight && tabBar){
	addEventListener(turnLeft,'mousedown',turnLeft_MouseDown);
	addEventListener(turnRight,'mousedown',turnRight_MouseDown);
    }
}
/** turn left mouse down */
function turnLeft_MouseDown(){
   RegisterUp();
   curNum=stepNum;
   ExcuteMove();
}
/** turn right mouse down */
function turnRight_MouseDown(){
    RegisterUp();
    curNum=0-stepNum;
    ExcuteMove();
}
/** document mouse up */
function document_MouseUp(){loop=false;removeEventListener(document,'mouseup',document_MouseUp,true);}
function RegisterUp(){
    loop=true; 
    addEventListener(document,'mouseup',document_MouseUp);
}
/** excute move */
function ExcuteMove(){
    if(loop==false)return;
    var st=tabBar.style;
    var ml=st.marginLeft;
    if(typeof(ml)=='undefined' || ml==''){
        ml='0px';
    }
    var r=/(-)?(\d+)px/ig;
    r=r.exec(ml);if(r!=null && r.length && r.length==3){//alert(r[1] +'|' + r[2]);return;
	if('undefined'==typeof(r[1]))r[1]='';
        ml=(parseInt(r[1] + r[2])+curNum) + 'px';
        st.marginLeft=ml;
    }
    window.setTimeout(ExcuteMove,100);
}
/** reset */
function reset(){
   var st=tabBar.style;
   st.marginLeft='0px'; 
}