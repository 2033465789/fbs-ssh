<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript" src="<%=contextPath%>/MY-JS/admin.js"></script>

<title>网站管理</title>
</head>
<body>
	<s:if test="#session.user == null">
		<jsp:forward page="/"></jsp:forward>
	</s:if>
	<s:if test="connPermission == 1">
		<s:set var="dbStatus" value="'开放连接中......'"></s:set>
	</s:if>

	<s:elseif test="connPermission == 2">
		<s:set var="dbStatus" value="'仅管理员可以连接......'"></s:set>
	</s:elseif>

	<s:elseif test="connPermission == 3">
		<s:set var="dbStatus" value="'仅超级管理员管理员可以连接......'"></s:set>
	</s:elseif>
	<s:else>
		<s:set var="dbStatus" value="'错误......'"></s:set>
	</s:else>
	<div class="container">
		<jsp:include page="mynav.jsp" />
		<div class="row">
			<div id="db-op" class="col-md-12 admin-module">
				<h3>
					<span>数据库状态:<s:property value="dbStatus" /></span>
				</h3>
				<button id="close-dbconn" class="btn btn-danger col-md-6 col-xs-12">禁止数据库连接</button>
				<button id="open-dbconn" class="btn btn-warning col-md-6 col-xs-12">开放数据库连接</button>
			</div>
			<!-- 其他模块 -->
			<div></div>
			<div id="db-op" class="col-md-12 admin-module">
				<h3>
					<span class="col-md-12 col-sm-12">网站访问人次: <s:property
							value="visiteNum" />
					</span>
				</h3>
			</div>
		</div>
	</div>
</body>
</html>
