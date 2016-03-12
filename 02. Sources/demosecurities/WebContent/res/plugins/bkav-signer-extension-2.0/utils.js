// FileBrowser
document.addEventListener(EXTENSION_EVENT_NAME.FILE_BROWSER, function (data) {
    var result = document.getElementById('hrSignedData').value;
    console.log(result);
    alert(result);
});
var iCheck = checkBrowser();
function FileBrowser() {
    try {
        if (iCheck == 1) {
            BkavExtensionSigner.FileBrowser(OPEN_FILE_FILTER.DOCX);
        }
        else {
            var result = BkavPluginSigner.FileBrowser(OPEN_FILE_FILTER.DOCX);
            alert(result);
        }
    }
    catch (e) {
        alert(e)
    }
}
// GetAllCert
function GetAllCerts() {
    try {
        var objFilterCert = new ObjFilter();
        objFilterCert.Value = "";
        objFilterCert.Filter = "SerialNumber";
        objFilterCert.UsePKCS11 = false;
        objFilterCert.isOnlyCertFromToken = true;

        var dllName = "bkavcaetoken,bkavcsp,BkavCA,BkavCAv2S";
        if (iCheck == 1) {
            BkavExtensionSigner.SetDLLName(dllName);
            BkavExtensionSigner.GetCertListByFilter(objFilterCert);
        }
        else {
            BkavPluginSigner.SetDLLName(dllName);
            var result = BkavPluginSigner.GetCertListByFilter(objFilterCert);
            alert(result);
        }
    }
    catch (e) {
        alert(e)
    }
}
document.addEventListener(EXTENSION_EVENT_NAME.GET_VERSION, function (data) {
    var result = document.getElementById('hrSignedData').value;
    alert(result);

});
function GetVersion() {
    if (iCheck == 1) {
        BkavExtensionSigner.GetVersion();
    }
    else
        alert(BkavPluginSigner.GetVersion());
}
// GetCertIndex
function GetCertIndex() {
    try {
        var serial = "5403298abdf42f20748915f96e65bfaa";
        BkavExtensionSigner.GetCertIndex(serial);
    }
    catch (e) {
        alert(e)
    }
}
function checkBrowser() {
    if (navigator.userAgent.indexOf("Chrome") != -1) {
        return 1;
    }
    else if (navigator.userAgent.indexOf("Opera") != -1) {
        return 2;
    }
    else if (navigator.userAgent.indexOf("Firefox") != -1) {
        return 3;
    }
    else if ((navigator.userAgent.indexOf("MSIE") != -1) || (!!document.documentMode == true)) //IF IE > 10
    {
        return 4;
    }
    else {
        return 5;
    }
}