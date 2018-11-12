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
<link href="${ctx}/resources/portal/css/paging.css" rel="stylesheet" />

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

	<div class="middle-c">
		<div class="main-content-left1" style="width: 100%;">

			<div class="new_x" style="margin-left:unset;">
				<ul>
					<c:forEach items="${page.models}" var="statistic" varStatus="st">
						<li><span style="float:left">${st.index+1}</span><a
						href="${ctx}/portal/main.jhtml?departId=${statistic.id}" style="float:left;background: unset;">${statistic.name} &nbsp;[${statistic.num} ]</a></li>

					</c:forEach>
				</ul>

			</div>
			<asiainfo:page pagesize="10" pageindex="${page.pageindex}"
				url="${ctx}/portal/${dest}"
				totalRecord="${page.totalrowcount}" />

		</div>

	</div>
	<!-- 底部版权信息 -->
	<%@include file="../footer/footer.jsp"%>
</body>

</html>