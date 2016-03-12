/**
 * 
 */

var checkGetCert = 0;

$(document).ready(function(){
	$('#select_cert-refresh').click(function(){
		GetAllCerts();
	});
});
function checkToken(){
	var iRet;
	var serial = "1";
	try{
		var iCheck = checkBrowser();
		if (iCheck == 1) {

			BkavExtensionSigner.SetDLLName('BkavCAv2S');
			iRet = BkavExtensionSigner.CheckToken(serial);
		} else {
			iRet = BkavPluginSigner.CheckToken(serial);
		}
		
		GetAllCerts();
	} catch (e) {
		console.log(e);
	}
}
/**
 * Lang nghe su kien tra ve tu ham getAllCerts cua plugin
 */
document.addEventListener(EXTENSION_EVENT_NAME.GET_CERTLIST_BY_FILTER,
		function(data) {
			certListEncoded = document.getElementById('hrSignedData').value;
			if (certListEncoded == null || certListEncoded == '') {
				$('#select_cert_error').text("[ERROR] Không tìm thấy chữ ký số");
				$('#select_cert_list').empty();
				return false;
			} else {
				parseGetAllCertResponse(certListEncoded);
			}
		});

function parseGetAllCertResponse(certListEn){
	var xmlString = null;
	if (iCheck == 1) {
		// Chrome
		var xmlString = Base64.decode(certListEn);
	}
	var xml = jQuery.parseXML(xmlString);
	if (xml == null || xml == '') {
		$('#select_cert_error').text("[ERROR] Không tìm thấy chữ ký số");
		$('#select_cert_list').empty();
		return "";
	}

	checkGetCert = 1;
	var certList = xml.getElementsByTagName("Certificate");

	$('#select_cert_list').empty();
	$('#select_cert_error').text("");
	for (var i = 0; i < certList.length; i++) {
		var serialNumber = certList[i].getElementsByTagName("SerialNumber")[0].innerHTML;
		var subjectCN = certList[i].getElementsByTagName("SubjectDN")[0]
				.getElementsByTagName("CN")[0].innerHTML;
		var validTo = certList[i].getElementsByTagName("TimeValidTo")[0].innerHTML;
		var issuerCN = certList[i].getElementsByTagName("IssuerDN")[0]
				.getElementsByTagName("CN")[0].innerHTML;
		$('#select_cert_list').append($('<option>', {
			value : serialNumber,
			text : subjectCN + ' - ' + serialNumber
		}));
	}
}

function Certificate(serialNumber, subjectCN, issuerCN, validTo){
	this.serialNumber = serialNumber;
	this.subjectCN = subjectCN;
	this.issuerCN = issuerCN;
	this.validTo = validTo;
}