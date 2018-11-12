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
<link href="${ctx}/resources/gentelella/css/bootstrap.min.css"
	rel="stylesheet" />
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
	<div id="wrapper">

		<div class="col-md-4" style="height: 400px;">
			<div
				style="margin-top: 5px; font-size: 20px; height: 50px; line-height: 50px; font-weight: bolder; text-align: center; vertical-align: middle; background-color: rgb(1, 111, 184); color: #fff;">
				<span class="glyphicon glyphicon-tasks" style="font-size: 20px;"></span>
				民生项目
			</div>
			<a href="${ctx}/portal/projectGroup.jhtml?departId=${depart.id}"
				target="_blank"> <img
				style="width: 100%; height: 200px; margin-top: 10px; margin-bottom: 20px; border: 5px solid #BEBEBE;"
				src="${ctx}/resources/portal/images/msxm.jpg" alt="">
			</a>
			<div style="text-align: center;">
				<span style="font-size: 18px; font-weight: bolder; color: #2a333c">旗级项目：共
					<a href="${ctx}/portal/projectGroup.jhtml?departId=${depart.id}">
						<span style="margin-left: 5px; margin-right: 5px; color: red;">${projectCount }</span>项
				
				</span> </a>
			</div>
		</div>

		<div class="col-md-4" style="height: 400px;">
			<div
				style="margin-top: 5px; font-size: 20px; height: 50px; line-height: 50px; font-weight: bolder; text-align: center; vertical-align: middle; background-color: rgb(1, 111, 184); color: #fff;">
				<span class="glyphicon glyphicon-th-large" style="font-size: 20px;"></span>
				民生资金分配
			</div>

			<a href="${ctx}/portal/projectFundsGroup.jhtml?departId=${depart.id}"
				target="_blank"> <img
				style="width: 100%; height: 200px; margin-top: 10px; margin-bottom: 20px; border: 5px solid #BEBEBE;"
				src="${ctx}/resources/portal/images/mszj.jpg" alt="">
			</a>
			<div style="text-align: center;">
				<span style="font-size: 18px; font-weight: bolder; color: #2a333c">民生资金分配：共
					<a
					href="${ctx}/portal/projectFundsGroup.jhtml?departId=${depart.id}">
						<span style="margin-left: 5px; margin-right: 5px; color: red;">
							<c:if test="${projectSum!=null}">
								<fmt:formatNumber value="${projectSum}" type="CURRENCY">
								</fmt:formatNumber>
							</c:if> <c:if test="${projectSum==null}">
								 0
								</c:if>
					</span>
				</a>万元
				</span>
			</div>
		</div>
		<div class="col-md-4" style="height: 400px;">
			<div
				style="margin-top: 5px; font-size: 20px; height: 50px; line-height: 50px; font-weight: bolder; text-align: center; vertical-align: middle; background-color: rgb(1, 111, 184); color: #fff;">
				<span class="glyphicon glyphicon-check" style="font-size: 20px;"></span>
				到户资金
			</div>

			<a href="fundsGroup.jhtml?departId=${depart.id}" target="_blank">
				<img
				style="width: 100%; height: 200px; margin-top: 10px; margin-bottom: 20px; border: 5px solid #BEBEBE;"
				src="${ctx}/resources/portal/images/dhzj.jpg" alt="">
			</a>
			<div style="text-align: center;">
				<span style="font-size: 18px; font-weight: bolder; color: #2a333c">到户资金：共
					<a href="${ctx}/portal/fundsGroup.jhtml?departId=${depart.id}"><span
						style="margin-left: 5px; margin-right: 5px; color: red;"> <c:if
								test="${fundsSum!=null}">
								<fmt:formatNumber value="${fundsSum}" type="CURRENCY">
								</fmt:formatNumber>
							</c:if> <c:if test="${fundsSum==null}">
								0
						</c:if></span></a>万元
				</span>
			</div>
		</div>

	</div>

	<!-- 底部版权信息 -->
	<!-- 底部版权信息 -->
	<%@include file="../footer/footer.jsp"%>
</body>
</html>