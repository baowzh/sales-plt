/**
 * search menu
 */
$j(function() {

	var ajaxurl = "portal?service=ajaxDirect/1/Nav/Nav/javascript/&pagename=Nav&eventname=geneMenuJSON";
	$j.ajax({
		type : "GET",
		url : ajaxurl,
		dataType : "text",
		success : function(menudata) {
			$j(menudata).find("JSONDATA").each(function(i) {
				var menuJSON = $j.parseJSON($j(this).text());
				setStorage("menuList", menuJSON[0].menu);
			});

		},
		error : function() {
			alert("error");
		}
	});
	var $textinput = $j("#searchcontent");
	var $autocomplete = $j("<div class='autocomplete'></div>").hide()
			.insertAfter("#searchcontent");
	var clear = function() {
		$autocomplete.empty().hide();
		$textinput.val("");
	};
	$textinput.blur(function() {
		setTimeout(clear, 500);
	});
	var selecteditem = null;
	var timeoutid = null;
	var setSelecteditem = function(item) {
		selecteditem = item;
		if (selecteditem < 0) {
			selecteditem = $autocomplete.find("li").length - 1;
		} else if (selecteditem > $autocomplete.find("li").length - 1) {
			selecteditem = 0;
		}
		$autocomplete.find("li").removeClass("active").eq(selecteditem)
				.addClass("active");
	};

	var searchMenu = function() {
		// foreach
		if($textinput.val().length <= 0){
			$autocomplete.empty().hide();
			return;
		}
		$autocomplete.show();
		$autocomplete.empty();
		var menuList = $j.parseJSON(getStorage("menuList"));
		var inputValue = $j("#searchcontent").val();
		for (var i = 0; i < menuList.length; i++) {
			var id = menuList[i].id;
			var name = menuList[i].name;
			var url = menuList[i].url;
			if (name.indexOf(inputValue) > -1) {
				var $autoli = $j("<li></li>").appendTo($autocomplete);
				$j(
						"<a id='" + id + "' menuid='" + id
								+ "' href='javascript:void(0)' title='" + name
								+ "' caption='" + name + "' url='" + url
								+ "'></a>").text(name).appendTo($autoli).hover(
						function() {
							selecteditem = i;
						}, function() {
							selecteditem = -1;
						}).click(function() {
					closeLeftMenu();
					switchframe('div_contentframe',this,true);
					openmenu("" + $j(this).attr("url") + "");
					$textinput.val("");
					$autocomplete.empty().hide();
				});
			}

		}
		var yAxis = $textinput.position().top + 27;
		var xAxis = $textinput.position().left;
		$autocomplete.css("width", $j("search_box").css("width"));
		$autocomplete.css({
			"left" : xAxis + "px",
			"top" : yAxis + "px"
		});
		setSelecteditem(0);
	};

	$textinput.keyup(function(event) {
		if (event.keyCode > 40 || event.keyCode == 8 || event.keyCode == 32) {
			// $autocomplete.empty().hide();
			clearTimeout(timeoutid);
			timeoutid = setTimeout(searchMenu, 500);
		}
	}).keypress(function(event) { // enter
		if (event.keyCode == 13) {
			if ($autocomplete.find("li").length == 0 || selecteditem == -1) {
				return;
			}
			$textinput.val($autocomplete.find('li').eq(selecteditem).text());
			$j(".autocomplete>.active>a").trigger("click");
			event.preventDefault();
		}
	}).keydown(function(event) { // esc
		if (event.keyCode == 27) {
			$autocomplete.empty().hide();
			event.preventDefault();
		} else if (event.keyCode == 38) { // up
			if (selecteditem == -1) {
				setSelecteditem($autocomplete.find("li").length - 1);
			} else {
				setSelecteditem(selecteditem - 1);
			}
			event.preventDefault();
		} else if (event.keyCode == 40) { // down
			if (selecteditem == -1) {
				setSelecteditem(0);
			} else {
				setSelecteditem(selecteditem + 1);
			}
			event.preventDefault();
		}
	});

	$j(window).resize(function() {
		var yAxis = $textinput.position().top + 27;
		var xAxis = $textinput.position().left;
		$autocomplete.css("width", $j("search_box").css("width"));
		$autocomplete.css({
			"left" : xAxis + "px",
			"top" : yAxis + "px"
		});
	});
});