<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String basePath = request.getContextPath();
%>
<nav class="navbar navbar-default" role="navigation">
	<div class="navber-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#my-collapse">
			<span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a href="<%=basePath%>/main" class="navbar-brand text-center">PiaoPiao</a>
	</div>

	<div class="collapse navbar-collapse" id="my-collapse">
		<ul class="nav navbar-nav">
			<!-- 失物招领 -->
			<li class="dropdown"><a href="" class="dropdown-toggle" data-toggle="dropdown">失物招领 <b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="<%=basePath%>/finder">我捡到物品</a></li>
					<li><a href="<%=basePath%>/loster">我丢失物品</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toogle" data-toggle="dropdown">资源共享 <b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="<%=basePath%>/upload_resource" id="upload-resource">上传资源</a></li>
					<li><a href="<%=basePath%>/sharedResource/download" id="download-resource">下载资源</a></li>
				</ul></li>
			<li><a href="<%=basePath%>/website/allWebsite">常用网址</a></li>
		</ul>

		<s:if test="#session.user == null">
			<ul class="nav navbar-nav navbar-right margin-right-5px">
				<li><a href="<%=basePath%>/signup.html"><span class="glyphicon glyphicon-user"></span>
						注册</a></li>
				<li><a onclick="show_login('<%=basePath%>')"><span class="glyphicon glyphicon-log-in"></span>
						登录</a></li>
			</ul>
		</s:if>
		<s:else>
			<ul class="nav navbar-nav navbar-right margin-right-5px">
				<li class="dropdown"><a class="dropdown-toggle nav-background" data-toggle="dropdown">
						<span class="glyphicon glyphicon-user"></span> 账户<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<%=basePath%>/user_center" target="_blank">个人中心</a></li>
						<s:if test="#session.user.hasBasePermission()">
							<li><a id="admin-operation-center" href="<%=basePath%>/admin/websiteBackground">网站后台</a></li>
						</s:if>
						<li class="divider"></li>
						<li><a id="log-out" onclick="logOut('<%=basePath%>')">注销</a></li>
					</ul></li>
			</ul>
		</s:else>
	</div>
</nav>
