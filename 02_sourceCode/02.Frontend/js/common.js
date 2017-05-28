var idInpId = '#inp-id';
var idInpPass = '#inp-pass';
var idErrSessionTimeout = '#err-session-time-out';
var idErrAuth = '#err-auth-fail';
var maxFormSize = 15;
var RegAlphNum = /^[0-9a-zA-Z]+$/;
var urlIdm = 'http://localhost:8080/IdentityManagement/'
var urlLogin = urlIdm + 'login';
var classHide = 'hidden';
var cookieCsrf = 'XSRF-TOKEN';

function checkFormSize(formAttr){
    return formAttr.length <= maxFormSize;
}

function logError(jqXHR, textStatus, errorThrown){
    console.error("Error Status Code: " , jqXHR.status);
    console.error("Error Message: ", textStatus);
    console.error("Error thrown: ", errorThrown);
}
function isAlpNum(str){
    return RegAlphNum.test(str);
}

