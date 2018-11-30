<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript" src="<%=contextPath%>/MY-JS/user.js"></script>
<title>个人中心</title>
</head>
<body class="container">
	<s:if test="#session.user == null">
		<script type="text/javascript">
			show_login('<%=contextPath%>');
		</script>
	</s:if>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="col-md-12 col-sm-12"><jsp:include page="mynav.jsp"></jsp:include></div>
		<div
			class="col-md-12 col-sm-12 text-center border-blue background-gray img-thumbnail image-rounded img-responsive"
			data-user="data-user">
			<a href="<%=contextPath%>\userCenter\allFind">我捡到的所有物品</a>
		</div>

		<div
			class="col-md-12 col-sm-12 text-center border-blue background-gray img-thumbnail image-rounded img-responsive"
			data-user="data-user">
			<a href="<%=contextPath%>\userCenter\allShared">我分享的资源</a>
		</div>

		<div
			class="col-md-12 col-sm-12 text-center border-blue background-gray img-thumbnail image-rounded img-responsive"
			data-user="data-user">
			<a href="<%=contextPath%>\userCenter\dormitoryinfo_alter">修改个人信息</a>
		</div>
	</div>
</body>
</html>
