<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>删除导入数据</title>
<asiainfo:resource needFallBack="${needFallback}"
	type="Bootstrap,easyui,jquery,dynamicform"></asiainfo:resource>
</head>
<body class="container body" style="background: #F7F7F7;">
	<div class="main_container">
		<div class="right_col" role="main" style="margin-left: 0px">

			<form id="delForm" action="" method="post">
				<table align="left" style="width: 95%;">
					<tr height=30>
						<td align="right" width="120px"><lable>请选择批次：</lable></td>
						<td width="400px"><select name="bath" id="bath">
								<c:forEach items="${bathIds}" var="bath">
									<option value="${bath}">${bath}</option>
								</c:forEach>
						</select> <input type="hidden" name="departType" id="departType"
							value="${departType}" /></td>

						<td width="20%"></td>
					</tr>
				</table>
				<div align="center"
					style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc; margin-top: 10px;">
					<a href="javascript:del();" class="btn btn-success">删除</a> <a
						href="#" class="btn btn-success" onclick="#">返回</a>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
 var del=function(){
   	$.ajax({
			type : "POST",
			url : '${ctx}/project/delData.jhtml',
			data : {
				bathId : $('#bath').val()
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					alert('删除成功。');
					var departType=$('#departType').val();
					window.location.href= window.contextroot+'/project/'+departType+'/index.jhtml';
				} else {
					alert(data.mess);
				}
			},
			error : function(info) {
				console.log("连接异常，请检查！")
			}
		});
  
 }
</script>

</html>