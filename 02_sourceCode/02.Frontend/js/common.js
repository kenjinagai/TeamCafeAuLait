var idInpId = '#inp-id';
var idInpPass = '#inp-pass';
var maxFormSize = 15;
var RegAlphNum = /^[0-9a-zA-Z]+$/;
var urlIdm = 'http://localhost:8080/IdentityManagement/'
var urlLogin = urlIdm + 'login';

function checkFormSize(formAttr){
    return formAttr.length <= maxFormSize;
}

function logError(jqXHR, textStatus, errorThrown){
    console.error("Error Status Code: " , jqXHR.status)
    console.error("Error Message: ", testStatus)
    console.error("Error thrown: ", errorThrown)
}
