<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/plugins/jquery-2.1.1/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/history/history.css">
</head>
<body>
	<%@include file="../common/navbar/navbar.jsp"%>
	<div class="container content">
		<div class="row">
			<div class="col-md-4 col-xs-12 col-sm-12 col-lg-4">
				<div id="panel">
					<div class="paper-header">
						<span>Thông tin tài khoản</span>
					</div>
					<div id="profile-image" class="row profile-image">
						<img class="" alt="" src="res/imgs/profile-image-default.png">
					</div>
					<hr class="divider">
					<br>
					<div id="brief-infor">
						<table class="table">
							<tbody>
								<tr>
									<td>Tên tài khoản</td>
									<td class="td-right">${username }</td>
								</tr>
								<tr>
									<td>Trạng thái</td>
									<td class="td-right"></td>
								</tr>
								<tr>
									<td>Số dư tiền mặt</td>
									<td class="td-right">${sodutienmat }VNĐ</td>
								</tr>
								<tr>
									<td>Tiền ứng trước</td>
									<td class="td-right">${tienungtruoc }VNĐ</td>
								</tr>
								<tr>
									<td>Số dư có thể giao dịch</td>
									<td class="td-right">${soducothegiaodich }VNĐ</td>
								</tr>
								<tr>
									<td>KL có thể mua</td>
									<td class="td-right">${khoiluongcothemua }</td>
								</tr>
								<tr>
									<td>Tiền treo gốc</td>
									<td class="td-right">${tientreogoc }VNĐ</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
				<div id="panel">
					<div class="paper-header">
						<span id="content-label">Lịch sử giao dịch</span>
					</div>
					<div id="list-user">
						<table class="table">
							<thead id="list-user-head">
								<tr>
									<th>#</th>
									<th>Thời gian</th>
									<th>Mua/bán</th>
									<th>Mã CK</th>
									<th>Số lượng</th>
									<th>Giá</th>
									<th>Trạng thái</th>
									<th>Thiết bị</th>
									<th>Dữ liệu</th>
								</tr>
							</thead>
							<tbody id="list-user-body">
								<c:set var="count" value="0" scope="page" />
								<c:forEach items="${logs}" var="log">
									<c:set var="count" value="${count + 1}" scope="page" />
									<tr>
										<td rowspan="${log.count }" class="test-rowspan-right">${count }</td>
										<td rowspan="${log.count }" class="test-rowspan-right"><fmt:formatDate
												pattern="dd/MM/yyyy" value="${log.signingTime }" /><br>
										<fmt:formatDate pattern="HH:mm:ss" value="${log.signingTime }" /></td>
										<c:forEach var="firstline" items="${log.logs}" end="0">
											<td>${firstline.transactionType }</td>
											<td>${firstline.stockName }</td>
											<td>${firstline.weight }</td>
											<td>${firstline.price }</td>
											<td>${firstline.status }</td>
											<td><c:choose>
													<c:when test="${firstline.client == 1}">
     													<i class="xx fa fa-mobile"></i>
    												</c:when>
    												<c:otherwise>
    													<i class="xx fa fa-desktop"></i>
    												</c:otherwise>
												</c:choose></td>
										</c:forEach>

										<td rowspan="${log.count }" class="test-rowspan-left"><a
											href="TransactionLogServlet?id=download&file=${log.signedFileUrl }"><i
												class="fa fa-download"></i></a></td>
									</tr>
									<c:forEach items="${log.logs}" var="item" varStatus="loop">
										<c:if test="${not loop.first}">
											<tr>
												<td>${item.transactionType }</td>
												<td>${item.stockName }</td>
												<td>${item.weight }</td>
												<td>${item.price }</td>
												<td>${item.status }</td>
												<td><c:choose>
													<c:when test="${item.client == 1}">
     													<i class="xx fa fa-mobile"></i>
    												</c:when>
    												<c:otherwise>
    													<i class="xx fa fa-desktop"></i>
    												</c:otherwise>
												</c:choose></td>
											</tr>
										</c:if>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../common/modal/modal.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/modal/modal.js"></script>
	<script type="text/javascript">
		$('.page-scroll').each(function() {
			$(this).removeClass('active');
		});
		$('#nav-history').addClass('active');
	</script>
</body>
</html>