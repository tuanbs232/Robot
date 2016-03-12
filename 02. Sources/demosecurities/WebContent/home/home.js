/**
 * 
 */

var certListEncoded = null;
var record = [];
var checkGetCert = 0;
$(document).ready(function() {
	alowDigitOnly("#add_tran_form", "#form_stock_count");
	alowDigitOnly("#add_tran_form", "#form_stock_price");
	$("#form_stock_price").change(function() {
		var t = $(this).val();
		if (t == null || t == '') {
			$(this).val('0');
		}
	});
	$("#form_stock_count").change(function() {
		var t = $(this).val();
		if (t == null || t == '') {
			$(this).val('0');
		}
	});

	$('.navbar-default').addClass('on');
	function plugin0() {
		return document.getElementById('plugin0');
	}
	plugin = plugin0;

	function load() {
		addEvent(plugin(), 'test', function() {
			alert("Received a test event from the plugin.")
		});
	}
	changeStockData();

	setInterval(function() {
		testChangeData();
	}, 1000);
});

function SigPDF(pdf, serNumber, signerName) {
	var serial = serNumber;
	try {
		var iCheck = checkBrowser();
		var objPDF = new ObjPdfSigner();
		objPDF.CertificateSerialNumber = serial;
		objPDF.SigningType = PDF_SIGNING_TYPE.SIGN_PDF_BASE64;
		objPDF.Base64String = pdf;
		objPDF.Signer = signerName;
		if (iCheck == 1) {
			BkavExtensionSigner.SetUsePKCS11(SET_USE_PKCS11.YES);

			BkavExtensionSigner.SetAESKey("1111111111111111112");

			BkavExtensionSigner.SetDLLName('BkavCAv2S');

			BkavExtensionSigner.SignPDF(objPDF);
		} else {
			BkavPluginSigner.SignPDF(objPDF);
		}
	} catch (e) {
		console.log(e);
	}
}

var element;
// JAVASCRIPT (jQuery)

// Trigger action when the contexmenu is about to be shown
$("tr").bind("contextmenu", function(event) {
	element = $(this);
	if (element.find(".stock-type").text() == '') {
		return;
	}
	$('.menu-stock-name').text(element.find("td.stock-type").text());

	// Avoid the real one
	event.preventDefault();

	// Show contextmenu
	$(".custom-menu").finish().toggle(100).

	// In the right position (the mouse)
	css({
		top : event.pageY + "px",
		left : event.pageX + "px"
	});
});

// If the document is clicked somewhere
$(document).bind("mousedown", function(e) {
	// If the clicked element is not the menu
	if (!$(e.target).parents(".custom-menu").length > 0) {

		// Hide it
		$(".custom-menu").hide(100);
	}
});

// If the menu element is clicked
$(".custom-menu li").click(function() {

	// This is the triggered action name
	switch ($(this).attr("data-action")) {

	// A case for each action. Your actions here
	case "first":
		sellStock();
		break;
	case "second":
		buyStock("second");
		break;
	}

	// Hide it AFTER the action was triggered
	$(".custom-menu").hide(100);
});

function sellStock() {
	var name = element.find("td.stock-type").text();
	var max_price = parseFloat(element.find(".max_price").text()) * 1000;
	var tc_price = parseFloat(element.find(".tc_price").text()) * 1000;
	var min_price = parseFloat(element.find(".min_price").text()) * 1000;

	var price = parseFloat(element.find(".sell_price_1").text()) * 1000;

	$('#form_max_price').text(max_price);
	$('#form_tc_price').text(tc_price);
	$('#form_min_price').text(min_price);

	$('#tran_type').val("BAN");
	$('#form_stock_name').val(name);
	$('#form_stock_price').val(price);
}

function buyStock() {
	var name = element.find("td.stock-type").text();
	var max_price = parseFloat(element.find(".max_price").text()) * 1000;
	var tc_price = parseFloat(element.find(".tc_price").text()) * 1000;
	var min_price = parseFloat(element.find(".min_price").text()) * 1000;

	var price = parseFloat(element.find(".buy_price_1").text()) * 1000;

	$('#form_max_price').text(max_price);
	$('#form_tc_price').text(tc_price);
	$('#form_min_price').text(min_price);

	$('#tran_type').val("MUA");
	$('#form_stock_name').val(name);
	$('#form_stock_price').val(price);
}

var num_of_tran = 0;

$("#add_tran").click(function() {
	addTransaction();
});

function addTransaction() {
	var tran_index = ++num_of_tran;
	var tran_type = $('#tran_type').val();
	var stock_name = $('#form_stock_name').val();
	var weigth = parseInt($('#form_stock_count').val());
	var price = parseInt($('#form_stock_price').val(), 10);

	if (stock_name == '') {
		warning("Bạn chưa chọn chứng khoán cần giao dịch.");
		return;
	}

	if (weigth % 10 != 0 || weigth == 0) {
		warning("Số lượng phải là bội số của 10");
		return;
	}

	var tc_price_x = parseInt($("#form_tc_price").text(), 10);
	var max_price_x = parseInt($("#form_max_price").text(), 10);
	var min_price_x = parseInt($("#form_min_price").text(), 10);
	if (price < min_price_x) {
		warning("Giá không được nhỏ hơn giá sàn");
		return;
	}
	if (price > max_price_x) {
		warning("Giá không được lớn hơn giá trần");
		return;
	}

	var html = "<tr>"
			+ "<td class='tran_index'>"
			+ tran_index
			+ "</td>"
			+ "<td>"
			+ tran_type
			+ "</td>"
			+ "<td>"
			+ stock_name
			+ "</td>"
			+ "<td>"
			+ weigth
			+ "</td>"
			+ "<td>"
			+ price
			+ "</td>"
			+ "<td>OK</td>"
			+ "<td><a class='remove_tran' href='#'><i class='fa fa-trash'></i></a></td>"
			+ "</tr>";
	$('#list-trans').append(html);

	$('#form_stock_name').val("");
	$('#form_stock_count').val("0");
	$('#form_stock_price').val("0");

	$('.remove_tran').each(function() {
		$(this).bind("click", function() {
			var tr = $(this).closest('tr');
			confirm("Bạn chắc chắn muốn xóa lệnh này?");
			doConfirm(function() {
				removeTransaction(tr);
				return false;
			});
		});
	});
}

function removeTransaction(tran) {
	tran.remove();
	num_of_tran = 0;
	$('.tran_index').each(function() {
		$(this).text(++num_of_tran);
	});
}

$('#send_tran').click(function() {
	sendTransaction();
});

function sendTransaction() {
	$('#list-trans').find('tr').each(function(i, el) {
		var $tds = $(this).find('td');
		id = $tds.eq(0).text();
		action = $tds.eq(1).text();
		name = $tds.eq(2).text();
		weight = $tds.eq(3).text();
		price = $tds.eq(4).text();
		status = $tds.eq(5).text();

		var tran = new Transaction(id, action, name, weight, price, status);
		record.push(tran);
	});

	if (record.length == 0) {
		warning("Chưa có lệnh nào trong danh sách chờ gửi.");
		return;
	} else {
		if (checkGetCert == 0) {
			GetAllCerts();
		} else {
			selectCertificate();
		}
	}
}

/**
 * Get all certificate and create tbody object
 * 
 * @param certListEn
 *            result from extension
 * @returns {String}
 */
function chooseCertificate(certListEn) {
	var iCheck = checkBrowser();
	var xmlString = null;
	if (iCheck == 1) {
		// Chrome
		var xmlString = Base64.decode(certListEn);
	}
	var xml = jQuery.parseXML(xmlString);
	if (xml == null || xml == '') {
		warning("Không tìm thấy chữ ký số");
		return "";
	}

	var certList = xml.getElementsByTagName("Certificate");

	var html = "";
	for (var i = 0; i < certList.length; i++) {
		var validTo = certList[i].getElementsByTagName("TimeValidTo")[0].innerHTML;
		var serialNumber = certList[i].getElementsByTagName("SerialNumber")[0].innerHTML;
		var subjectCN = certList[i].getElementsByTagName("SubjectDN")[0]
				.getElementsByTagName("CN")[0].innerHTML;
		var issuerCN = certList[i].getElementsByTagName("IssuerDN")[0]
				.getElementsByTagName("CN")[0].innerHTML;

		html += "<tr>" + "<td>" + (i + 1) + "</td>" + "<td class='signer'>"
				+ subjectCN + "</td>" + "<td>" + issuerCN + "</td>"
				+ "<td class='serial'>" + serialNumber + "</td>" + "<td>"
				+ validTo + "</td>" + "</tr>"
	}

	return html;
}

function selectCertificate() {
	$('#cert_chooser').modal('show');
}

var t_serial = null;
var signer = null;
$('#list-cert-tbl').on('click', 'tbody tr', function() {
	$('#error').text('');
	$('#error').hide();
	$(this).addClass('hightlight').siblings().removeClass('hightlight');
	t_serial = $(this).find('.serial').text();
	signer = $(this).find('.signer').text();
});

$('#select').click(function() {

	if (t_serial == null) {
		$('#error').text('[ERROR] Chọn chữ ký số trước.');
		$('#error').show();
		return;
	} else {
		$('#error').text("");
		$('.spinner_03-loader').show();
		$(this).attr('disabled', true);
		signDocument(t_serial, signer);
		return false;
	}
});

function signDocument(serialNumber, signerName) {
	if(checkGetCert == 0){
		return;
	}
	
	var columns = [ {
		title : "#",
		dataKey : "id"
	}, {
		title : "Buy/sell",
		dataKey : "action"
	}, {
		title : "Stock ID",
		dataKey : "name"
	}, {
		title : "Weight",
		dataKey : "weight"
	}, {
		title : "Price",
		dataKey : "price"
	}, {
		title : "Status",
		dataKey : "status"
	} ];

	var jsonData = JSON.stringify(record);
	var rows = JSON.parse(jsonData);
	var data = rows
	// Only pt supported (not mm or in)
	var doc = new jsPDF('p', 'pt');
	doc.setFont('helvetica');
	
//	var imgData = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD//gAqRWRpdGVkIHdpdGggTHVuYVBpYzogaHR0cDovL2x1bmFwaWMuY29tL//bAEMAAwICAwICAwMDAwQDAwQFCAUFBAQFCgcHBggMCgwMCwoLCw0OEhANDhEOCwsQFhARExQVFRUMDxcYFhQYEhQVFP/bAEMBAwQEBQQFCQUFCRQNCw0UFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFP/CABEIADcAdgMBEQACEQEDEQH/xAAcAAEAAgMBAQEAAAAAAAAAAAAABQYDBAcCCAH/xAAbAQEAAgMBAQAAAAAAAAAAAAAAAwQBAgUGB//aAAwDAQACEAMQAAAB+qSJjvYMSfucTknOAAAAA84z8+8z6lER3p+Xmdw6Pzjc2gAAAAENHfp1fv8ALKftMjXu/S+aWKblecZ9ZwAAAKvD2eE836Vcp/PwEXUlZKWDEkhvVnJOdvbVrbNw5aSiABVoO1yGj7qGj6G3tD2K/wCBgo+lJbVALJNyJXel6Y951AFHrej5BR95ubV+3dD51Ra3pNjMWTOuVpibx2lu8WfN2SbkgAUWt6Tc2gskvI2toQAAAB//xAAhEAACAwEAAgMAAwAAAAAAAAAEBQIDBgEAMBMUIBEVFv/aAAgBAQABBQLxozqUBqnQrihY5Fbc9cu/xGOv+9M1cTm7w+1l8DKgaL6mbcVRW3RA6gZUzuz5TEO3LsUBIxazzkuS9L9BU+HICZ44svg+2WZoqB9CQa9Yoz5FtDJE4mPY70ZS8onTTDvVEXFrvzpTCgFIu1st4TXZmGmmojC+m8dtnkyoCd3cwFwcrMClWtk4J9NDQa3nyw75GcbOfjS5ylsIjh1uqzFf9yutXRrT/wCdYTFpQEUy6lN4WHnZjjioCafKcuTClCFeCH+VOVqUtJqoqhRvk+v6v//EADMRAAEDAwEGAwcDBQAAAAAAAAECAwQABRExEhMhIkFRYXHBBhUgMDKRoRQj0RAlgbHw/9oACAEDAQE/AahQ3JzwYa1NTbfIgL2Hh/npUy3yIJG+GvzAMnBo2H9MEyLcvnH2NR5bF3QqJLRhY1HqKfC2P7fP5mlfSrt2zT7Ko7qml6j5cOC/OUUsJzioNyl2ZzcSQdnt/FTYbd0aTLiKwsaH0NRH27zHVFlDCxqPUVc2X2JKkSOJ79x0/oQRr8m2XNy2OFaBkHWmpEC/s7Chx7dR5UxvvZ2Vu3TllfWruwqK4m6RdR9XiKuLrUyZCdTxCv5q6NNuRpCUhKlJPQYKRVzgJdSJT2dhKBp1NW60x5TSN6FAq65H4Gppqzpfb/bPMF7J8u9TWmmJC2mTkD4rRHYky0tyTy097PIR+/b1bKx9qZWi8RVx5IwscCOx71Z3FKQ5bpP1I4eYpxt6DcAw1zFCuUVcJsxKFILaU7zUp4k/mvfEoucUZ5dkjjpTF4fZSgboHY0JGgqDPlxVvPIA48Tn0+9OQ30FRdGCOP3rYVw4UpJScKHw2i7OwXglR5DqKuShBlNTk6K5VelXhXu+S1cUeR8aRLKpn6lxWDnORXvWGl1K8ZPHKtnGvhmnLoy4FJ29knHMB26a5r3jF3WSTtbGxp40/dUOuSOY7Kk4T+KeujDm8VknaSkY8RjNLvLClhaTgZz9OnDz/wBVc5DUl4LZzjH/AGvxTr25OiojKTjHWkzVTXUCacpFPbG8O70+X//EACYRAAICAQIFBAMAAAAAAAAAAAECAAMREjETICEiMAQjQVEyYYH/2gAIAQIBAT8BjuEGTEsWwZESxX28vH1dtg6RlNR1rtFw3uV7xTqGR43dU/KPWtwysRzUdDbRlNLal2lRDLlfHbWLBiFXoOYceoXI3lR1DhNKwURxKyQyyt8do+5Zayk4hu0nr9RCSuTzWsyrlYPUE9LIc0tqXaXDqLF+YCHr1GVohO+04S43hqU56x0VsAwOp2mea2oOP3K+9Skp9xTXCvZpE4T4gqI+Jw2z/YtWAsFTDEFJxKlKjrzJSEbVNGgdkG3Xx//EADQQAAIBAgQEAgkCBwAAAAAAAAECAwAEERIhMQUTIkEUYSAjMDIzQlFSsRBxFSRygZGh0f/aAAgBAQAGPwKnuZsci9l3Ncy3fb3kO4p/DyYlDgynce0JAxP0qW04vAGtnOHQMGjqO/sJubat7ky/g1/FeFjk30PVPajZx3wqKeP3JFzD2avdSZAxwGmNeJs5E5/aRO/kaksb6PPaN0ywt28xUN7ZPntZOqN+xH2mo5LUZIzun2nuP00OPsVSRjG6aqwoSo3R2kX3W/ejNCoj4lANU+tS8Evfcf4JPyNXH4HxR4h2/Y1wxna4himQgmVsyyt5U1jbhGuZrt/ibKtTiF4JFhwxjyMT/c7CvWoohktefGe+b6VBNOoSWRcxVe3pSS2i5pQR2xwFG24rCtxbvo3TgRUN3aPzLZ+qJ/uX6Grbi9n0xXHXp8r09zN6hZ4vXP8A6qKQXNzN4TWNLgZVXz2rS4ZDz+eswIxBq4bxksYuDhIiOMGarCCR3PLIRWjwPbv/AIqEQNzFZjGMg2wo9a6b67VipDD6j0XdUC3SjFXHfyq84a2skY58Hke4q84RIcD8WIn5T3o2cUayKI8gRzgDUqZ+XHmUrbtLn231wqJ+Qk0al/5eWQEa4a7YVgEi5Hixc5s+u22FcM9VGJYps8xHca/9q2XJHGIp5HMincEHD806OmZimQsJgM+v9P5po58mbOSMoG3nh6U14spbPjlTDbGpm4dGEmkbFmO9R834mGvs/wD/xAAlEAEAAgEEAQQCAwAAAAAAAAABABEhMUFRYXEgMIGRobEQ0fD/2gAIAQEAAT8hl1NAotHQmdF4Hzky8YFXdXHuNXAKcotlXZBePNRVLrySuGbqkbsOjESsHye3oHO0l8EXYHkY6sUemhuDQAhNo1hbna23e39n8XFFMYfZT7Dl6Xkj0BNZJ8D+5Yybx+OziKwlrqx2iG0W1WdQ/ET8o3Yum0dUOqxNXt6mPHR2e6IDd/utS7wg0AELaa9eqscizWstTPhyPJVvNEX443pLDhjhan+7iT4cSkos/uHhwIAugEQLkIaTsPGJUJgQJyc9TY2sU/CMNUWOB8uNIIqnGPtO48tnpb38Wmm7mZRzs1H9k2MbYhqLA+ieWmkLBuuTlhfAeKdIqrrGCcNHY4reDNCxgMHBtAyzli9DneFTIw7GKq1KRHjoZwt1u+UoZIIlYVgBfj1Vy8EpVW53mMxynJ726jSVx48+3//aAAwDAQACAAMAAAAQidkkkkkjHFkkkkkrrc0kkkhnY9AUkkXYMS1ikgYLvLgskgo0kkkkn//EACYRAQACAgEDAwUBAQAAAAAAAAERIQAxQVFhcTCBkSChwdHwEPH/2gAIAQMBAT8QyDiy3QG3OdnQteH8bwBjDIlj79e3qEKQOAvBzbNN+J+M5C2aZ6n9HjGZ9F9rh3H9WHlaj7fv05ewS2Ae7hvq1bO6/Gs2wEdTH/C9eM0mXzCa6V/ftjFQxHQEL4/ylEeidAMB5jo8OOKjaruL8nviEx1dD17Jycl4UWmBpef37PGQzo+0KfDJhIhIByE1bvWLmO7kmDmC7cdJRiCcxJ9gy/LH6glEKxiC4FiWN6jn6ouiHmJQonHxNwuV2nZ9zPIlKDQ7cnxglzyuXT/cRg2WHAyiHvU44CcIIGxRQ4VKIJII5SZG95L9hWFTozricAWihIufknDpkeoCltIa6t3iMlXqm/HXJMB719LLVoTieTpGUuN5Bte2Rikszgkn94wMxpgFPA0x06Y6g5AaiKWTlEbrAGxXY3bS1IlraXgu58NJsMzz4x0kgDNO9cabwlGCIYEQmWmFnIzYAKWwiaxcUOu8ooAMru5iSh0F+oCNMy3BBXHfeO1HAExRHHL1wAeWvHp//8QAJhEBAAICAQMDBAMAAAAAAAAAAQARITFBMFFxIKHREGGRsYHB8P/aAAgBAgEBPxCI9UuDLG+uo4IXOU9oOW+75i4AbO8ENT0xRVXL5Z7/ADEA/cQg+WviDdH0u9dEUmqlXOO/E48MNtLr7MWeyOCoPfmO3er8SxA1xn/EbAw2IC2vqZ7JiKxhClrXjtAND+0XYBMwAieF4n5y7jqsXvzBnaxj+5SGd4lO8EcnpfoYQr+zJBY+SCUrxLmXRjF37xIcjOF7/wASvUrKLiFjn3lQoKXPm4NCcVvefH7lH9V2HcS6KWXpy6f/xAAjEAEAAgICAQQDAQAAAAAAAAABESEAMUFRYSAwcYEQkbGh/9oACAEBAAE/EMLoAyBQAwX5cTRrQl/w8kjiSdcFFJdkUle4+AsOQUfeJNYKkWbnybrnWHEE/KuNVlXT4cUSrKVrJfkY1PTGSUYnICx8jI/Ht0NdIBSwCwG3APikSfpWPlJMUdOIA1E/ut73jGu+wyNNVezyYQsjHyANQlAqH8BI9SSYemOfZCKtxCQibGDrWW+LhKfEfFunIDgg3zl2nLY05H8Ge6Yzq7DuTnIBUDYVnOyQnjGIyV5JBKGXbk84uwbhDaEUhgPGSSelkTk7KwLqM6SWMikmG3gGzClxSKwhLMifUve0ZSAxZij7nD8FDjtUVDdA1WO/DdGN7sKZ8ONqlirDW6VJ+TBUW7kawXCQppMbk1VRsgSTvgyZCwttmND0M4SzaYqRQ+Xy1gK9FImEK4W5yYnBQiWyThFwYvD1nUr/AEr7wwnMAW+z0JOD6UykLTo6vTGFv2QrBdEiu3ADBjKwn9CvLgtaGlAEmMrZOXCe05gZkQBIQUnGSoxIIgCIQoUZwI60xT1fsm80VUEM6NYV5cqbAyOsGQIdBgisvFDglot+RY4UK44UAVC2g49KSQ4LrLYEAh6QUY3ps0BFBoBSOmXoSVxTxX69v//Z";
//	doc.addImage(imgData, 'JPEG', 15, 40, 180, 160);
	
	doc.text('Stock transaction', 40, 50);
	doc.setFontSize(11);
	doc.setTextColor(100);
	doc.text('Date and time: ' + (formatDate(new Date())), 40, 70);
	var signerAccount = $('#signer_account').text();
	doc.text('Account:            ' + signerAccount, 40, 82);
	doc.autoTable(columns, data, {
		theme : 'striped',
		startY : 95
	});
	var out = doc.output();
	var dataToSign = Base64.encode(out);
	var signedData = SigPDF(dataToSign, serialNumber, signerName);

	// Xoa danh sach sau khi da gui lenh xong
	$('#list-trans').find('tr').each(function() {
		$(this).remove();
	});
	num_of_tran = 0;
}

function formatDate(d) {
	var curr_day = d.getDate();
	var curr_month = d.getMonth();
	var curr_year = d.getFullYear();

	var curr_hour = d.getHours();
	var curr_min = d.getMinutes();
	var curr_sec = d.getSeconds();

	curr_month++; // In js, first month is 0, not 1

	return "" + curr_day + "/" + curr_month + "/" + curr_year + " " + curr_hour
			+ ":" + curr_min + ":" + curr_sec;
}

function sendData(tranData) {
	var rawData = {
		data : tranData
	};
	if(checkGetCert == 0){
		return false;
	}

	var jsonObj = JSON.stringify(rawData);
	$.ajax({
		url : 'SignatureServlet',
		type : 'POST',
		dataType : 'json',
		data : {
			para : jsonObj
		},
		success : function(data) {
			$('.spinner_03-loader').hide();
			$("#select").attr('disabled', false);
			$('#cert_chooser').modal("hide");
			if (data.type == 'OK') {
				message(data.message);
			} else {
				error(data.message);
			}
			checkGetCert = 0;
		}
	});
}

function Transaction(id, action, name, weight, price, status) {
	this.id = id;
	this.action = action;
	this.name = name;
	this.weight = weight;
	this.price = price;
	this.status = status;
}
function alowDigitOnly(parent, child) {
	$(parent).on(
			'keydown',
			child,
			function(e) {
				-1 !== $.inArray(e.keyCode, [ 46, 8, 9, 27, 13 ])
						|| /65|67|86|88/.test(e.keyCode)
						&& (!0 === e.ctrlKey || !0 === e.metaKey)
						|| 35 <= e.keyCode && 40 >= e.keyCode
						|| (e.shiftKey || 48 > e.keyCode || 57 < e.keyCode)
						&& (96 > e.keyCode || 105 < e.keyCode)
						&& e.preventDefault();

				if ($(child).val() == '' || $(child).val() == null) {
					$(child).val('0');
				}
			});
}

/**
 * Lang nghe su kien tra ve tu ham ky PDF cua plugin
 */
document.addEventListener(EXTENSION_EVENT_NAME.SIGN_PDF_BASE64, function(data) {
	var result = document.getElementById('hrSignedData').value;
	sendData(result);
});

/**
 * Lang nghe su kien tra ve tu ham getAllCerts cua plugin
 */
document.addEventListener(EXTENSION_EVENT_NAME.GET_CERTLIST_BY_FILTER,
		function(data) {
			certListEncoded = document.getElementById('hrSignedData').value;
			if (certListEncoded == null || certListEncoded == '') {
				warning("Không tìm thấy chữ ký số");
				return false;
			} else {
				var certificateList = chooseCertificate(certListEncoded);

				if (certificateList == null || certificateList == '') {
					warning("Không tìm thấy chữ ký số");
					return false;
				} else {
					checkGetCert = 1;
					$('#list-cert').empty();
					$('#list-cert').append(certificateList);
					selectCertificate();
				}
			}
		});

function changeStockData() {
	changePrice('sell', 1);
	changePrice('sell', 2);
	changePrice('sell', 3);
	changePrice('buy', 1);
	changePrice('buy', 2);
	changePrice('buy', 3);
	chainMatchPrice();
	chainTotalPrice(1);
	chainTotalPrice(2);
	chainTotalPrice(3);

}

function changePrice(act, index) {
	$('.' + act + '_price_' + index).each(
			function() {
				var __max = $(this).closest('tr').find('.max_price').text();
				var max = parseFloat(__max, 10);
				var __min = $(this).closest('tr').find('.min_price').text();
				var min = parseFloat(__min, 10);
				var __tc = $(this).closest('tr').find('.tc_price').text();
				var tc = parseFloat(__tc, 10);
				var __current = $(this).text();
				var current = parseFloat(__current, 10);
				if (current == max) {
					$(this).css('color', '#F791D2');
					$(this).closest('tr').find('.' + act + '_weigth_' + index)
							.css('color', '#F791D2');
				}
				if (current == min) {
					$(this).css('color', '#1BAFE2');
					$(this).closest('tr').find('.' + act + '_weigth_' + index)
							.css('color', '#1BAFE2');
				}
				if (current == tc) {
					$(this).css('color', '#FFFF00');
					$(this).closest('tr').find('.' + act + '_weigth_' + index)
							.css('color', '#FFFF00');
				}
				if (current < tc && current > min) {
					$(this).css('color', 'red');
					$(this).closest('tr').find('.' + act + '_weigth_' + index)
							.css('color', 'red');
				}
				if (current > tc && current < max) {
					$(this).css('color', '#0BFF09');
					$(this).closest('tr').find('.' + act + '_weigth_' + index)
							.css('color', '#0BFF09');
				}
			});
}

function chainMatchPrice() {
	$('.match_price').each(
			function() {
				var __max = $(this).closest('tr').find('.max_price').text();
				var max = parseFloat(__max, 10);
				var __min = $(this).closest('tr').find('.min_price').text();
				var min = parseFloat(__min, 10);
				var __tc = $(this).closest('tr').find('.tc_price').text();
				var tc = parseFloat(__tc, 10);
				var __current = $(this).text();
				var current = parseFloat(__current, 10);
				if (current == max) {
					$(this).css('color', '#F791D2');
					$(this).closest('tr').find('.match_weight').css('color',
							'#F791D2');
					$(this).closest('tr').find('.match_value').css('color',
							'#0BFF09');
				}
				if (current == min) {
					$(this).css('color', '#1BAFE2');
					$(this).closest('tr').find('.match_weight').css('color',
							'#1BAFE2');
					$(this).closest('tr').find('.match_value').css('color',
							'#1BAFE2');
				}
				if (current == tc) {
					$(this).css('color', '#FFFF00');
					$(this).closest('tr').find('.match_weight').css('color',
							'#FFFF00');
					$(this).closest('tr').find('.match_value').css('color',
							'#FFFF00');
				}
				if (current < tc) {
					$(this).css('color', 'red');
					$(this).closest('tr').find('.match_weight').css('color',
							'red');
					$(this).closest('tr').find('.match_value').css('color',
							'red');
				}
				if (current > tc) {
					$(this).css('color', '#0BFF09');
					$(this).closest('tr').find('.match_weight').css('color',
							'#0BFF09');
					$(this).closest('tr').find('.match_value').css('color',
							'#0BFF09');
				}
			});
}

function chainTotalPrice(index) {
	$('.total_price_' + index).each(function() {
		var __max = $(this).closest('tr').find('.max_price').text();
		var max = parseFloat(__max, 10);
		var __min = $(this).closest('tr').find('.min_price').text();
		var min = parseFloat(__min, 10);
		var __tc = $(this).closest('tr').find('.tc_price').text();
		var tc = parseFloat(__tc, 10);
		var __total_1 = $(this).text();
		var current = parseFloat(__total_1, 10);
		if (current == max) {
			$(this).css('color', '#F791D2');
		}
		if (current == min) {
			$(this).css('color', '#1BAFE2');
		}
		if (current == tc) {
			$(this).css('color', '#FFFF00');
		}
		if (current < tc && current > min) {
			$(this).css('color', 'red');
		}
		if (current > tc && current < max) {
			$(this).css('color', '#0BFF09');
		}
	});
}

function testChangeData() {
	$('.sell_price_1').each(function() {
		changeStockValue($(this));
	});
	$('.sell_price_2').each(function() {
		changeStockValue($(this));
	});
	$('.sell_price_3').each(function() {
		changeStockValue($(this));
	});
	$('.buy_price_1').each(function() {
		changeStockValue($(this));
	});
	$('.buy_price_2').each(function() {
		changeStockValue($(this));
	});
	$('.buy_price_3').each(function() {
		changeStockValue($(this));
	});
}

function changeStockValue(element) {
	var __max = $(this).closest('tr').find('.max_price').text();
	var max = parseFloat(__max, 10);
	var __min = $(this).closest('tr').find('.min_price').text();
	var min = parseFloat(__min, 10);
	var __current = $(this).text();
	var current = parseFloat(__current, 10);

	var ran = Math.random() * 100;
	var newValue = 0;
	if (ran > 30) {
		if (ran < 50 && current < max) {
			newValue = current + 0.1;
		} else if (ran > 50 && current > min) {
			newValue = current - 0.1;
		}
	}
	if (newValue != 0) {
		console.log(element + ":" + newValue);
		element.text(newValue);
		$(this).css('background-color',
				'rgba(170, 170, 170, 0.7) transition: 1s !important');
		$(this).css('background-color',
				'rgba(0,0,0,0.8) transition: 1s !important');
		changeStockData();
	}
}