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
	<%@include file="../head/head-hamlet.jsp"%>
	<div class="new_tb" style="width: 1180px;">
		<a href="${ctx}/portal/index.jhtml"> 首页 </a> > > <a href="#"> 到户资金
		</a>
	</div>
	<div class="new_li" style="width: 1180px;">
		<div class="row"
			style="text-align: center; padding-top: 10px; padding-bottom: 10px;">
			<p>
				<span style="font-size: 20px; font-weight: bolder;">${depart.name}·到户资金</span>
			</p>
		</div>

		<div class="row">
			<!--  -->
			<table class="table table-bordered table-hover "
				style="width: 94%; margin-left: 20px;">
				<tbody>
					<tr
						style="background-color: #337ab7; font-size: 18px; font-weight: bolder;">
						<td style="width: 60px">序号</td>
						<td>填报时间</td>
						<td>部门名称</td>
						<td>到户资金名称</td>					
						<td>项目金额(元)</td>
						<td>发放时间</td>
						<td>备注</td>
					</tr>
					<c:forEach items="${departProhectDetail}" var="item" varStatus="st">
						<tr>
							<td style="text-align: center;">${st.index+1}</td>
							<td><fmt:formatDate value="${item.input_time}" pattern="yyyy-MM-dd" /></td>
							<td>${item.areaName}</td>
							<td>${item.name}</td>
							
							<td><fmt:formatNumber
										value="${item.amount}" type="CURRENCY">
									</fmt:formatNumber></td>
							<td><fmt:formatDate value="${item.release_time}" pattern="yyyy-MM-dd" /></td>
							<td>${item.comm}</td>
						</tr>
					</c:forEach>


				</tbody>
			</table>
			<!--  -->
		</div>
	</div>
	<!-- 底部版权信息 -->
	<%@include file="../footer/footer.jsp"%>
</body>

</html>