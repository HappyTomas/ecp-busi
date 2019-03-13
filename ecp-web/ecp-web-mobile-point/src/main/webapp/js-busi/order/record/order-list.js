
/**
 * Created by wang on 16/8/1.
 */
$(function(){
    var pre_url = GLOBAL.WEBROOT + '/point/order/record/';
    var url = {'00':'all','01':'pay','02':'send','05':'recept','06':'recepted','99':'cancel'};
    //1、 $($html).insertBefore($opera.children(":last"));
    //2、 $($html).insertBefore($opera.closest('.shopItem').children(":first"));
    var operaIndex = {'00':1,'01':1,'02':0,'05':1,'06':2,'08':2,'99':2};
 

    var loadCallBack = function(){ 
        resetAllFormData();
    };

    //将数字转换为金额，默认返回的数字单位为分，必须除以100，并格式化两位数字
    var numberToPrice = function(num){
        return ebcUtils.numFormat(accDiv(num,100));
    };

    var resetAllFormData = function(){
        var moneyWhere = $('.opera .gopaylist').closest('.shopItem');
        var money = 0;
        $(':input[name="orderMoney"]',moneyWhere).each(function(){
            money += Number($(this).val());
        });
        var mergeOrderId = '';
        $(':input[name="orderIds"]',moneyWhere).each(function(){
            mergeOrderId += $(this).val()+',';
        });
        $('#payTotal').html('&yen; '+numberToPrice(money));
        var allPayedForm = $('#allPayedForm');
        $('input[name="orderIds"]',allPayedForm).val(mergeOrderId);
        $('input[name="orderMoney"]',allPayedForm).val(money);
    };

    //==================================下拉刷新相关===================
	$('#wrapper2').height($(window).height()-$('.am-header').height());
	
	var pageInit = {
	    'status':$('#status').val(),
	    'pageSize':3
	};
	
	
	//page 被LoadScroll变量的中page独立，传入后台pageNo叠加
	var loadScroll = new LoadScroll("wrapper2", {
	    url: GLOBAL.WEBROOT+'/point/order/scrollData',
	    isAjax:true,
	    call:loadCallBack,
	    params:{
	        status:pageInit.status,
	        pageSize:pageInit.pageSize
	    }
	});
	
	loadScroll.refreshPage({
	    params:{
	        status:pageInit.status,
	        pageSize:pageInit.pageSize
	    }
	});
    //==================================下拉刷新相关===================


    //------------------事件绑定委派------------------


    $('body').delegate('.ordlink span,.ordlink a', 'click', function() {
        //e.stopPropagation();
        var status = $(this).attr('class');
        var postfix = url[status.replace('status_','')];

        window.location.href = pre_url+(postfix==undefined?'':postfix);
    });

    $('body').delegate('.gopaylist', 'click', function(){
        //e.stopPropagation();
        var shop = $(this).closest('.shopItem');
        $('form',shop).submit();
    });

    //订单详情
    $('body').delegate('.ordclick', 'click', function(e) {
        e.stopPropagation();
        var $this = $(this).hasClass('.shopItem')?$(this):$(this).closest('.shopItem');
        var orderId = $(':input[name="orderIds"]',$this).val();
        window.location.href = GLOBAL.WEBROOT + '/point/order/detail?orderId='+orderId;
        stopDefault(e);
    });  
     
});