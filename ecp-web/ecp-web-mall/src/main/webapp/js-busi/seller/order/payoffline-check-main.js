	/*
	 * 获取当前tab页面的id
	 */
	function getTabId(){
		var type = $("#myTab>li.active").attr("id");
		var _id= type;
		return _id;
	}
	
	function addLoadParamsToUrl(_load_url_){
	    var applyType  = $('select[name="applyType"]').val();
	    if(applyType != "")
	    {
	    	_load_url_ += '&applyType=' + applyType;	
	    }
	    var status  = $('select[name="status"]').val();
	    if(status != "")
	    {
	    	_load_url_ += '&status=' + status;	
	    }
	    var orderId  = $('input[name="orderId"]').val();
	    if(orderId != "")
	    {
	    	_load_url_ += '&orderId=' + orderId;	
	    }
	    var shopId  = $('select[name="shopId"]').val();
	    if(shopId != "")
	    {
	    	$('#searchForm_shopId').attr("value",shopId);
	    	_load_url_ += '&shopId=' + shopId;	
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
	});
	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		
    		/*选项卡事件注册*/
            $('#unchecklistTab').click(function(){
            	var _load_url_ = '/seller/order/pay/unchecklist?';
            	_load_url_ = addLoadParamsToUrl(_load_url_);
        	    
            	$(this).attr("class","active");
            	$('#checkedlistTab').attr("class","");
            	$('#tabContentDiv').load(GLOBAL.WEBROOT + _load_url_);
            });
	    	
	    	
            $('#checkedlistTab').click(function(){
            	var _load_url_ = '/seller/order/pay/checkedlist?';
            	_load_url_ = addLoadParamsToUrl(_load_url_);
        	    
            	$(this).attr("class","active");
            	$('#unchecklistTab').attr("class","");
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