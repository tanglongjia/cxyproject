<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<head>
<title>403 权限页面</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/bootstrap-theme.min.css" rel="stylesheet"
	type="text/css">
<link href="assets/css/templatemo_style.css" rel="stylesheet"
	type="text/css">
<script src="assets/vendor/js/jquery.min.js"></script>
<script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
<!-- toastr -->
<script src="assets/plugins/toastr/toastr.js"></script>
<!-- toastr -->
<link href="assets/plugins/toastr/toastr.css" rel="stylesheet" />
</head>
<body>
	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">
			<!-- Main Page -->
			<div id="content" class="col-sm-12 full">
				<div class="row box-error">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="text-center">
							<h1>403</h1>
							<h2>未授权 ...</h2>
							<p>您未授权，无法访问该页面.</p>
							<a href="javascript:history.go(-1);">
								<button type="button" class="btn btn-danger">
									<i class="fa fa-chevron-left"></i> 返回
								</button> 
							</a> 
							<a href="login.jsp">
								<button type="button" class="btn btn-danger">
									<i class="fa fa-lock"></i> 登录
								</button> </a> 
							<a href="#">
								<button type="button" class="btn btn-danger">
									<i class="fa fa-envelope"></i> 联系管理员
								</button> 
							</a>
						</div>
					</div>
				</div>
			</div>
			<!-- End Main Page -->
		</div>
	</div>
</body>