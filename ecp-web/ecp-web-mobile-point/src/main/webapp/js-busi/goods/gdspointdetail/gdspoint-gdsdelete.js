$(function(){
    var se = 15;
    var timer = window.setInterval(function(){
    	se --;
        $("#J_ShowDiv").html(+se);
        if(se==0){
        	window.location.href= GLOBAL.WEBROOT + "/homepage";
            clearInterval(timer);
            }
    },1000);
});