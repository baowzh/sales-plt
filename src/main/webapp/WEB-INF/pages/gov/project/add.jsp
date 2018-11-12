<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>编辑新闻</title>
<asiainfo:resource needFallBack="${needFallback}"
	type="easyui,jquery,dynamicform"></asiainfo:resource>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/easyUI/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/easyUI/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/easyUI/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div style="margin: 20px 0;"></div>

	<form id="addMenu" action="saveInfo.jhtml" method="post">
		<table align="left" style="width: 95%;">
			<tr height=30>
				<td align="right" width="120px">
				 <lable>栏目：</lable>
				</td>
				<td width="400px">
				  <input
					type="text" style="width:100%;" />
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>分类：</lable>
				</td>
				<td width="400px">
				  <input
					type="text" style="width:100%;" />
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>标题：</lable>
				</td>
				<td width="400px">
				  <input
					type="text" id="title" name="title" style="width:100%;" />
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>关键字：</lable>
				</td>
				<td width="400px">
				  <input
					type="text" id="title" name="title" style="width:100%;" />
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>摘要：</lable>
				</td>
				<td width="400px">
				  <textarea
					 id="title" name="title" style="width:100%;" rows=10 cols=300 >
					</textarea>
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>内容：</lable>
				</td>
				<td width="400px">
				  <textarea
					 id="content" name="content" style="width:100%;height:300px;" rows=100 cols=300 >
					</textarea>
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
		<input type="hidden" id="parentMenuId"
			name="systemGuiMenu.parentMenuId"
			value="${systemMenuBean.systemGuiMenu.parentMenuId}">

		<div align="center"
			style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc">
			<a href="javascript:void(0)" onclick="submitForm()"
				class="easyui-linkbutton" iconCls="icon-save">保存</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:window.close()">取消</a>
		</div>


	</form>


</body>
</html>