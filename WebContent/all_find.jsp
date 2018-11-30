<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript" src="<%=contextPath%>/MY-JS/all_find.js"></script>
<title>所有捡到的物品</title>
</head>
<body class="container">

	<s:if test="allFind == null">
		<script type="text/javascript">
			location.href = "<%=contextPath%>/userCenter/allFind";
		</script>
	</s:if>

	<s:if test="allFind.size == 0">
		<script type="text/javascript">
			showNoticeMSG("你暂未捡到任何物品");
		</script>
	</s:if>

	<div class="col-md-12 col-sm-12">
		<jsp:include page="mynav.jsp"></jsp:include>
		<div class="col-md-12 col-sm-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<td>失主姓名</td>
						<td>物品描述</td>
						<td>物品图片</td>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="item" value="allFind">
						<tr>
							<td><s:property value="#item.losterName" /></td>
							<td><s:property value="#item.goodDesc" /></td>
							<td><img class="img-thumbnail image-roundedimg-responsiveshow-image show-image"
								src='<%=contextPath%>\<s:property value="#item.imagePath" />'></td>
							<td><button type="button" class="btn btn-danger"
									onclick="deleteGood('<s:property value="#item.gid" />')">删除</button></td>

							<td><button type="button" class="btn btn-mybtn"
									onclick="findLoster('<s:property value="#item.gid" />')">已成功找到失主</button></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
