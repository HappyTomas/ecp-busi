$(function(){
	//加载待评价的
	$('#wrapper1').height($(window).height()-$('.am-header').height()-$(".am-nav-tabs").height()-10);
    var loadScroll = new LoadScroll("wrapper1", {
        url: GLOBAL.WEBROOT+'/buyereval/unevallist',
        isAjax:true,
        params:{
        }
	});
    //加载已评价的

	$('#wrapper2').height($(window).height()-$('.am-header').height()-$(".am-nav-tabs").height() -120);

	    var loadScroll1 = new LoadScroll("wrapper2", {
	        url: GLOBAL.WEBROOT+'/buyereval/evaledlist',
	        isAjax:true,
	        params:{
	            
	        }
	});
});
function toeval(obj,orderId,orderSubId){
	var $this = $(obj);
	$("#shopId").val($this.attr('shopId'));
	$("#gdsId").val($this.attr('gdsId'));
	$("#skuId").val($this.attr('skuId'));
	$("#gdsName").val($this.attr('gdsName'));
	$("#orderId").val(orderId);
	$("#orderSubId").val(orderSubId);
	$("#imgUrl").val($this.attr('imgUrl'));
	$("#gotoeval").submit();
}
function textCounter(id,limitNum,showArea)   {			
	if   ($("#"+id).val().length  > limitNum)        
		//如果元素区字符数大于最大字符数，按照最大字符数截断；        
		$("#"+id).val($("#"+id).val().substring(0, limitNum));      
	else      
		//在记数区文本框内显示剩余的字符数；        
		$("#"+showArea).text($("#"+id).val().length);     
}