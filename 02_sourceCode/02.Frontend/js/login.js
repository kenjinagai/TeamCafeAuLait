var idInpId = '#inp-id';
var idInpPass = '#inp-pass';
var maxFormSize = 15;
var RegAlphNum = /^[0-9a-zA-Z]+$/;
var urlIdm = 'http://localhost:8080/IdentityManagement/'
var urlLogin = urlIdm + 'login';

$(document).ready(function() {
    $( "#signin-form" ).submit(function( event ) {
        // Valid text size
        var id = $( idInpId ).val();
        var pass = $( idInpPass ).val();
        if ( !(checkFormSize(id) && checkFormSize(pass))) {
            alert("Invalid text size");
            event.preventDefault();
            return;
        }
        
        if( !(isAlpNum(id) && isAlpNum(pass))) {
            alert("Text must be alphabet or number");
            return;
        }
        
        var json = JSON.stringify(getLoginObj(id, pass));
        $.ajax({
              method: "POST",
              url: urlLogin,
              dataType: "json",
              data: json,
              contentType: "application/json",
              accepts: {mycustomtype : "*/*"},
              jsonp: false
        }).done( function(data) {
            alert( data );
        }).fail(function(jqXHR, textStatus, errorThrown) {
            logError(jqXHR, textStatus, errorThrown);
            event.preventDefault();
        });
    });
});

function checkFormSize(formAttr){
    return formAttr.length <= maxFormSize;
}

function logError(jqXHR, textStatus, errorThrown){
    console.error("Error Status Code: " , jqXHR.status)
    console.error("Error Message: ", testStatus)
    console.error("Error thrown: ", errorThrown)
}


function isAlpNum(str){
    return RegAlphNum.test(str);
}

function getLoginObj(idVal, passVal){
    return {
        userId : idVal,
        password : passVal
    };
}