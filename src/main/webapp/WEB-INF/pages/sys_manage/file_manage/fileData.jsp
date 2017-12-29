<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<div class="col-lg-12">
	<div class="panel">
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table-container table table-hover table-striped" id="fileTable">
					<thead>
						<tr>
							<th>文件编号</th>
							<th>文件名称</th>
							<th>文件类型</th>
							<th>文件路径</th>
							<th>上传时间</th>
							<th>图片预览</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="file" items="${page.resultList }" varStatus="ind">
							<tr>
								<td>${file.id}</td>
								<td>${file.fileName}</td>
								<td>
									<c:forEach items="${filedList}" var="field">
										  <c:if test="${field['dicValue'] == file.fileType}">   
										    ${field['dicKey'] } 
										  </c:if> 
									</c:forEach>
								</td>
								<td>${file.filePath }</td>
								<td>
									<fmt:formatDate value="${file.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<a class="img-thumbnail lightbox" href="<%=basePath %>${file.viewFilePath}" 
									   data-plugin-options='{ "type":"image" }' >
										<img  src="<%=basePath %>${file.viewFilePath}" class="img-responsive" width="115" height="20" >
										<span class="zoom"><i class="fa fa-search"></i></span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="pageTotal" value="${page.totalResult}"/>
<input type="hidden" id="fileId" value=""/>
