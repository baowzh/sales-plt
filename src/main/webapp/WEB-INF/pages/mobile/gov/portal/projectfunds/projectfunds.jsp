<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link href="${ctx}/resources/portal/mobile/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/resources/portal/mobile/css/style.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/resources/portal/mobile/css/extends-style.css"
	rel="stylesheet" type="text/css">

<script src="${ctx}/resources/portal/js/jquery-1.11.0.min.js"></script>
<script src="${ctx}/resources/gentelella/js/bootstrap.min.js"></script>

<title>科尓沁左翼后旗三务公开</title>
<style>
body {
	background: url(${ctx}/resources/portal/images/bg.jpg) #f5f5f5 no-repeat
		top;
	background-size: 100%;
}
</style>
</head>

<body>
	<%@include file="../head/head.jsp"%>
	<div class="new_tb">
		<a href="${ctx}/portal/main.jhtml?departId=${depart.id}"> 首页 </a> >> <a href="#"> 项目资金
		</a>
	</div>
	<div class="new_li">
		<div class="row"
			style="text-align: center; padding-top: 10px; padding-bottom: 10px;">
			<p>
				<span style="font-size: 20px; font-weight: bolder;">${depart.name}·项目资金</span>
			</p>
		</div>

		<div class="row">
			<ul id="myTab" class="nav-tabs" style="padding-left: 1rem;">
				<li class="active"><a href="#anbumen" data-toggle="tab"
					style="font-size: 20px; font-weight: bolder;"> 按部门统计</a></li>
				<li><a href="#qizhi" data-toggle="tab"
					style="font-size: 20px; font-weight: bolder;"> 按苏木镇场统计</a></li>
			</ul>

			<div id="myTabContent" class="tab-content"
				style="padding-left: 10px;">
				<div class="tab-pane fade in active" id="anbumen">
					<c:forEach items="${projectsByDepart}" var="item">
						<div class="row">
							<div class="col-sm-8 col-xs-8 projectList">
								${item.name} : <span
									style="color: red; text-decoration: underline;">${ item.sum}</span>&nbsp;万元
								&nbsp;
							</div>
							<div class="col-sm-4 col-xs-4" style="padding-left: 10px;">
								<a href="${ctx}/portal/projectFundsGroup.jhtml?departId=${item.id}"
									class="btn btn-success"
									style="background-color: #2780E3; float: left;">查看详情</a>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="tab-pane" id="qizhi">
					<c:forEach items="${projectsByTown}" var="item">
						<div class="row">
							<div class="col-sm-8 col-xs-8 projectList">
								${item.name} : <span
									style="color: red; text-decoration: underline;">${ item.sum}</span>&nbsp;万元
								&nbsp;
							</div>
							<div class="col-sm-4 col-xs-4" style="padding-left: 10px;">
								<a href="${ctx}/portal/projectFundsGroup.jhtml?departId=${item.id}"
									class="btn btn-success"
									style="background-color: #2780E3; float: left;">查看详情</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<script>
				$(function() {
					$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
						// 获取已激活的标签页的名称
						var activeTab = $(e.target).text();
						// 获取前一个激活的标签页的名称
						var previousTab = $(e.relatedTarget).text();
						$(".active-tab span").html(activeTab);
						$(".previous-tab span").html(previousTab);
					});
				});
			</script>
		</div>
	</div>
	<!-- 底部版权信息 -->
	<%@include file="../footer/footer.jsp"%>
</body>

</html>