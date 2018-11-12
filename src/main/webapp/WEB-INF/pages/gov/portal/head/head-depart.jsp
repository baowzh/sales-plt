<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="top departzy">
	<div class="top-dy">
		<div class="logo">
			<a href="#">
				<div class="depart">${depart.name}</div>
			</a>
		</div>
	</div>
</div>
<div class="lt-p-header">
	<div class="headnav">
		<div class="lx-nav">
			<div class="nav1-p t1" style="background-position: 0px 0px;">
				<a href="${ctx}/portal/main.jhtml?departId=${depart.id}">网站首页</a>
			</div>
		</div>
		<div class="lx-nav">
			<div class="nav1-p t3" style="background-position: 0px 0px;">
				<a href="${ctx}/portal/news/list.jhtml?catId=18&departId=${depart.id}">党务公开</a>
			</div>
		</div>
		<div class="lx-nav">
			<div class="nav1-p t4" style="background-position: 0px 0px;">
				<a href="${ctx}/portal/news/list.jhtml?catId=19&departId=${depart.id}">政务公开</a>
			</div>
		</div>
		<div class="lx-nav">
			<div class="nav1-p t5" style="background-position: 0px 0px;">
				<a href="${ctx}/portal/mszj.jhtml?departId=${depart.id}">民生项目</a>
			</div>
		</div>
		<div class="lx-nav">
			<div class="nav1-p t5" style="background-position: 0px 0px;">
				<a href="${ctx}/portal/news/list.jhtml?catId=20&departId=${depart.id}">办事依据</a>
			</div>
		</div>

		<div class="lx-nav">
			<div class="nav1-p t7" style="background-position: 0px 0px;">
				<a href="${ctx}/portal/news/list.jhtml?catId=27&departId=${depart.id}">监督举报</a>
			</div>
		</div>
	</div>
</div>
