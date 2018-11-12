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

	<%@include file="../head/head-depart.jsp"%>
	<div class="new_tb">
		<a href="${ctx}/portal/main.jhtml?departId=${depart.id}"> 首页 </a> > <a
			href="${ctx}/portal/news/list.jhtml?catId=${parentCateGory.id}&departId=${depart.id}">
			${parentCateGory.name} </a> > <span> ${currentCategory.name} </span>
	</div>
	<c:if test="${not empty childs}">
		<div class="middle-c row">
			<div class="ysqg-box-left col-sm-12 col-xs-12">
				<div class="yleft-a1">
					<span> ${parentCateGory.name} </span>
				</div>
				<div class="yleft-a2">
					<ul>
						<c:forEach items="${childs}" var="item">
							<li><a style="
    float: left;"
								href="${ctx}/portal/news/list.jhtml?catId=${item.id}&departId=${depart.id}"
								class="ygzlc">${item.name} <span style="margin-top: 15px;
    float: right;padding-left:0px;"> <img
										src="${ctx}/resources/portal/images/jt.png" width="6"
										height="12" />
								</span>
							</a>
						</c:forEach>

						</li>

					</ul>

				</div>
			</div>
		</div>
	</c:if>


	<div class="middle-c row">
		<div class="main-content-left1">
			<div class="zfbm-bt">
				<span> ${currentCategory.name} </span>
			</div>
			<div class="new_x">
				<ul>
					<c:forEach items="${news}" var="item">
						<li><a style="width:65%;padding-left: 10px;"
							href="${ctx}/portal/news/detail.jhtml?id=${item.id}&departId=${depart.id}">
								${item.title} </a> <span style="width:30%"> <fmt:formatDate
									value="${item.inputtime}" pattern="yyyy-MM-dd" />
						</span></li>
					</c:forEach>
				</ul>

			</div>
			<asiainfo:page pagesize="10" pageindex="${pageindex}" mobile="true"
				url="${ctx}/portal/news/list.jhtml?catId=${currentCategory.id}&departId=${depart.id}"
				totalRecord="${totalRecord}" />
			<script>
				function tabNav(num) {
					for (var i = 1; i < 3; i++) {
						document.getElementById('cnt_' + i).style.display = 'none';
					}
					document.getElementById('cnt_' + num).style.display = 'block';
				}
			</script>
			<script src="${ctx}/resources/portal/js/jquery-1.11.0.min.js">
				
			</script>
		</div>

	</div>
	<!-- 底部版权信息 -->
	<%@include file="../footer/footer.jsp"%>
</body>

</html>