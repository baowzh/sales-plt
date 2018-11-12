<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>编辑新闻</title>
<asiainfo:resource needFallBack="${needFallback}"
	type="Bootstrap,easyui,jquery,dynamicform"></asiainfo:resource>

</head>
<body class="container body" style="background: #F7F7F7;">
	<div class="main_container">
		<div class="right_col" role="main" style="margin-left: 0px">

			<form id="import" action="upload.jhtml" method="post" enctype="multipart/form-data">
				<table align="left" style="width: 95%;">
					<tr height=30>
						<td align="right" width="120px"><lable>excel文件：</lable></td>
						<td width="400px"><input type="file" name="excelFile"
							id="excelFile" style="width: 100%;" /></td>

						<td width="20%"></td>

					</tr>

					<tr height=30>
						<td align="right" width="120px"><lable>项目数据导入模板文件：</lable></td>
						<td width="400px"><a href="${ctx}/attached/funds/town.xlsx" download="导入到户资金信息_乡镇导入模板.xlsx">请点击下载</a></td>

						<td width="20%"></td>

					</tr>



					<tr height=30>
						<td align="right" width="120px"><lable>导入说明：</lable></td>
						<td width="400px">
							<div>请准确完整填写嘎查村名称，否则无法导入。</div>
						</td>

						<td width="20%"></td>
						<script type="text/javascript">
							$(document).ready(function() {
								$('#content').asiainfoEditor({
									id : '#content',
									readonly : false,

								});
							});
						</script>
					</tr>

				</table>
				
				<div align="center"
					style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc; margin-top: 10px;">
					<a href="javascript:$('#import').submit();" class="btn btn-success">提交</a> <a
						href="#" class="btn btn-success" onclick="#">返回</a>
				</div>


			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
 var success='${success}';
 if(success=='true'){
   alert('${mess}');
   window.location.href='${ctx}/funds/town/index.jhtml';
 }else if(success=='false'){
  alert('${mess}');
 }
</script>
</html>