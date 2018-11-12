<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻管理</title>
<asiainfo:resource needFallBack="false"
	type="Bootstrap,easyui,jquery,dynamicform,validate"></asiainfo:resource>
<script src="${ctx}/resources/js/formatter/decimalFormat.js"></script>
<script src="${ctx}/resources/js/dateFormatter.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/gov/news/index.js"></script>
</head>
<body class="container body" style="background: #F7F7F7;">
	<div class="main_container">
		<div class="right_col" role="main" style="margin-left: 0px">
			<input type="hidden" name="openParams" id="openParams" value="{}">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="search clearfix">
						<div id="search-form" style="display: block;">
							<form id="form1" class="form-horizontal form-label-left">
								<div class="form-group">
									<label for="title"
										class="control-label  col-md-1 col-sm-4 col-xs-12">标题：</label>
									<div class=" col-md-3 col-sm-8 col-xs-12 ">
										<input id="title" name="title"
											class="form-control col-md-12 col-sm-12 col-xs-12" />

									</div>
									<label for="keyword"
										class="control-label  col-md-1 col-sm-4 col-xs-12">关键字：</label>
									<div class=" col-md-3 col-sm-8 col-xs-12 ">
										<input id="keyword" name="keyword"
											class="form-control col-md-12 col-sm-12 col-xs-12" />

									</div>

									<label for="keyword"
										class="control-label  col-md-1 col-sm-4 col-xs-12">摘要：</label>
									<div class=" col-md-3 col-sm-8 col-xs-12 ">
										<input id="description" name="description"
											class="form-control col-md-12 col-sm-12 col-xs-12" />

									</div>

								</div>
								<div class="form-group">
									<label for="title"
										class="control-label  col-md-1 col-sm-4 col-xs-12">时间(开始)</label>
									<div class=" col-md-3 col-sm-8 col-xs-12 ">
										<input id="startDate" name="startDate"
											class="form-control col-md-12 col-sm-12 col-xs-12" />

									</div>
									<label for="keyword"
										class="control-label  col-md-1 col-sm-4 col-xs-12">时间(结束)</label>
									<div class=" col-md-3 col-sm-8 col-xs-12 ">
										<input id="endDate" name="endDate"
											class="form-control col-md-12 col-sm-12 col-xs-12" />

									</div>
									<input type="button" class="btn btn-success select-btn-r"
										onclick="doSearch();return false;" value="执行查询"></input>



								</div>

								<div class="errormessage"></div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			  <div class="col-md-12 col-sm-12 col-xs-12">
			  <a class="btn btn-success select-btn-r" style="float:right;width:100px;height:30px;"
										href="javascript:cancelTop();" >
										取消推送</a>
			   <a class="btn btn-success select-btn-r" style="float:right;width:60px;height:30px;"
										href="javascript:toTop();" >
										推送</a>
				
			   <a class="btn btn-success select-btn-r" style="float:right;width:60px;height:30px;"
										href="edit.jhtml" >
										新增</a>
			  
			   
			  </div>
			</div>
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<table id="dg">
						<div id="tb" style="padding: 2px 5px;">
							<a href="javascript:download_111();void(0);" title="导出"> <span
								class="l-btn-left l-btn-icon-left" id="export"> <i
									class="fa fa-save menu-ico" style="font-size: 16px"> </i>导出
							</span>
							</a>
						</div>
					</table>

				</div>
			</div>

			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12" style="background: none;"></div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
 var mess='${mess}';
 if(mess!=''&&mess!='null'){
   alert(mess);
 }
</script>


</html>