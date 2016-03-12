<!-- 	Result popup -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.hightlight {
	background-color: #ff8000;
}

#list-cert tr:HOVER {
	cursor: pointer;
	background-color: #ebebeb;
}

.hightlight:HOVER {
	background-color: #ff8000;
}

#error {
	color: red;
	font-size: 12px;
}

.spinner_03-loader {
	display: none;
}

.error {
	color: red;
}
#select_cert-refresh{
	margin-left: 20px;
}
</style>
<div id="cert_register" class="modal fade">
	<div id="modal-dialog" class="modal-dialog">
		<div id="modal-content" class="modal-content">

			<div class="modal-header" id="result_modal_header">
				<button type="button" id="close_ico" class="close"
					data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="">Đăng ký sử dụng chữ ký số</h4>
			</div>

			<div id="result_content" class="modal-body">
				<div class="row form-group">

					<div class="col-md-12">
						<label>Chọn chữ ký số để đăng ký:</label>
						<div class="input-group">
							<select id="select_cert_list" class="form-control"></select> <span
								class="input-group-btn">
								<button id="select_cert-refresh" class="btn btn-default" type="button">Cập nhật</button>
							</span>
						</div>
						<span class="error" id="select_cert_error"></span>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<span id="error"></span>
				<%@include file="../loading/spinner_03.jsp"%>
				<button type="button" id="select" class="btn btn-default">Chọn</button>
			</div>
		</div>
		<!-- End modal-content -->
	</div>
	<!-- End modal-dialog -->
</div>
<!-- End modal -->
<script>
	
</script>