	/*
	 * 获取当前tab页面的id
	 */
	function getTabId(){
		var type = $("#myTab>li.active").attr("id");
		var _id= type;
		return _id;
	}
	
	function addLoadParamsToUrl(_load_url_){
		var begDate = $('input[name="begDate"]').val();
	    if(begDate != "")
	    {
	    	_load_url_ += '&begDate=' + begDate;	
	    }
	    var endDate = $('input[name="endDate"]').val();
	    if(endDate != "")
	    {
	    	_load_url_ += '&endDate=' + endDate;	
	    }
	    var siteId  = $('select[name="siteId"]').val();
	    if(siteId != "")
	    {
	    	_load_url_ += '&siteId=' + siteId;	
	    }
	    var shopId  = $('select[name="shopId"]').val();
	    if(shopId != "")
	    {
	    	_load_url_ += '&shopId=' + shopId;	
	    }
	    var dispatchType  = $('select[name="dispatchType"]').val();
	    if(dispatchType != "")
	    {
	    	_load_url_ += '&dispatchType=' + dispatchType;	
	    }   
	    var orderId = $('input[name="orderId"]').val();
	    if(orderId != "")
	    {
	    	_load_url_ += '&orderId=' + orderId;
	    }
	    var date = new Date();
	    _load_url_ += '&t='+date.getTime();
	    return _load_url_;
	};
//页面初始化模块
$(function(){
	$('#searchfrombtn').click(function(){
		if(!$('#searchForm').valid())
			return;
		var cur_active_tab = getTabId();
		$('#'+cur_active_tab).click();
	});	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#begDate").val($("#resetBegDate").val());
		$("#endDate").val($("#resetEndDate").val());
	});
	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		
    		/*选项卡事件注册*/
            $('#delaysendtab').click(function(){
            	var _load_url_ = '/seller/order/delivery/delyedlist?';
            	_load_url_ = addLoadParamsToUrl(_load_url_);
        	    
            	$(this).attr("class","active");
            	$('#alreadysendtab').attr("class","");
            	$('#tabContentDiv').load(GLOBAL.WEBROOT + _load_url_);
            });
	    	
	    	//积分消费
            $('#alreadysendtab').click(function(){
            	var _load_url_ = '/seller/order/delivery/sendlist?';
            	_load_url_ = addLoadParamsToUrl(_load_url_);
        	    
            	$(this).attr("class","active");
            	$('#delaysendtab').attr("class","");
                $('#tabContentDiv').load(GLOBAL.WEBROOT + _load_url_);
            
            });
            
            
            
            //初始化完成后，加载数据
    		var cur_active_tab = getTabId();
    		$('#'+cur_active_tab).click();
    	};
    	return {
    		init : init
    	};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bForm','bPage'],
		//指定页面
		init : function(){
			var p = new pInit();
			p.init();
		}
	});
});