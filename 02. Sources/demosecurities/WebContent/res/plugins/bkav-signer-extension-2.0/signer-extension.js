$(document).ready(function () {
    CreatControl();
});

function CreatControl() {
    window.jQuery('#ExtensionPlaceHolder').append("<input type='image' id='holderDataInputToExtension' style='visibility:hidden' style='display:none;' ClientIDMode='Static' name='' ></input>");
    window.jQuery('#ExtensionPlaceHolder').append("<input type='image' id='hrSignedData' style='visibility:hidden' style='display:none;' ClientIDMode='Static' name=''></input>"); //ResultExtensionPlaceHolder
    window.jQuery('#ExtensionPlaceHolder').append("<button type='button' id='actionToExtensionProcess' style='visibility:hidden' style='display:none;' ClientIDMode='Static' name=''></button>"); //OnClientClick, onclick = 'WaitingDataresponse();' 
}

function ProcessData(inputData) {
    var dataResponse = "";
    try {
        document.getElementById("holderDataInputToExtension").value = inputData;
        var event = document.createEvent('Event');
        event.initEvent('SendToNativeApp', true, true);
        document.dispatchEvent(event);
    } catch (e) {
        console.log("Lỗi: " + e);
    }
}

var BkavPluginSigner = {
    SignXML: function (objXmlSigner) {
        var objXml = new ObjXmlSigner();
        objXml = objXmlSigner;
        if (objXml.SigningType == XML_SIGNING_TYPE.SIGN_XML_FILE) {
            return plugin().SignXMLFile(objXml.PathFileInput, objXml.PathFileInput, objXml.TagSigning, objXml.NodeToSign, objXml.TagSaveResult, objXml.SigningTime, objXml.CertificateSerialNumber, objXml.DsSignature);
        } else if (objXml.SigningType == XML_SIGNING_TYPE.SIGN_XML_BASE64) {
            xmlOut = plugin().SignXMLBase64(objXml.Base64String, objXml.TagSigning, objXml.NodeToSign, objXml.TagSaveResult, objXml.SigningTime, objXml.CertificateSerialNumber, objXml.DsSignature);
            return xmlOut;

        } else {
            return plugin().SignXMLBase64XPath(objXml.Base64String, objXml.NameXPathFilter, objXml.TagSigning, objXml.NameIDTimeSignature, objXml.SigningTime, objXml.CertificateSerialNumber, objXml.DsSignature);
        }
        return "";
    },

    SignPDF: function (objPdf) {
        if (objPdf.SigningType == PDF_SIGNING_TYPE.SIGN_PDF_BASE64) {
            return plugin().SignPDFBase64(objPdf.Base64String, objPdf.SigningTime, objPdf.CertificateSerialNumber, objPdf.Signer);
        }
        else
            return plugin().SignPDFFile(objPdf.PathFileInput, objPdf.PathFileInput, objPdf.SigningTime, objPdf.CertificateSerialNumber, objPdf.Signer);
    },

    SignOOXML: function (objOOXml) {
        //var objOOXml = new ObjOOXmlSigner();
        if (objOOXml.SigningType = OOXML_SIGNING_TYPE.SIGN_OOXML_FILE) {
            return plugin().SignOOXMLFile(objOOXml.PathFileInput, objOOXml.PathFileInput, objOOXml.CertificateSerialNumber);
        } else {
            return plugin().SignOOXMLBase64(objOOXml.Base64String, objOOXml.CertificateSerialNumber);
        }
    },

    VerifyXML: function (objVerifier) {
        if (objVerifier.VerifyType = VERIFY_TYPE.VERYFY_BASE64) {
            return plugin().VerifyXML(objVerifier.Base64Signed, objVerifier.TimeCheck);
        } else {
            return plugin().VerifyXML(objVerifier.PathFileInput, objVerifier.TimeCheck);
        }
    },

    VerifyOOXML: function (objVerifier) {
        if (objVerifier.VerifyType = VERIFY_TYPE.VERYFY_BASE64) {
            return plugin().VerifyOOXML(objVerifier.Base64Signed, objVerifier.TimeCheck);
        } else {
            return plugin().VerifyOOXML(objVerifier.PathFileInput, objVerifier.TimeCheck);
        }
    },
    VerifyPDF: function (objVerifier) {
        if (objVerifier.VerifyType = VERIFY_TYPE.VERYFY_BASE64) {
            return plugin().VerifyPDF(objVerifier.Base64Signed, objVerifier.TimeCheck);
        } else {
            return plugin().VerifyPDF(objVerifier.PathFileInput, objVerifier.TimeCheck);
        }
    },
    ReadPDFBase64ToText: function (pdfBase64) {
        try {
            return plugin().ReadPDFBase64(pdfBase64);
        } catch (e) {
            console.log(e);
        }
    },

    GetCertIndex: function (CertificateSerialNumber) {
        return plugin().GetCertIndex(CertificateSerialNumber);
    },

    GetCertListByFilter: function (objFilter) {
        try {
            var objFilterCert = new ObjFilter();
            objFilterCert = objFilter;
            var isOnlyToken = '0', isPKCS11 = '0';
            if (objFilterCert.isOnlyCertFromToken) {
                isOnlyToken = '1';
            }
            if (objFilterCert.UsePKCS11) {
                isPKCS11 = '1';
            }
            if (objFilterCert.Filter == INFO_CERT_FILTER.CERTIFICATE_SERIAL_NUMBER) {
                return plugin().GetCertListByFilter("SerialNumber", objFilterCert.Filter, isPKCS11, isOnlyToken);
            }
            else if (objFilterCert.Filter == INFO_CERT_FILTER.CERTIFICATE_SUBJECT_CN) {
                return plugin().GetCertListByFilter("SubjectCN", objFilterCert.Filter, objFilterCert.UsePKCS11, objFilterCert.isOnlyToken);
            }
            else if (objFilterCert.Filter == INFO_CERT_FILTER.CERTIFICATE_ISSUER_CN) {
                return plugin().GetCertListByFilter("IssuerCN", objFilterCert.Filter, objFilterCert.UsePKCS11, objFilterCert.isOnlyToken);
            }
            else if (objFilterCert.Filter == INFO_CERT_FILTER.CERTIFICATE_VALIDTO) {
                return plugin().GetCertListByFilter("ValidTo", objFilterCert.Filter, objFilterCert.UsePKCS11, objFilterCert.isOnlyToken);
            }
            else if (objFilterCert.Filter == INFO_CERT_FILTER.CERTIFICATE_VALIDFROM) {
                return plugin().GetCertListByFilter("ValidFrom", objFilterCert.Filter, objFilterCert.UsePKCS11, objFilterCert.isOnlyToken);
            }
            else {
                return plugin().GetCertListByFilter("SerialNumber", "", isPKCS11, isOnlyToken);
            }
        } catch (e) {
            console.log(e);
        }
    },

    ReadPDFFileToText: function (ptrPDF) {
        try {
            return plugin().ReadPDFFileToText(ptrPDF);
        } catch (e) {
            console.log(e);
        }
    },

    ReadFormFieldsToText: function (ptrPDF) {
        try {
            return plugin().ReadFormFieldsToText(ptrPDF);
        } catch (e) {
            console.log(e);
        }
    },

    FileBrowser: function (OPEN_FILE_FILTER) {
        try {
            if (OPEN_FILE_FILTER == 0) {
                return plugin().FileBrowser("XML");
            }
            else if (OPEN_FILE_FILTER == 1) {
                return plugin().FileBrowser("PDF");
            }
            else if (OPEN_FILE_FILTER == 2) {
                return plugin().FileBrowser("WORD");
            }
            else if (OPEN_FILE_FILTER == 3) {
                return plugin().FileBrowser("EXCEL");
            }
            else
                return plugin().FileBrowser("");
        } catch (e) {
            console.log(e);
        }
    },

    CheckOCSP: function (objCert) {
        //var objCert=new ObjCertificate();
        try {
            if (objCert.CertificateBase64 != null && objCert.CertificateBase64.trim().length > 0) {
                return plugin().CheckOCSP(objCert.CertificateBase64, objCert.OcspUrl, objCert.TimeCheck);
            }
            else {
                return plugin().CheckOCSPBySerial(objCert.CertificateSerialNumber, objCert.OcspUrl, objCert.TimeCheck);
            }
        } catch (e) {
            console.log(e);
        }
    },
    CheckToken: function (serial) {
        return plugin().CheckToken(serial);
    },

    CheckCRL: function (objCert) {
        try {
            if (objCert.CertificateBase64 != null && objCert.CertificateBase64.trim().length > 0) {
                return plugin().CheckCRL(objCert.CertificateBase64, objCert.TimeCheck);
            }
            else {
                return plugin().CheckCRL(objCert.CertificateSerialNumber, objCert.TimeCheck);
            }
        } catch (e) {

        }
    },

    CheckValidTime: function (objCert) {
        //var objCert = new ObjCertificate();
        try {
            if (objCert.CertificateBase64 != null && objCert.CertificateBase64.trim().length > 0) {
                return plugin().CheckValidTime(objCert.CertificateBase64, objCert.TimeCheck);
            }
            else {
                return plugin().CheckValidTime(objCert.CertificateSerialNumber, objCert.TimeCheck);
            }
        } catch (e) {

        }
    },
    SetAESKey: function (keyAES) {
        keyAES = keyAES + '*' +CreateKeyAES();
        plugin().SetAESKey(keyAES);
    },
    SetLicenseKey: function (license) {
        return plugin().SetLicenseKey(license);
    },
    SetUsePKCS11: function (SET_USE_PKCS11) {
        if (SET_USE_PKCS11 == 0) {
            plugin().SetUsePKCS11("0");
        }
        else {
            plugin().SetUsePKCS11("1");
        }
    },

    SetPINCache: function (oneSessiosPINCache, sessionsPINCache, secondPINCache) {
        var strOneSessionPINCache = '0';
        var strSessionsPINCache = '0';

        if (oneSessiosPINCache) {
            strOneSessionPINCache = '1';
        }
        if (sessionsPINCache) {
            strSessionsPINCache = '1';
        }
        return plugin().SetPINCache(strOneSessionPINCache, strSessionsPINCache, secondPINCache);
    },
    GetVersion: function () {
        return plugin().GetVersion();
    },
    SetDLLName: function (dllNameList) {
        plugin().SetDLLName(dllNameList);
    },
    PluginValid: function () {
        return plugin().valid;
    },
    ConvertFileToBase64: function (pathFile) {
        if (pathFile == null || pathFile.trim().length == 0) {
            pathFile = "1";
        }
        return plugin().ConvertFileToBase64(pathFile);
    }
};
/************************************************************************/
/*                                                                      */
/************************************************************************/
/************************************************************************/
/* BkavExtension                                                        */
/************************************************************************/

var BkavExtensionSigner = {

    SignXML: function (objXml) {
        var dataInput = "";
        var fileToSign = objXml.FileToSign;
        var fileSigned = objXml.FileSigned;
        var tagSigning = objXml.TagSigning;
        var nodeToSign = objXml.NodeToSign;
        var tagSaveResult = objXml.TagSaveResult;
        var timeSign = objXml.SigningTime;
        var serialToken = objXml.CertificateSerialNumber;
        var b64Xml = objXml.Base64String;
        var nameXPathFilter = objXml.NameXPathFilter;
        var nameIDTimeSignature = objXml.NameIDTimeSignature;
        var dsSignature = "1";
        if (objXml == null || objXml == undefined)
            return "";
        if (serialToken == null || serialToken == 0) {
            serialToken = "1";
        }
        if (!objXml.DsSignature) {
            dsSignature = "0";
        }
        if (objXml.SigningType == XML_SIGNING_TYPE.SIGN_XML_FILE) {
            dataInput = 'SignXMLFile*' + fileToSign + '*' + fileSigned + '*' + tagSigning + '*' + nodeToSign + '*' + tagSaveResult + '*' + timeSign + '*' + serialToken + '*' + dsSignature;
        }
        else if (objXml.SigningType == XML_SIGNING_TYPE.SIGN_XML_XPATH_FILTER) {
            var dataInput = 'SignXMLBase64XPath*' + b64Xml + '*' + nameXPathFilter + '*' + tagSaveResult + '*' + nameIDTimeSignature + '*' + timeSign + '*' + serialToken + '*' + dsSignature;
        }
        else {
            //dataInput = 'SignXMLBase64*77u/PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxIU29UaHVlRFR1IHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhtbG5zPSJodHRwOi8va2VraGFpdGh1ZS5nZHQuZ292LnZuL1RLaGFpVGh1ZSI+DQoJPEhTb0toYWlUaHVlPg0KCQk8VFRpbkNodW5nPg0KCQkJPFRUaW5EVnU+DQoJCQkJPG1hRFZ1PkhUS0s8L21hRFZ1Pg0KCQkJCTx0ZW5EVnU+SFRLSzwvdGVuRFZ1Pg0KCQkJCTxwYmFuRFZ1PjMuMy40PC9wYmFuRFZ1Pg0KCQkJCTx0dGluTmhhQ0NhcERWdT48L3R0aW5OaGFDQ2FwRFZ1Pg0KCQkJPC9UVGluRFZ1Pg0KCQkJPFRUaW5US2hhaVRodWU+DQoJCQkJPFRLaGFpVGh1ZT4NCgkJCQkJPG1hVEtoYWk+MDc8L21hVEtoYWk+DQoJCQkJCTx0ZW5US2hhaT5U4bucIEtIQUkgVEhV4bq+IEdJw4EgVFLhu4ogR0lBIFTEgk5HIChN4bqrdSBz4buRIDA0L0dUR1QpPC90ZW5US2hhaT4NCgkJCQkJPG1vVGFCTWF1PihCYW4gaMOgbmgga8OobSB0aGVvIFRow7RuZyB0xrAgc+G7kSAxNTYvMjAxMy9UVC1CVEMgbmfDoHkgMDYvMTEvMjAxMyBj4bunYSBC4buZIFTDoGkgY2jDrW5oKTwvbW9UYUJNYXU+DQoJCQkJCTxwYmFuVEtoYWlYTUw+Mi4xLjA8L3BiYW5US2hhaVhNTD4NCgkJCQkJPGxvYWlUS2hhaT5DPC9sb2FpVEtoYWk+DQoJCQkJCTxzb0xhbj4wPC9zb0xhbj4NCgkJCQkJPEt5S0toYWlUaHVlPg0KCQkJCQkJPGtpZXVLeT5NPC9raWV1S3k+DQoJCQkJCQk8a3lLS2hhaT4wOS8yMDE1PC9reUtLaGFpPg0KCQkJCQkJPGt5S0toYWlUdU5nYXk+MDEvMDkvMjAxNTwva3lLS2hhaVR1TmdheT4NCgkJCQkJCTxreUtLaGFpRGVuTmdheT4zMC8wOS8yMDE1PC9reUtLaGFpRGVuTmdheT4NCgkJCQkJCTxreUtLaGFpVHVUaGFuZz48L2t5S0toYWlUdVRoYW5nPg0KCQkJCQkJPGt5S0toYWlEZW5UaGFuZz48L2t5S0toYWlEZW5UaGFuZz4NCgkJCQkJPC9LeUtLaGFpVGh1ZT4NCgkJCQkJPG1hQ1FUTm9pTm9wPjEwMTA5PC9tYUNRVE5vaU5vcD4NCgkJCQkJPHRlbkNRVE5vaU5vcD5DaGkgY+G7pWMgVGh14bq/IFF14bqtbiDEkOG7kW5nIMSRYTwvdGVuQ1FUTm9pTm9wPg0KCQkJCQk8bmdheUxhcFRLaGFpPjIwMTUtMDktMjE8L25nYXlMYXBUS2hhaT4NCgkJCQkJPEdpYUhhbj4NCgkJCQkJCTxtYUx5RG9HaWFIYW4+PC9tYUx5RG9HaWFIYW4+DQoJCQkJCQk8bHlEb0dpYUhhbj48L2x5RG9HaWFIYW4+DQoJCQkJCTwvR2lhSGFuPg0KCQkJCQk8bmd1b2lLeT5Ca2F2PC9uZ3VvaUt5Pg0KCQkJCQk8bmdheUt5PjIwMTUtMDktMjE8L25nYXlLeT4NCgkJCQkJPG5nYW5oTmdoZUtEPjwvbmdhbmhOZ2hlS0Q+DQoJCQkJPC9US2hhaVRodWU+DQoJCQkJPE5OVD4NCgkJCQkJPG1zdD4wMTAxMzYwNjk3LTk5OTwvbXN0Pg0KCQkJCQk8dGVuTk5UPkN0eSBDUCBCa2F2PC90ZW5OTlQ+DQoJCQkJCTxkY2hpTk5UPllIPC9kY2hpTk5UPg0KCQkJCQk8cGh1b25nWGE+PC9waHVvbmdYYT4NCgkJCQkJPG1hSHV5ZW5OTlQ+PC9tYUh1eWVuTk5UPg0KCQkJCQk8dGVuSHV5ZW5OTlQ+Q0c8L3Rlbkh1eWVuTk5UPg0KCQkJCQk8bWFUaW5oTk5UPjwvbWFUaW5oTk5UPg0KCQkJCQk8dGVuVGluaE5OVD5ITjwvdGVuVGluaE5OVD4NCgkJCQkJPGR0aG9haU5OVD48L2R0aG9haU5OVD4NCgkJCQkJPGZheE5OVD48L2ZheE5OVD4NCgkJCQkJPGVtYWlsTk5UPjwvZW1haWxOTlQ+DQoJCQkJPC9OTlQ+DQoJCQk8L1RUaW5US2hhaVRodWU+DQoJCTwvVFRpbkNodW5nPg0KCQk8Q1RpZXVUS2hhaUNoaW5oPg0KCQkJPGN0MjE+MTAwMDAwMDA8L2N0MjE+DQoJCQk8UFBob2lDQ2FwSEhvYT4NCgkJCQk8Y3QyMj4xMDAwMDAwMDwvY3QyMj4NCgkJCQk8Y3QyMz4xMDAwMDA8L2N0MjM+DQoJCQk8L1BQaG9pQ0NhcEhIb2E+DQoJCQk8RFZ1WER1bmdLb0Jhb1RoYXVOVkw+DQoJCQkJPGN0MjQ+MTAwMDAwMDA8L2N0MjQ+DQoJCQkJPGN0MjU+NTAwMDAwPC9jdDI1Pg0KCQkJPC9EVnVYRHVuZ0tvQmFvVGhhdU5WTD4NCgkJCTxISG9hWER1bmdDb0Jhb1RoYXVOVkw+DQoJCQkJPGN0MjY+MTAwMDAwMDA8L2N0MjY+DQoJCQkJPGN0Mjc+MzAwMDAwPC9jdDI3Pg0KCQkJPC9ISG9hWER1bmdDb0Jhb1RoYXVOVkw+DQoJCQk8SERvbmdLRG9hbmhLaGFjPg0KCQkJCTxjdDI4PjEwMDAwMDAwPC9jdDI4Pg0KCQkJCTxjdDI5PjIwMDAwMDwvY3QyOT4NCgkJCTwvSERvbmdLRG9hbmhLaGFjPg0KCQkJPFRvbmdDb25nPg0KCQkJCTxjdDMwPjQwMDAwMDAwPC9jdDMwPg0KCQkJCTxjdDMxPjExMDAwMDA8L2N0MzE+DQoJCQk8L1RvbmdDb25nPg0KCQkJPGN0MzI+NTAwMDAwMDA8L2N0MzI+DQoJCQk8Y3QzMz4xMTAwMDAwPC9jdDMzPg0KCQk8L0NUaWV1VEtoYWlDaGluaD4NCgkJPFBMdWM+DQoJCQk8UEwwNF8xX0dUR1Q+DQoJCQkJPEhIRFZLQ2hpdVRodWU+DQoJCQkJCTxDaGlUaWV0SEhEVktDaGl1VGh1ZT4NCgkJCQkJCTxIRG9uQlJhIGlkPSJJRF8xIj4NCgkJCQkJCQk8a3lIaWV1TWF1SERvbj48L2t5SGlldU1hdUhEb24+DQoJCQkJCQkJPGt5SGlldUhEb24+PC9reUhpZXVIRG9uPg0KCQkJCQkJCTxzb0hEb24+MTwvc29IRG9uPg0KCQkJCQkJCTxuZ2F5UEhhbmg+MjAxNS0wOS0wMTwvbmdheVBIYW5oPg0KCQkJCQkJCTx0ZW5OTVVBPmtow6FjaCBs4bq7PC90ZW5OTVVBPg0KCQkJCQkJCTxtc3ROTVVBIHhzaTpuaWw9InRydWUiLz4NCgkJCQkJCQk8bWF0SGFuZz48L21hdEhhbmc+DQoJCQkJCQkJPGRzb0JhbkNodWFUaHVlPjEwMDAwMDAwPC9kc29CYW5DaHVhVGh1ZT4NCgkJCQkJCQk8Z2hpQ2h1PjwvZ2hpQ2h1Pg0KCQkJCQkJPC9IRG9uQlJhPjwvQ2hpVGlldEhIRFZLQ2hpdVRodWU+DQoJCQkJCTx0b25nRG9hbmhTb0Jhbl8xPjEwMDAwMDAwPC90b25nRG9hbmhTb0Jhbl8xPg0KCQkJCTwvSEhEVktDaGl1VGh1ZT4NCgkJCQk8SEhEVlRodWUxPg0KCQkJCQk8Q2hpVGlldEhIRFZUaHVlMT4NCgkJCQkJCTxIRG9uQlJhIGlkPSJJRF8xIj4NCgkJCQkJCQk8a3lIaWV1TWF1SERvbj48L2t5SGlldU1hdUhEb24+DQoJCQkJCQkJPGt5SGlldUhEb24+PC9reUhpZXVIRG9uPg0KCQkJCQkJCTxzb0hEb24+Mjwvc29IRG9uPg0KCQkJCQkJCTxuZ2F5UEhhbmg+MjAxNS0wOS0wMjwvbmdheVBIYW5oPg0KCQkJCQkJCTx0ZW5OTVVBPmtow6FjaCBs4bq7PC90ZW5OTVVBPg0KCQkJCQkJCTxtc3ROTVVBIHhzaTpuaWw9InRydWUiLz4NCgkJCQkJCQk8bWF0SGFuZz48L21hdEhhbmc+DQoJCQkJCQkJPGRzb0JhbkNodWFUaHVlPjEwMDAwMDAwPC9kc29CYW5DaHVhVGh1ZT4NCgkJCQkJCQk8Z2hpQ2h1PjwvZ2hpQ2h1Pg0KCQkJCQkJPC9IRG9uQlJhPjwvQ2hpVGlldEhIRFZUaHVlMT4NCgkJCQkJPHRvbmdEb2FuaFNvQmFuXzI+MTAwMDAwMDA8L3RvbmdEb2FuaFNvQmFuXzI+DQoJCQkJPC9ISERWVGh1ZTE+DQoJCQkJPEhIRFZUaHVlNT4NCgkJCQkJPENoaVRpZXRISERWVGh1ZTU+DQoJCQkJCQk8SERvbkJSYSBpZD0iSURfMSI+DQoJCQkJCQkJPGt5SGlldU1hdUhEb24+PC9reUhpZXVNYXVIRG9uPg0KCQkJCQkJCTxreUhpZXVIRG9uPjwva3lIaWV1SERvbj4NCgkJCQkJCQk8c29IRG9uPjM8L3NvSERvbj4NCgkJCQkJCQk8bmdheVBIYW5oPjIwMTUtMDktMDM8L25nYXlQSGFuaD4NCgkJCQkJCQk8dGVuTk1VQT5raMOhY2ggbOG6uzwvdGVuTk1VQT4NCgkJCQkJCQk8bXN0Tk1VQSB4c2k6bmlsPSJ0cnVlIi8+DQoJCQkJCQkJPG1hdEhhbmc+PC9tYXRIYW5nPg0KCQkJCQkJCTxkc29CYW5DaHVhVGh1ZT4xMDAwMDAwMDwvZHNvQmFuQ2h1YVRodWU+DQoJCQkJCQkJPGdoaUNodT48L2doaUNodT4NCgkJCQkJCTwvSERvbkJSYT48L0NoaVRpZXRISERWVGh1ZTU+DQoJCQkJCTx0b25nRG9hbmhTb0Jhbl8zPjEwMDAwMDAwPC90b25nRG9hbmhTb0Jhbl8zPg0KCQkJCTwvSEhEVlRodWU1Pg0KCQkJCTxISERWVGh1ZTM+DQoJCQkJCTxDaGlUaWV0SEhEVlRodWUzPg0KCQkJCQkJPEhEb25CUmEgaWQ9IklEXzEiPg0KCQkJCQkJCTxreUhpZXVNYXVIRG9uPjwva3lIaWV1TWF1SERvbj4NCgkJCQkJCQk8a3lIaWV1SERvbj48L2t5SGlldUhEb24+DQoJCQkJCQkJPHNvSERvbj40PC9zb0hEb24+DQoJCQkJCQkJPG5nYXlQSGFuaD4yMDE1LTA5LTA0PC9uZ2F5UEhhbmg+DQoJCQkJCQkJPHRlbk5NVUE+a2jDoWNoIGzhurs8L3Rlbk5NVUE+DQoJCQkJCQkJPG1zdE5NVUEgeHNpOm5pbD0idHJ1ZSIvPg0KCQkJCQkJCTxtYXRIYW5nPjwvbWF0SGFuZz4NCgkJCQkJCQk8ZHNvQmFuQ2h1YVRodWU+MTAwMDAwMDA8L2Rzb0JhbkNodWFUaHVlPg0KCQkJCQkJCTxnaGlDaHU+PC9naGlDaHU+DQoJCQkJCQk8L0hEb25CUmE+PC9DaGlUaWV0SEhEVlRodWUzPg0KCQkJCQk8dG9uZ0RvYW5oU29CYW5fND4xMDAwMDAwMDwvdG9uZ0RvYW5oU29CYW5fND4NCgkJCQk8L0hIRFZUaHVlMz4NCgkJCQk8SEhEVlRodWUyPg0KCQkJCQk8Q2hpVGlldEhIRFZUaHVlMj4NCgkJCQkJCTxIRG9uQlJhIGlkPSJJRF8xIj4NCgkJCQkJCQk8a3lIaWV1TWF1SERvbj48L2t5SGlldU1hdUhEb24+DQoJCQkJCQkJPGt5SGlldUhEb24+PC9reUhpZXVIRG9uPg0KCQkJCQkJCTxzb0hEb24+NTwvc29IRG9uPg0KCQkJCQkJCTxuZ2F5UEhhbmg+MjAxNS0wOS0wNTwvbmdheVBIYW5oPg0KCQkJCQkJCTx0ZW5OTVVBPmtow6FjaCBs4bq7PC90ZW5OTVVBPg0KCQkJCQkJCTxtc3ROTVVBIHhzaTpuaWw9InRydWUiLz4NCgkJCQkJCQk8bWF0SGFuZz48L21hdEhhbmc+DQoJCQkJCQkJPGRzb0JhbkNodWFUaHVlPjEwMDAwMDAwPC9kc29CYW5DaHVhVGh1ZT4NCgkJCQkJCQk8Z2hpQ2h1PjwvZ2hpQ2h1Pg0KCQkJCQkJPC9IRG9uQlJhPjwvQ2hpVGlldEhIRFZUaHVlMj4NCgkJCQkJPHRvbmdEb2FuaFNvQmFuXzU+MTAwMDAwMDA8L3RvbmdEb2FuaFNvQmFuXzU+DQoJCQkJPC9ISERWVGh1ZTI+DQoJCQkJPHRvbmdEVGh1QlJhPjUwMDAwMDAwPC90b25nRFRodUJSYT4NCgkJCQk8dG9uZ0RUaHVCUmFDaGl1VGh1ZT4xMDAwMDAwMDwvdG9uZ0RUaHVCUmFDaGl1VGh1ZT4NCgkJCQk8dG9uZ1RodWVCUmE+NDAwMDAwMDA8L3RvbmdUaHVlQlJhPg0KCQkJPC9QTDA0XzFfR1RHVD4NCgkJPC9QTHVjPg0KCTwvSFNvS2hhaVRodWU+DQo8L0hTb1RodWVEVHU+DQo=*HSoKhaiThue*_NODE_TO_SIGN*CKyDTu*2015/04/08 10:50:11*5403103a726ef61092b0ab893a06c1a6';
            dataInput = 'SignXMLBase64*' + b64Xml + '*' + tagSigning + '*' + nodeToSign + '*' + tagSaveResult + '*' + timeSign + '*' + serialToken + '*' + dsSignature;
        }
        return ProcessData(dataInput);
    },
    //trong code sẽ tự động kiểm tra xem dữ liệu vào là base64 hay file và gọi đến các hàm xử lý tương ứng, không cần đặt ra 2 tên hàm xử lý base64 và file riêng.
    SignPDF: function (objPdf) {
        //var objPdf = new ObjPdfSigner();
        var dataInput = "";
        if (objPdf == null || objPdf == undefined)
            return "";
        if (objPdf.CertificateSerialNumber == null || objPdf.CertificateSerialNumber.trim().length == 0) {
            objPdf.CertificateSerialNumber = "1";
        }
        if (objPdf.SigningType == PDF_SIGNING_TYPE.SIGN_PDF_FILE) {
            dataInput = 'SignPDFFile*' + objPdf.PathFileInput + '*' + objPdf.PathFileInput + '*' + objPdf.SigningTime + '*' + objPdf.CertificateSerialNumber + '*' + objPdf.Signer;
        }
        else {
            dataInput = "SignPDFBase64*" + objPdf.Base64String + '*' + objPdf.SigningTime + '*' + objPdf.CertificateSerialNumber + '*' + objPdf.Signer;
        }
        return ProcessData(dataInput);
    },

    SignOOXML: function (objOOXml) {
        var dataInput = "";
        if (objOOXml == null || objOOXml == undefined)
            return "";
        if (objOOXml.CertificateSerialNumber == null || objOOXml.CertificateSerialNumber.trim().length == 0) {
            objOOXml.CertificateSerialNumber = "1";
        }
        if (objOOXml.SigningType == OOXML_SIGNING_TYPE.SIGN_OOXML_FILE) {
            dataInput = "SignOOXMLFile*" + objOOXml.PathFileInput + '*' + objOOXml.PathFileInput + '*' + objOOXml.CertificateSerialNumber;
        }
        else {
            dataInput = "SignOOXMLBase64*" + objOOXml.Base64String + '*' + objOOXml.CertificateSerialNumber;
        }
        return ProcessData(dataInput);
    },

    VerifyXML: function (objVerifier) {
        var dataInput = "";
        if (objVerifier.TimeCheck == null || objVerifier.TimeCheck.trim().length == 0) {
            objVerifier.TimeCheck = "1";
        }
        if (objVerifier.VerifyType = VERIFY_TYPE.VERYFY_BASE64) {
            dataInput = "VerifyXML*" + objVerifier.Base64Signed + '*' + objVerifier.TimeCheck;
        } else {
            dataInput = "VerifyXML*" + objVerifier.PathFileInput + '*' + objVerifier.TimeCheck;
        }
        return ProcessData(dataInput);
    },

    VerifyOOXML: function (objVerifier) {
        var dataInput = "";
        if (objVerifier.TimeCheck == null || objVerifier.TimeCheck.trim().length == 0) {
            objVerifier.TimeCheck = "1";
        }
        if (objVerifier.VerifyType = VERIFY_TYPE.VERYFY_BASE64) {
            dataInput = "VerifyOOXML*" + objVerifier.Base64Signed + '*' + objVerifier.TimeCheck;
        }
        else {
            dataInput = "VerifyOOXML*" + objVerifier.PathFileInput + '*' + objVerifier.TimeCheck;
        }
        return ProcessData(dataInput);
    },
    VerifyPDF: function (objVerifier) {
        if (objVerifier.TimeCheck == null || objVerifier.TimeCheck.trim().length == 0) {
            objVerifier.TimeCheck = "1";
        }
        if (objVerifier.VerifyType = VERIFY_TYPE.VERYFY_BASE64) {
            dataInput = "VerifyPDF*" + objVerifier.Base64Signed + '*' + objVerifier.TimeCheck;
        }
        else {
            dataInput = "VerifyPDF*" + objVerifier.PathFileInput + '*' + objVerifier.TimeCheck;
        }
        return ProcessData(dataInput);
    },

    //Utils function

    ReadPDFBase64ToText: function (pdfBase64) {
        if (pdfBase64 == null || pdfBase64.trim().length == 0) {
            pdfBase64 = "1";
        }
        var dataInput = 'ReadPDFBase64ToText*' + pdfBase64;
        return ProcessData(dataInput);
    },

    GetCertListByFilter: function (objFilter) {
        try {
            var dataInput, usePKCS11 = '0';
            var isOnlyToken = '0';
            var objFilterCert = new ObjFilter();
            objFilterCert = objFilter;
            if (objFilterCert.Value == "") {
                objFilterCert.Value = "1";
            }
            if (objFilterCert.UsePKCS11) {
                usePKCS11 = '1';
            }
            if (objFilterCert.isOnlyCertFromToken) {
                isOnlyToken = '1';
            }

            if (objFilterCert.Filter == INFO_CERT_FILTER.SerialNumber) {
                dataInput = "GetCertListByFilter*SerialNumber" + objFilterCert.Value + '*' + usePKCS11 + '*' + isOnlyToken;
            }
            else if (objFilterCert.Filter == INFO_CERT_FILTER.SubjectCN) {
                dataInput = "GetCertListByFilter*SubjectCN" + objFilterCert.Value + '*' + usePKCS11 + '*' + isOnlyToken;
            }
            else if (objFilterCert.Filter == INFO_CERT_FILTER.IssuerCN) {
                dataInput = "GetCertListByFilter*IssuerCN" + objFilterCert.Value + '*' + usePKCS11 + '*' + isOnlyToken;
            }
            else if (objFilterCert.Filter == INFO_CERT_FILTER.ValidTo) {
                dataInput = "GetCertListByFilter*ValidTo" + objFilterCert.Value + '*' + usePKCS11 + '*' + isOnlyToken;
            }
            else if (objFilterCert.Filter == INFO_CERT_FILTER.ValidFrom) {
                dataInput = "GetCertListByFilter*ValidFrom" + objFilterCert.Value + '*' + usePKCS11 + '*' + isOnlyToken;
            }
            else
                dataInput = "GetCertListByFilter*SerialNumber*1" + '*' + usePKCS11 + '*' + isOnlyToken;
            return ProcessData(dataInput);
        } catch (e) {
            console.log(e);
        }
    },

    ReadPDFFileToText: function (pdfFile) {
        if (pdfFile == null || pdfFile.trim().length == 0) {
            pdfFile = "1";
        }
        var dataInput = 'ReadPDFFileToText*' + pdfFile;
        return ProcessData(dataInput);
    },

    ReadFormFieldsToText: function (pdfFile) {
        if (pdfFile == null || pdfFile.trim().length == 0) {
            pdfFile = "1";
        }
        var dataInput = 'ReadFormFieldsToText*' + pdfFile;
        return ProcessData(dataInput);
    },

    FileBrowser: function (OPEN_FILE_FILTER) {
        if (OPEN_FILE_FILTER == 0) {
            var dataInput = 'FileBrowser*XML';
        }
        else if (OPEN_FILE_FILTER == 1) {
            var dataInput = 'FileBrowser*PDF';
        }
        else if (OPEN_FILE_FILTER == 2) {
            var dataInput = 'FileBrowser*WORD';
        }
        else if (OPEN_FILE_FILTER == 3) {
            var dataInput = 'FileBrowser*EXCEL';
        }
        else
            var dataInput = 'FileBrowser*1';
        return ProcessData(dataInput);
    },

    CheckOCSP: function (objCert) {
        //var objCert=new ObjCertificate();
        if (objCert.TimeCheck == null || objCert.TimeCheck.trim().length == 0) {
            objCert.TimeCheck = "1";
        }
        var dataInput = "";
        if (objCert.CertificateBase64 != null && objCert.CertificateBase64.trim().length > 0) {
            dataInput = 'CheckOCSP*' + objCert.CertificateBase64 + '*' + objCert.OcspUrl + '*' + objCert.TimeCheck;
        }
        else {
            dataInput = 'CheckOCSPBySerial*' + objCert.CertificateSerialNumber + '*' + objCert.OcspUrl + '*' + objCert.TimeCheck;
        }
        return ProcessData(dataInput);
    },

    CheckCRL: function (objCert) {
        var dataInput = "";
        if (objCert.TimeCheck == null || objCert.TimeCheck.trim().length == 0) {
            objCert.TimeCheck = "1";
        }
        if (objCert.CertificateBase64 != null && objCert.CertificateBase64.trim().length > 0) {
            dataInput = 'CheckCRL*' + objCert.CertificateBase64 + '*' + objCert.TimeCheck;
        }
        else {
            dataInput = 'CheckCRL*' + objCert.CertificateSerialNumber + '*' + objCert.TimeCheck;
        }
        return ProcessData(dataInput);
    },

    CheckValidTime: function (objCert) {
        //var objCert = new ObjCertificate();
        if (objCert.TimeCheck == null || objCert.TimeCheck.trim().length == 0) {
            objCert.TimeCheck = "1";
        }
        var dataInput = "";
        if (objCert.CertificateBase64 != null && objCert.CertificateBase64.trim(), length > 0) {
            dataInput = 'CheckValidTime*' + objCert.CertificateBase64 + '*' + objCert.TimeCheck;
        }
        else {
            dataInput = 'CheckValidTime*' + objCert.CertificateSerialNumber + '*' + objCert.TimeCheck;
        }
        return ProcessData(dataInput);
    },

    CheckToken: function (CertificateSerialNumber) {
        if (CertificateSerialNumber == null || CertificateSerialNumber.trim().length == 0) {
            CertificateSerialNumber = "1";
        }
        var dataInput = 'CheckToken*' + CertificateSerialNumber;
        return ProcessData(dataInput);
    },
    //PdfUtils
    SetAESKey: function (keyAES) {
        keyAES = keyAES + '*' +CreateKeyAES();
        var dataInput = 'SetAESKey*' + keyAES;
        return ProcessData(dataInput);
    },
    SetUsePKCS11: function (SET_USE_PKCS11) {
        var dataInput;
        if (SET_USE_PKCS11 == 0) {
            dataInput = 'SetUsePKCS11*0';
        }
        else {
            dataInput = 'SetUsePKCS11*1';
        }
        return ProcessData(dataInput);
    },
    SetDLLName: function (dllNameList) {
        var dataInput;
        if (dllNameList == null || dllNameList.trim().length == 0) {
            dllNameList = "1";
        }
        var dataInput = 'SetDLLName*' + dllNameList;
        return ProcessData(dataInput);
    },
    ConvertFileToBase64: function (pathFile) {
        if (pathFile == null || pathFile.trim().length == 0) {
            pathFile = "1";
        }
        var dataInput = 'ConvertFileToBase64*' + pathFile;
        return ProcessData(dataInput);
    },
    GetCertIndex: function (CertificateSerialNumber) {
        if (CertificateSerialNumber == null || CertificateSerialNumber.trim().length == 0) {
            CertificateSerialNumber = "1";
        }
        var dataInput = 'GetCertIndex*' + CertificateSerialNumber;
        return ProcessData(dataInput);
    },
    SetLicenseKey: function (license) {
        var dataInput = 'SetLicenseKey*' + license;
        return ProcessData(dataInput);
    },
    ExtensionValid: function () {
        var dataInput = 'ExtensionValid*1';
        return ProcessData(dataInput);
    },
    GetAllExtensions: function () {
        var dataInput = 'GetAllExtensions*';
        return ProcessData(dataInput);
    },
    GetSelfExtension: function () {
        var dataInput = 'GetSelfExtension*';
        return ProcessData(dataInput);
    },
    GetExtensionWithID: function (id) {
        var dataInput = 'GetExtensionWithID*' + id;
        return ProcessData(dataInput);
    },
    /************************************************************************/
    /* Set PIN Cache                                                        */
    /************************************************************************/
    SetPINCache: function (oneSessiosPINCache, sessionsPINCache, secondPINCache) {
        var strOneSessionPINCache = '0';
        var strSessionsPINCache = '0';

        if (oneSessiosPINCache) {
            strOneSessionPINCache = '1';
        }
        if (sessionsPINCache) {
            strSessionsPINCache = '1';
        }
        var dataInput = 'SetPINCache*' + strOneSessionPINCache + '*' + strSessionsPINCache + '*' + secondPINCache;
        return ProcessData(dataInput);
    },
    GetVersion: function () {
        return ProcessData('GetVersion*1')
    },
    // Cac ham ho tro version cu 1.0:
    ReadFileToBase64: function (pathFile) {
        var dataInput = 'ReadFileToBase64*' + pathFile;
        return ProcessData(dataInput);
    },
    GetAllCert: function (filter, value) {
        var dataInput = "GetAllCert*" + filter + '*' + value;
        return ProcessData(dataInput);
    },
    SignOffice: function (fileIn, fileOut, serialCert) {
        var dataInput = "SignOffice*" + fileIn + '*' + fileOut + '*' + serialCert;
        return ProcessData(dataInput);
    },
    SignOfficeBase64: function (b64Office, serialCert) {
        var dataInput = "SignOffice*" + b64Office + '*' + serialCert;
        return ProcessData(dataInput);
    },
    SignXMLBase64: function (b64Xml, tagSigning, nodeToSign, tagSaveResult, timeSign, serialToken) {
        //var dataInput = 'SignXML*' + b64Xml + '*' + tagSigning + '*' + nodeToSign + '*' + tagSaveResult + '*' + timeSign + '*' + serialToken;
        var dataInput = 'SignXMLBase64*' + b64Xml + '*' + tagSigning + '*' + nodeToSign + '*' + tagSaveResult + '*' + timeSign + '*' + serialToken;
        return ProcessData(dataInput);
    }
};

/**
* Đây là hàm demo dạng định nghĩa danh sách kiểu enum.
*/
VERIFY_STATUS = {
    GOOD: 0,
    DATA_INVALID: 1,
    CERTIFICATE_EXPIRE: 2,
    CERTIFICATE_REVOKED: 3,
    CERTIFICATE_HOLD: 4,
    CERTIFICATE_NOT_TRUST: 5
};

XML_SIGNING_TYPE = {
    SIGN_XML_FILE: 0,
    SIGN_XML_BASE64: 1,
    SIGN_XML_XPATH_FILTER: 2
};
PDF_SIGNING_TYPE = {
    SIGN_PDF_FILE: 0,
    SIGN_PDF_BASE64: 1
};
OOXML_SIGNING_TYPE = {
    SIGN_OOXML_FILE: 0,
    SIGN_OOXML_BASE64: 1
};
INFO_CERT_FILTER = {
    CERTIFICATE_SERIAL_NUMBER: 0,
    CERTIFICATE_SUBJECT_CN: 1,
    CERTIFICATE_ISSUER_CN: 2,
    CERTIFICATE_VALIDTO: 3,
    CERTIFICATE_VALIDFROM: 4
};
OPEN_FILE_FILTER = {
    XML: 0,
    PDF: 1,
    DOCX: 2,
    XLSX: 3//
};
SET_USE_PKCS11 = {
    YES: 1,
    NO: 0
};
VERIFY_TYPE = {
    VERYFY_BASE64: 0,
    VERYFY_FILE: 1
};
//EVENT_EXTENSION = {
//    OOXMLFile: 0,
//    OOXMLBase64: 1,
//};
/**
* Đây là đối tượng ký xml.
* @FileIn Dữ liệu dạng đường dẫn đến tập tin Xml cần ký.
* @Base64In Dữ liệu dạng xml Base64 String cần ký.
* @FileOut Đây là kết quả trả về sau khi ký file dạng đưa vào đường dẫn, nếu là ký Base64Xml thì để null trường này.
* @TagSigning Đây là thẻ dữ liệu cần ký trong tài liệu. Nếu để null thì mặc định hệ thống sẽ ký toàn bộ tài liệu.
* @NodeToSign .
* @TagSaveResult Đây là thẻ lưu chữ ký.
* @SigningTime Thời gian ký.
* @CertificateSerialNumber serial number của cert dùng để ký.
* @NameXPathFilter Thẻ dữ liệu cần ký theo chuẩn XPath Filter 2.0.
* @NameIDTimeSignature ID của thẻ thời gian (Ký theo chuẩn XPath Filter 2.0).
* @DsSignature Tiền tố ds:.
* @SigningType Kiểu ký XML.
*/
function ObjXmlSigner() {
    this.PathFileInput = "";
    this.Base64String = "";
    this.PathFileInput = "";
    this.TagSigning = "";
    this.NodeToSign = "";
    this.TagSaveResult = "";
    this.SigningTime = "";
    this.CertificateSerialNumber = "";
    this.NameXPathFilter = "";
    this.NameIDTimeSignature = "";
    this.DsSignature = true;
    this.SigningType = XML_SIGNING_TYPE.SIGN_XML_BASE64;
}

function ObjOOXmlSigner() {
    this.PathFileInput = "";
    this.Base64String = "";
    this.PathFileInput = "";
    this.CertificateSerialNumber = "";
    this.SigningType = OOXML_SIGNING_TYPE.SIGN_OOXML_BASE64;
}

function ObjPdfSigner() {
    this.PathFileInput = "";
    this.Base64String = "";
    this.PathFileInput = "";
    this.SigningTime = "";
    this.CertificateSerialNumber = "";
    this.Signer = "";
    this.SigningType = PDF_SIGNING_TYPE.SIGN_PDF_BASE64;
}

function ObjVerifier() {
    this.OriginalData = "";
    this.PathFileInput = "";
    this.Base64Signed = "";
    this.TimeCheck = "";
    this.VerifyType = VERIFY_TYPE.VERYFY_BASE64;
}

function ObjCertificate() {
    this.CertificateBase64 = "";
    this.CertificateSerialNumber = "";
    this.OcspUrl = "";
    this.TimeCheck = "";
}

function ObjFilter() {
    this.Filter = INFO_CERT_FILTER.SerialNumber;
    this.Value = "";
    this.UsePKCS11 = false;
    this.isOnlyCertFromToken = false;
}

//stringToDate("17/9/2014", "dd/MM/yyyy", "/");
//stringToDate("9/17/2014", "mm/dd/yyyy", "/")
//stringToDate("9-17-2014", "mm-dd-yyyy", "-")

function stringToDate(_date, _format, _delimiter) {
    var formatLowerCase = _format.toLowerCase();
    var formatItems = formatLowerCase.split(_delimiter);
    var dateItems = _date.split(_delimiter);
    var monthIndex = formatItems.indexOf("mm");
    var dayIndex = formatItems.indexOf("dd");
    var yearIndex = formatItems.indexOf("yyyy");
    var month = parseInt(dateItems[monthIndex]);
    month -= 1;
    var formatedDate = new Date(dateItems[yearIndex], month, dateItems[dayIndex]);
    return formatedDate;
}

/*
*	Event Name
*/
EXTENSION_EVENT_NAME = {
    // Sign XMl
    SIGN_XML_FILE: 'SignXMLFile',
    SIGN_XML_BASE64: 'SignXMLBase64',
    SIGN_XML_XPATH_FILTER: 'SignXMLBase64XPath',
    // Sign PDF
    SIGN_PDF_FILE: 'SignPDFFile',
    SIGN_PDF_BASE64: 'SignPDFBase64',
    // SIGN OOXML
    SIGN_OOXML_FILE: 'SignOOXMLFile',
    SIGN_OOXML_BASE64: 'SignOOXMLBase64',
    // VERIFY
    VERIFY_XML: 'VerifyXML',
    VERIFY_PDF: 'VerifyPDF',
    VERIFY_OOXML: 'VerifyOOXML',
    // CHECK
    CHECK_OCSP: 'CheckOCSP',
    CHECK_OCSP_BY_SERIAL: 'CheckOCSPBySerial',
    CHECK_CRL: 'CheckCRL',
    CHECK_VALID_TIME: 'CheckValidTime',
    CHECK_TOKEN: 'CheckToken',
    // GET INFO CERT
    GET_CERTLIST_BY_FILTER: 'GetCertListByFilter',
    GET_CERT_INDEX: 'GetCertIndex',
    // READ FILE
    READ_PDF_BASE64_TO_TEXT: 'ReadPDFBase64ToText',
    READ_PDF_FILE_TO_TEXT: 'ReadPDFFileToText',
    READ_FORM_FIELS_TO_TEXT: 'ReadFormFieldsToText',
    CONVERT_FILE_TO_BASE64: 'ConvertFileToBase64',
    SET_LICENSE_KEY: 'SetLicenseKey',
    // FILE BROWSER
    FILE_BROWSER: 'FileBrowser',
    ERROR_MESSAGE: 'ExtensionError',
    GET_SELF_EXTENSION: 'GetSelfExtension',
    GET_EXTENSION_WITH_ID: 'GetExtensionWithID',
    GET_ALL_EXTENSION: 'GetAllExtensions',

    // GEt version
    GET_VERSION: 'GetVersion',
    SET_PIN_CACHE: 'SetPINCache'
};

/************************************************************************/
/* Utils                                                                */
/************************************************************************/
function CreateKeyAES() {
    var key1 = window.location.host;
    var key3 = (key1.length) * (key1.length) + (key1.length);
    var key2 = key1 + '*' + key3 + '*11';
    return key2;
}