/**
 * 
 */
function error(message) {
	$("#result_modal_header").css("background-color", '#d9534f');
	$("#result_title").text("Error");
	$("#result_title").css("color", '#fff');
	$("#result_content").css("color", "#d9534f");

	$("#result_content").empty();
	$("#result_content").append("<p>" + message + "</p>");
	$('#result_modal').modal({
		backdrop : "static"
	});
}

function warning(message) {
	$("#result_modal_header").css("background-color", '#EC5F2F');
	$("#result_title").text("Warning");
	$("#result_title").css("color", '#fff');
	$("#result_content").css("color", "#d9534f");

	$("#result_content").empty();
	$("#result_content").append("<p>" + message + "</p>");
	$('#result_modal').modal({
		backdrop : "static"
	});
}

function message(message) {
	$("#result_modal_header").css("background-color", '#45B6B0');
	$("#result_title").text("Message");
	$("#result_title").css("color", '#fff');
	$("#result_content").css("color", "#0000ff");

	$("#result_content").empty();
	$("#result_content").append("<p>" + message + "</p>");
	$('#result_modal').modal({
		backdrop : "static"
	});
}

function confirm(message) {
	$("#confirm_modal_header").css("background-color", '#45B6B0');
	$("#confirm_title").css("color", '#fff');

	$("#confirm_content").empty();
	$("#confirm_content").append("<p>" + message + "</p>");
	$('#confirm_modal').modal({
		backdrop : "static"
	});
}

function confirm_red(message) {
	$("#confirm_modal_header").css("background-color", '#EC5F2F');
	$("#confirm_title").css("color", '#fff');
	$("#result_content").css("color", "#d9534f");
	$('#confirm_title').text('Cảnh báo');

	$("#confirm_content").empty();
	$("#confirm_content").append("<p>" + message + "</p>");
	$('#confirm_modal').modal({
		backdrop : "static"
	});
}

function doConfirm(callBack) {
	$("#confirm-btn").click(function() {
		$('#confirm_modal').modal('hide');
		callBack();
		return;
	});
	$('#super-confirm').click(function() {
		$('#super-admin').modal('hide');
		callBack();
		return;
	})
}