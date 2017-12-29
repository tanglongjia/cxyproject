<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<div class="navbar" role="navigation">
	<div class="container-fluid container-nav">
		<!-- Navbar Action -->
		<ul class="nav navbar-nav navbar-actions navbar-left">
			<li class="visible-md visible-lg"><a href="#"
				id="main-menu-toggle"><i class="fa fa-th-large"></i>
			</a>
			</li>
			<li class="visible-xs visible-sm"><a href="#" id="sidebar-menu"><i
					class="fa fa-navicon"></i>
			</a>
			</li>
		</ul>
		<!-- Navbar Left -->
		<div class="navbar-left"></div>
		<!-- Navbar Right -->
		<div class="navbar-right">
			<!-- Userbox -->
			<div class="userbox">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i
					class="fa fa-user"></i>
					<div class="profile-info">
						<span class="name">欢迎您！ ${ sessionScope.bsUser.trueName}</span>
					</div> </a> <a onclick="logout()" style="cursor:pointer;"><i
					class="fa fa-power-off"></i>&nbsp;&nbsp;注销</a> &nbsp;&nbsp;&nbsp;&nbsp;<a
					onclick="updatePwd()" style="cursor:pointer;"><i
					class="fa fa-wrench"></i>&nbsp;&nbsp;更改密码</a>
			</div>
			<!-- End Userbox -->
		</div>
		<!-- End Navbar Right -->
	</div>
</div>