var cart_create = {
		constant : {
			ORDCARTLIST:'ordCartList',
			ORDITEMLIST:'ordCartItemList'
		},
		
};

$(function(){
	//选中和还原
	$(".info-box").click(function(){
	    var appdiv = "<i class='curmarker'></i>";
	    //加默认地址样式
	    $(this).parent().addClass('addr-def').append(appdiv);
	    //去默认地址样式
	    $(this).parent().siblings().removeClass('addr-def').find('.curmarker').remove();
	});
	
	//addr-item addr-create
	//增加收货地址
	$(".addr-create").click(function(){
		alert(1);
	});
	
	$(".pbtn-sure").click(function(){
		alert(1);
	});
	
});