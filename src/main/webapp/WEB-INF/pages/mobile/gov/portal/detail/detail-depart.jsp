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
	background: url(${ctx}/resources/portal/images/bg.jpg) #f5f5f5 no-repeat top;
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
		</span>
	</div>
	<div class="new_li public_width">
		<div class="news_li2">
			<h3>${news.title}</h3>
			<h2>
				<span>发布时间：<fmt:formatDate
									value="${news.inputtime}" pattern="yyyy-MM-dd" /></span>  
			</h2>
			<div class="article-body oflow-hd">
				 ${news.data.content}
				</div>
		</div>
		
	</div>
	<!-- 底部版权信息 -->
	<%@include file="../footer/footer.jsp"%>
</body>

</html>