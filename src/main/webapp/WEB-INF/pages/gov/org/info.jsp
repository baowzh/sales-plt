<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../../public/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>组织机构信息</title>
<asiainfo:resource needFallBack="false"
	type="Bootstrap,easyui,jquery,dynamicform"></asiainfo:resource>
<script src="${ctx}/resources/js/jquery-ui-1.12.0/jquery-ui.js"></script>
<link rel="stylesheet"
	href="${ctx}/resources/js/uploadify/css/uploadify.css" type="text/css">
<script type="text/javascript"
	src="${ctx}/resources/js/uploadify/jquery.uploadify.min.js"></script>
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
								<td width="400px"><c:choose>
										<c:when test="${depart.type==1}">
                                                                                                                部门
										</c:when>
										<c:when test="${depart.type==2}">
                                                                                                                       苏木/乡镇
										</c:when>
										<c:when test="${depart.type==3}">
                                                                                                                   嘎查村                                                                             
										</c:when>
										<c:otherwise>
                                                                                                              其他
										</c:otherwise>
									</c:choose></td>

								<td width="20%"></td>

							</tr>

							<tr height=30>
								<td align="right" width="120px"><lable>组织名称：</lable></td>
								<td width="400px">${depart.name} <input type="hidden"
									id="id" name="id" value="${depart.id}" />
								</td>
								<td width="20%"></td>
							</tr>

							<tr height=30>
								<td align="right" width="120px"><lable>组织名称：</lable></td>
								<td width="400px">${depart.name} <input type="hidden"
									id="id" name="id" value="${depart.id}" />
								</td>
								<td width="20%"></td>
							</tr>

							<tr height=30>
								<td align="right" width="120px"><lable>组织名称-别名：</lable></td>
								<td width="400px">${depart.alias}</td>
								<td width="20%"></td>
							</tr>

							<tr height=30>
								<td align="right" width="120px"><lable>主页背景图：</lable></td>
								<td width="400px"><img src="${ctx}/${depart.img}"
									style="margin-bottom: 10px;" /></td>
								<td width="20%"></td>
							</tr>
						</table>


						<div align="center"
							style="padding: 4px; background: #fafafa; width: 99%; border: 0px solid #ccc; margin-top: 20px;">
							<a href="javascript:edit('${depart.id}');"
								class="btn btn-success">修改</a> <a
								href="javascript:del('${depart.id}');" class="btn btn-success">删除</a>

						</div>


					</form>
				</div>
			</div>
		</div>
</body>
</html>