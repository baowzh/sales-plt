$(document).ready(function() {
	$('#catId').asiainfoSelect({
		id : '#catId',
		url : '${pageContext.request.contextPath}/ui/categorys.jhtml',
		listeners : [ {
			selector : "#typeId",
			type : "select"
		} ],
		defaultvalue : '${news.catId}'
	});
});

