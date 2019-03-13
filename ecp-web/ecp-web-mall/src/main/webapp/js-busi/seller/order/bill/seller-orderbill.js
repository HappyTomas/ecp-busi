
var bill_grid = { 
		getOrdDetail: function(orderid){
			var url = GLOBAL.WEBROOT+"/seller/order/orderdetails?orderId="+orderid;
			window.open(url);
		},
		reviewOrd:function(orderId){  
    		var isStatus = true;
        	var url = GLOBAL.WEBROOT + "/seller/order/bill/checkReturn";
        	$.eAjax({
    			url:url,
    			data:[{name:"orderId",value:orderId},{name:"oper",value:"00"}],
    			async:false,
    			method:'post',
    			success:function(returnInfo){
    				if(returnInfo&&returnInfo.flag==false){
    					isStatus = false; 
    					eDialog.alert(returnInfo.msg);
    				}
    				
    			}
    		}); 
        	if(isStatus == true){
        		bDialog.open({
    			    title : '开票状态',
    			    width : 400,
    			    height : 270,
    			    scroll : true,
    			    params : {
    			    	orderId:orderId
    			    },
    			    url : GLOBAL.WEBROOT+'/seller/order/bill/review?orderId='+orderId,
    			    callback:function(data){ 
    			    	if(data && data.results && data.results.length > 0 ){
    			    		$('#btnFormSearch').click();
    			    	}
    			    }
    			});  
    		} 
			
		},
		getPrintDetail: function(orderId){
			window.open(GLOBAL.WEBROOT+'/seller/order/bill/printDetail?orderId='+orderId);
		} 
		
};
function addLoadParamsToUrl(_load_url_){
	var date = new Date();
	_load_url_ += 't='+date.getTime();
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
    var billingFlag  = $('select[name="billingFlag"]').val();
    if(billingFlag != "")
    {
    	_load_url_ += '&billingFlag=' + billingFlag;	
    }  
    var orderId = $('input[name="orderId"]').val();
    if(orderId != "")
    {
    	_load_url_ += '&orderId=' + orderId;
    }
    var shopId  = $('select[name="shopId"]').val();
    if(shopId != "")
    {
    	_load_url_ += '&shopId=' + shopId;	
    }

    return _load_url_;
};
$(function(){
	 
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
    	var _load_url_ = '/seller/order/bill/billlist?';
    	_load_url_ = addLoadParamsToUrl(_load_url_);
    	$('#tabContentDiv').load(GLOBAL.WEBROOT + _load_url_);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#begDate").val($("#resetBegDate").val());
		$("#endDate").val($("#resetEndDate").val());
	});  
	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){

            //初始化动作
    		
            //初始化完成后，加载数据
    		$('#btnFormSearch').click();
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