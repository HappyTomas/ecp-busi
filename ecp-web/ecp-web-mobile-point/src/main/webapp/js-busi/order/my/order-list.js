/**
 * Created by wang on 16/8/1.
 */
$(function(){
    var pre_url = GLOBAL.WEBROOT + '/point/order/my/';
    var url = {'00':'all','01':'pay','02':'send','04':'send','05':'recept','06':'recepted','80':'recepted','07':'cancel','08':'cancel','99':'cancel'};
    //1、 $($html).insertBefore($opera.children(":last"));
    //2、 $($html).insertBefore($opera.closest('.shopItem').children(":first"));
    var operaIndex = {'00':1,'01':1,'02':0,'05':1,'80':2,'06':2,'07':2,'08':2,'99':2};

    function stopDefault(e) {
        //阻止默认浏览器动作(W3C)
        if (e && e.preventDefault)
            e.preventDefault();
        //IE中阻止函数器默认动作的方式
        else
            window.event.returnValue = false;
        return false;
    }

    //操作片段
    var createOperaHtml = function(obj){
        var $opera = $(obj);
        var status = $('#shop_order_status',$opera).val();
        var dispatchType = $('#shop_order_dispatchType',$opera).val();
        var $html = '';
        if(operaIndex[status] == 2){
            $html= '<div class="odr-status sus"></div>';
            return $html;
        }
        if(status == "01") {
            $html= '<span class="btn btn-gw gopaylist">去支付</span>';
        }else if(status == "05") {
        	if(dispatchType!=''&&dispatchType=='1'){
        		$html= '<span class="btn btn-whiteB goexpresslist">查看物流</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn btn-gw confirmord">确认收货</span>';
        	}else{
        		$html= '<span class="btn btn-gw confirmord">确认收货</span>';
        	}           
        }else{
            $html= '';
        }

        return $html;
    };

    //拆入到页面
    var createOpera = function(){
        var created = $('.gopaylist,.confirmord').closest('.opera');
        $('.opera').not(created).each(function(){
            var $opera = $(this);
            var status = $('#shop_order_status',$opera).val();
            var index = operaIndex[status];
            var $html = createOperaHtml($opera);
            switch(index){
                case 1:
                    $($html).insertBefore($opera.children(":last"));
                    break;
                case 2:
                    $($html).insertBefore($opera.closest('.shopItem').children(":first"));
                    break;
                default:
                    return;
            }

        });
    };

    var loadCallBack = function(){
        createOpera();
       // resetAllFormData();
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
    $('#wrapper2').height($(window).height()-$('.payBar').height());

    var pageInit = {
        'status':$('#status').val(),
        'pageSize':3
    };


    //page 被LoadScroll变量的中page独立，传入后台pageNo叠加
    var loadScroll = new LoadScroll("wrapper2", {
        url: GLOBAL.WEBROOT+'/point/order/my/scrollData',
        isAjax:true,
        call:loadCallBack,
        params:{
            status:pageInit.status,
            count:pageInit.pageSize,
            pageSize:pageInit.pageSize
        }
    });

    loadScroll.refreshPage({
        params:{
            status:pageInit.status,
            count:pageInit.pageSize,
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
        $.AMUI.progress.start();//打开进度条
        var shop = $(this).closest('.shopItem');
        $('form',shop).submit();
    });

    $('body').delegate('.goexpresslist', 'click', function(e){
        //e.stopPropagation();
    	 e.stopPropagation();
         var _this = this;
         var orderId = $('form',$(_this).closest('.shopItem')).attr('id');
         location.href =  GLOBAL.WEBROOT+"/point/order/queryExpress/"+orderId;
    });    
    
    //买家收货确认
    $('body').delegate('.confirmord', 'click', function(e){
        e.stopPropagation();
        var _this = this;
        eDialog.confirm("收货确认" , function(){
            var orderId = $('form',$(_this).closest('.shopItem')).attr('id');
            var url = pre_url+"/recept/confirmord";
            $.eAjax({
                url:url,
                data:[{name:"orderId",value:orderId},{name:"oper",value:"03"}],
                method:'post',
                success:function(returnInfo){
                    if(returnInfo&&returnInfo.resultFlag=='true'){
                        window.location.reload();
                    }else{
                        eDialog.alert(returnInfo.resultMsg,function(){
                            window.location.reload();
                        });
                    }
                }
            });
        });
        stopDefault(e);
    });

    $('#goPay').on('click',function(){
        var total = $('.btn.btn-gw.gopaylist').length;
        if(total < 1){
            eDialog.alert("没有待支付的订单！");
            return;
        }
        $.AMUI.progress.start();//打开进度条
        $('#allPayedForm').submit();
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