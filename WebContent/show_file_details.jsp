<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>

<!DOCTYPE HTML>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript"
	src="<%=contextPath%>/MY-JS/sharedResource.js"></script>
</head>

<title>资源详情</title>

<body class="container">
	<s:if test="file == null">
		<script>
			location.href = "<%=contextPath%>";
		</script>
	</s:if>
	<s:if test="#session.user == null">
		<script type="text/javascript">
			show_login('<%=contextPath%>');
		</script>
	</s:if>
	<s:set var="type" value="'其他'"></s:set>

	<s:elseif test="file.fileType == 'image'">
		<s:set var="type" value="'图片'"></s:set>
	</s:elseif>
	<s:elseif test="file.fileType == 'application'">
		<s:set var="type" value="'应用'"></s:set>
	</s:elseif>
	<s:elseif test="file.fileType == 'resource'">
		<s:set var="type" value="'资源'"></s:set>
	</s:elseif>
	<s:elseif test="file.fileType == 'video'">
		<s:set var="type" value="'音频'"></s:set>
	</s:elseif>

	<div class="row">
		<!-- 文件信息 -->
		<div class="text-center">
			<div data-file='<s:property value="file.id"/>'>
				<div class="col-md-12 col-sm-12 margin-top-bottom-10px">
					<span class="label label-file-name margin-top-bottom-10px"><s:property
							value="file.fileName" /> </span>
				</div>

				<div class="col-md-3 col-sm-3 margin-top-bottom-10px">
					<span class="label label-info">文件类型:<s:property
							value="#type" /></span>
				</div>

				<div class="col-md-4 col-sm-3 margin-top-bottom-10px">
					<span class="label label-warning">上传作者: <s:property
							value="file.uploadUser" /></span>
				</div>

				<div class="col-md-3 col-sm-3 margin-top-bottom-10px">
					<span class="label label-info">上传时间: <s:date
							name="file.uploadTime" format="yyyy-MM-dd" /></span>
				</div>

				<div class="col-md-12 col-sm-12 margin-top-bottom-10px">
					<div class="file-desc margin-top-bottom-10px">
						<s:property value="file.fileDesc" />
					</div>
				</div>
			</div>
			<!-- 下载按钮 -->
			<div class="col-md-12 col-sm-12 margin-top-bottom-10px">
				<button type="button"
					onclick="download('<s:property value="file.filePath"/>')"
					class="btn btn-download margin-top-bottom-10px">立即下载</button>
			</div>
		</div>
		<!-- 评论面板 -->
		<div id="comments-panel" class="col-md-12 col-sm-12">
			<span>评论数：<s:property value="file.comments.size" />
			</span>
			<table class="table table-hover col-md-12 col-sm-12">
				<s:iterator var="comment" value="file.comments">
					<tr class="comment-item" id='<s:property value="#comment.id"/>'>
						<td class="comment-user-info">
							<div>
								<span class="margin-right-10px"><b> <s:property
											value="#comment.uid" /></b></span> <span> <s:property
										value="#comment.createTime" /></span>
							</div> <span><s:property value="#comment.content" /></span>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

		<!-- 发表评论 -->
		<div class="col-md-12 col-sm-12">
			<textarea rows="4" class="margin-top-bottom-10px"
				id="comment-content" style="width: 100%"></textarea>
			<button type="button" class="col-md-12 col-sm-12 btn btn-success"
				style="width: 100%" id="make-comment">发表评论</button>
		</div>
	</div>
</body>
</html>
