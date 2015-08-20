$(function() {
//validação de email
	$(".inputEmail").on("blur", function() {
		var value = $(this).val();
		var pattern = /^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$/;
		if(!pattern.test(value)){
			$(this).val('');
		}
	});
});  
function listenerEvent(data) {
    var status = data.status; 
    switch (status) {
//        case "begin": 
//            break;
//
//        case "complete": 
//            break;

        case "success": 
        	if(window.location.pathname.indexOf("questionario/")<0){
        		window.location.reload();
        	}
            break;
    }
}