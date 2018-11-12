<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>组织机构</title>
<asiainfo:resource needFallBack="false"
	type="Bootstrap,easyui,jquery,dynamicform"></asiainfo:resource>
<script src="${ctx}/resources/js/gov/org/edit.js"></script>

</head>
<body class="container body" style="background: #F7F7F7;">
	<div class="main_container">
		<div class="right_col" role="main" style="margin-left: 0px">
			<div style="margin: 20px 0;"></div>
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<form id="editOrg" action="${ctx}/org/save.jhtml" method="post"
						enctype="multipart/form-data">
						<table align="left" style="width: 95%; font-size: 12px;">
							<tr height=30>
								<td align="right" width="120px"><lable>组织类型：</lable></td>
								<td width="400px"><select id="type" name="type"
									onchange="loadTowns();">
										<option value="1"
											<c:if test="${depart.type==1}"> selected="selected"</c:if>>
											部门</option>
										<option value="2"
											<c:if test="${depart.type==2}"> selected="selected"</c:if>>苏木/乡镇</option>
										<option value="3"
											<c:if test="${depart.type==3}"> selected="selected"</c:if>>嘎查村</option>
								</select></td>

								<td width="20%"></td>

							</tr>
							<tr height=30>
								<td align="right" width="120px"><lable>上级组织：</lable></td>
								<td width="400px"><select id="parentId" name="parentId">
										<option value="">请选择</option>
										<c:forEach items="${parents}" var="parent">
											<option value="${parent.id}">${parent.text}</option>
										</c:forEach>
										<c:if test="${parent!=null}">
											<option value="${parent.id}" selected="selected">${parent.name}</option>
										</c:if>

								</select></td>

								<td width="20%"></td>

							</tr>


							<tr height=30>
								<td align="right" width="120px"><lable>组织名称：</lable></td>
								<td width="400px"><input type="text" id="name" name="name"
									maxlength="100" value="${depart.name}" style="width: 100%;" />
									<input type="hidden" id="id" name="id" value="${depart.id}" /></td>

								<td width="20%"></td>

							</tr>

							<tr height=30>
								<td align="right" width="120px"><lable>组织名称-别名：</lable></td>
								<td width="400px"><input type="text" id="alias"
									name="alias" maxlength="100" value="${depart.alias}"
									style="width: 100%;" /></td>
								<td width="20%"></td>

							</tr>

							<tr height=30>
								<td align="right" width="120px"><lable>主页背景图：</lable></td>
								<td width="400px"><input type="file" id="imgFile"
									name="imgFile" /></td>
								<td width="20%"></td>
							</tr>
						</table>
						<input type="hidden" id="parentMenuId"
							name="systemGuiMenu.parentMenuId"
							value="${systemMenuBean.systemGuiMenu.parentMenuId}">

						<div align="center"
							style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc; margin-top: 10px;">
							<a href="javascript:submitForm();" class="btn btn-success">保存</a>
							<a href="#" class="btn btn-success" onclick="#">取消</a>
						</div>

					</form>
				</div>
			</div>
		</div>
</body>
</html>