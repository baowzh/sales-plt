<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="icon" type="image/png" sizes="16x16"
	href="/ui2017/logo-16.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="/ui2017/logo-32.png">
<link rel="icon" type="image/png" sizes="48x48"
	href="/ui2017/logo-48.png">
<link href="${ctx}/resources/portal/css/style.css" rel="stylesheet">
<title>科尓沁左翼后旗三务公开</title>
<style>
body {
	background: url(${ctx}/resources/portal/images/nmqbkg.jpg) no-repeat top;
	background-size: 100%;
}

.Banners {
	width: 100%;
	padding: 0px;
	margin-top: 12%;
}

.bannerfont {
	padding: 15px 0;
	text-align: center;
}

.Nav01 {
	position: absolute;
	top: 70%;
	left: 0;
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: calc(100% - 60px);
	height: 60px;
	margin: 30px;
	font-size: 20px;
}

.Nav01 a {
	display: flex;
	justify-content: center;
	align-items: center;
	vertical-align: middle;
	width: 30%;
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
	background-color: #00000087;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display: none;
	position: absolute;
	top: 10%;
	left: 23%;
	width: 54%;
	height: 82%;
	border: 2px solid lightblue;
	background-color: white;
	border-radius: 15px;
	z-index: 1002;
	overflow: hidden;
	color: lightblue;
}

.white_content a {
	color: #23527c;
	text-decoration: none;
	text-align: center;
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
	width: 10px;
	height: 10px;
	padding: 8px;
	text-align: center;
	color: #5c656e;
}
</style>
</head>

<body>
	<div class="Banners">
		<div class="bannerfont">
			<img src="${ctx}/resources/portal/images/bannerfont.png"
				alt="科尓沁左翼后旗三务公开" width="" />
		</div>
	</div>
	<div class="Nav01">
		<a href="${ctx}/portal/main.jhtml" target="_blank"><span><img
				src="${ctx}/resources/portal/images/01ban.png" width="128"
				height="46" /></span><span>科尓沁左翼后旗</span></a> <a
			onclick="ShowDiv('MyDiv','fade')" href="javascript:void(0);"><span><img
				src="${ctx}/resources/portal/images/02ban.png" width="128"
				height="46" /></span><span>旗直部门</span> </a> <a
			onclick="ShowDiv('townDiv','fade')" href="javascript:void(0);"><span><img
				src="${ctx}/resources/portal/images/03ban.png" width="128"
				height="66" /></span><span>苏木镇场</span></a>
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
			<span class="bottom02" onclick="CloseDiv('MyDiv','fade')"><img
				src="${ctx}/resources/portal/images/close.jpg" /></span>
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
		style="width: 50%; height: 70%;">
		<div class="close" id="move">
			<span class="bottom02" onclick="CloseDiv('townDiv','fade')"><img
				src="${ctx}/resources/portal/images/close.jpg" /></span>
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