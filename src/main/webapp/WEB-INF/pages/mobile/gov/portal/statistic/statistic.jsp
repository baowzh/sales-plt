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
<link href="${ctx}/resources/portal/mobile/css/paging.css"
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

	<%@include file="../head/head.jsp"%>
	<div class="new_tb" style="width: 100%;">
		<a href="${ctx}/portal/main.jhtml?departId=${depart.id}"> 首页 </a> >> 
		<span>排名  </span>
	</div>
	<div class="middle-c" style="width: 100%;">
		<div class="main-content-left1" style="width: 100%;">

			<div class="new_x" style="margin-left: unset; min-height: 500px;">
				<ul>
					<c:forEach items="${page.models}" var="statistic" varStatus="st">
						<li><span style="float: left">${st.index+1}</span><a
							href="${ctx}/portal/main.jhtml?departId=${statistic.id}"
							style="float: left; background: unset;">${statistic.name}
								&nbsp;[${statistic.num} ]</a></li>

					</c:forEach>
				</ul>

			</div>
			<asiainfo:page pagesize="10" pageindex="${page.pageindex}"
				url="${ctx}/portal/${dest}" mobile="true"
				totalRecord="${page.totalrowcount}" />

		</div>

	</div>
	<!-- 底部版权信息 -->
	<%@include file="../footer/footer.jsp"%>
</body>
<style>
.pagination {
	clear: both;
	color: #323232;
	padding: 0px;
	float: right;
	position: absolute;
	left: 0px;
	bottom: 10px;
	font-family: "Microsoft YaHei", "SimSun", "STHeiti Light", Arial;
	font-size: 11px;
}
</style>

</html>