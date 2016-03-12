<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/res/imgs/shortcut.ico">
<title>Giải pháp Bkav Core CA</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/plugins/bootstrap-3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/plugins/font-awesome-4.3.0/css/font-awesome.min.css">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

html {
	background: url(res/imgs/background.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

body {
	background: transparent;
}
/*
 * Specific styles of signin component
 */
/*
 * General styles
 */
.card-container.card {
	max-width: 350px;
	padding: 40px 40px;
	position: absolute;
	margin: auto;
	position: absolute;
	height: 70%;
	min-height: 430px;
	top: 0;
	bottom: 0;
	right: 100px;
	background: rgba(255, 255, 255, 0.7);
}

@media ( max-width :767px) {
	.card-container.card {
		top: 0 !important;
		bottom: 0 !important;
		left: 0 !important;
		right: 0 !important;
		width: 80% !important;
	}
}

.btn {
	font-weight: 700;
	height: 36px;
	-moz-user-select: none;
	-webkit-user-select: none;
	user-select: none;
	cursor: default;
}

/*
 * Card component
 */
.card {
	background-color: #F7F7F7;
	/* just in case there no content*/
	padding: 20px 25px 30px;
	margin: 0 auto 25px;
	margin-top: 50px;
	/* shadows and rounded borders */
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
	width: 96px;
	height: 96px;
	margin: 0 auto 10px;
	display: block;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}

/*
 * Form styles
 */
.profile-name-card {
	font-size: 16px;
	font-weight: bold;
	text-align: center;
	margin: 10px 0 0;
	min-height: 1em;
}

.reauth-email {
	display: block;
	color: #404040;
	line-height: 2;
	margin-bottom: 10px;
	font-size: 14px;
	text-align: center;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin #inputEmail, .form-signin #inputPassword {
	direction: ltr;
	height: 44px;
	font-size: 16px;
}

.form-signin input[type=email], .form-signin input[type=password],
	.form-signin input[type=text], .form-signin button {
	width: 100%;
	display: block;
	margin-bottom: 10px;
	z-index: 1;
	position: relative;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	border-radius: 1px !important;
}

.form-signin .form-control:focus {
	border-color: rgb(104, 145, 162);
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgb(104, 145, 162);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgb(104, 145, 162);
}

.btn.btn-signin {
	background-color: #45B6B0; 
	/*     background-color: rgb(104, 145, 162); */
	/* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
	cursor: pointer;
	padding: 0px;
	font-weight: 700;
	font-size: 14px;
	height: 36px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	border: none;
	-o-transition: all 0.218s;
	-moz-transition: all 0.218s;
	-webkit-transition: all 0.218s;
	transition: all 0.218s;
}

.btn.btn-signin:hover, .btn.btn-signin:active, .btn.btn-signin:focus {
	background-color: #3DA29D;
	
}

.forgot-password {
	color: rgb(104, 145, 162);
}

.forgot-password:hover, .forgot-password:active, .forgot-password:focus
	{
	color: rgb(12, 97, 33);
}

.error {
	color: red;
}
</style>
</head>
<body>
	<div class="container">
		<div class="card card-container">
			<img id="profile-img" class="profile-img-card"
				src="res/imgs/profile-image-default.png" />
			<p id="profile-name" class="profile-name-card"></p>
			<form id="login-form" class="form-signin" action="Authentication"
				method="post">
				<span id="reauth-email" class="reauth-email"></span> <input
					type="text" id="inputEmail" class="form-control"
					placeholder="Tên tài khoản" required autofocus name="username">
				<input type="password" id="inputPassword" class="form-control"
					placeholder="Mật khẩu" required name="password">
				<div id="remember" class="checkbox">
					<label> <input type="checkbox" value="remember-me">
						Ghi nhớ thông tin đăng nhập
					</label>
				</div>
				<span class="error">${error }</span>
				<button id="signin"
					class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Đăng
					nhập</button>
			</form>
			<!-- /form -->
			<a href="#" class="forgot-password"> Quên mật khẩu? </a><br>
			<a href="#" class="signup"> Đăng ký tài khoản </a>
		</div>
		<!-- /card-container -->
	</div>
	<!-- /container -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/jquery-2.1.1/jquery.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		// 		$('#signin').click(function(){
		// 			location.href="home/index.jsp";
		// 		});
		$("#login-form").on('keydown', $("#inputEmail"), function(e) {
			$('.error').text('');
		});
		$("#login-form").on('keydown', $("#inputPassword"), function(e) {
			$('.error').text('');
		});
	</script>
</body>
</html>