<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="icon" type="image/png" sizes="16x16"
	href="/ui2017/logo-16.png" />
<link rel="icon" type="image/png" sizes="32x32"
	href="/ui2017/logo-32.png" />
<link rel="icon" type="image/png" sizes="48x48"
	href="/ui2017/logo-48.png" />
<link href="${ctx}/resources/portal/css/style.css" rel="stylesheet" />
<link href="${ctx}/resources/portal/css/bootstrap.min.css"
	rel="stylesheet" />

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
	<%@include file="../head/head-town.jsp"%>
	<div class="new_tb" style="width: 1180px;">
		<a href="${ctx}/portal/index.jhtml"> 首页 </a> > > <a href="#"> 项目列表
		</a>
	</div>
	<div class="new_li" style="width: 1180px;">
		<div class="row"
			style="text-align: center; padding-top: 10px; padding-bottom: 10px;">
			<p>
				<span style="font-size: 20px; font-weight: bolder;">${depart.name}·民生项目</span>
			</p>
		</div>

		<div class="row">
			

			<div id="myTabContent" class="tab-content"
				style="padding-left: 20px;">
				<div class="tab-pane fade in active" id="anbumen">
					<c:forEach items="${projectsByDepart}" var="item">
						<div
							style="width: 320px; height: 32px; font-weight: bolder; font-size: 15px; line-height: 32px; margin: 10px; float: left;">
							${item.name} :<span
								style="color: red; text-decoration: underline;">${ item.count}</span>&nbsp;项
							&nbsp;<a
								href="${ctx}/portal/projectGroup.jhtml?departId=${item.id}"
								class="btn btn-success"
								style="background-color: #2780E3; float: right;">查看详情</a>
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