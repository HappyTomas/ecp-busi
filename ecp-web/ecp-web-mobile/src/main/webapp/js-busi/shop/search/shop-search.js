$(function(){
	$('#wrapper1').height($(window).height()
    		-20
    		-$('.am-header').height()
    	);
        var loadScroll = new LoadScroll("wrapper1", {
            url:GLOBAL.WEBROOT + '/shopsearch/scroller',
            params :{keyword:$("#needSearch").val()},
            isAjax:true,
			bindevent:function(){
				$(".search-shop").each(function(){
                	var $this = $(this);
                	//滑动
            	    var w=0;
            	     $('.body-w li',$this).each(function () {
                        w = w + $(this).width();
                    });
                    $('#bodylist .body-w',$this).width(w + 10);
                    var $scrollObj = $('#bodylist',$this)[0];
                    new $.AMUI.iScroll($scrollObj, {
                        scrollX: true,
                        scrollY: false,
        				preventDefault:false
                    });
                });
			}
        });
});
function gotoshoppage(shopId){
	window.location.href =  GLOBAL.WEBROOT+'/shopmain/'+shopId;
}