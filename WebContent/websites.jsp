<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/MY-JS/websites.js"></script>
<s:if test="webInfos == null">
	<script type="text/javascript">
		showNoticeMSG("没有网址信息");
	</script>
</s:if>
<title>常用网站</title>
</head>
<body>
	<div class="container">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<jsp:include page="mynav.jsp"></jsp:include>
			<div class="row">
				<s:if
					test="#session.user != null && #session.user.hasBasePermission() ">
					<!-- 管理员组件 -->
					<jsp:include page="pageModules/admin_operate.html"></jsp:include>
					<!-- 显示模块 -->
					<div id="admin-operate" class="col-md-12  col-xs-12 text-center">
						<div class="col-md-10 col-md-offset-1 col-xs-12">
							<span class="label label-success">后台管理</span>
							<ul class="nav nav-tabs text-center">
								<li><a data-operate="delete">删除常用网站</a></li>
								<li><a data-operate="add">增加常用网站</a></li>
							</ul>
						</div>
					</div>
				</s:if>
				<div id="website-info" class="col-md-12 text-center"></div>
			</div>
			<s:iterator var="web" value="webInfos">
				<div class='col-xs-6 col-md-3' role="item">
					<div class=' col-xs-12 text-center'>
						<img src='<%=contextPath%>/<s:property value="#web.imgURL"/>'
							class='img-thumbnail image-rounded img-responsive' />
					</div>
					<div class='col-xs-12'>
						<h4 class='text-center'>
							<s:property value="#web.webName" />
						</h4>
					</div>
					<div class='webDesc col-xs-12'>
						<textarea class='margin-top-bottom-10px full-width' rows="5"
							disabled="disabled"><s:property value="#web.webDesc" /></textarea>
					</div>
					<div class='text- center col-xs-12'>
						<a target="_blank" type='button'
							class='margin-top-bottom-10px btn btn-info col-md-12 col-xs-12'
							href='<s:property value="#web.aimURL" />'>立即前往</a>
					</div>
				</div>
			</s:iterator>
		</div>
	</div>
</body>
</html>