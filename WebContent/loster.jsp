<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript" src="<%=contextPath%>/MY-JS/loster.js"></script>

<title>搜索</title>
</head>

<body>
	<s:if test="#session.user == null">
		<script type="text/javascript">
			show_login('<%=contextPath%>');
		</script>
	</s:if>

	<div class="container">
		<!--  导航  -->
		<jsp:include page="mynav.jsp"></jsp:include>
		<div class="row">
			<form role="form">
				<input style="display: none">
				<div class="input-group form-group col-md-8 col-md-offset-2 col-sm-9 col-sm-offset-1">
					<input type="text" name="searchInfo" class="form-control" id="search-info" placeholder="丢失物品的信息"> <span class="input-group-btn"> <input
						type="button" id="search" class="btn btn-search" value="搜索"
					></span>
				</div>
				<div id="show-lost-goods" class="table-responsive form-grounp"></div>
			</form>
		</div>
	</div>
</body>

</html>