	/*
	 * 获取当前tab页面的id
	 */
	function getTabId(){
		var type = $("#membercoupinfo>li.am-active").attr("id");
		var _id= type;
		return _id;
	}
	function addLoadParamsToPayUrl(_load_url_){
		var date = new Date();
		_load_url_ += '&t='+date.getTime();
		/*
	    var orderId = $('input[name="orderId"]').val();
	    if(orderId != "")
	    {
	    	_load_url_ += '&orderId=' + orderId;
	    }
		 */
	    return _load_url_;
	};
//页面初始化模块
$(function(){

	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		/*选项卡事件注册*/
            $('#couppage_active').click(function(){
            	var yh_pay_url = GLOBAL.WEBROOT + '/membercoup/data?dataType=1';
            	yh_pay_url = addLoadParamsToPayUrl(yh_pay_url);
            	
            	$(this).attr("class","am-active");
            	$('#couppage_past').attr("class","");
            	$('#couppage_used').attr("class","");
            	$('#tabContentDiv').load(yh_pay_url);
            });
            $('#couppage_past').click(function(){
            	var yh_pay_url = GLOBAL.WEBROOT + '/membercoup/data?dataType=0';

            	yh_pay_url = addLoadParamsToPayUrl(yh_pay_url);
            	
            	$(this).attr("class","am-active");
            	$('#couppage_active').attr("class","");
            	$('#couppage_used').attr("class","");
            	$('#tabContentDiv').load(yh_pay_url);
            });
            $('#couppage_used').click(function(){
            	var yh_pay_url = GLOBAL.WEBROOT + '/membercoup/data?dataType=2';

            	yh_pay_url = addLoadParamsToPayUrl(yh_pay_url);
            	
            	$(this).attr("class","am-active");
            	$('#couppage_past').attr("class","");
            	$('#couppage_active').attr("class","");
            	$('#tabContentDiv').load(yh_pay_url);
            });
            //初始化完成后，加载数据
    		var cur_active_tab = getTabId();
    		$('#'+cur_active_tab).click();
    	};
    	return {
    		init : init
    	};
	};
	var p = new pInit();
	p.init();
	/*
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bForm','bPage'],
		//指定页面
		init : function(){
			var p = new pInit();
			p.init();
		}
	});*/
	function paySum(){
		var load_pay_sum_url = GLOBAL.WEBROOT + '/seller/order/bankpay/paySumData?'; 

		$.eAjax({
			url : addLoadParamsToPayUrl(load_pay_sum_url),
			async : true,
			type : "post",
			dataType : "json",
			success : function(datas) {
				
			}
		});
	}
});