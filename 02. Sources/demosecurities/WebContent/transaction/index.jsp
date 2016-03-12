<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/res/imgs/shortcut.ico">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/plugins/bootstrap-3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/plugins/font-awesome-4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/modal/modal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/transaction/transaction.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/plugins/jquery-2.1.1/jquery.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<title>Giải pháp Bkav Core CA</title>
</head>
<body>
	<%@include file="../common/navbar/navbar.jsp"%>
	<div class="container content">
		<div class="row">
			<h3>Thông tin thị trường</h3>
			<hr>
			<%@include file="stockmarket.jsp"%>
		</div>
		<div class="row">
			<c:if test="${fn:length(listCert) eq 0}">
				<script>
				$(document).ready(function(){
					confirm_red("Bạn chưa đăng ký sử dụng chữ ký số. <br>Đăng ký bây giờ?");
					doConfirm(function(){
						$('#cert_register').modal('show');
						checkToken();
					});
				});
				</script>
			</c:if>
			<h3>Đặt lệnh giao dịch</h3>
			<hr>
			<div class="col-md-3">
				<h4>Thông tin tài khoản:</h4>
				<table class="table">
					<tr>
						<td class="td-left">Số dư tiền mặt:</td>
						<td class="td-right">${sodutienmat }VNĐ</td>
					</tr>
					<tr>
						<td class="td-left">Tiền ứng trước</td>
						<td class="td-right">${tienungtruoc }VNĐ</td>
					</tr>
					<tr>
						<td class="td-left">Số dư có thể giao dịch</td>
						<td class="td-right">${soducothegiaodich }VNĐ</td>
					</tr>
					<tr>
						<td class="td-left">KL có thể mua</td>
						<td class="td-right">${khoiluongcothemua }</td>
					</tr>
					<tr>
						<td class="td-left">Tiền treo gốc</td>
						<td class="td-right">${tientreogoc }VNĐ</td>
					</tr>
				</table>
			</div>
			<div class="col-md-9">
				<h4>Thêm lệnh:</h4>
				<div id="add_tran_form" class="row form-group">
					<div class="col-md-2">
						<label>Mua/bán:</label> <select id="tran_type"
							class="form-control">
							<option value="BAN" selected="selected">Bán</option>
							<option value="MUA">Mua</option>
						</select>
					</div>
					<div class="col-md-2">
						<label>Chứng khoán:</label> <input id="form_stock_name"
							class="form-control" type="text" name="action_type"
							disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>Số lượng:</label> <input id="form_stock_count"
							class="form-control" type="text" name="action_type" value="0">
					</div>
					<div class="col-md-2">
						<label>Loại lệnh:</label> <select id="_type" class="form-control">
							<option value="LO" selected="selected">LO</option>
							<option value="ATO">ATO</option>
							<option value="ATC">ATC</option>
							<option value="MP">MP</option>
						</select>
					</div>
					<div class="col-md-2">
						<label>Giá (VNĐ):</label> <input id="form_stock_price"
							class="form-control" type="text" name="action_type" value="0">
					</div>
					<div class="col-md-2">
						<label>Sàn GD:</label> <select id="market" class="form-control"
							disabled="disabled">
							<option value="HOSE" selected="selected">HOSE</option>
						</select>
					</div>
				</div>
				<div class="row form-group tc_price_group">
					<label>Giá trần: </label><label id="form_max_price"
						class="form_tc_price">0</label><label class="vnd">VNĐ</label> <label>Giá
						TC: </label><label id="form_tc_price" class="form_tc_price">0</label><label
						class="vnd">VNĐ</label> <label>Giá sàn: </label><label
						id="form_min_price" class="form_tc_price">0</label><label
						class="vnd">VNĐ</label>
					<div>
						<button id="add_tran" class="btn btn-default">Ghi lệnh</button>
					</div>
				</div>
				<hr>
				<h4>Danh sách lệnh chờ gửi:</h4>
				<div>
					<table id="record" class="table table-striped">
						<thead>
							<tr>
								<th class="">#</th>
								<th class="">Mua/bán</th>
								<th class="">Mã chứng khoán</th>
								<th class="">KL</th>
								<th class="">Giá</th>
								<th class="">Trạng thái</th>
								<th class="">Xóa</th>
							</tr>
						</thead>
						<tbody id="list-trans">

						</tbody>
					</table>
				</div>
				<div class="form-group tc_price_group">
					<button id="send_tran" class="btn btn-success">Gửi lệnh</button>
				</div>
			</div>
		</div>
		<div id="test"></div>
	</div>
	<ul class='custom-menu'>
		<li data-action="first"><i
			class="fa fa-money context-menu-icon red"></i>Bán <b
			class="menu-stock-name"></b></li>
		<li data-action="second"><i
			class="fa fa-shopping-cart context-menu-icon green"></i>Mua <b
			class="menu-stock-name"></b></li>
	</ul>
	<object id="plugin0" type="application/x-bkavcaplugin"> </object>
	<div id="ExtensionPlaceHolder" ClientIDMode='Static'></div>

	<%@include file="../common/modal/modal.jsp"%>
	<%@include file="../common/certchooser/certchooser.jsp"%>
	<%@include file="../common/certregister/certregister.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/modal/modal.js"></script>
		<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/base64/Base64.js"></script>
		<script src="${pageContext.request.contextPath}/res/plugins/bkav-signer-extension-2.0/signer-extension.js"></script>
		<script src="${pageContext.request.contextPath}/res/plugins/bkav-signer-extension-2.0/utils.js"></script>
		<script type="text/javascript"
		src="${pageContext.request.contextPath}/transaction/transaction.js"></script>
</body>
</html>