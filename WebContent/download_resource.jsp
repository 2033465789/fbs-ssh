<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript" src="<%=contextPath%>/MY-JS/sharedResource.js"></script>

<title>资源共享</title>
</head>
<body>
	<s:if test="shared == null">
		<script type="text/javascript">
			location.href = "<%=contextPath%>/sharedResource/download";
		</script>
	</s:if>

	<s:if test="shared.size == 0">
		<script type="text/javascript">
			showNoticeMSG("暂无资源");
		</script>
	</s:if>
	<!-- 文件显示栏 -->
	<!--  导航栏 -->
	<div class="container">
		<jsp:include page="mynav.jsp"></jsp:include>
		<div class="row">
			<!-- 搜索栏 -->
			<div class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1">
				<form role="form">
					<div class="input-group">
						<input style='display: none'> <input type="text" class="form-control" id="search-info"
							placeholder="资源信息"> <span class="input-group-btn"><input type="button"
							class="btn btn-search" value="搜索" onclick="search()"></span>
					</div>
				</form>
			</div>
			<div class="text-center">
				<s:iterator var="e" value="shared">
					<s:if test="#e.fileType == 'image'">
						<s:set var="type" value="'图片'"></s:set>
					</s:if>
					<s:elseif test="#e.fileType == 'application'">
						<s:set var="type" value="'软件'"></s:set>
					</s:elseif>
					<s:elseif test="#e.fileType == 'resource'">
						<s:set var="type" value="'资源'"></s:set>
					</s:elseif>
					<s:elseif test="#e.fileType == 'video'">
						<s:set var="type" value="'音频'"></s:set>
					</s:elseif>
					<s:else>
						<s:set var="type" value="'其他'"></s:set>
					</s:else>

					<div class="file-container col-md-offset-1 col-md-5 col-sm-5 col-xs-5">
						<div data-file='<s:property value="#e.id" />'>
							<div class="full-width margin-top-bottom-10px auto-overflow">
								<span class="label label-file-name"> <s:property value="#e.fileName" />
								</span>
							</div>
							<div class="full-width margin-top-bottom-10px">
								<span class="label label-info">文件类型:<s:property value="#type" /></span> <span
									class="label label-warning"> 上传作者: <s:property value="#e.uploadUser" /></span> <span
									class="label label-info"> 上传时间: <s:date name="#e.uploadTime" format="yyyy-MM-dd" />
								</span>
							</div>
							<div class="full-width margin-top-bottom-10px">
								<textarea rows="3" class="file-desc full-width text-center" disabled="disabled"><s:property value="#e.fileDesc"/></textarea>
							</div>
						</div>
						<div class="full-width margin-top-bottom-10px">
							<button type="button" class="btn btn-download"
								onclick="download('<s:property value="#e.filePath"/>')">立即下载</button>
						</div>
					</div>
				</s:iterator>
			</div>
			<div class="text-center full-width">
				<s:if test="itemCount/pageSize>0">
					<ul class="pagination text-center">
						<s:iterator var="i" begin="0" end="itemCount/pageSize">
							<s:if test="page == #i">
								<li class="active"><a
									href="<%=contextPath%>/sharedResource/download?page=<s:property value="#i+1" />"><s:property
											value="#i+1" /></a></li>
							</s:if>
							<s:else>
								<li><a href="<%=contextPath%>/sharedResource/download?page=<s:property value="#i+1" />"><s:property
											value="#i+1" /></a></li>
							</s:else>
						</s:iterator>
					</ul>
				</s:if>
			</div>
		</div>
	</div>
</body>
</html>