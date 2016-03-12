
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="tf-menu" class="navbar navbar-default navbar-fixed-top on">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="Home"><img id="bkav_logo" alt="" src="res/imgs/bkavca.gif"> <label id="brand-label">Demo Securites</label></a><br>

		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a id="nav-home" href="SignatureServlet"
					class="page-scroll active"><i class="fa fa-home home"></i></a></li>
				<!-- 				<li><a href="#" class="page-scroll">Giao dịch</a></li> -->
				<li><a id="nav-history" href="TransactionLogServlet"
					class="page-scroll">LỊCH SỬ GIAO DỊCH</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><i class="fa fa-user"></i>&nbsp;<span
						id="signer_account">${username}</span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="FileUpload"><i class="fa fa-info-circle"></i>&nbsp;Tài
								khoản</a></li>
						<li><a href="Authentication?id=logout"><i
								class="fa fa-sign-out"></i>&nbsp;Đăng xuất</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
<link rel="stylesheet" type="text/css" href="common/navbar/navbar.css" />
<!-- <script type="text/javascript" src="../common/navbar/navbar.js"></script> -->