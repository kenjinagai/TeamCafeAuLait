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
            if(jqXHR.status === 401 || jqXHR.status === 400){
                showLoginErr(true);
            }
        });
        return event.preventDefault();
    });
});

function checkFormSize(formAttr){
    return formAttr.length <= maxFormSize;
}

function getLoginObj(idVal, passVal){
    return {
        userId : idVal,
        password : passVal
    };
}

function showLoginErr(errFlag){
    if(errFlag === true){
        $( idErrAuth ).removeClass(classHide);  
    } else{
        $( idErrAuth ).addClass(classHide);  
    }
}