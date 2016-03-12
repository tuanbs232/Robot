<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/modal/modal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/home/home.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/plugins/jquery-2.1.1/jquery.js"></script>
</head>
<body>
	<%@include file="../common/navbar/navbar.jsp"%>
	<div class="container content">
		<div class="market_panel row">
			<div id="market_panel_header" class="paper-header">
				<span>Thông tin thị trường</span>
			</div>
			<div class="market_panel_content">
				<div class="row brief_panel">
					<div class="col-md-6">
						<span>VNI:</span><span class="brief_info">579.45(<i
							class="fa fa-caret-up status green"></i>3.16 0.55%)
						</span><span> KL: 90,049,368&nbsp;&nbsp; GT: 1485.91 tỷ </span>
					</div>
					<div class="col-md-6 brief_panel-right">
						<span> HNX:</span><span class="brief_info">79.28(<i
							class="fa fa-caret-up status green"></i>0.67 0.85%)
						</span><span>KL: 48,394,912&nbsp;&nbsp; GT: 502.53 tỷ </span>
					</div>
				</div>
				<div class="row brief_panel">
					<div class="col-md-6">
						<span>VN30:</span><span class="brief_info">594.8(<i
							class="fa fa-caret-up status green"></i>5.37 0.81%)
						</span><span> KL: 30,984,840&nbsp;&nbsp; GT: 706.86 tỷ </span>
					</div>
					<div class="col-md-6 brief_panel-right">
						<span> HNX30:</span><span class="brief_info">139.93(<i
							class="fa fa-caret-up status green"></i>1.4 1.01%)
						</span><span>KL: 16,757,700&nbsp;&nbsp; GT: 162.51 tỷ</span>
					</div>
				</div>
				<%@include file="stockmarket.jsp"%>
			</div>
		</div>
		<div class="row">
			<div class="custome col-md-3">
				<div class="panel">
					<div class="paper-header">
						<span>Thông tin tài khoản</span>
					</div>
					<table class="table panel_content ">
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
			</div>
			<div class="custome custom_right col-md-9">
				<div class="panel">
					<div class="paper-header">
						<span>Ghi lệnh</span>
					</div>
					<div id="add_tran_form" class="panel_content row form-group">
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
				</div>
				<div class="panel">
					<div class="paper-header">
						<span>Danh sách lệnh chờ gửi</span>
					</div>
					<div class="panel_content">
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/jspdf/jspdf.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/jspdf/jspdf.plugin.standard_fonts_metrics.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/jspdf/jspdf.plugin.split_text_to_size.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/jspdf/jspdf.plugin.from_html.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/base64/jquery.base64.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/base64/Base64.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/jspdf/FileSaver.js"></script>

	<script
		src="${pageContext.request.contextPath}/res/plugins/jspdf_autotable/jspdf.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/res/plugins/jspdf_autotable/jspdf.plugin.autotable.js"></script>
	<script
		src="${pageContext.request.contextPath}/home/bkav-extension-signer-v2.0.js"></script>
	<script src="${pageContext.request.contextPath}/home/Utils.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/home/home.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/modal/modal.js"></script>
	<script type="text/javascript">
		var test = new ObjPdfSigner();
	</script>
</body>
</html>