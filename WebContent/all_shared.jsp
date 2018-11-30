<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/MY-JS/all_shared.js"></script>
<title>分享清单</title>
</head>
<body class="container">
	<s:if test="#session.user == null">
		<script type="text/javascript">
			show_login('<%=contextPath%>');
		</script>
	</s:if>
	<s:if test="allShared == null">
		<script type="text/javascript">
			location.href = '<%=contextPath%>\\userCenter\\allShared';
		</script>
	</s:if>
	<s:elseif test="allShared.size == 0">
		<script type="text/javascript">
			showNoticeMSG("你暂未分享任何资源");
		</script>
	</s:elseif>

	<div class="col-md-12 col-sm-12">
		<jsp:include page="mynav.jsp"></jsp:include>
		<div class="col-md-12 col-sm-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<td>文件名</td>
						<td>上传时间</td>
						<td>文件类型</td>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="item" value="allShared">
						<tr>
							<td><s:property value="#item.fileName" /></td>
							<td><s:property value="#item.uploadTime" /></td>
							<td><s:property value="#item.fileType" /></td>
							<td><button type="button" class="btn btn-danger "
									onclick="deleteShared('<s:property value="#item.id" />')">删除</button></td>
							<td><button type="button" class="btn btn-mybtn"
									onclick="alterShared'<s:property value="#item.id" />')">修改</button></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
