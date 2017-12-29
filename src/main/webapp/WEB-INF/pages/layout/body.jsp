<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%
	response.setStatus(200);
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>右侧内容展示</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
</head>
<body>
	<!-- 用于展示所有右侧区域的内容 -->
	<iframe name="commonFrame" id="commonFrame"
		src="<%=basePath%>/welcome.jsp" frameborder="0" width="100%"
		scrolling="no" marginheight="0" marginwidth="0"
		onLoad="changeFrameHeight()"></iframe>
	<script type="text/javascript" language="javascript">
		function changeFrameHeight() {
			var ifm = document.getElementById("commonFrame");
			ifm.height = document.documentElement.clientHeight;
		}
		window.onresize = function() {
			changeFrameHeight();
		}
	</script>
</body>
</html>