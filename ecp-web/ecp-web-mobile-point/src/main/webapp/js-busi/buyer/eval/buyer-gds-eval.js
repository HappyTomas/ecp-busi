$(function(){
	//加载待评价的
	$('#wrapper1').height($(window).height()-$('.am-header').height());
    var loadScroll = new LoadScroll("wrapper1", {
        url: GLOBAL.WEBROOT+'/buyereval/unevallist',
        isAjax:true,
        params:{
        }
	});
    //加载已评价的
	$('#wrapper2').height($(window).height()-$('.am-header').height());
	    var loadScroll = new LoadScroll("wrapper2", {
	        url: GLOBAL.WEBROOT+'/buyereval/evaledlist',
	        isAjax:true,
	        params:{
	            
	        }
	});
});
function toeval(id){
	window.location.href = GLOBAL.WEBROOT + "/buyereval/toeval?id="+id;
}
function textCounter(id,limitNum,showArea)   {			
	if   ($("#"+id).val().length  > limitNum)        
		//如果元素区字符数大于最大字符数，按照最大字符数截断；        
		$("#"+id).val($("#"+id).val().substring(0, limitNum));      
	else      
		//在记数区文本框内显示剩余的字符数；        
		$("#"+showArea).text($("#"+id).val().length);     
}