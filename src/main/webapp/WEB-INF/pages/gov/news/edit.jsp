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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/gov/news/edit.js"></script>

</head>
<body>
	<div style="margin: 20px 0;"></div>

	<form id="editNews" action="save.jhtml" method="post" enctype="multipart/form-data">
		<table align="left" style="width: 95%;">
			<tr height=30>
				<td align="right" width="120px">
				 <lable>栏目：</lable>
				</td>
				<td width="400px">
				  <select id="catId" name="catId">
				  </select>
				  <script type="text/javascript">
					$(document).ready(function() {
						$('#catId').asiainfoSelect({
							id : '#catId',
							url : '${pageContext.request.contextPath}/ui/categorys.jhtml',
							listeners:[{selector:"#typeId",type:"select"}],
							defaultvalue:'${news.catId}'
						});
					});
				</script>
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>分类：</lable>
				</td>
				<td width="400px">
				   <select id="typeId" name="typeId">
				  </select>
				  <script type="text/javascript">
					$(document).ready(function() {
						$('#typeId').asiainfoSelect({
							id : '#typeId',
							url : '${pageContext.request.contextPath}/ui/subcategorys.jhtml',
							filterfields:[{filedname:'#catId',sqlfieldname:'catId'}]
							,
							defaultvalue:'${news.typeId}'
						});
					});
				</script>
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>标题：</lable>
				</td>
				<td width="400px">
				  <input
					type="text" id="title" name="title" value="${news.title}" style="width:100%;" />
					<input
					type="hidden" id="id" name="id" value="${news.id}"  />
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>关键字：</lable>
				</td>
				<td width="400px">
				  <input
					type="text" id="keywords" name="keywords" value="${news.keywords}" style="width:100%;" />
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>封面图：</lable>
				</td>
				<td width="400px">
				  <input
					type="file" id="thumb" name="thumb"  style="width:100%;" />
				</td>
				
				<td width="20%"></td>

			</tr>
			
			<tr height=30>
				<td align="right" width="120px">
				 <lable>摘要：</lable>
				</td>
				<td width="400px">
				  <textarea
					 id="description" name="description" style="width:100%;" rows=10 cols=300 >
					 ${news.description}
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
					 id="content" name="content" style="width:100%;height:270px;" rows=90 cols=300 >
					 ${news.data.content}
					</textarea>
				</td>
				
				<td width="20%"></td>
                 <script type="text/javascript">
					$(document).ready(function() {
						$('#content').asiainfoEditor({
							id : '#content',
							readonly : false,
							height:600					
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
				onclick="#">取消</a>
		</div>


	</form>


</body>
</html>