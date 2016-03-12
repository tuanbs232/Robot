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
#error{
	color: red;
    font-size: 12px;
}
.spinner_03-loader {
	display: none;
}
</style>
<div id="cert_chooser" class="modal fade">
	<div id="modal-dialog" class="modal-dialog">
		<div id="modal-content" class="modal-content">

			<div class="modal-header" id="result_modal_header">
				<button type="button" id="close_ico" class="close"
					data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="">Chọn chữ ký số để ký</h4>
			</div>

			<div id="result_content" class="modal-body">
				<table id="list-cert-tbl" class="table">
					<thead>
						<tr>
							<th class="">#</th>
							<th class="">Chủ sở hữu</th>
							<th class="">Cấp bởi</th>
							<th class="">Số Serial</th>
							<th class="">Hạn dùng</th>
						</tr>
					</thead>
					<tbody id="list-cert">

					</tbody>
				</table>
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