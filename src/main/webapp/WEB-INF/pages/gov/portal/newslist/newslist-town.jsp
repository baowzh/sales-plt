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

	<%@include file="../head/head-town.jsp"%>
	<div class="new_tb">
		<a href="${ctx}/portal/index.jhtml?departId=${depart.id}"> 首页 </a> > <a
			href="${ctx}/portal/news/list.jhtml?catId=${parentCateGory.id}&departId=${depart.id}">
			${parentCateGory.name} </a> > <span> ${currentCategory.name} </span>
	</div>
	<div class="middle-c">
		<div class="main-content-left1">
			<div class="zfbm-bt">
				<span> ${currentCategory.name} </span>
			</div>
			<div class="new_x">
				<ul>
					<c:forEach items="${news}" var="item">
						<li><a
							href="${ctx}/portal/news/detail.jhtml?id=${item.id}&departId=${depart.id}">
								${item.title} </a> <span> <fmt:formatDate
									value="${item.inputtime}" pattern="yyyy-MM-dd" />
						</span></li>
					</c:forEach>


				</ul>
			</div>
			<asiainfo:page pagesize="10" pageindex="${pageindex}"
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
		<div class="ysqg-box-left">
			<div class="yleft-a1">
				<span> ${parentCateGory.name} </span>
			</div>
			<div class="yleft-a2">
				<ul>
					<c:forEach items="${childs}" var="item">
						<li><a
							href="${ctx}/portal/news/list.jhtml?catId=${item.id}&departId=${depart.id}"
							class="ygzlc">${item.name} <span> <img
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
	<!-- 底部版权信息 -->
	<%@include file="../footer/footer.jsp"%>
</body>

</html>