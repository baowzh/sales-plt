<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link href="${ctx}/resources/portal/mobile/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/resources/portal/mobile/css/style.css"
	rel="stylesheet" type="text/css">
<title>科尓沁左翼后旗三务公开</title>
<style type="text/css">
body {
	background: url(${ctx}/resources/portal/mobile/images/nmqbkg.jpg) 0 0
		no-repeat scroll transparent;
	padding: 0;
	margin: 0;
	background-size: cover;
}

.Banners {
	width: 100%;
	padding: 0px;
	margin-top: 11%;
	heigth:100px;
}

.Banners img{
width: 100%;
 height:90px; 
}

.bannerfont {
	padding: 15px 0;
	text-align: center;
}

.Nav01 {
   position:fixed;
	top: 50%;
	left: 0;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 0 3%;
	font-size: 17px;
}

.Nav01 a {
	display: flex;
	justify-content: center;
	align-items: center;
	vertical-align: middle;
	font-weight: bold;
}

.Nav01 a span {
	display: flex;
	flex: 1;
	justify-content: center;
	vertical-align: middle;
	align-items: center;
	color: #fff;
}

@media ( max-width : 769px) {
	.Nav01 {
	    position:fixed;
		display: block;
		overflow: hidden;
		text-align: center;
		margin: 0 3%;
		top: 50%;
		font-size: 17px;
	}
	.Nav01 a {
		width: 100%;
		margin: 8px 0;
	}
}

.Nav01 span:nth-child(even) {
	background: rgba(0, 74, 142, 0.6);
	height: 60px;
	border-radius: 0 5px 5px 0;
}

.Nav01 span:nth-child(odd) {
	background: rgba(219, 253, 221, 0.6);
	height: 60px;
	border-radius: 5px 0 0 5px;
	color: #23527c;
}

/* 弹出层效果 */
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display: none;
	position: fixed;
	top: 5%;
	left: 10%;
	width: 80%;
	height: 80%;
	border: 2px solid lightblue;
	background-color: white;
/* 	border-radius: 15px; */
	z-index: 1002;
	overflow: hidden;
	color: lightblue;
}

.white_content a {
	color: #23527c;
	text-decoration: none;
	text-align: left;
	margin-left:20px;
}

.white_content span {
	display: inline-block;
}

.close {
	text-align: right;
	cursor: default;
	height: 28px;
}

.bottom02 {
	width: 20px;
	height: 20px;
/* 	padding: 8px; */
	text-align: center;
	color: #5c656e;
}
</style>
</head>

<body>
	<div class="Banners">
		<div class="bannerfont">
			<img src="${ctx}/resources/portal/mobile/images/bannerfont.png"
				alt="科尓沁左翼后旗三务公开" width="" />
		</div>
	</div>
	<div class="Nav01 row">
		<a class="col-sm-12 col-xs-12" href="${ctx}/portal/main.jhtml" target="_blank"><span><img width="90"
				src="${ctx}/resources/portal/mobile/images/01ban.png" 
				height="38" /></span><span>科尓沁左翼后旗</span></a> <a class="col-sm-12 col-xs-12"
			onclick="ShowDiv('MyDiv','fade')" href="javascript:void(0);"><span><img width="90"
				src="${ctx}/resources/portal/mobile/images/02ban.png" 
				height="38"  /></span><span>旗直部门</span> </a> <a class="col-sm-12 col-xs-12"
			onclick="ShowDiv('townDiv','fade')" href="javascript:void(0);"><span><img width="90"
				src="${ctx}/resources/portal/mobile/images/03ban.png" 
				height="42"  /></span><span>苏木镇场</span></a>
	</div>
	<!-- 弹出层js开始 -->
	<script type="text/javascript">
		//弹出隐藏层
		function ShowDiv(show_div, bg_div) {
			document.getElementById(show_div).style.display = 'block';
			document.getElementById(bg_div).style.display = 'block';
			var bgdiv = document.getElementById(bg_div);
			bgdiv.style.width = document.body.scrollWidth;
			// bgdiv.style.height = $(document).height();
			//$("#" + bg_div).height($(document).height());
		};
		//关闭弹出层
		function CloseDiv(show_div, bg_div) {
			document.getElementById(show_div).style.display = 'none';
			document.getElementById(bg_div).style.display = 'none';
		};
	</script>
	<!-- 弹出层js结束 -->
	<div id="fade" class="black_overlay"></div>
	<div id="MyDiv" class="white_content">
		<div class="close" id="move">
			<span class="bottom02" onclick="CloseDiv('MyDiv','fade')">
			 <a style="font-size:26px;margin-left:0px;" href="#">X</a>
<!-- 			<img -->
<%-- 				src="${ctx}/resources/portal/mobile/images/close.jpg" /> --%>
				
				</span>
		</div>
		<div style="height: 100%; overflow: scroll; margin: 0 0 10px 10px;">
			<ul>
				<li style="height: 35px; line-height: 35px; list-style-type: none;">
					<c:forEach items="${departs}" var="depart">
						<a href="${ctx}/portal/main.jhtml?departId=${depart.id}"
							style="width: 180px; float: left; font-size: 18px; ext-overflow: ellipsis; white-space: nowrap; overflow: hidden;"
							target="_blank">${depart.name}</a>
					</c:forEach>


				</li>
			</ul>
		</div>
	</div>

	<div id="townDiv" class="white_content"
		>
		<div class="close" id="move">
			<span class="bottom02" onclick="CloseDiv('townDiv','fade')">
			<a style="font-size:26px;margin-left:0px;" href="#">X</a>
			<!--  
			<img
				src="${ctx}/resources/portal/mobile/images/close.jpg" />
				-->
				</span>
		</div>
		<div style="height: 100%; overflow: scroll; margin: 0 0 10px 10px;">
			<ul>
				<li style="height: 35px; line-height: 35px; list-style-type: none;">
					<c:forEach items="${towns}" var="depart">
						<a href="${ctx}/portal/main.jhtml?departId=${depart.id}"
							style="width: 120px; float: left; font-size: 18px; ext-overflow: ellipsis; white-space: nowrap; overflow: hidden;"
							target="_blank">${depart.name}</a>
					</c:forEach>


				</li>
			</ul>
		</div>
	</div>
	<!-- 弹出层结束 -->
	 <audio id="music" src="${ctx}/resources/portal/backgrouond-music.mp3" hidden="true" controls="controls" autoplay="autoplay" loop="loop">
        你的浏览器不支持声音播放
    </audio>
	
</body>

</html>